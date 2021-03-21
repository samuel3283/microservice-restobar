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
import pe.com.maprsoft.facturador.dao.repository.RestauranteRepository;
import pe.com.maprsoft.facturador.dao.repository.SalonRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.MozoMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.RestauranteMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.SalonMapper;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.PagoDetalle;
import pe.com.maprsoft.facturador.model.Restaurante;
import pe.com.maprsoft.facturador.model.Salon;


@SuppressWarnings("all")
@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

	private final Logger logger = LoggerFactory.getLogger(RestauranteRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Restaurante> list() throws Exception {
		List <Restaurante> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_franquicia, nombre, razon_social, ruc, direccion, estado ");		
		sql_find_user.append("from tbl_restaurante where estado = 1 ");
		sql_find_user.append("order by nombre ");
		lista = (List <Restaurante>)jdbcTemplate.query(sql_find_user.toString(), new RestauranteMapper());
		return lista;
	}

	@Override
	public List<Restaurante> listAll() throws Exception {
		List <Restaurante> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_franquicia, nombre, razon_social, ruc, direccion, estado ");		
		sql_find_user.append("from tbl_restaurante ");
		sql_find_user.append("order by nombre ");
		lista = (List <Restaurante>)jdbcTemplate.query(sql_find_user.toString(), new RestauranteMapper());
		return lista;
	}

	@Override
	public boolean insert(Restaurante restaurante) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_restaurante ");
		sql_insert_user.append("(codigo_franquicia, nombre, razon_social, ruc, direccion, estado, fec_registro) ");
		sql_insert_user.append("VALUES (?,?,?,?,?,?,?) ");

		java.util.Date dt = new java.util.Date();
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String fechaHora = sdf.format(dt);
        	    
		Object[] params = new Object[] {
		restaurante.getFranquicia().getCodigo(), restaurante.getNombre(),
		restaurante.getRazon_social(), restaurante.getRuc(), restaurante.getDireccion(),
		restaurante.getEstado(), fechaHora };
		
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean delete(Restaurante restaurante) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("delete from tbl_restaurante ");
		sql_insert_user.append("WHERE codigo = ? ");        	    
		Object[] params = new Object[] { restaurante.getCodigo() };			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean update(Restaurante restaurante) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_restaurante ");
		sql_insert_user.append("SET nombre = ?, razon_social = ?, ruc = ?, direccion = ?, estado = ?, codigo_franquicia = ?  ");
		sql_insert_user.append("WHERE codigo = ? ");
		Object[] params = new Object[] {  restaurante.getNombre(),
				restaurante.getRazon_social(), restaurante.getRuc(), restaurante.getDireccion(),
				restaurante.getEstado(), restaurante.getFranquicia().getCodigo(), restaurante.getCodigo() };			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean updateStatus(Restaurante restaurante) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_restaurante ");
		sql_insert_user.append("SET estado = ?  ");
		sql_insert_user.append("WHERE codigo = ? ");
		Object[] params = new Object[] { restaurante.getEstado(), restaurante.getCodigo() };			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	
	/*
	}

	@Override
	public boolean update(Mozo mozo) throws Exception {
	}

	@Override
	public boolean updateStatus(Mozo mozo) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_mozo ");
		sql_insert_user.append("SET estado = ?  ");
		sql_insert_user.append("WHERE codigo = ? ");
		Object[] params = new Object[] {  mozo.getEstado(), mozo.getCodigo() };			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;	
	}

	@Override
	public List<Mozo> listAll(Integer sucursal) throws Exception {
		List <Mozo> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_restaurante_local, nombre, turno, estado ");		
		sql_find_user.append("from tbl_mozo where codigo_restaurante_local = ? ");
		sql_find_user.append("order by 1 ");
		Object[] params = new Object[] { sucursal };
		lista = (List <Mozo>)jdbcTemplate.query(sql_find_user.toString(), params, new MozoMapper());
		return lista;
	}
*/
}
