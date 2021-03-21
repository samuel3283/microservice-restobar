package pe.com.maprsoft.facturador.dao.repository.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.maprsoft.facturador.dao.repository.MozoRepository;
import pe.com.maprsoft.facturador.dao.repository.NumeracionRepository;
import pe.com.maprsoft.facturador.dao.repository.SalonRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.MozoMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.NumeracionMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.SalonMapper;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Numeracion;
import pe.com.maprsoft.facturador.model.PagoDetalle;
import pe.com.maprsoft.facturador.model.Pedido;
import pe.com.maprsoft.facturador.model.Salon;


@SuppressWarnings("all")
@Repository
public class NumeracionRepositoryImpl implements NumeracionRepository {

	private final Logger logger = LoggerFactory.getLogger(NumeracionRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Numeracion> list(Integer sucursal) throws Exception {
		List <Numeracion> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_restaurante_local, periodo, documento, serie, ");		
		sql_find_user.append("rango_inicio, rango_fin, correlativo, ");	
		sql_find_user.append("DATE_FORMAT(fec_registro,'%d/%m/%Y') fec_reg, estado ");
		sql_find_user.append("from tbl_numeracion where codigo_restaurante_local = ? ");
		sql_find_user.append("order by 1 ");
		Object[] params = new Object[] { sucursal };
		lista = (List <Numeracion>)jdbcTemplate.query(sql_find_user.toString(), params, new NumeracionMapper());
		return lista;
	}

	@Override
	public boolean delete(Numeracion numeracion) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("DELETE FROM tbl_numeracion ");
		sql_insert_user.append("WHERE codigo = ? ");
        	    
		Object[] params = new Object[] { numeracion.getCodigo() };			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	
	@Override
	public boolean update(Numeracion numeracion) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_numeracion ");
		sql_insert_user.append("SET periodo = ?, documento = ?, serie = ?, ");
		sql_insert_user.append("rango_inicio = ?, rango_fin = ?, correlativo = ?, estado = ? ");
		sql_insert_user.append("WHERE codigo = ? ");

		java.util.Date dt = new java.util.Date();
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String fechaHora = sdf.format(dt);
        	    
		Object[] params = new Object[] {
				numeracion.getPeriodo(), numeracion.getDocumento(), numeracion.getSerie(), 
				numeracion.getInicio(),	numeracion.getFin(), numeracion.getCorrelativo(), 
				numeracion.getEstado(), numeracion.getCodigo() };
			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean insert(Numeracion numeracion) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_numeracion ");
		sql_insert_user.append("(codigo_restaurante_local, periodo, documento, serie, rango_inicio, rango_fin, correlativo, estado, fec_registro) ");
		sql_insert_user.append("VALUES (?,?,?,?,?,?,?,?,?) ");

		java.util.Date dt = new java.util.Date();
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String fechaHora = sdf.format(dt);
        	    
		Object[] params = new Object[] {
				numeracion.getSucursal().getCodigo(), numeracion.getPeriodo(),
				numeracion.getDocumento(), numeracion.getSerie(), numeracion.getInicio(), 
				numeracion.getFin(), numeracion.getCorrelativo(), numeracion.getEstado(), fechaHora };
			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public Numeracion get(Numeracion numeracion) throws Exception {
		List <Numeracion> lista = null;
		Numeracion bean = new Numeracion();
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_restaurante_local, periodo, documento, serie, ");		
		sql_find_user.append("rango_inicio, rango_fin, correlativo, ");	
		sql_find_user.append("DATE_FORMAT(fec_registro,'%d/%m/%Y') fec_reg, estado ");
		sql_find_user.append("from tbl_numeracion ");
		sql_find_user.append("where codigo_restaurante_local = ? and documento = ? and estado = ? ");
		Object[] params = new Object[] { numeracion.getSucursal().getCodigo(), numeracion.getDocumento(), numeracion.getEstado() };
		lista = (List <Numeracion>)jdbcTemplate.query(sql_find_user.toString(), params, new NumeracionMapper());
    	if(lista!=null && lista.size()>0) {    		
    		bean = new Numeracion();
    		bean = lista.get(0);
    	}	    	

		return bean;
	}

	@Override
	public boolean updateCorrelatibo(Numeracion numeracion) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_numeracion ");
		sql_insert_user.append("SET correlativo = correlativo + 1 ");
		sql_insert_user.append("where codigo_restaurante_local = ? and documento = ? and estado = ? ");
		Object[] params = new Object[] { numeracion.getSucursal().getCodigo(), numeracion.getDocumento(), numeracion.getEstado() };
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public List<Numeracion> listActivos(Integer sucursal) throws Exception {
		List <Numeracion> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_restaurante_local, periodo, documento, serie, ");		
		sql_find_user.append("rango_inicio, rango_fin, correlativo, ");	
		sql_find_user.append("DATE_FORMAT(fec_registro,'%d/%m/%Y') fec_reg, estado ");
		sql_find_user.append("from tbl_numeracion where codigo_restaurante_local = ? and estado = 1 ");
		sql_find_user.append("order by 1 ");
		Object[] params = new Object[] { sucursal };
		lista = (List <Numeracion>)jdbcTemplate.query(sql_find_user.toString(), params, new NumeracionMapper());
		return lista;
	}

	

}
