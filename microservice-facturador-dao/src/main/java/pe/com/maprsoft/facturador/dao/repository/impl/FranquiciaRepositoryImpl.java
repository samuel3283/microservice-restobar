package pe.com.maprsoft.facturador.dao.repository.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.maprsoft.facturador.dao.repository.FranquiciaRepository;
import pe.com.maprsoft.facturador.dao.repository.MozoRepository;
import pe.com.maprsoft.facturador.dao.repository.FranquiciaRepository;
import pe.com.maprsoft.facturador.dao.repository.SalonRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.FranquiciaMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.MozoMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.SalonMapper;
import pe.com.maprsoft.facturador.model.Franquicia;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.PagoDetalle;
import pe.com.maprsoft.facturador.model.Franquicia;
import pe.com.maprsoft.facturador.model.Salon;


@SuppressWarnings("all")
@Repository
public class FranquiciaRepositoryImpl implements FranquiciaRepository {

	private final Logger logger = LoggerFactory.getLogger(FranquiciaRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Franquicia> list() throws Exception {
		List <Franquicia> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, nombre, razon_social, ruc, direccion, estado ");		
		sql_find_user.append("from tbl_franquicia where estado = 1 ");
		sql_find_user.append("order by razon_social ");
		lista = (List <Franquicia>)jdbcTemplate.query(sql_find_user.toString(), new FranquiciaMapper());
		return lista;
	}

	@Override
	public List<Franquicia> listAll() throws Exception {
		List <Franquicia> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, nombre, razon_social, ruc, direccion, estado ");		
		sql_find_user.append("from tbl_franquicia ");
		sql_find_user.append("order by razon_social ");
		lista = (List <Franquicia>)jdbcTemplate.query(sql_find_user.toString(), new FranquiciaMapper());
		return lista;
	}

	@Override
	public boolean insert(Franquicia franquicia) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_franquicia ");
		sql_insert_user.append("(nombre, razon_social, ruc, direccion, estado, fec_registro) ");
		sql_insert_user.append("VALUES (?,?,?,?,?,?) ");
		
		java.util.Date dt = new java.util.Date();
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String fechaHora = sdf.format(dt);
        	    
		Object[] params = new Object[] {
		franquicia.getNombre(),	franquicia.getRazonSocial(), franquicia.getRuc(), franquicia.getDireccion(),
		franquicia.getEstado(), fechaHora };
		
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean delete(Franquicia franquicia) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("delete from tbl_franquicia ");
		sql_insert_user.append("WHERE codigo = ? ");        	    
		Object[] params = new Object[] { franquicia.getCodigo() };			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean update(Franquicia franquicia) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_franquicia ");
		sql_insert_user.append("SET nombre = ?, razon_social = ?, ruc = ?, direccion = ?, estado = ?  ");
		sql_insert_user.append("WHERE codigo = ? ");
		Object[] params = new Object[] {  franquicia.getNombre(),
				franquicia.getRazonSocial(), franquicia.getRuc(), franquicia.getDireccion(),
				franquicia.getEstado(), franquicia.getCodigo() };			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean updateStatus(Franquicia franquicia) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_franquicia ");
		sql_insert_user.append("SET estado = ?  ");
		sql_insert_user.append("WHERE codigo = ? ");
		Object[] params = new Object[] {  franquicia.getEstado(), franquicia.getCodigo() };			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	/*
	}
	public boolean updateStatus(Franquicia Franquicia) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_Franquicia ");
		sql_insert_user.append("SET estado = ?  ");
		sql_insert_user.append("WHERE codigo = ? ");
		Object[] params = new Object[] { Franquicia.getEstado(), Franquicia.getCodigo() };			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	
*/
}
