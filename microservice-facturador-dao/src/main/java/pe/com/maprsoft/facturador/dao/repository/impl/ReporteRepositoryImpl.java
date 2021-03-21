package pe.com.maprsoft.facturador.dao.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.maprsoft.facturador.dao.repository.CategoriaRepository;
import pe.com.maprsoft.facturador.dao.repository.ReporteRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.CategoriaAllMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.CategoriaMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.Map5Mapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.MapMapper;
import pe.com.maprsoft.facturador.model.Categoria;
import pe.com.maprsoft.facturador.model.Filter;
import pe.com.maprsoft.facturador.model.ListaCategoria;
import pe.com.maprsoft.facturador.model.Pedido;
import pe.com.maprsoft.facturador.model.Usuario;

@SuppressWarnings("all")
@Repository
public class ReporteRepositoryImpl implements ReporteRepository {

	private final Logger logger = LoggerFactory.getLogger(ReporteRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> list(Integer sucursal, Map<String, Object> mapa) throws Exception {
		List <Map<String, Object>> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select pe.tipo_pedido, pa.val, count(*) ");		
		sql_find_user.append("from tbl_pedido pe ");
		sql_find_user.append("inner join tbl_parametros pa on pa.grupo='TIPOPEDIDO' ");
		sql_find_user.append("where pe.estado != 6 and pe.tipo_pedido = pa.cod ");
		//sql_find_user.append("and DATE_FORMAT(pe.fec_registro,'%Y%m%d')>=20181009 ");
		//sql_find_user.append("and DATE_FORMAT(pe.fec_registro,'%Y%m%d')<=20181019 ");
		sql_find_user.append("and pe.codigo_restaurante_local = ? ");		
		sql_find_user.append("group by pe.tipo_pedido, pa.val ");
		
		Object[] params = new Object[] { sucursal };
		lista = (List <Map<String, Object>>)jdbcTemplate.query(sql_find_user.toString(), params, new MapMapper());
		return lista;
	}

	@Override
	public Map<String, Object> listPagoxMes(Integer sucursal, String periodo, Map<String, Object> mapa) throws Exception {
		List <Map<String, Object>> lista = null;
		Map<String, Object> bean = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select DATE_FORMAT(pa.fec_emision,'%Y%m'), DATE_FORMAT(pa.fec_emision,'%Y%m'), sum(pa.total) ");		
		sql_find_user.append("from tbl_pago pa ");
		sql_find_user.append("where DATE_FORMAT(pa.fec_emision,'%Y%m') = ? ");
		sql_find_user.append("and pa.codigo_restaurante_local = ? ");		
		sql_find_user.append("group by DATE_FORMAT(pa.fec_emision,'%Y%m'),DATE_FORMAT(pa.fec_emision,'%Y%m') ");
		
				Object[] params = new Object[] { periodo, sucursal };
		lista = (List <Map<String, Object>>)jdbcTemplate.query(sql_find_user.toString(), params, new MapMapper());
		
    	if(lista!=null && lista.size()>0) {    		
    		bean = new HashMap<String, Object>();
    		bean = lista.get(0);
    	}	    	

		return bean;
	}

	@Override
	public Map<String, Object> listPagoxDia(Integer sucursal, String periodo, Map<String, Object> mapa) throws Exception {
		List <Map<String, Object>> lista = null;
		Map<String, Object> bean = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select DATE_FORMAT(pa.fec_emision,'%Y%m%d'), DATE_FORMAT(pa.fec_emision,'%Y%m%d'), sum(pa.total) ");		
		sql_find_user.append("from tbl_pago pa ");
		sql_find_user.append("where DATE_FORMAT(pa.fec_emision,'%Y%m%d') = ? ");
		sql_find_user.append("and pa.codigo_restaurante_local = ? ");		
		sql_find_user.append("group by DATE_FORMAT(pa.fec_emision,'%Y%m%d'),DATE_FORMAT(pa.fec_emision,'%Y%m%d') ");
		
				Object[] params = new Object[] { periodo, sucursal };
		lista = (List <Map<String, Object>>)jdbcTemplate.query(sql_find_user.toString(), params, new MapMapper());
		
    	if(lista!=null && lista.size()>0) {    		
    		bean = new HashMap<String, Object>();
    		bean = lista.get(0);
    	}	    	

		return bean;
	}
	
	@Override
	public Map<String, Object> listDescuentoxDia(Integer sucursal, String periodo, Map<String, Object> mapa) throws Exception {
		List <Map<String, Object>> lista = null;
		Map<String, Object> bean = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select DATE_FORMAT(pa.fec_emision,'%Y%m%d'), DATE_FORMAT(pa.fec_emision,'%Y%m%d'), sum(pa.descuento) ");		
		sql_find_user.append("from tbl_pago pa ");
		sql_find_user.append("where DATE_FORMAT(pa.fec_emision,'%Y%m%d') = ? ");
		sql_find_user.append("and pa.codigo_restaurante_local = ? ");		
		sql_find_user.append("group by DATE_FORMAT(pa.fec_emision,'%Y%m%d'),DATE_FORMAT(pa.fec_emision,'%Y%m%d') ");
		
				Object[] params = new Object[] { periodo, sucursal };
		lista = (List <Map<String, Object>>)jdbcTemplate.query(sql_find_user.toString(), params, new MapMapper());
		
    	if(lista!=null && lista.size()>0) {    		
    		bean = new HashMap<String, Object>();
    		bean = lista.get(0);
    	}	    	

		return bean;
	}

	@Override
	public Map<String, Object> listPagoxAnio(Integer sucursal, String periodo, Map<String, Object> mapa) throws Exception {
		List <Map<String, Object>> lista = null;
		Map<String, Object> bean = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select DATE_FORMAT(pa.fec_emision,'%Y'), DATE_FORMAT(pa.fec_emision,'%Y'), sum(pa.total) ");		
		sql_find_user.append("from tbl_pago pa ");
		sql_find_user.append("where DATE_FORMAT(pa.fec_emision,'%Y') = ? ");
		sql_find_user.append("and pa.codigo_restaurante_local = ? ");		
		sql_find_user.append("group by DATE_FORMAT(pa.fec_emision,'%Y'),DATE_FORMAT(pa.fec_emision,'%Y') ");
		
				Object[] params = new Object[] { periodo, sucursal };
		lista = (List <Map<String, Object>>)jdbcTemplate.query(sql_find_user.toString(), params, new MapMapper());
		
    	if(lista!=null && lista.size()>0) {    		
    		bean = new HashMap<String, Object>();
    		bean = lista.get(0);
    	}	    	

		return bean;
	}

	@Override
	public List<Map<String, Object>> pedidoMasVendidos(Integer sucursal, Filter filter) throws Exception {
		List <Map<String, Object>> lista = null;
		Map<String, Object> bean = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select de.codigo_producto, de.nombre, count(*) ");		
		sql_find_user.append("from tbl_pedido pe ");
		sql_find_user.append("inner join tbl_pedido_detalle de on pe.codigo=de.codigo_pedido ");
		sql_find_user.append("where pe.codigo_restaurante_local = ? and pe.estado = 5 ");				
		sql_find_user.append("from tbl_pedido pe ");
		sql_find_user.append("and DATE_FORMAT(pe.fec_pedido,'%Y%m%d') >= ? ");
		sql_find_user.append("and DATE_FORMAT(pe.fec_pedido,'%Y%m%d') <= ? ");		
		sql_find_user.append("group by de.codigo_producto, de.nombre ");
		sql_find_user.append("order by 3 desc ");

		Object[] params = new Object[] { sucursal, filter.getInicio(), filter.getFin() };
		lista = (List <Map<String, Object>>)jdbcTemplate.query(sql_find_user.toString(), params, new MapMapper());
		return lista;
	}

	
	@Override
	public List<Map<String, Object>> ventasPorMozo(Integer sucursal, Filter filter) throws Exception {
		List <Map<String, Object>> lista = null;
		Map<String, Object> bean = null;
		StringBuilder sql_find_user = new StringBuilder();

		sql_find_user.append("select us.usuario, DATE_FORMAT(pa.fec_emision,'%d/%m/%Y'), sum(pa.total) ");
		sql_find_user.append("from tbl_pago pa ");
		sql_find_user.append("inner join tbl_pedido pe on pe.codigo = pa.codigo_pedido ");
		sql_find_user.append("inner join tbl_usuario us on us.codigo  = pe.codigo_mozo ");
		sql_find_user.append("where pa.estado=1 and pa.codigo_restaurante_local = ?  ");
		sql_find_user.append("DATE_FORMAT(pa.fec_emision,'%Y%m%d') = ? ");
		sql_find_user.append("group by us.usuario, DATE_FORMAT(pa.fec_emision,'%d/%m/%Y') ");

		Object[] params = new Object[] { sucursal, filter.getInicio() };
		lista = (List <Map<String, Object>>)jdbcTemplate.query(sql_find_user.toString(), params, new MapMapper());
		return lista;
	}

	
	@Override
	public List<Map<String, Object>> ventasPorDocumento(Integer sucursal, Filter filter) throws Exception {
		List <Map<String, Object>> lista = null;
		Map<String, Object> bean = null;
		StringBuilder sql_find_user = new StringBuilder();
		
		sql_find_user.append("select pa.documento, pa.serie, sum(pa.total), min(pa.numero), max(pa.numero) ");
		sql_find_user.append("from tbl_pago pa ");
		sql_find_user.append("where pa.estado=1 and pa.codigo_restaurante_local = ? ");
		//sql_find_user.append("and pa.documento='T' ");
		sql_find_user.append("and DATE_FORMAT(pa.fec_emision,'%Y%m%d') = ? ");
		sql_find_user.append("group by pa.documento, pa.serie ");		
		
		Object[] params = new Object[] { sucursal, filter.getInicio() };
		lista = (List <Map<String, Object>>)jdbcTemplate.query(sql_find_user.toString(), params, new Map5Mapper());
		return lista;
	}

	@Override
	public List<Map<String, Object>> reporteIncurrencias(Integer sucursal, Filter filter) throws Exception {
		List <Map<String, Object>> lista = null;
		Map<String, Object> bean = null;
		StringBuilder sql_find_user = new StringBuilder();
				
		sql_find_user.append("select mo.usuario, pe.codigo, pe.motivo_cancelacion, de.nombre, de.precio ");
		sql_find_user.append("from tbl_pedido pe ");
		sql_find_user.append("inner join tbl_pedido_detalle de on pe.codigo=de.codigo_pedido ");
		sql_find_user.append("inner join tbl_usuario mo on mo.codigo = pe.codigo_mozo ");
		sql_find_user.append("where pe.codigo_restaurante_local = ? and pe.estado = 6 ");
		sql_find_user.append("and DATE_FORMAT(pe.fec_pedido,'%Y%m%d') = ? ");
		sql_find_user.append("order by pe.codigo ");
		
		Object[] params = new Object[] { sucursal, filter.getInicio() };
		lista = (List <Map<String, Object>>)jdbcTemplate.query(sql_find_user.toString(), params, new Map5Mapper());
		return lista;
	}

	@Override
	public List<Map<String, Object>> ventasPorMaxMin(Integer sucursal, Filter filter) throws Exception {
		List <Map<String, Object>> lista = null;
		Map<String, Object> bean = null;
		StringBuilder sql_find_user = new StringBuilder();
		
		sql_find_user.append("select pa.serie, min(pa.numero), max(pa.numero) ");
		sql_find_user.append("from tbl_pago pa ");
		sql_find_user.append("where pa.estado=1 and pa.documento = ? ");
		sql_find_user.append("and DATE_FORMAT(pa.fec_emision,'%Y%m%d')=20181101 ");
		sql_find_user.append("group by pa.serie ");

		Object[] params = new Object[] { sucursal, filter.getPatron(), filter.getInicio() };
		lista = (List <Map<String, Object>>)jdbcTemplate.query(sql_find_user.toString(), params, new MapMapper());
		return lista;
	}

	@Override
	public List<Map<String, Object>> ventasPorDiaPorMozo(Usuario usuario, String periodo) throws Exception {
		List <Map<String, Object>> lista = null;
		Map<String, Object> bean = null;
		StringBuilder sql_find_user = new StringBuilder();

		sql_find_user.append("select us.usuario, DATE_FORMAT(pa.fec_emision,'%d/%m/%Y'), sum(pa.total) ");
		sql_find_user.append("from tbl_pago pa ");
		sql_find_user.append("inner join tbl_pedido pe on pe.codigo = pa.codigo_pedido ");
		sql_find_user.append("inner join tbl_usuario us on us.codigo  = pe.codigo_mozo ");
		sql_find_user.append("where pa.estado=1 and pa.codigo_restaurante_local = ?  ");
		sql_find_user.append("and pe.codigo_mozo = ? ");
		sql_find_user.append("and DATE_FORMAT(pa.fec_emision,'%Y%m%d') = ? ");
		sql_find_user.append("group by us.usuario, DATE_FORMAT(pa.fec_emision,'%d/%m/%Y') ");
		
		Object[] params = new Object[] { usuario.getCodigoRestauranteLocal(),usuario.getCodigo(), periodo };
		lista = (List <Map<String, Object>>)jdbcTemplate.query(sql_find_user.toString(), params, new MapMapper());
		return lista;
	}

	@Override
	public List<Map<String, Object>> montoByProducto(Map<String, Object> mapa) throws Exception {
		List <Map<String, Object>> lista = null;
		Map<String, Object> bean = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select pr.nombre, sum(pd.precio), count(pd.nombre) ");
		sql_find_user.append("from tbl_categoria ca ");
		sql_find_user.append("inner join tbl_subcategoria sc on sc.codigo_categoria = ca.codigo ");
		sql_find_user.append("inner join tbl_producto pr on pr.codigo_subcategoria = sc.codigo ");
		sql_find_user.append("left join tbl_pedido_detalle pd on pd.codigo_producto = pr.codigo ");
		sql_find_user.append("and DATE_FORMAT(pd.fec_registro,'%Y%m%d') = ? ");
		sql_find_user.append("left join tbl_pedido pe on pe.codigo = pd.codigo_pedido and pe.estado = 5 ");
		sql_find_user.append("where ca.codigo_restaurante_local = ? and ca.codigo = ? ");
		//sql_find_user.append("and pd.codigo_producto = ? and pe.estado = 5 ");
		sql_find_user.append("group by pr.nombre ");
		sql_find_user.append("order by 3 desc");
		//mapa.get("producto"),
		Object[] params = new Object[] { mapa.get("inicio") , 
				mapa.get("sucursal"), mapa.get("categoria") 
				};
		lista = (List <Map<String, Object>>)jdbcTemplate.query(sql_find_user.toString(), params, new MapMapper());
		return lista;
	}


	/*
	@Override
	public List<Categoria> list(Integer sucursal) throws Exception {
		List <Categoria> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select ca.codigo, ca.nombre, ca.estado ");		
		sql_find_user.append("from tbl_categoria ca where  ");
		sql_find_user.append("ca.codigo_restaurante_local = ? ");
		sql_find_user.append("order by 2 ");
		
		Object[] params = new Object[] { sucursal };
		lista = (List <Categoria>)jdbcTemplate.query(sql_find_user.toString(), params, new CategoriaMapper());
		return lista;
	}
*/

}
