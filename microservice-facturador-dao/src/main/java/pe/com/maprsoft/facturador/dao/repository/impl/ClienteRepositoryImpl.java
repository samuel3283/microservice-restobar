package pe.com.maprsoft.facturador.dao.repository.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.maprsoft.facturador.dao.repository.ClienteRepository;
import pe.com.maprsoft.facturador.dao.repository.MozoRepository;
import pe.com.maprsoft.facturador.dao.repository.SalonRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.ClienteMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.MozoMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.SalonMapper;
import pe.com.maprsoft.facturador.model.Cliente;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.PagoDetalle;
import pe.com.maprsoft.facturador.model.Salon;


@SuppressWarnings("all")
@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

	private final Logger logger = LoggerFactory.getLogger(ClienteRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Cliente> list(Cliente cliente) throws Exception {
		List <Cliente> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, nombre, direccion, telefono, referencia ");		
		sql_find_user.append("from tbl_cliente where nombre like '%?%' and estado = 1 ");
		sql_find_user.append("order by nombre ");
		Object[] params = new Object[] { cliente.getNombre() };
		lista = (List <Cliente>)jdbcTemplate.query(sql_find_user.toString(), params, new ClienteMapper());
		return lista;
	}

	@Override
	public Integer insert(Cliente cliente) throws Exception {
		
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_cliente ");
		sql_insert_user.append("(nombre, direccion, referencia, telefono, fec_registro) ");
		sql_insert_user.append("VALUES (?,?,?,?,?) ");

		java.util.Date dt = new java.util.Date();
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String fechaHora = sdf.format(dt);

	    Object[] params = new Object[] {
	    cliente.getNombre(), cliente.getDireccion(), cliente.getReferencia(), cliente.getTelefono(), fechaHora };
			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		Integer valor = getIdCliente(cliente);		
		return valor;
	}

	@Override
	public boolean delete(Cliente cliente) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Cliente cliente) throws Exception {
		
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_cliente ");
		sql_insert_user.append("SET nombre=?, direccion=?, referencia=?, telefono=? ");
		sql_insert_user.append("WHERE codigo = ? ");

	    Object[] params = new Object[] {
	    cliente.getNombre(), cliente.getDireccion(), cliente.getReferencia(), cliente.getTelefono(), cliente.getCodigo() };
	    
		jdbcTemplate.update(sql_insert_user.toString(), params);
		return true;
	}

	@Override
	public Integer getIdCliente(Cliente cliente) throws Exception {
		Integer bean = new Integer(0);
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("SELECT max(codigo) as id from tbl_cliente ");
		sql_find_user.append("where telefono = ? ");
		
		Object[] params = new Object[] { cliente.getTelefono() };
		bean = jdbcTemplate.queryForObject(sql_find_user.toString(), params, Integer.class);
  		return bean;
	}

	
}
