package pe.com.maprsoft.facturador.dao.repository.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.maprsoft.facturador.dao.repository.InsumoRepository;
import pe.com.maprsoft.facturador.dao.repository.MozoRepository;
import pe.com.maprsoft.facturador.dao.repository.PreparacionRepository;
import pe.com.maprsoft.facturador.dao.repository.SalonRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.InsumoMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.MozoMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.PreparacionInsumoMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.PreparacionMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.SalonMapper;
import pe.com.maprsoft.facturador.model.Insumo;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.PagoDetalle;
import pe.com.maprsoft.facturador.model.Preparacion;
import pe.com.maprsoft.facturador.model.Producto;
import pe.com.maprsoft.facturador.model.Salon;


@SuppressWarnings("all")
@Repository
public class PreparacionRepositoryImpl implements PreparacionRepository {

	private final Logger logger = LoggerFactory.getLogger(PreparacionRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Preparacion> listIngredientes(Producto producto) throws Exception {
		List <Preparacion> lista = null;
		StringBuilder sql_find_user = new StringBuilder();

		sql_find_user.append("select pi.codigo cod_preparacion, pi.medida,pi.cantidad_preparacion, pi.estado, ");
		sql_find_user.append("pi.codigo_insumo, pi.codigo_producto ");
		sql_find_user.append("from tbl_producto_insumo pi ");
		sql_find_user.append("where pi.codigo_producto = ? and pi.estado = 1 ");
		sql_find_user.append("order by 1 ");
		Object[] params = new Object[] { producto.getId() };
		lista = (List <Preparacion>)jdbcTemplate.query(sql_find_user.toString(), params, new PreparacionInsumoMapper());
		return lista;	
	}

	@Override
	public List<Preparacion> list(Producto producto, Integer sucursal) throws Exception {
		List <Preparacion> lista = null;
		StringBuilder sql_find_user = new StringBuilder();

		sql_find_user.append("select pi.codigo cod_preparacion, pi.medida,pi.cantidad_preparacion, pi.estado, ");
		sql_find_user.append("pi.codigo_insumo, i.codigo_restaurante_local,i.medida medida_insumo, i.nombre nombre_insumo, ");
		sql_find_user.append("i.cantidad cantidad_insumo, i.merma, i.stock_minimo, i.estado estado_insumo, ");
		sql_find_user.append("pi.codigo_producto, p.nombre nombre_prod, p.tipo tipo_prod, p.descripcion desc_prod, ");
		sql_find_user.append("p.precio precio_prod, p.moneda, p.estado, p.lugar_elaboracion ");
		sql_find_user.append("from tbl_producto_insumo pi ");
		sql_find_user.append("inner join tbl_producto  p on p.codigo = pi.codigo_producto ");
		sql_find_user.append("inner join tbl_insumo  i on i.codigo = pi.codigo_insumo ");
		sql_find_user.append("where i.codigo_restaurante_local = ? and pi.codigo_producto = ? and pi.estado = 1 ");
		sql_find_user.append("order by 1 ");
		Object[] params = new Object[] { sucursal, producto.getId() };
		lista = (List <Preparacion>)jdbcTemplate.query(sql_find_user.toString(), params, new PreparacionMapper());
		return lista;	
	}

	@Override
	public List<Preparacion> listAll(Producto producto, Integer sucursal) throws Exception {
		List <Preparacion> lista = null;
		StringBuilder sql_find_user = new StringBuilder();

		sql_find_user.append("select pi.codigo cod_preparacion, pi.medida,pi.cantidad_preparacion, pi.estado, ");
		sql_find_user.append("pi.codigo_insumo, i.codigo_restaurante_local,i.medida medida_insumo, i.nombre nombre_insumo, ");
		sql_find_user.append("i.cantidad cantidad_insumo, i.merma, i.stock_minimo, i.estado estado_insumo, ");
		sql_find_user.append("pi.codigo_producto, p.nombre nombre_prod, p.tipo tipo_prod, p.descripcion desc_prod, ");
		sql_find_user.append("p.precio precio_prod, p.moneda, p.estado, p.lugar_elaboracion ");
		sql_find_user.append("from tbl_producto_insumo pi ");
		sql_find_user.append("inner join tbl_producto  p on p.codigo = pi.codigo_producto ");
		sql_find_user.append("inner join tbl_insumo  i on i.codigo = pi.codigo_insumo ");
		sql_find_user.append("where i.codigo_restaurante_local = ? and pi.codigo_producto = ? ");
		sql_find_user.append("order by 1 ");
		Object[] params = new Object[] { sucursal, producto.getId() };
		lista = (List <Preparacion>)jdbcTemplate.query(sql_find_user.toString(), params, new PreparacionMapper());
		return lista;	
	}

	@Override
	public boolean insert(Preparacion preparacion) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();		
		sql_insert_user.append("INSERT INTO tbl_producto_insumo ");
		sql_insert_user.append("(codigo_producto, codigo_insumo, medida, cantidad_preparacion, estado, fec_registro) ");
		sql_insert_user.append("VALUES (?,?,?,?,?,?) ");
		java.util.Date dt = new java.util.Date();
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String fechaHora = sdf.format(dt);	        	    
		Object[] params = new Object[] {
		preparacion.getCodigoProducto(), preparacion.getCodigoInsumo(),preparacion.getMedida(),
		preparacion.getCantidad(),preparacion.getEstado(), fechaHora };				
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;	
	}

	@Override
	public boolean delete(Preparacion preparacion) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();		
		sql_insert_user.append("DELETE FROM tbl_producto_insumo ");
		sql_insert_user.append("WHERE codigo = ? ");
		java.util.Date dt = new java.util.Date();
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String fechaHora = sdf.format(dt);	        	    
		Object[] params = new Object[] { preparacion.getCodigo() };				
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;	
	}

	@Override
	public boolean update(Preparacion preparacion) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();		
		sql_insert_user.append("UPDATE tbl_producto_insumo ");
		sql_insert_user.append("SET medida = ?, cantidad_preparacion = ?, estado = ? ");
		sql_insert_user.append("WHERE codigo = ? ");
		java.util.Date dt = new java.util.Date();
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String fechaHora = sdf.format(dt);	        	    
		Object[] params = new Object[] {
		preparacion.getMedida(), preparacion.getCantidad(),preparacion.getEstado(), preparacion.getCodigo() };				
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;	
	}

	@Override
	public boolean updateStatus(Preparacion preparacion) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
}
