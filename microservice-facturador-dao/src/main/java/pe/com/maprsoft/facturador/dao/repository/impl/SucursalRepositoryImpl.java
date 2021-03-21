package pe.com.maprsoft.facturador.dao.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.maprsoft.facturador.dao.repository.SalonRepository;
import pe.com.maprsoft.facturador.dao.repository.SucursalRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.SalonMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.SucursalAllMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.SucursalMapper;
import pe.com.maprsoft.facturador.model.Pago;
import pe.com.maprsoft.facturador.model.Restaurante;
import pe.com.maprsoft.facturador.model.Salon;
import pe.com.maprsoft.facturador.model.Sucursal;


@SuppressWarnings("all")
@Repository
public class SucursalRepositoryImpl implements SucursalRepository {

	private final Logger logger = LoggerFactory.getLogger(SucursalRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Sucursal> listByRestaurante(Restaurante restaurante) throws Exception {
		List <Sucursal> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_restaurante, nombre, estado, direccion ");		
		sql_find_user.append("from tbl_restaurante_local ");
		sql_find_user.append("where codigo_restaurante = ?  "); //and estado = 1
		sql_find_user.append("order by nombre ");
		Object[] params = new Object[] { restaurante.getCodigo() };
		lista = (List <Sucursal>)jdbcTemplate.query(sql_find_user.toString(), params, new SucursalMapper());
		return lista;
	}

	@Override
	public List<Sucursal> list() throws Exception {
		List <Sucursal> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_restaurante, nombre, estado, direccion ");		
		sql_find_user.append("from tbl_restaurante_local ");
		sql_find_user.append("where estado = ? ");
		sql_find_user.append("order by nombre ");
		Integer estado = 1;
		Object[] params = new Object[] { estado };
		lista = (List <Sucursal>)jdbcTemplate.query(sql_find_user.toString(), params, new SucursalMapper());
		return lista;
	}

	@Override
	public List<Sucursal> listAll() throws Exception {
		List <Sucursal> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_restaurante, nombre, estado, direccion ");		
		sql_find_user.append("from tbl_restaurante_local ");
		sql_find_user.append("order by nombre ");
		lista = (List <Sucursal>)jdbcTemplate.query(sql_find_user.toString(), new SucursalMapper());
		return lista;
	}

	@Override
	public Sucursal getAll(Integer codigoLocal) throws Exception {
		List <Sucursal> lista = null;
		Sucursal bean = new Sucursal();
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select r.codigo_franquicia cod_franq, f.razon_social razon_franq, f.ruc ruc_franq, f.direccion direccion_franq, ");		
		sql_find_user.append("r.codigo cod_rest, r.razon_social razon_rest, r.ruc ruc_rest, r.direccion direccion_rest, ");
		sql_find_user.append("l.codigo cod_local, l.nombre nombre_local, l.direccion direccion_local ");
		sql_find_user.append("from tbl_restaurante_local l ");
		sql_find_user.append("left join tbl_restaurante r on l.codigo_restaurante = r.codigo ");
		sql_find_user.append("left join tbl_franquicia f on f.codigo = r.codigo_franquicia ");
		sql_find_user.append("where l.codigo = ? ");
		sql_find_user.append("order by r.razon_social, l.nombre ");		
		Object[] params = new Object[] { codigoLocal };
		lista = (List <Sucursal>)jdbcTemplate.query(sql_find_user.toString(), params, new SucursalAllMapper());
    	if(lista!=null && lista.size()>0) {    		
    		bean = new Sucursal();
    		bean = lista.get(0);
    	}	    	
		return bean;
	}

	@Override
	public boolean update(Sucursal sucursal) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_restaurante_local ");
		sql_insert_user.append("SET nombre = ?, estado = ?, direccion = ?  ");
		sql_insert_user.append("WHERE codigo = ? ");
		Object[] params = new Object[] {  sucursal.getNombre(), sucursal.getEstado(), sucursal.getDireccion(), sucursal.getCodigo() };			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean insert(Sucursal sucursal) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_restaurante_local ");
		sql_insert_user.append("(codigo_restaurante, nombre, direccion, estado, fec_registro) ");
		sql_insert_user.append("VALUES (?,?,?,?,?) ");

		
		java.util.Date dt = new java.util.Date();
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String fechaHora = sdf.format(dt);
	    sucursal.setEstado(1);
		Object[] params = new Object[] { sucursal.getRestaurante().getCodigo(),  sucursal.getNombre(), 
				sucursal.getDireccion(), sucursal.getEstado(), fechaHora };			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean delete(Sucursal sucursal) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("DELETE FROM  tbl_restaurante_local ");
		sql_insert_user.append("WHERE codigo = ? ");
		Object[] params = new Object[] { sucursal.getCodigo() };			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

}
