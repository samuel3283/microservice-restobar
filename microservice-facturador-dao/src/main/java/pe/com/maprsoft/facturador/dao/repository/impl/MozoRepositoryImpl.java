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
import pe.com.maprsoft.facturador.dao.repository.SalonRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.MozoMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.SalonMapper;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.PagoDetalle;
import pe.com.maprsoft.facturador.model.Salon;


@SuppressWarnings("all")
@Repository
public class MozoRepositoryImpl implements MozoRepository {

	private final Logger logger = LoggerFactory.getLogger(MozoRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Mozo> list(Integer sucursal) throws Exception {
		List <Mozo> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_restaurante_local, nombre, turno, estado ");		
		sql_find_user.append("from tbl_mozo where codigo_restaurante_local = ? and estado = 1 ");
		sql_find_user.append("order by 1 ");
		Object[] params = new Object[] { sucursal };
		lista = (List <Mozo>)jdbcTemplate.query(sql_find_user.toString(), params, new MozoMapper());
		return lista;
	}
	
	@Override
	public boolean insert(Mozo mozo) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_mozo ");
		sql_insert_user.append("(codigo_restaurante_local, nombre, turno, estado, fec_registro) ");
		sql_insert_user.append("VALUES (?,?,?,?,?) ");

		java.util.Date dt = new java.util.Date();
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String fechaHora = sdf.format(dt);
        	    
		Object[] params = new Object[] {
		mozo.getSucursal().getCodigo(), mozo.getNombre(),
		mozo.getTurno(), mozo.getEstado(), fechaHora };
			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean delete(Mozo mozo) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("delete from tbl_mozo ");
		sql_insert_user.append("WHERE codigo = ? ");        	    
		Object[] params = new Object[] { mozo.getCodigo() };			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean update(Mozo mozo) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_mozo ");
		sql_insert_user.append("SET nombre = ?, estado = ?  ");
		sql_insert_user.append("WHERE codigo = ? ");
		Object[] params = new Object[] {  mozo.getNombre(), mozo.getEstado(), mozo.getCodigo() };			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
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

}
