package pe.com.maprsoft.facturador.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.maprsoft.facturador.dao.repository.ClienteRepository;
import pe.com.maprsoft.facturador.dao.repository.DetallePedidoRepository;
import pe.com.maprsoft.facturador.dao.repository.MesaRepository;
import pe.com.maprsoft.facturador.dao.repository.PagoRepository;
import pe.com.maprsoft.facturador.dao.repository.PedidoRepository;
import pe.com.maprsoft.facturador.dao.repository.ReservaRepository;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.model.Cliente;
import pe.com.maprsoft.facturador.model.DetallePedido;
import pe.com.maprsoft.facturador.model.Pago;
import pe.com.maprsoft.facturador.model.Pedido;
import pe.com.maprsoft.facturador.model.Session;
import pe.com.maprsoft.facturador.model.core.BooxException;
import pe.com.maprsoft.facturador.model.core.EstadoMesaCodeEnum;
import pe.com.maprsoft.facturador.model.core.EstadoPedidoCodeEnum;
import pe.com.maprsoft.facturador.model.core.MonedaCodeEnum;
import pe.com.maprsoft.facturador.service.PedidoService;
import pe.com.maprsoft.facturador.service.core.Util;


@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private DetallePedidoRepository detallePedidoRepository;

	@Autowired
	private MesaRepository mesaRepository;

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PagoRepository pagoRepository;
	
	@Override
	public Map<String, Object> insert(Pedido pedido, String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		if(pedido.getTipo().equals("2")) {
			//Delivery Guardar datos de cliente
			if(pedido.getCliente().getCodigo().intValue()<=0) {
			  Integer valor =  clienteRepository.insert(pedido.getCliente());
			  pedido.setCliente(new Cliente(valor));
			}
		}
		
		Map<String, Object> mapa = new HashMap<>();
		pedido.setEstado("1");
		pedido.setCodigoSucursal(session.getCodigoRestauranteLocal());
		Integer idPedido = pedidoRepository.insert(pedido);
		if(idPedido.intValue()>0) {
			if(pedido.getPedidos() != null || pedido.getPedidos().size()>0) {
			for(DetallePedido detallePedido: pedido.getPedidos()) {
				detallePedido.setEstado(new Integer("1"));
				detallePedidoRepository.insert(detallePedido, idPedido);
			}
			}
		}
		
		
		if(pedido.getTipo().equals("3")) {
		if(pedido.getReserva().getCodigo().intValue()>0) {
			pedido.getReserva().setEstado(1);
			reservaRepository.updateStatus(pedido.getReserva());
		}
		}
		
		if(pedido.getTipo().equals("1") || pedido.getTipo().equals("3")) {
		pedido.getMesa().setEstado(EstadoMesaCodeEnum.MESA_OCUPADA.getCode());
		mesaRepository.updateStatus(pedido.getMesa());
		}
		
		mapa.put("idPedido", idPedido);
		return mapa;
	}

	@Override
	public void update(Pedido pedido) throws Exception {
	
		pedidoRepository.update(pedido);

		int indicadorCocina = 0;
		int indicadorBarra = 0;
		
		if(pedido.getAtendidoBarra()==null)
			pedido.setAtendidoBarra(0);
		if(pedido.getAtendidoCocina()==null)
			pedido.setAtendidoCocina(0);
		
		if(pedido.getCodigo().intValue()>0) {
			if(pedido.getPedidos() != null || pedido.getPedidos().size()>0) {
			for(DetallePedido detallePedido: pedido.getPedidos()) {
				
				if(detallePedido.getEstado().intValue()==0) {
					detallePedido.setEstado(new Integer("1"));
					detallePedidoRepository.insert(detallePedido, pedido.getCodigo());
					
					if(detallePedido.getLugarElaboracion().equals("1"))
						indicadorCocina++;
					if(detallePedido.getLugarElaboracion().equals("2") || detallePedido.getLugarElaboracion().equals("3"))
						indicadorBarra++;
					
				}
				
				}
			}
		}
		
		if(indicadorCocina>0) {
			pedido.setAtendidoCocina(0);
			pedidoRepository.updateAtendidoCocina(pedido);
		}
		if(indicadorBarra>0) {
			pedido.setAtendidoCocina(0);
			pedidoRepository.updateAtendidoBarra(pedido);
		}
		
		
	}

	@Override
	public void delete(Pedido pedido) throws Exception {
		detallePedidoRepository.delete(pedido.getCodigo());		
		pedidoRepository.delete(pedido);
	}


	@Override
	public Pedido get(Pedido pedido, String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		pedido.setEstado("1"); //Pendiente
		pedido.setCodigoSucursal(session.getCodigoRestauranteLocal());
		Pedido bean = pedidoRepository.get(pedido);

		if(bean != null) {
			List<DetallePedido> lista = detallePedidoRepository.list(bean);
			if(lista!=null && lista.size()>0) {    
				bean.setPedidos(lista);
			}
		}
		return bean;
	}

	@Override
	public List<DetallePedido> getDetalle(Pedido pedido) throws Exception {

		List<DetallePedido> lista = detallePedidoRepository.list(pedido);
		return lista;
	}

	@Override
	public void deleteItemPedido(DetallePedido detallePedido, String token) throws Exception {
	
		detallePedidoRepository.deleteItem(detallePedido);
	}


	@Override
	public void insertItem(DetallePedido detallePedido, String token) throws Exception {
		detallePedido.setEstado(new Integer("1"));
		detallePedidoRepository.insert(detallePedido, detallePedido.getCodigoPedido());
		
		Pedido pedido = new Pedido();
		pedido.setCodigo(detallePedido.getCodigoPedido());
		pedido.setEstado("1");
		pedidoRepository.updateStatus(pedido);
	}

	@Override
	public void updateStatusItem(DetallePedido detallePedido, String token) throws Exception {		
		detallePedidoRepository.updateStatus(detallePedido);
	}

	@Override
	public void updateStatus(Pedido pedido, String token) throws Exception {				
		pedidoRepository.updateStatus(pedido);		
	}

	@Override
	public void cancelar(Pedido pedido, String token) throws Exception {
		pedido.setEstado("6");
		pedidoRepository.cancelar(pedido);
		//Liberamos mesa
		pedido.getMesa().setEstado(EstadoMesaCodeEnum.MESA_LIBRE.getCode());
		mesaRepository.updateStatus(pedido.getMesa());
	}

	@Override
	public void validateDelivery(Pedido pedido, String token) throws Exception {		
		
		pedidoRepository.updateStatus(pedido);
		
		Pago pago = new Pago();
		pago.setEstado("1"); //Activo confirmado
		pago.setPedido(pedido);
		pagoRepository.confirmPay(pago);		
	}
	
	@Override
	public Pedido getPrePago(Pedido pedido, String token) throws Exception {
		pedido.setEstado("1"); //Pendiente
		Pedido bean = pedidoRepository.getById(pedido);
		bean.setMoneda("1");
					
		if(MonedaCodeEnum.MONEDA_SOLES_PERU.getCode().toString().equals(bean.getMoneda())) {
			bean.setMoneda(MonedaCodeEnum.MONEDA_SOLES_PERU.getMessage());	
		} else if(MonedaCodeEnum.MONEDA_DOLAR_AMERICANO.getCode().toString().equals(bean.getMoneda())) {
			bean.setMoneda(MonedaCodeEnum.MONEDA_DOLAR_AMERICANO.getMessage());	
		} else if(MonedaCodeEnum.MONEDA_EURO_EUROPA.getCode().toString().equals(bean.getMoneda())) {
			bean.setMoneda(MonedaCodeEnum.MONEDA_EURO_EUROPA.getMessage());	
		}		
		
		double igv = 1.18;
		
		if(bean != null) {
			List<DetallePedido> lista = detallePedidoRepository.listPago(bean);
			if(lista!=null && lista.size()>0) {    

				List<DetallePedido> listTemp = new ArrayList<DetallePedido>();
				BigDecimal total = new BigDecimal("0.00");
				double subTotalPedido = 0;
				double igvPedido = 0;
				
				try {					
					for(DetallePedido detalle: lista) {
						BigDecimal precio = new BigDecimal(detalle.getPrecio());
						BigDecimal cantidad = new BigDecimal(detalle.getCantidad());
						precio = precio.multiply(cantidad);						
						total = total.add(precio);	
												
						double detalleTotal = Double.parseDouble(String.valueOf(precio));
						//detalleTotal =  Util.formatearDecimales(detalleTotal,2);
						double detalleBruto = detalleTotal / igv;
						//detalleBruto =  Util.formatearDecimales(detalleBruto,2);
						double detalleIgv = detalleTotal - detalleBruto;
						//detalleIgv =  Util.formatearDecimales(detalleIgv,2);
						
						//Suma antes de renodear
						//subTotalPedido = subTotalPedido + detalleBruto;
						//igvPedido = igvPedido + detalleIgv;

						detalleIgv =  Util.formatearDecimales(detalleIgv,2);
						detalleBruto =  Util.formatearDecimales(detalleBruto,2);

						//Suma despues de renodear
						subTotalPedido = subTotalPedido + detalleBruto;
						igvPedido = igvPedido + detalleIgv;

						detalle.setIgv(String.valueOf(detalleIgv));						
						detalle.setSubTotalBruto(String.valueOf(detalleBruto));
						detalle.setDescuento("0");
						detalle.setSubTotal(String.valueOf(precio));
						listTemp.add(detalle);
					}
					
					double mtoTotal = Double.parseDouble(String.valueOf(total));
					mtoTotal =  Util.formatearDecimales(mtoTotal,2);
					double bruto = mtoTotal / igv;
					bruto =  Util.formatearDecimales(bruto,2);
					double mtoIgv = mtoTotal - bruto;
					mtoIgv =  Util.formatearDecimales(mtoIgv,2);
					
					
					subTotalPedido =  Util.formatearDecimales(subTotalPedido,2);
					igvPedido =  Util.formatearDecimales(igvPedido,2);
					bean.setIgv(String.valueOf(igvPedido));
					bean.setSubTotal(String.valueOf(subTotalPedido));
										
					//bean.setIgv(String.valueOf(mtoIgv));
					//bean.setSubTotal(String.valueOf(bruto));
					total = total.setScale(2);
					bean.setTotal(String.valueOf(total));
				} catch(Exception e) {
					bean.setTotal("0.00");
				}
				
				bean.setPedidos(listTemp);
			}
		
			if(pedido.getMesa().getEstado().toString().equals(EstadoMesaCodeEnum.MESA_OCUPADA.getCode().toString())) {
				pedido.getMesa().setEstado(EstadoMesaCodeEnum.MESA_POR_SALIR.getCode());
				mesaRepository.updateStatus(pedido.getMesa());
			}
			bean.setMesa(pedido.getMesa());

		}
		return bean;
	}

	@Override
	public Pedido getPrePagoMultiple(Pedido pedido, String token) throws Exception {
		pedido.setEstado("1"); //Pendiente
		Pedido bean = pedidoRepository.getById(pedido);
		bean.setMoneda("1");
					
		if(MonedaCodeEnum.MONEDA_SOLES_PERU.getCode().toString().equals(bean.getMoneda())) {
			bean.setMoneda(MonedaCodeEnum.MONEDA_SOLES_PERU.getMessage());	
		} else if(MonedaCodeEnum.MONEDA_DOLAR_AMERICANO.getCode().toString().equals(bean.getMoneda())) {
			bean.setMoneda(MonedaCodeEnum.MONEDA_DOLAR_AMERICANO.getMessage());	
		} else if(MonedaCodeEnum.MONEDA_EURO_EUROPA.getCode().toString().equals(bean.getMoneda())) {
			bean.setMoneda(MonedaCodeEnum.MONEDA_EURO_EUROPA.getMessage());	
		}		
		
		double igv = 1.18;
		
		if(bean != null) {
			List<DetallePedido> lista = detallePedidoRepository.listPagoMultpile(bean);
			if(lista!=null && lista.size()>0) {    

				List<DetallePedido> listTemp = new ArrayList<DetallePedido>();
				BigDecimal total = new BigDecimal("0.00");
				double subTotalPedido = 0;
				double igvPedido = 0;
				int contador = 0;
				try {					
					for(DetallePedido detalle: lista) {
						contador++;
						BigDecimal precio = new BigDecimal(detalle.getPrecio());
						BigDecimal cantidad = new BigDecimal(detalle.getCantidad());
						precio = precio.multiply(cantidad);						
						total = total.add(precio);	
							
						detalle.setCodigo(contador);
						double detalleTotal = Double.parseDouble(String.valueOf(precio));
						//detalleTotal =  Util.formatearDecimales(detalleTotal,2);
						double detalleBruto = detalleTotal / igv;
						//detalleBruto =  Util.formatearDecimales(detalleBruto,2);
						double detalleIgv = detalleTotal - detalleBruto;
						//detalleIgv =  Util.formatearDecimales(detalleIgv,2);
						
						//Suma antes de renodear
						//subTotalPedido = subTotalPedido + detalleBruto;
						//igvPedido = igvPedido + detalleIgv;

						detalleIgv =  Util.formatearDecimales(detalleIgv,2);
						detalleBruto =  Util.formatearDecimales(detalleBruto,2);

						//Suma despues de renodear
						subTotalPedido = subTotalPedido + detalleBruto;
						igvPedido = igvPedido + detalleIgv;

						detalle.setIgv(String.valueOf(detalleIgv));						
						detalle.setSubTotalBruto(String.valueOf(detalleBruto));
						
						detalle.setSubTotal(String.valueOf(precio));
						listTemp.add(detalle);
					}
					
					double mtoTotal = Double.parseDouble(String.valueOf(total));
					mtoTotal =  Util.formatearDecimales(mtoTotal,2);
					double bruto = mtoTotal / igv;
					bruto =  Util.formatearDecimales(bruto,2);
					double mtoIgv = mtoTotal - bruto;
					mtoIgv =  Util.formatearDecimales(mtoIgv,2);
					
					
					subTotalPedido =  Util.formatearDecimales(subTotalPedido,2);
					igvPedido =  Util.formatearDecimales(igvPedido,2);
					bean.setIgv(String.valueOf(igvPedido));
					bean.setSubTotal(String.valueOf(subTotalPedido));
										
					//bean.setIgv(String.valueOf(mtoIgv));
					//bean.setSubTotal(String.valueOf(bruto));
					total = total.setScale(2);
					bean.setTotal(String.valueOf(total));
				} catch(Exception e) {
					bean.setTotal("0.00");
				}
				
				bean.setPedidos(listTemp);
			}
		
			
			if(pedido.getMesa().getEstado().toString().equals(EstadoMesaCodeEnum.MESA_OCUPADA.getCode().toString())) {
				pedido.getMesa().setEstado(EstadoMesaCodeEnum.MESA_POR_SALIR.getCode());
				mesaRepository.updateStatus(pedido.getMesa());
			}

		}
		return bean;
	}

	@Override
	public List<Pedido> listDelivery(String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		Pedido pedido = new Pedido();
		pedido.setCodigoSucursal(session.getCodigoRestauranteLocal());
		return pedidoRepository.listDelivery(pedido);
	}

	@Override
	public List<DetallePedido> listDetallePedido(Pedido pedido) throws Exception {
		List<DetallePedido> lista = detallePedidoRepository.list(pedido);
		return lista;
	}

	@Override
	public void updateAtendidoCocina(Pedido pedido) throws Exception {
		
		pedidoRepository.updateAtendidoCocina(pedido);		
	}

	@Override
	public void updateAtendidoBarra(Pedido pedido) throws Exception {

		pedidoRepository.updateAtendidoBarra(pedido);
	}

	@Override
	public void updateDelivery(Pedido pedido) throws Exception {
		clienteRepository.update(pedido.getCliente());		
	}
			
}