package pe.com.maprsoft.facturador.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.maprsoft.facturador.model.Session;
import pe.com.maprsoft.facturador.model.SubCategoria;
import pe.com.maprsoft.facturador.model.core.BooxException;
import pe.com.maprsoft.facturador.service.CocinaService;
import pe.com.maprsoft.facturador.service.core.Util;
import pe.com.maprsoft.facturador.dao.repository.CocinaRepository;
import pe.com.maprsoft.facturador.dao.repository.PedidoRepository;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.model.Categoria;
import pe.com.maprsoft.facturador.model.DetallePedido;
import pe.com.maprsoft.facturador.model.Mesa;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Pedido;
import pe.com.maprsoft.facturador.model.PedidoCocina;

@Service
public class CocinaServiceImpl implements CocinaService {

	@Autowired
	private CocinaRepository cocinaRepository;
		
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public List<Pedido> listPedidosCocina(String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");
		
		List<PedidoCocina> listPedidoCocina = cocinaRepository.listCocina(session.getCodigoRestauranteLocal());
		
		List<Pedido> listPedido = new ArrayList<Pedido>();
		Pedido pedido = new Pedido();
		int i = 0;
		Integer idPedido = 0;

		for(PedidoCocina bean:  listPedidoCocina) {
			if(i==0) {
				idPedido = bean.getCodigoPedido();
				pedido = new Pedido();
				pedido.setCodigo(bean.getCodigoPedido());
				pedido.setTipo(bean.getTipoPedido());
				pedido.setPersonas(bean.getPersonas());
				pedido.setEstado(bean.getEstadoPedido().toString());
				pedido.setFechaPedido(bean.getFechaPedido());
				
				pedido.setMozo(new Mozo(bean.getCodigoMozo()));
				pedido.getMozo().setNombre(bean.getNombreMozo());
				pedido.setMesa(new Mesa(bean.getCodigoMesa()));
				pedido.getMesa().setNombre(bean.getNombreMesa());
				pedido.getMesa().setNombreBreve(bean.getNombreBreveMesa());
				pedido.getMesa().setCapacidad(bean.getCapacidadMesa());
				pedido.getMesa().setEstado(bean.getEstadoMesa());
								
				listPedido.add(pedido);
			}			
			if(idPedido.intValue()!=bean.getCodigoPedido().intValue()) {
				idPedido = bean.getCodigoPedido();
				pedido = new Pedido();
				pedido.setCodigo(bean.getCodigoPedido());
				pedido.setTipo(bean.getTipoPedido());
				pedido.setPersonas(bean.getPersonas());
				pedido.setEstado(bean.getEstadoPedido().toString());
				pedido.setFechaPedido(bean.getFechaPedido());
				
				pedido.setMozo(new Mozo(bean.getCodigoMozo()));
				pedido.getMozo().setNombre(bean.getNombreMozo());
				pedido.setMesa(new Mesa(bean.getCodigoMesa()));
				pedido.getMesa().setNombre(bean.getNombreMesa());
				pedido.getMesa().setNombreBreve(bean.getNombreBreveMesa());
				pedido.getMesa().setCapacidad(bean.getCapacidadMesa());
				pedido.getMesa().setEstado(bean.getEstadoMesa());
								
				listPedido.add(pedido);
			}						
			i++;						
		}
		
		
		List<Pedido> listPedido2 = new ArrayList<Pedido>(); 
		List<DetallePedido> listDetallePedido = new ArrayList<DetallePedido>(); 
		DetallePedido detallePedido = new DetallePedido();
		idPedido = 0;
		
	    String pendiente = "#C9C9C9";
	    pendiente="";
	    String preparacion = "#8AFF8A";
	    String despacho = "#FFD34F";
	    String atendido = "#598FFF";
	    int total = 0;
	    int despachados = 0;
	    int enCocina = 0;
		for(Pedido bean1: listPedido) {
			listDetallePedido = new ArrayList<DetallePedido>(); 	
			idPedido = bean1.getCodigo();								
			total = 0;
		    despachados = 0;
		    //LuegaElaboracion  1: Cocina 2: Bar  3: Mostrador
		for(PedidoCocina bean2:  listPedidoCocina) {
			
			if(idPedido.intValue()==bean2.getCodigoPedido().intValue() 
					&& bean2.getLugarElaboracion().equals("1") ) {
				total++;
				
				if(bean2.getEstadoPedidoDetalle().intValue()==3) {
					despachados++;	
				}				
				detallePedido = new DetallePedido();				
				detallePedido.setCodigo(bean2.getCodigoPedidoDetalle());
				detallePedido.setCodigoProducto(bean2.getCodigoProducto());
				detallePedido.setComentario(bean2.getComentarioPedidoDetalle());				
				detallePedido.setEstado(bean2.getEstadoPedidoDetalle());
				detallePedido.setFechaRegistro(bean2.getFechaPedidoDetalle());
				detallePedido.setNombre(bean2.getNombreProducto());
				detallePedido.setMoneda(bean2.getMonedaProducto());
				detallePedido.setPrecio(bean2.getPrecioProducto());
				
				detallePedido.setLugarElaboracion(bean2.getLugarElaboracion());
				detallePedido.setTipo(bean2.getTipo());
				
				if(bean2.getEstadoPedidoDetalle().intValue()==1) {
					detallePedido.setColor(pendiente);
				} else if(bean2.getEstadoPedidoDetalle().intValue()==2) {
					detallePedido.setColor(preparacion);
				} else if(bean2.getEstadoPedidoDetalle().intValue()==3) {
					detallePedido.setColor(despacho);
				} else if(bean2.getEstadoPedidoDetalle().intValue()==4) {
					detallePedido.setColor(atendido);
				}  
				
				listDetallePedido.add(detallePedido);								
			}				
		}

		
			if(total==despachados) {
				
				if(!bean1.getTipo().equals("2")) {
				bean1.setEstado("2");
				pedidoRepository.updateStatus(bean1);
				}
				
				bean1.setAtendidoCocina(1);
				pedidoRepository.updateAtendidoCocina(bean1);
			} else {
				long valor = Util.diferenciaMinutosFechas(bean1.getFechaPedido(), Util.PATTERN_DD_MM_YY_HH_MI_SS);
				String minutos = String.valueOf(valor);
				bean1.setMinutos(minutos);

				bean1.setPedidos(listDetallePedido);
				bean1.setReserva(null);
				listPedido2.add(bean1);			
			}
		}
		
		
		return listPedido2;
	}

	@Override
	public List<Pedido> listPedidosBarra(String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");
		
		List<PedidoCocina> listPedidoCocina = cocinaRepository.listBarra(session.getCodigoRestauranteLocal());
		
		List<Pedido> listPedido = new ArrayList<Pedido>();
		Pedido pedido = new Pedido();
		int i = 0;
		Integer idPedido = 0;

		for(PedidoCocina bean:  listPedidoCocina) {
			if(i==0) {
				idPedido = bean.getCodigoPedido();
				pedido = new Pedido();
				pedido.setCodigo(bean.getCodigoPedido());
				pedido.setTipo(bean.getTipoPedido());
				pedido.setPersonas(bean.getPersonas());
				pedido.setEstado(bean.getEstadoPedido().toString());
				pedido.setFechaPedido(bean.getFechaPedido());
				
				pedido.setMozo(new Mozo(bean.getCodigoMozo()));
				pedido.getMozo().setNombre(bean.getNombreMozo());
				pedido.setMesa(new Mesa(bean.getCodigoMesa()));
				pedido.getMesa().setNombre(bean.getNombreMesa());
				pedido.getMesa().setNombreBreve(bean.getNombreBreveMesa());
				pedido.getMesa().setCapacidad(bean.getCapacidadMesa());
				pedido.getMesa().setEstado(bean.getEstadoMesa());
								
				listPedido.add(pedido);
			}			
			if(idPedido.intValue()!=bean.getCodigoPedido().intValue()) {
				idPedido = bean.getCodigoPedido();
				pedido = new Pedido();
				pedido.setCodigo(bean.getCodigoPedido());
				pedido.setTipo(bean.getTipoPedido());
				pedido.setPersonas(bean.getPersonas());
				pedido.setEstado(bean.getEstadoPedido().toString());
				pedido.setFechaPedido(bean.getFechaPedido());
				
				pedido.setMozo(new Mozo(bean.getCodigoMozo()));
				pedido.getMozo().setNombre(bean.getNombreMozo());
				pedido.setMesa(new Mesa(bean.getCodigoMesa()));
				pedido.getMesa().setNombre(bean.getNombreMesa());
				pedido.getMesa().setNombreBreve(bean.getNombreBreveMesa());
				pedido.getMesa().setCapacidad(bean.getCapacidadMesa());
				pedido.getMesa().setEstado(bean.getEstadoMesa());
								
				listPedido.add(pedido);
			}						
			i++;						
		}
		
		
		List<Pedido> listPedido2 = new ArrayList<Pedido>(); 
		List<DetallePedido> listDetallePedido = new ArrayList<DetallePedido>(); 
		DetallePedido detallePedido = new DetallePedido();
		idPedido = 0;
		
	    String pendiente = "#C9C9C9";
	    pendiente="";
	    String preparacion = "#8AFF8A";
	    String despacho = "#FFD34F";
	    String atendido = "#598FFF";
	    int total = 0;
	    int despachados = 0;
	    int enCocina = 0;
		for(Pedido bean1: listPedido) {
			listDetallePedido = new ArrayList<DetallePedido>(); 	
			idPedido = bean1.getCodigo();								
			total = 0;
		    despachados = 0;
		    //LuegaElaboracion  1: Cocina 2: Bar  3: Mostrador
		for(PedidoCocina bean2:  listPedidoCocina) {
			
			if(idPedido.intValue()==bean2.getCodigoPedido().intValue() 
					&& (bean2.getLugarElaboracion().equals("2") || bean2.getLugarElaboracion().equals("3") ) ) {
				total++;
				
				if(bean2.getEstadoPedidoDetalle().intValue()==3) {
					despachados++;	
				}				
				detallePedido = new DetallePedido();				
				detallePedido.setCodigo(bean2.getCodigoPedidoDetalle());
				detallePedido.setCodigoProducto(bean2.getCodigoProducto());
				detallePedido.setComentario(bean2.getComentarioPedidoDetalle());				
				detallePedido.setEstado(bean2.getEstadoPedidoDetalle());
				detallePedido.setFechaRegistro(bean2.getFechaPedidoDetalle());
				detallePedido.setNombre(bean2.getNombreProducto());
				detallePedido.setMoneda(bean2.getMonedaProducto());
				detallePedido.setPrecio(bean2.getPrecioProducto());
				
				detallePedido.setLugarElaboracion(bean2.getLugarElaboracion());
				detallePedido.setTipo(bean2.getTipo());
				
				if(bean2.getEstadoPedidoDetalle().intValue()==1) {
					detallePedido.setColor(pendiente);
				} else if(bean2.getEstadoPedidoDetalle().intValue()==2) {
					detallePedido.setColor(preparacion);
				} else if(bean2.getEstadoPedidoDetalle().intValue()==3) {
					detallePedido.setColor(despacho);
				} else if(bean2.getEstadoPedidoDetalle().intValue()==4) {
					detallePedido.setColor(atendido);
				}  
				
				listDetallePedido.add(detallePedido);								
			}				
		}

		
			if(total==despachados) {

				if(!bean1.getTipo().equals("2")) {
					bean1.setEstado("2");
					pedidoRepository.updateStatus(bean1);
				}	
				bean1.setAtendidoCocina(1);
				pedidoRepository.updateAtendidoCocina(bean1);
			} else {
				long valor = Util.diferenciaMinutosFechas(bean1.getFechaPedido(), Util.PATTERN_DD_MM_YY_HH_MI_SS);
				String minutos = String.valueOf(valor);
				bean1.setMinutos(minutos);

				bean1.setPedidos(listDetallePedido);
				bean1.setReserva(null);
				listPedido2.add(bean1);			
			}
		}
		
		
		return listPedido2;
	}

}
