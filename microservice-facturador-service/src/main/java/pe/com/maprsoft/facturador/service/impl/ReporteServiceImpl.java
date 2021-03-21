package pe.com.maprsoft.facturador.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.maprsoft.facturador.model.core.BooxException;
import pe.com.maprsoft.facturador.model.core.DocumentosCodeEnum;
import pe.com.maprsoft.facturador.model.core.EstadoMesaCodeEnum;
import pe.com.maprsoft.facturador.model.core.StringUtil;
import pe.com.maprsoft.facturador.service.MesaService;
import pe.com.maprsoft.facturador.service.ReporteService;
import pe.com.maprsoft.facturador.service.SalonService;
import pe.com.maprsoft.facturador.service.core.Util;
import pe.com.maprsoft.facturador.dao.repository.CajaRepository;
import pe.com.maprsoft.facturador.dao.repository.CategoriaRepository;
import pe.com.maprsoft.facturador.dao.repository.MesaRepository;
import pe.com.maprsoft.facturador.dao.repository.NumeracionRepository;
import pe.com.maprsoft.facturador.dao.repository.ParametroRepository;
import pe.com.maprsoft.facturador.dao.repository.ProductoRepository;
import pe.com.maprsoft.facturador.dao.repository.ReporteRepository;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.dao.repository.UsuarioRepository;
import pe.com.maprsoft.facturador.model.Caja;
import pe.com.maprsoft.facturador.model.Categoria;
import pe.com.maprsoft.facturador.model.Filter;
import pe.com.maprsoft.facturador.model.Mesa;
import pe.com.maprsoft.facturador.model.Numeracion;
import pe.com.maprsoft.facturador.model.Parametro;
import pe.com.maprsoft.facturador.model.Producto;
import pe.com.maprsoft.facturador.model.Session;
import pe.com.maprsoft.facturador.model.Sucursal;
import pe.com.maprsoft.facturador.model.Usuario;

@Service
public class ReporteServiceImpl implements ReporteService {

	@Autowired
	private ReporteRepository reporteRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CajaRepository cajaRepository;

	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private NumeracionRepository numeracionRepository;
	@Autowired
	private ParametroRepository parametroRepository;
	@Override
	public List<Map<String, Object>> listEstados(String token, Map<String, Object> mapa) throws Exception {

		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		List<Map<String, Object>> lista = reporteRepository.list(session.getCodigoRestauranteLocal(), mapa); 
		
		List<Map<String, Object>> listado = new ArrayList<Map<String, Object>>();
		List<String> listaColores =  Util.listColorGraph();
		int indice = 0;
		for(Map<String, Object> map: lista) {
			map.put("highlight", listaColores.get(indice));
			map.put("color", listaColores.get(indice));
			indice++;
			listado.add(map);
		}
		
		return listado;
	}

	@Override
	public List<Map<String, Object>> listxPago(String token, Map<String, Object> mapa) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		//logger.info("periodo:"+request.get("periodo"));
		List<Map<String, Object>> listado = new ArrayList<Map<String, Object>>();
		
		String periodo = mapa.get("periodo").toString();
		if(periodo.equals("M")) {
		List<String> lista = Util.listFechaDateToFormat();

		List<Map<String, Integer>> lisInt = new ArrayList<Map<String, Integer>>();
		Map<String, Integer> mapInt = new HashMap<String, Integer>();
		
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("color", "#3c8dbc");
			
		for(String valor: lista) {
			Map<String, Object> map = reporteRepository.listPagoxMes(session.getCodigoRestauranteLocal(), valor, mapa); 
			//map.put("color", listaColores.get(indice));
			//listado.add(map);
			if(map!=null) {
			map.put("periodo", Util.getFormatDescFecha(valor));
			} else {
				map = new HashMap<String, Object>();
				map.put("periodo", Util.getFormatDescFecha(valor));
				map.put("label", valor);
				map.put("value", "0.00");				
			}
			listado.add(map);
		}
		}
		
		if(periodo.equals("D")) {
			List<String> lista = Util.listFechaDateToFormatByDia();

			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("color", "#3c8dbc");
				
			for(String valor: lista) {
				Map<String, Object> map = reporteRepository.listPagoxDia(session.getCodigoRestauranteLocal(), valor, mapa); 
				//map.put("color", listaColores.get(indice));
				//listado.add(map);
				if(map!=null) {
				map.put("periodo", Util.getDateToFormat(valor,"yyyyMMdd","dd/MM/yyyy"));
				} else {
					map = new HashMap<String, Object>();
					map.put("periodo", Util.getDateToFormat(valor,"yyyyMMdd","dd/MM/yyyy"));
					map.put("label", valor);
					map.put("value", "0.00");				
				}
				listado.add(map);
			}
			
			
		}

		if(periodo.equals("A")) {
			List<String> lista = Util.listFechaDateToFormatByAnio();

			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("color", "#3c8dbc");
				
			for(String valor: lista) {
				Map<String, Object> map = reporteRepository.listPagoxAnio(session.getCodigoRestauranteLocal(), valor, mapa); 
				//map.put("color", listaColores.get(indice));
				//listado.add(map);
				if(map!=null) {
				map.put("periodo", valor);
				} else {
					map = new HashMap<String, Object>();
					map.put("periodo", valor);
					map.put("label", valor);
					map.put("value", "0.00");				
				}
				listado.add(map);
			}
			
			
		}

		return listado;
	}


	@Override
	public Map<String, Object> ventaByDia(String token, Map<String, Object> mapa) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		Map<String, Object> mapeo = new HashMap<String, Object>();	
		String periodo = mapa.get("periodo").toString();
		Caja caja = new Caja();
		caja.setCodigoSucursal(session.getCodigoRestauranteLocal());
		caja.setFechaRegistro(Util.getDateToFormat(periodo,"dd/MM/yyyy","yyyyMMdd"));

		List<Caja> listCaja = cajaRepository.listCajaByDia(caja);
		
		mapeo.put("listaCaja", listCaja);
		
		mapeo.put("listaVenta", ventaDiario(token, mapa));
		
		return mapeo;
	}
	
	@Override
	public List<Map<String, Object>> ventaDiario(String token, Map<String, Object> mapa) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		//logger.info("periodo:"+request.get("periodo"));
		List<Map<String, Object>> listado = new ArrayList<Map<String, Object>>();
		
		String periodo = mapa.get("periodo").toString();
				
			List<String> lista = Util.listFechaDateToFormatByDiaAnteriores(periodo);

			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("color", "#3c8dbc");
				
			for(String valor: lista) {
				Map<String, Object> map = reporteRepository.listPagoxDia(session.getCodigoRestauranteLocal(), valor, mapa); 
				//map.put("color", listaColores.get(indice));
				//listado.add(map);
				if(map!=null) {
				map.put("periodo", Util.getDateToFormat(valor,"yyyyMMdd","dd/MM/yyyy"));
				} else {
					map = new HashMap<String, Object>();
					map.put("periodo", Util.getDateToFormat(valor,"yyyyMMdd","dd/MM/yyyy"));
					map.put("label", valor);
					map.put("value", "0.00");				
				}
				listado.add(map);
			}		

		return listado;
	}

	@Override
	public Map<String, Object> descuentoByDia(String token, Map<String, Object> mapa) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		Map<String, Object> mapeo = new HashMap<String, Object>();	
		String periodo = mapa.get("periodo").toString();
		Caja caja = new Caja();
		caja.setCodigoSucursal(session.getCodigoRestauranteLocal());
		caja.setFechaRegistro(Util.getDateToFormat(periodo,"dd/MM/yyyy","yyyyMMdd"));

		List<Caja> listCaja = cajaRepository.listCajaByDia(caja);
		
		mapeo.put("listaCaja", listCaja);
		
		mapeo.put("listaVenta", descuentoDiario(token, mapa));
		
		return mapeo;
	}

	@Override
	public List<Map<String, Object>> descuentoDiario(String token, Map<String, Object> mapa) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		//logger.info("periodo:"+request.get("periodo"));
		List<Map<String, Object>> listado = new ArrayList<Map<String, Object>>();
		
		String periodo = mapa.get("periodo").toString();
				
			List<String> lista = Util.listFechaDateToFormatByDiaAnteriores(periodo);

			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("color", "#3c8dbc");
				
			for(String valor: lista) {
				Map<String, Object> map = reporteRepository.listDescuentoxDia(session.getCodigoRestauranteLocal(), valor, mapa); 
				//map.put("color", listaColores.get(indice));
				//listado.add(map);
				if(map!=null) {
				map.put("periodo", Util.getDateToFormat(valor,"yyyyMMdd","dd/MM/yyyy"));
				} else {
					map = new HashMap<String, Object>();
					map.put("periodo", Util.getDateToFormat(valor,"yyyyMMdd","dd/MM/yyyy"));
					map.put("label", valor);
					map.put("value", "0.00");				
				}
				listado.add(map);
			}		

		return listado;
	}

	@Override
	public List<Map<String, Object>> ventaByMozo(String token, Map<String, Object> mapa) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		List<Map<String, Object>> listado = new ArrayList<Map<String, Object>>();
		Map<String, Object> maps = new HashMap<String, Object>();
		List<Usuario> listaMozo =  usuarioRepository.listMozo(new Usuario(session.getCodigoRestauranteLocal()));
		String periodo = mapa.get("periodo").toString();
		
		for(Usuario user: listaMozo) {
			
			List<Map<String, Object>> listaMap = reporteRepository.ventasPorDiaPorMozo(user, Util.getDateToFormat(periodo,"dd/MM/yyyy","yyyyMMdd"));
			maps = new HashMap<String, Object>();
			if(listaMap!=null && !listaMap.isEmpty()) {
				maps = listaMap.get(0);
				maps.put("label", user.getUsuario());
				maps.put("periodo", user.getUsuario());
			} else {
				maps.put("periodo", user.getUsuario());
				maps.put("label", periodo);
				maps.put("value", "0.00");								
			}		
			listado.add(maps);
		}
		
		return listado;
	}

	@Override
	public List<List<Map<String, Object>>> ventasByProducto(String token, Map<String, Object> mapa) throws Exception {
		
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");
		
		String periodo = StringUtil.getFechaDateToFormat("dd/MM/yyyy");
		if(mapa.get("inicio")!=null)
			periodo = mapa.get("inicio").toString();		
		
		List<Categoria> listaSucursales = categoriaRepository.list(session.getCodigoRestauranteLocal());

		List<List<Map<String, Object>>> listamulti = new ArrayList<List<Map<String, Object>>>();
		
		List <Map<String, Object>> lista = null;
		Map<String, Object> mapeo = null;

		for(Categoria cat: listaSucursales) {
			lista = new ArrayList<Map<String, Object>>();
			mapeo = new HashMap<String, Object>();
			mapeo.put("label",cat.getNombre()); 
			mapeo.put("value",""); 
			lista.add(mapeo);			
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sucursal",session.getCodigoRestauranteLocal()); 
			map.put("categoria",cat.getId());
			map.put("inicio",Util.getDateToFormat(periodo,"dd/MM/yyyy","yyyyMMdd"));
			
			List<Map<String, Object>> listaMontos = reporteRepository.montoByProducto(map);
			if(listaMontos!=null && !listaMontos.isEmpty()) {
				
				int total = 0;
				for(Map<String, Object> maping: listaMontos) {
				int result=0;
				if(maping.get("value")!=null)
					result = Integer.valueOf(maping.get("value").toString());
				
				int valor = result;	
				total = total + valor;
				}

				
				BigDecimal totales = new BigDecimal(String.valueOf(total));
				
				for(Map<String, Object> maping: listaMontos) {
				//("value",rs.getString(3)); ("label",rs.getString(2));	("code",rs.getString(1));
				//pd.nombre, sum(pd.precio), count(pd.nombre)
				mapeo = new HashMap<String, Object>();
				mapeo.put("label",maping.get("code").toString()); 
				//mapeo.put("value",maping.get("value").toString()); 
				try {
				BigDecimal cien = new BigDecimal("100");
				BigDecimal value = new BigDecimal(maping.get("value").toString()).multiply(cien);
				BigDecimal porcentaje = value.divide(totales).setScale(0);
				mapeo.put("value",String.valueOf(porcentaje));
				} catch(Exception e) {  
					mapeo.put("value",String.valueOf("0"));  
				}
				mapeo.put("count",maping.get("value").toString()); 
				mapeo.put("total",total); 
				lista.add(mapeo);
				}
			}

			listamulti.add(lista);
			lista = new ArrayList<Map<String, Object>>();
			
		/*
		for(Categoria cat: listaSucursales) {
			lista = new ArrayList<Map<String, Object>>();
			mapeo = new HashMap<String, Object>();
			
			cat.setSucursal(new Sucursal(session.getCodigoRestauranteLocal()));
			
			List<Producto> listProducto = productoRepository.listByCategoria(cat);
			for(Producto prod: listProducto) {
				
				Map<String, Object> map = new HashMap<String, Object>();

				mapa.put("sucursal",session.getCodigoRestauranteLocal()); 
				mapa.put("categoria",cat.getId());
				mapa.put("producto", prod.getId());
				mapa.put("inicio",Util.getDateToFormat(periodo,"dd/MM/yyyy","yyyyMMdd"));
				
				List<Map<String, Object>> listaMontos = reporteRepository.montoByProducto(map);
				if(listaMontos!=null && !listaMontos.isEmpty()) {
					Map<String, Object> maping = listaMontos.get(0);
					//("value",rs.getString(3)); ("label",rs.getString(2));	("code",rs.getString(1));
					//pd.nombre, sum(pd.precio), count(pd.nombre)
					
				}
				
				
			}	
			*/
		}
		
		
		return listamulti;
	}


	
	@Override
	public List<Map<String, Object>> emisionComprobantes(String token, Map<String, Object> mapa) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		
		String inicio = StringUtil.getFechaDateToFormat("dd/MM/yyyy");
		if(mapa.get("inicio")!=null)
			inicio = mapa.get("inicio").toString();		
		//List<Numeracion> listNumeracion = numeracionRepository.list(session.getCodigoRestauranteLocal());
		Parametro parametro = new Parametro();
		parametro.setTipo("DOCUMENTO");
		parametro.setGrupo("DOCUMENTO");
		parametro.setSucursal(new Sucursal(session.getCodigoRestauranteLocal()));
		List<Parametro> listParametro = parametroRepository.listGeneric(parametro);
		
		Filter filter = new Filter();
		filter.setInicio(Util.getDateToFormat(inicio,"dd/MM/yyyy","yyyyMMdd"));
		List<Map<String, Object>> listDocumentos = reporteRepository.ventasPorDocumento(session.getCodigoRestauranteLocal(), filter);
		
		List<Map<String, Object>> listado = new ArrayList<Map<String, Object>>();
		Map<String, Object> maps = new HashMap<String, Object>();
		
		for(Parametro param: listParametro) {
			maps = new HashMap<String, Object>();
			maps.put("documento", param.getCode());
			maps.put("documentoDescripcion", DocumentosCodeEnum.getDocumento(param.getCode()));
			Map<String, Object> map = getMap(listDocumentos, "value1", param.getCode());
			if(map!=null) {
				maps.put("serie", map.get("value2").toString());
				maps.put("total", map.get("value3").toString());
				maps.put("inicio", StringUtil.zeroLeftFiller(map.get("value4").toString(),6));
				maps.put("fin", StringUtil.zeroLeftFiller(map.get("value5").toString(),6));
			} else {
				maps.put("serie", "-");
				maps.put("total", "-");
				maps.put("inicio", "-");
				maps.put("fin", "-");				
			}
			listado.add(maps);
		}
				
		return listado;
	}


	
	@Override
	public List<Map<String, Object>> reporteIncurrencias(String token, Map<String, Object> mapa) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		
		String inicio = StringUtil.getFechaDateToFormat("dd/MM/yyyy");
		if(mapa.get("inicio")!=null)
			inicio = mapa.get("inicio").toString();		
		
		Filter filter = new Filter();
		filter.setInicio(Util.getDateToFormat(inicio,"dd/MM/yyyy","yyyyMMdd"));
		List<Map<String, Object>> listIncurrencias = reporteRepository.reporteIncurrencias(session.getCodigoRestauranteLocal(), filter);
		
		List<Map<String, Object>> listado = new ArrayList<Map<String, Object>>();
		Map<String, Object> maps = new HashMap<String, Object>();
		
		for(Map<String, Object> maping: listIncurrencias) {
			//mo.usuario, pe.codigo, pe.motivo_cancelacion, de.nombre, de.precio 
			maps = new HashMap<String, Object>();
			maps.put("usuario", maping.get("value1").toString());
			maps.put("pedido", StringUtil.zeroLeftFiller(maping.get("value2").toString(),6));
			maps.put("motivo", maping.get("value3").toString());
			maps.put("producto", maping.get("value4").toString());
			maps.put("precio", maping.get("value5").toString());
			
			listado.add(maps);
		}
				
		return listado;
	}

	
	@Override
	public List<Caja> reporteCajaByDia(String token, Map<String, Object> mapa) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		
		String inicio = StringUtil.getFechaDateToFormat("dd/MM/yyyy");
		if(mapa.get("inicio")!=null)
			inicio = mapa.get("inicio").toString();		
		
		Filter filter = new Filter();
		filter.setInicio(Util.getDateToFormat(inicio,"dd/MM/yyyy","yyyyMMdd"));
		
		Caja caja = new Caja();
		caja.setCodigoSucursal(session.getCodigoRestauranteLocal());
		caja.setFechaRegistro(Util.getDateToFormat(inicio,"dd/MM/yyyy","yyyyMMdd"));
		List<Caja> listCaja  = cajaRepository.listCajaByDia(caja);
						
		return listCaja;
	}

	
	public Map<String, Object> getMap(List<Map<String, Object>> listado, String label, String value) {		
		for(Map<String, Object> map: listado) {
			if(map.get(label).toString().equals(value))
				return map;
		}
		return null;
	}



}
