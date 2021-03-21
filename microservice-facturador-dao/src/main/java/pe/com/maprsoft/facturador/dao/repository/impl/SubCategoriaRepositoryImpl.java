package pe.com.maprsoft.facturador.dao.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.maprsoft.facturador.dao.repository.SubCategoriaRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.SubCategoriaMapper;
import pe.com.maprsoft.facturador.model.SubCategoria;

@SuppressWarnings("all")
@Repository
public class SubCategoriaRepositoryImpl implements SubCategoriaRepository {
	
	private final Logger logger = LoggerFactory.getLogger(SubCategoriaRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<SubCategoria> list(Integer idCategoria) throws Exception {
		List <SubCategoria> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select sc.codigo, sc.nombre, sc.estado ");		
		sql_find_user.append("from tbl_subcategoria sc where  ");
		sql_find_user.append("sc.codigo_categoria = ? ");
		sql_find_user.append("order by 2 ");
		
		Object[] params = new Object[] { idCategoria };
		lista = (List <SubCategoria>)jdbcTemplate.query(sql_find_user.toString(), params, new SubCategoriaMapper());
		return lista;
	}

	@Override
	public boolean update(SubCategoria subCategoria) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_subcategoria ");
		sql_insert_user.append("SET nombre=?, estado=? ");
		sql_insert_user.append("WHERE codigo = ? ");

		java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaHora = sdf.format(dt);
        
		Object[] params = new Object[] { subCategoria.getNombre(), 
				subCategoria.getEstado(), subCategoria.getId()	};	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;	
	}

	@Override
	public boolean delete(SubCategoria subCategoria) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("DELETE FROM tbl_subcategoria ");
		sql_insert_user.append("WHERE codigo = ? ");        
		Object[] params = new Object[] { subCategoria.getId() };	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;	
	}	

	@Override
	public boolean deleteAll(Integer idCategoria) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("DELETE FROM tbl_subcategoria ");
		sql_insert_user.append("WHERE codigo_categoria = ? ");        
		Object[] params = new Object[] { idCategoria };	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;	
	}	

	@Override
	public boolean insert(SubCategoria subCategoria) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_subcategoria ");
		sql_insert_user.append("(codigo_categoria, nombre, estado, fec_registro) ");
		sql_insert_user.append("VALUES (?,?,?,?) ");

		java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaHora = sdf.format(dt);
        
		Object[] params = new Object[] { subCategoria.getCodigoCategoria(), subCategoria.getNombre(), 
				subCategoria.getEstado(), fechaHora	};	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

}
