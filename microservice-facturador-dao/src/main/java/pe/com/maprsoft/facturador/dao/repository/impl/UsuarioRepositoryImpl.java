package pe.com.maprsoft.facturador.dao.repository.impl;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import pe.com.maprsoft.facturador.dao.repository.UsuarioRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.UsuarioMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.UsuarioOnlyMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.UsuarioSucursalMapper;
import pe.com.maprsoft.facturador.model.Usuario;


@SuppressWarnings("all")
@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

	private final Logger logger = LoggerFactory.getLogger(UsuarioRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;

	@Override
	public void insert(Usuario usuario) throws Exception {
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_usuario ");
		sql_insert_user.append("(codigo_restaurante_local,usuario,nombre,apellido,email,password, ");
		sql_insert_user.append("tipodoc,numdoc,telefono,perfil,fec_registro,estado) ");
		sql_insert_user.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ");
						  
		java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaHora = sdf.format(dt);
                
		Object[] params = new Object[] {
		usuario.getCodigoRestauranteLocal(), usuario.getUsuario(), usuario.getNombre(), usuario.getApellido(),
		usuario.getEmail(), usuario.getPassword(), 
		usuario.getTipoDocumento(), usuario.getNumDocumento(), usuario.getTelefono(), usuario.getPerfil(),
		fechaHora, usuario.getEstado()
		};
		jdbcTemplate.update(sql_insert_user.toString(), params);
	}

	@Override
	public Usuario login(Usuario usuario) throws Exception {
		List <Usuario> lista = null;
		Usuario bean = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("SELECT u.codigo, u.codigo_restaurante_local, u.nombre, u.apellido, u.email, u.perfil, ");
		sql_find_user.append("u.tipodoc, u.numdoc, u.telefono, u.estado, u.usuario, ");
		sql_find_user.append("r.codigo cod_rest, r.nombre nombre_rest, r.razon_social, r.ruc, r.direccion direccion_rest, ");
		sql_find_user.append("l.codigo cod_local, l.nombre nombre_local, l.direccion direccion_local ");
		sql_find_user.append("FROM tbl_usuario u ");
		sql_find_user.append("INNER JOIN tbl_restaurante_local l ON l.codigo = u.codigo_restaurante_local ");
		sql_find_user.append("INNER JOIN tbl_restaurante r ON r.codigo = l.codigo_restaurante ");
		sql_find_user.append("WHERE u.usuario = ? and u.password = ? ");
		//sql_find_user.append("and u.codigo_restaurante_local = ? ");

  		usuario.setEstado(1);
  		Object[] params = new Object[] {
  				usuario.getUsuario(), usuario.getPassword() }; //, usuario.getCodigoRestauranteLocal() };
  		
    	lista = (List <Usuario> )jdbcTemplate.query(sql_find_user.toString(),params, new UsuarioMapper());        	
    	if(lista!=null && lista.size()>0){
    		//bean = new Usuario();
    		bean = lista.get(0);
    		//bean = getUser(lista, usuario);
    	}

		return bean;		
	}
	
	public Usuario getUser(List <Usuario> lista, Usuario usuario) {
		
		for(Usuario bean: lista) {
			
			if(bean.getPerfil().equals("Z")) {
				return bean;
			} else {
				if(bean.getCodigoRestauranteLocal().intValue() == usuario.getCodigoRestauranteLocal().intValue())
				return bean;				
			}
			
		}
		
		return null;
	}
	

	@Override
	public boolean update(Usuario usuario) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE  tbl_usuario ");
		sql_insert_user.append("SET nombre = ?, apellido = ?, estado = ?, tipodoc = ?, numdoc = ?, perfil = ?  ");
		sql_insert_user.append("WHERE codigo = ? ");

		Object[] params = new Object[] { usuario.getNombre(), usuario.getApellido(), usuario.getEstado(), 
				usuario.getTipoDocumento(), usuario.getNumDocumento(), usuario.getPerfil(),				
				usuario.getCodigo() };	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;		
	}

	@Override
	public List<Usuario> listAdmin(Usuario usuario) throws Exception {
		List <Usuario> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		/*
		sql_find_user.append("SELECT codigo,codigo_restaurante_local,usuario,nombre,apellido,email, ");
  		sql_find_user.append("tipodoc,numdoc,telefono,perfil, DATE_FORMAT(fec_registro,'%d/%m/%Y %H:%i:%s') fec_reg, estado, estado_tbl ");
  		sql_find_user.append("FROM tbl_usuario ");
  		sql_find_user.append("WHERE perfil = 'A' ");
  		*/
  		sql_find_user.append("SELECT u.codigo,u.codigo_restaurante_local,u.usuario,u.nombre,u.apellido,u.email, ");
  		sql_find_user.append("u.tipodoc,u.numdoc,u.telefono,u.perfil, DATE_FORMAT(u.fec_registro,'%d/%m/%Y %H:%i:%s') fec_reg, u.estado, u.estado_tbl, ");
  		sql_find_user.append("r.codigo codigo_rest,r.nombre nombre_rest ");
  		sql_find_user.append("FROM tbl_usuario  u ");
  		sql_find_user.append("LEFT JOIN tbl_restaurante_local l ON l.codigo = u.codigo_restaurante_local ");
  		sql_find_user.append("LEFT JOIN tbl_restaurante r ON r.codigo = l.codigo_restaurante ");
  		sql_find_user.append("WHERE perfil = 'A' ");
  		
  		Object[] params = new Object[] { usuario.getCodigoRestauranteLocal() };
    	lista = (List <Usuario> )jdbcTemplate.query(sql_find_user.toString(), new UsuarioSucursalMapper());        	
		return lista;
	}

	@Override
	public List<Usuario> list(Usuario usuario) throws Exception {
		List <Usuario> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
  		sql_find_user.append("SELECT codigo,codigo_restaurante_local,usuario,nombre,apellido,email, ");
  		sql_find_user.append("tipodoc,numdoc,telefono,perfil, DATE_FORMAT(fec_registro,'%d/%m/%Y %H:%i:%s') fec_reg, estado, estado_tbl ");
  		sql_find_user.append("FROM tbl_usuario ");
  		sql_find_user.append("WHERE codigo_restaurante_local = ?  and estado = 1 ");
  		
  		
  		Object[] params = new Object[] { usuario.getCodigoRestauranteLocal() };
    	lista = (List <Usuario> )jdbcTemplate.query(sql_find_user.toString(),params, new UsuarioOnlyMapper());        	
		return lista;
	}

	@Override
	public List<Usuario> listAll(Usuario usuario) throws Exception {
		List <Usuario> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
  		sql_find_user.append("SELECT codigo,codigo_restaurante_local,usuario,nombre,apellido,email, ");
  		sql_find_user.append("tipodoc,numdoc,telefono,perfil, DATE_FORMAT(fec_registro,'%d/%m/%Y %H:%i:%s') fec_reg, estado, estado_tbl ");
  		sql_find_user.append("FROM tbl_usuario ");
  		sql_find_user.append("WHERE codigo_restaurante_local = ? ");
  		
  		Object[] params = new Object[] { usuario.getCodigoRestauranteLocal() };
    	lista = (List <Usuario> )jdbcTemplate.query(sql_find_user.toString(),params, new UsuarioOnlyMapper());        	
		return lista;
	}

	@Override
	public Usuario get(Usuario usuario) throws Exception {
		List <Usuario> lista = null;
		Usuario bean = null;
		StringBuilder sql_find_user = new StringBuilder();
  		sql_find_user.append("SELECT codigo,codigo_restaurante_local,usuario,nombre,apellido,email, ");
  		sql_find_user.append("tipodoc,numdoc,telefono,perfil, DATE_FORMAT(fec_registro,'%d/%m/%Y %H:%i:%s') fec_reg, estado, estado_tbl ");
  		sql_find_user.append("FROM tbl_usuario ");
  		sql_find_user.append("WHERE codigo = ? ");

  		Object[] params = new Object[] { usuario.getCodigo() };
    	lista = (List <Usuario> )jdbcTemplate.query(sql_find_user.toString(),params, new UsuarioOnlyMapper());        	
    	if(lista!=null && lista.size()>0){
    		bean = new Usuario();
    		bean = lista.get(0);
    	}

		return bean;	
	}

	
	
	@Override
	public boolean insertMozo(Usuario usuario) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_usuario ");
		sql_insert_user.append("(codigo_restaurante_local,usuario,nombre,apellido,email,password, ");
		sql_insert_user.append("tipodoc,numdoc,telefono,perfil,fec_registro,estado) ");
		sql_insert_user.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ");
						 
		java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaHora = sdf.format(dt);
                
		Object[] params = new Object[] {
		usuario.getCodigoRestauranteLocal(), usuario.getUsuario(), usuario.getNombre(), usuario.getApellido(),
		usuario.getEmail(), usuario.getPassword(), 
		usuario.getTipoDocumento(), usuario.getNumDocumento(), usuario.getTelefono(), usuario.getPerfil(),
		fechaHora, usuario.getEstado()
		};
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;		
	}

	@Override
	public boolean updateMozo(Usuario usuario) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE  tbl_usuario ");
		sql_insert_user.append("SET nombre = ?, apellido = ?, estado_tbl = ? ");
		sql_insert_user.append("WHERE codigo = ? ");
				  
		Object[] params = new Object[] { usuario.getNombre(), usuario.getApellido(), usuario.getEstado(), usuario.getCodigo() };	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;		
	}

	@Override
	public boolean updateStatusMozo(Usuario usuario) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE  tbl_usuario ");
		sql_insert_user.append("SET estado_tbl = ? ");
		sql_insert_user.append("WHERE codigo = ? ");
				  
		Object[] params = new Object[] { usuario.getEstadoTbl(), usuario.getCodigo() };	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;		
	}

	@Override
	public List<Usuario> listMozo(Usuario usuario) throws Exception {
		List <Usuario> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
  		sql_find_user.append("SELECT codigo,codigo_restaurante_local,usuario,nombre,apellido,email, ");
  		sql_find_user.append("tipodoc,numdoc,telefono,perfil, DATE_FORMAT(fec_registro,'%d/%m/%Y %H:%i:%s') fec_reg, estado, estado_tbl ");
  		sql_find_user.append("FROM tbl_usuario ");
  		sql_find_user.append("WHERE codigo_restaurante_local = ? and perfil ='M' and estado_tbl = '1' ");
  		
  		Object[] params = new Object[] { usuario.getCodigoRestauranteLocal() };
    	lista = (List <Usuario> )jdbcTemplate.query(sql_find_user.toString(),params, new UsuarioOnlyMapper());        	
		return lista;
	}
	
	@Override
	public List<Usuario> listAllMozo(Usuario usuario) throws Exception {
		List <Usuario> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
  		sql_find_user.append("SELECT codigo,codigo_restaurante_local,usuario,nombre,apellido,email, ");
  		sql_find_user.append("tipodoc,numdoc,telefono,perfil, DATE_FORMAT(fec_registro,'%d/%m/%Y %H:%i:%s') fec_reg, estado, estado_tbl ");
  		sql_find_user.append("FROM tbl_usuario ");
  		sql_find_user.append("WHERE codigo_restaurante_local = ? and perfil ='M'  ");
  		
  		Object[] params = new Object[] { usuario.getCodigoRestauranteLocal()};
    	lista = (List <Usuario> )jdbcTemplate.query(sql_find_user.toString(),params, new UsuarioOnlyMapper());        	
		return lista;
	}

	@Override
	public boolean delete(Usuario usuario) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("DELETE from  tbl_usuario ");
		sql_insert_user.append("WHERE codigo = ? ");
		Object[] params = new Object[] { usuario.getCodigo() };	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;		
	}

	/*
	@Override
	public UsuarioEmpresa getUsuarioEmpresa(UsuarioEmpresa usuarioEmpresa) throws Exception {
		List <UsuarioEmpresa> lista = null;
		UsuarioEmpresa bean = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("SELECT U.CODIGO,U.RUC,U.NOMBRE,U.APELLIDO,U.EMAIL,U.PASSWORD,U.TIPDOC,U.NUMDOC,U.TELEFONO,  "); 
		sql_find_user.append("DATE_FORMAT(U.FEC_REGISTRO,'%d/%m/%Y %H:%i:%s') FEC_REG, U.PERFIL, E.RAZON_SOCIAL, U.ESTADO ");
  		sql_find_user.append("FROM tbl_usuario_empresa U LEFT JOIN tbl_empresa E ON U.RUC = E.RUC ");
  		sql_find_user.append("WHERE U.EMAIL=? AND U.ESTADO=? ");

  		usuario.setEstado(1);
  		Object[] params = new Object[] {
 				usuario.getEmail(), usuario.getEstado()
		};
    	lista = (List <UsuarioEmpresa> )jdbcTemplate.query(sql_find_user.toString(),params, new UsuarioEmpresaMapper());        	
    	if(lista!=null && lista.size()>0){
    		bean = new UsuarioEmpresa();
    		bean = lista.get(0);
    	}

		return bean;	
	}
*/

	
}
