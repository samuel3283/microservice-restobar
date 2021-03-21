package pe.com.maprsoft.facturador.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.maprsoft.facturador.dao.repository.DetallePedidoRepository;
import pe.com.maprsoft.facturador.dao.repository.InsumoRepository;
import pe.com.maprsoft.facturador.dao.repository.MesaRepository;
import pe.com.maprsoft.facturador.dao.repository.NumeracionRepository;
import pe.com.maprsoft.facturador.dao.repository.PagoDetalleRepository;
import pe.com.maprsoft.facturador.dao.repository.PagoRepository;
import pe.com.maprsoft.facturador.dao.repository.PedidoRepository;
import pe.com.maprsoft.facturador.dao.repository.PreparacionRepository;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.dao.repository.SucursalRepository;
import pe.com.maprsoft.facturador.dao.repository.impl.SucursalRepositoryImpl;
import pe.com.maprsoft.facturador.model.Boleta;
import pe.com.maprsoft.facturador.model.DetallePedido;
import pe.com.maprsoft.facturador.model.Filter;
import pe.com.maprsoft.facturador.model.Numeracion;
import pe.com.maprsoft.facturador.model.Pago;
import pe.com.maprsoft.facturador.model.PagoDetalle;
import pe.com.maprsoft.facturador.model.Pedido;
import pe.com.maprsoft.facturador.model.Preparacion;
import pe.com.maprsoft.facturador.model.Producto;
import pe.com.maprsoft.facturador.model.Session;
import pe.com.maprsoft.facturador.model.Sucursal;
import pe.com.maprsoft.facturador.model.core.BooxException;
import pe.com.maprsoft.facturador.model.core.EstadoMesaCodeEnum;
import pe.com.maprsoft.facturador.model.core.EstadoPedidoCodeEnum;
import pe.com.maprsoft.facturador.model.core.MonedaCodeEnum;
import pe.com.maprsoft.facturador.service.PagoService;
import pe.com.maprsoft.facturador.service.PedidoService;
import pe.com.maprsoft.facturador.service.core.Util;
import pe.com.maprsoft.facturador.service.core.Utiles;


@Service
public class PagoServiceImpl implements PagoService {

	@Autowired
	private PagoRepository pagoRepository;
	
	@Autowired
	private PagoDetalleRepository pagoDetalleRepository;

	@Autowired
	private MesaRepository mesaRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private NumeracionRepository numeracionRepository;

	@Autowired
	private DetallePedidoRepository detallePedidoRepository;
	
	@Autowired
	private PreparacionRepository preparacionRepository;
	
	@Autowired
	private InsumoRepository insumoRepository;
	
	@Autowired
	private SucursalRepository sucursalRepository;
	
	@Override
	public String generaComprobante(Pago pago, String token) throws Exception {
	
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		Sucursal sucursal = sucursalRepository.getAll(session.getCodigoRestauranteLocal());
        
		Pago bean = pagoRepository.get(pago);
		List<PagoDetalle> pedidos = pagoDetalleRepository.list(bean);
		bean.setPedidos(pedidos);		
		bean.setMoneda("S/");
		
		Boleta boleta = new Boleta();

		boleta.setRazonSocial(sucursal.getRestaurante().getRazon_social());
		boleta.setRuc(sucursal.getRestaurante().getRuc());
        boleta.setDireccion(sucursal.getDireccion());
        boleta.setCliente("-");
        boleta.setUrlDescarga("www.maprsoft.com");
        if(sucursal.getRestaurante().getFranquicia().getCodigo().intValue()<=0) {
        	boleta.setRazonSocialFranquicia("");
            boleta.setDireccionFranquicia("");
        	boleta.setFranquicia(Boolean.FALSE);
        } else {
            boleta.setRazonSocialFranquicia(sucursal.getRestaurante().getFranquicia().getRazonSocial());
            boleta.setDireccionFranquicia(sucursal.getRestaurante().getFranquicia().getDireccion());
        	boleta.setFranquicia(Boolean.TRUE);        	
        }

      
       
        String pdf64 = null;

        if(bean.getTipoDocumento().equals("F")) {
            FacturaPDF d3 = new FacturaPDF();
            //d3.generaBoletaPDF(boleta, bean);
            pdf64 = Utiles.getImageEncode64(d3.generaBoletaPDF(boleta,bean));
        } else if(bean.getTipoDocumento().equals("B")) {
        	BoletaPDF d2 = new BoletaPDF();
            pdf64 = Utiles.getImageEncode64(d2.generaBoletaPDF(boleta,bean));
        } else  {
        	TicketPDF d1 = new TicketPDF();
            pdf64 = Utiles.getImageEncode64(d1.generaBoletaPDF(boleta,bean));
        } 
			
					
		//pdf.generaBoletaPDF(boleta, bean);
				
		return pdf64;
	}

	@Override
	public String generaBoleta(Pago pago, String token) throws Exception {
	
		Pago bean = pagoRepository.get(pago);
		
		List<PagoDetalle> pedidos = pagoDetalleRepository.list(bean);
		bean.setPedidos(pedidos);
		
		Boleta boleta = new Boleta();
		boleta.setRazonSocial("Restaurante Gefac");
		boleta.setRuc("20100454523");
        boleta.setDireccion("Av. Jr de la Unión # 159 - Lima");
        boleta.setCliente("-");
        boleta.setUrlDescarga("www.maprsoft.com");
        boleta.setRazonSocialFranquicia("Restaurante GeFac Integrales");
        boleta.setDireccionFranquicia("Av. Los Precursores # 1245 - Surco");
        boleta.setFranquicia(Boolean.FALSE);
        
		BoletaPDF pdf = new BoletaPDF();
		//pdf.generaBoletaPDF(boleta, bean);

		String pdf64 = Utiles.getImageEncode64(pdf.generaBoletaPDF(boleta,bean));		
		return pdf64;
	}

	
	@Override
	public Map<String, Object> insert(Pago pago, String token) throws Exception {
		Map<String, Object> mapa = new HashMap<>();
		
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");
		
		Numeracion numeracion = new Numeracion();
		numeracion.setSucursal(new Sucursal(session.getCodigoRestauranteLocal()));
		numeracion.setEstado(1);
		numeracion.setDocumento(pago.getTipoDocumento());
		numeracionRepository.updateCorrelatibo(numeracion);
		Numeracion mySerie =numeracionRepository.get(numeracion);
		
		pago.setSerie(mySerie.getSerie());		
		String numeroDOcumento = String.valueOf(mySerie.getCorrelativo());
		numeroDOcumento = Util.completa(numeroDOcumento, 6);
		pago.setNumero(numeroDOcumento);
		pago.setUsuario(session.getUsuario());
		pago.setCodigoSucursal(session.getCodigoRestauranteLocal());
		//pago.setEstado("1");
		if(pago.getPagoDolares()==null)
			pago.setPagoDolares("0");
		
		Pago beanPago = pagoRepository.insert(pago);
		
		if(beanPago.getCodigo().intValue()>0) {
			for(PagoDetalle pagoDetalle: pago.getPedidos()) {
				pagoDetalle.setCodigoPago(beanPago.getCodigo());
				pagoDetalle.setEstado(new Integer("1"));
				pagoDetalleRepository.insert(pagoDetalle);
			}
			
		}
		
		if(pago.getEstado().equals("0")) { //
			//Pedido Delivery pendiente de validar Pago
			pago.getPedido().setEstado("4"); 				
		} else {
			//Pedido Cerrado
			pago.getPedido().setEstado("5");
		}
		pedidoRepository.updateStatus(pago.getPedido());
		
		pago.getMesa().setEstado(EstadoMesaCodeEnum.MESA_LIBRE.getCode());
		mesaRepository.updateStatus(pago.getMesa());
		
		
		//Ini Descuenta ingredientes
		descontarInsumo(pago.getPedido());
		//Fin Descuenta ingredientes
		
		
		mapa.put("idPago", beanPago.getCodigo());
		mapa.put("numero", Util.completa(beanPago.getNumero(), 6));
		mapa.put("serie", beanPago.getSerie());
		return mapa;
	}
			
	
	public void descontarInsumo(Pedido pedido) throws Exception {

		List<DetallePedido> lista = detallePedidoRepository.list(pedido);
		for(DetallePedido detallePedido: lista) {
			
			if(detallePedido.getTipo().equals("2")) {
				
				List<Preparacion> ingrediente = preparacionRepository.listIngredientes(new Producto(detallePedido.getCodigoProducto()));
				for(Preparacion preparacion: ingrediente) {
					
					insumoRepository.updateInsumo(preparacion);
				}
				
			}
			
		}
				
	}

	
	
	@Override
	public List<Pago> insertList(List<Pago> pagos, String token) throws Exception {
		Map<String, Object> mapa = new HashMap<>();
		List<Pago> lista = new ArrayList<Pago>();
				
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");
	
		
		int contador = 0;
		for(Pago pago: pagos) {
		contador++;
		
		Numeracion numeracion = new Numeracion();
		numeracion.setSucursal(new Sucursal(session.getCodigoRestauranteLocal()));
		numeracion.setEstado(1);
		numeracion.setDocumento(pago.getTipoDocumento());
		numeracionRepository.updateCorrelatibo(numeracion);
		Numeracion mySerie =numeracionRepository.get(numeracion);
		
		pago.setSerie(mySerie.getSerie());		
		String numeroDOcumento = String.valueOf(mySerie.getCorrelativo());
		numeroDOcumento = Util.completa(numeroDOcumento, 6);
		pago.setNumero(numeroDOcumento);
		pago.setCodigoSucursal(session.getCodigoRestauranteLocal());
		//pago.setEstado("1");
		
		BigDecimal pagoSoles = new BigDecimal("0.00");
		BigDecimal pagoDolares = new BigDecimal("0.00");
		BigDecimal pagoVisaCredito = new BigDecimal("0.00");
		BigDecimal pagoMasterCredito = new BigDecimal("0.00");
		
		pago.setPagoSoles("0");
		pago.setPagoDolares("0");			
		pago.setPagoVisa("0");
		pago.setPagoMasterCard("0");		
		
		if(pago.getTipo().equals("ES")) {
			pagoSoles = new BigDecimal(pago.getMonto());
			pago.setPagoSoles(String.valueOf(pagoSoles));
		} else if(pago.getTipo().equals("ED")) {
			pagoDolares = new BigDecimal(pago.getMonto());
			pago.setPagoDolares(String.valueOf(pagoDolares));			
		} else if(pago.getTipo().equals("VC")) {
			pagoVisaCredito = new BigDecimal(pago.getMonto());			
			pago.setPagoVisa(String.valueOf(pagoVisaCredito));
		} else if(pago.getTipo().equals("MC")) {
			pagoMasterCredito = new BigDecimal(pago.getMonto());
			pago.setPagoMasterCard(String.valueOf(pagoMasterCredito));
		} 
		Pago beanPago = pagoRepository.insert(pago);
		
		pago.setCodigo(beanPago.getCodigo());
		lista.add(pago);
		if(beanPago.getCodigo().intValue()>0) {
			for(PagoDetalle pagoDetalle: pago.getPedidos()) {
				pagoDetalle.setCodigoPago(beanPago.getCodigo());
				pagoDetalle.setEstado(new Integer("1"));
				pagoDetalleRepository.insert(pagoDetalle);
			}
			
		}
		
		
		if(contador==1) {
		if(pago.getEstado().equals("0")) { //
			//Pedido Delivery pendiente de validar Pago
			pago.getPedido().setEstado("4"); 				
		} else {
			//Pedido Cerrado
			pago.getPedido().setEstado("5");
		}
		pedidoRepository.updateStatus(pago.getPedido());
		
		pago.getMesa().setEstado(EstadoMesaCodeEnum.MESA_LIBRE.getCode());
		mesaRepository.updateStatus(pago.getMesa());

		//Ini Descuenta ingredientes
		descontarInsumo(pago.getPedido());
		//Fin Descuenta ingredientes

		}
		

		
		}
		
		/*
		fmapa.put("idPago", beanPago.getCodigo());
		mapa.put("numero", Util.completa(beanPago.getNumero(), 6));
		mapa.put("serie", beanPago.getSerie());
		*/
		return lista;
	}

	@Override
	public String generPreCuenta(Pedido pedido, String token) throws Exception {

		/*
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		Sucursal sucursal = sucursalRepository.getAll(session.getCodigoRestauranteLocal());
        
		Pago bean = pagoRepository.get(pago);
		List<PagoDetalle> pedidos = pagoDetalleRepository.list(bean);
		bean.setPedidos(pedidos);		
		bean.setMoneda("S/");
		
		Boleta boleta = new Boleta();

		boleta.setRazonSocial(sucursal.getRestaurante().getRazon_social());
		boleta.setRuc(sucursal.getRestaurante().getRuc());
        boleta.setDireccion(sucursal.getDireccion());
        boleta.setCliente("-");
        boleta.setUrlDescarga("www.maprsoft.com");
        if(sucursal.getRestaurante().getFranquicia().getCodigo().intValue()<=0) {
        	boleta.setRazonSocialFranquicia("");
            boleta.setDireccionFranquicia("");
        	boleta.setFranquicia(Boolean.FALSE);
        } else {
            boleta.setRazonSocialFranquicia(sucursal.getRestaurante().getFranquicia().getRazonSocial());
            boleta.setDireccionFranquicia(sucursal.getRestaurante().getFranquicia().getDireccion());
        	boleta.setFranquicia(Boolean.TRUE);        	
        }

      
       

        if(bean.getTipoDocumento().equals("F")) {
            FacturaPDF d3 = new FacturaPDF();
            //d3.generaBoletaPDF(boleta, bean);
            pdf64 = Utiles.getImageEncode64(d3.generaBoletaPDF(boleta,bean));
        } else if(bean.getTipoDocumento().equals("B")) {
        	BoletaPDF d2 = new BoletaPDF();
            pdf64 = Utiles.getImageEncode64(d2.generaBoletaPDF(boleta,bean));
        } else  {
        	TicketPDF d1 = new TicketPDF();
            pdf64 = Utiles.getImageEncode64(d1.generaBoletaPDF(boleta,bean));
        } 
		//pdf.generaBoletaPDF(boleta, bean);
		*/	
        String pdf64 = null;
        PrecuentaPDF d1 = new PrecuentaPDF();
        pdf64 = Utiles.getImageEncode64(d1.generaBoletaPDF(pedido));
        
        return pdf64;
	}

	@Override
	public List<Pago> listAll(Filter filter, String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		List<Pago> lista = new ArrayList<Pago>();
		lista = pagoRepository.listAll(session.getCodigoRestauranteLocal());
		
		return lista;
	}

	@Override
	public List<Pago> listByFilter(Filter filter, String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		String inicio =  Util.getDateToFormat(filter.getInicio(), Util.PATTERN_DD_MM_YY, Util.PATTERN_TIME_YYYYMMDD);
		String fin =  Util.getDateToFormat(filter.getFin(), Util.PATTERN_DD_MM_YY, Util.PATTERN_TIME_YYYYMMDD);
		filter.setInicio(inicio);
		filter.setFin(fin);
		
		List<Pago> lista = new ArrayList<Pago>();
		lista = pagoRepository.listByFilter(session.getCodigoRestauranteLocal(),filter);
		
		return lista;
	}
	
	
}