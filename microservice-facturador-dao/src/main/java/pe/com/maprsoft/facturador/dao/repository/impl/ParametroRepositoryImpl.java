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
import pe.com.maprsoft.facturador.dao.repository.ParametroRepository;
import pe.com.maprsoft.facturador.dao.repository.SalonRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.MozoMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.ParametroMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.SalonMapper;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.PagoDetalle;
import pe.com.maprsoft.facturador.model.Parametro;
import pe.com.maprsoft.facturador.model.Pedido;
import pe.com.maprsoft.facturador.model.Salon;


@SuppressWarnings("all")
@Repository
public class ParametroRepositoryImpl implements ParametroRepository {

	private final Logger logger = LoggerFactory.getLogger(ParametroRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Parametro> list(Parametro parametro) throws Exception {
		List <Parametro> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo,codigo_restaurante_local,grupo,tipo,cod,val,val_abre,estado ");		
		sql_find_user.append("from tbl_parametros where codigo_restaurante_local = ? ");
		sql_find_user.append("and grupo = ? and tipo = ? and estado = 1 ");
		sql_find_user.append("order by val ");
		Object[] params = new Object[] { parametro.getSucursal().getCodigo(), parametro.getGrupo(), parametro.getTipo() };
		lista = (List <Parametro>)jdbcTemplate.query(sql_find_user.toString(), params, new ParametroMapper());
		return lista;
	}

	@Override
	public List<Parametro> listAll(Parametro parametro) throws Exception {
		List <Parametro> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo,codigo_restaurante_local,grupo,tipo,cod,val,val_abre,estado ");		
		sql_find_user.append("from tbl_parametros where codigo_restaurante_local = ? ");
		sql_find_user.append("and grupo = ? and tipo = ?  ");
		sql_find_user.append("order by val ");
		Object[] params = new Object[] { parametro.getSucursal().getCodigo(), parametro.getGrupo(), parametro.getTipo() };
		lista = (List <Parametro>)jdbcTemplate.query(sql_find_user.toString(), params, new ParametroMapper());
		return lista;
	}

	@Override
	public boolean insert(Parametro parametro) throws Exception {
		
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_parametros ");
		sql_insert_user.append("(codigo_restaurante_local, grupo, tipo, cod, val, val_abre, estado) ");
		sql_insert_user.append("VALUES (?,?,?,?,?,?,?) ");
        	    
		Object[] params = new Object[] {  parametro.getSucursal().getCodigo(), 
		parametro.getGrupo(), parametro.getTipo(), getCode(parametro), parametro.getValor(),
		parametro.getValorBreve(), parametro.getEstado() };
			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean insertCode(Parametro parametro) throws Exception {
		
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_parametros ");
		sql_insert_user.append("(codigo_restaurante_local, grupo, tipo, cod, val, val_abre, estado) ");
		sql_insert_user.append("VALUES (?,?,?,?,?,?,?) ");
        	    
		Object[] params = new Object[] {  parametro.getSucursal().getCodigo(), 
		parametro.getGrupo(), parametro.getTipo(), parametro.getCode(), parametro.getValor(),
		parametro.getValorBreve(), parametro.getEstado() };
			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	public Integer getCode(Parametro parametro) throws Exception {
		Integer bean = new Integer(0);
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("SELECT max(cod)+1 as id from tbl_parametros ");
		sql_find_user.append("where codigo_restaurante_local = ? and grupo = ?  and tipo = ? ");
		
		Object[] params = new Object[] {  parametro.getSucursal().getCodigo(), 
				parametro.getGrupo(), parametro.getTipo() };
		bean = jdbcTemplate.queryForObject(sql_find_user.toString(), params, Integer.class);
  		return bean;
	}
	

	@Override
	public boolean delete(Parametro parametro) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("delete from tbl_parametros ");
		sql_insert_user.append("WHERE codigo = ? ");        	    
		Object[] params = new Object[] { parametro.getCodigo() };			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean update(Parametro parametro) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_parametros ");
		sql_insert_user.append("SET cod = ?, val = ?, val_abre = ? , estado = ?  ");
		sql_insert_user.append("WHERE codigo = ? ");
		Object[] params = new Object[] { 
		parametro.getCode(), parametro.getValor(), parametro.getValorBreve(),
		parametro.getEstado(), parametro.getCodigo() };			
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean updateStatus(Parametro parametro) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Parametro> listGeneric(Parametro parametro) throws Exception {
		List <Parametro> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo,codigo_restaurante_local,grupo,tipo,cod,val,val_abre,estado ");		
		sql_find_user.append("from tbl_parametros where ");
		sql_find_user.append("grupo = ? and tipo = ? and estado = 1 ");
		sql_find_user.append("order by val ");
		Object[] params = new Object[] { parametro.getGrupo(), parametro.getTipo() };
		lista = (List <Parametro>)jdbcTemplate.query(sql_find_user.toString(), params, new ParametroMapper());
		return lista;
	}

	
}
