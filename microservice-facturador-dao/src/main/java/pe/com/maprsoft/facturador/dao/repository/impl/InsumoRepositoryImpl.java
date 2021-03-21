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
import pe.com.maprsoft.facturador.dao.repository.SalonRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.InsumoMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.MozoMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.SalonMapper;
import pe.com.maprsoft.facturador.model.Insumo;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.PagoDetalle;
import pe.com.maprsoft.facturador.model.Preparacion;
import pe.com.maprsoft.facturador.model.Salon;


@SuppressWarnings("all")
@Repository
public class InsumoRepositoryImpl implements InsumoRepository {

	private final Logger logger = LoggerFactory.getLogger(InsumoRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Insumo> list(Integer sucursal) throws Exception {
		List <Insumo> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_restaurante_local, medida, nombre, ");		
		sql_find_user.append("cantidad, merma, stock_minimo, estado,cantidad_real, lugar_elaboracion,precio_compra ");		
		sql_find_user.append("from tbl_insumo where codigo_restaurante_local = ? and estado = 1 ");
		sql_find_user.append("order by lugar_elaboracion,nombre ");
		Object[] params = new Object[] { sucursal };
		lista = (List <Insumo>)jdbcTemplate.query(sql_find_user.toString(), params, new InsumoMapper());
		return lista;	
	}

	@Override
	public List<Insumo> listAll(Integer sucursal) throws Exception {
		List <Insumo> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_restaurante_local, medida, nombre, ");		
		sql_find_user.append("cantidad, merma, stock_minimo, estado,cantidad_real, lugar_elaboracion,precio_compra ");		
		sql_find_user.append("from tbl_insumo where codigo_restaurante_local = ? ");
		sql_find_user.append("order by lugar_elaboracion,nombre ");
		Object[] params = new Object[] { sucursal };
		lista = (List <Insumo>)jdbcTemplate.query(sql_find_user.toString(), params, new InsumoMapper());
		return lista;	
	}


	@Override
	public boolean insert(Insumo insumo) throws Exception {
			boolean resultado = false;
			StringBuilder sql_insert_user = new StringBuilder();
			sql_insert_user.append("INSERT INTO tbl_insumo ");
			sql_insert_user.append("(codigo_restaurante_local, medida, nombre, cantidad, cantidad_real,merma, stock_minimo, lugar_elaboracion, precio_compra, estado, fec_registro) ");
			sql_insert_user.append("VALUES (?,?,?,?,?,?,?,?,?,?,?) ");
			java.util.Date dt = new java.util.Date();
		    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String fechaHora = sdf.format(dt);	        	    
			Object[] params = new Object[] {
			insumo.getSucursal().getCodigo(), insumo.getMedida(),insumo.getNombre(),insumo.getCantidad(),
			insumo.getCantidadReal(), insumo.getMerma(), insumo.getStockMinimo(), insumo.getLugarElaboracion(), insumo.getPrecioCompra(), insumo.getEstado(), fechaHora };				
			jdbcTemplate.update(sql_insert_user.toString(), params);
			resultado = Boolean.TRUE;
			return resultado;
	}

	@Override
	public boolean delete(Insumo insumo) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("DELETE FROM tbl_insumo ");
		sql_insert_user.append("WHERE codigo = ? ");
		java.util.Date dt = new java.util.Date();
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String fechaHora = sdf.format(dt);	        	    
		Object[] params = new Object[] { insumo.getCodigo() };				
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean update(Insumo insumo) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE INTO tbl_insumo ");
		sql_insert_user.append("SET medida=?, nombre=?, cantidad=?, merma=?, cantidad_real = ?, precio_compra=? ");
		sql_insert_user.append("stock_minimo=?, estado=? ");
		sql_insert_user.append("WHERE codigo = ? ");
		Object[] params = new Object[] {
		insumo.getMedida(),insumo.getNombre(),insumo.getCantidad(), insumo.getMerma(),
		insumo.getCantidadReal(), insumo.getPrecioCompra(), insumo.getStockMinimo(), insumo.getEstado(), insumo.getCodigo() };				
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean updateStatus(Insumo insumo) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE INTO tbl_insumo ");
		sql_insert_user.append("SET estado=? ");
		sql_insert_user.append("WHERE codigo = ? ");
		Object[] params = new Object[] { insumo.getEstado(), insumo.getCodigo() };				
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean updateInsumo(Preparacion preparacion) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_insumo ");
		sql_insert_user.append("SET cantidad_real = cantidad_real - ? ");
		sql_insert_user.append("WHERE codigo = ? ");
		Object[] params = new Object[] { preparacion.getCantidad(), preparacion.getCodigoInsumo() };				
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

}
