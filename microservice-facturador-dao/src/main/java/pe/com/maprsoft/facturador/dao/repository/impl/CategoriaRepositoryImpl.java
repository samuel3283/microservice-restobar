package pe.com.maprsoft.facturador.dao.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.maprsoft.facturador.dao.repository.CategoriaRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.CategoriaAllMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.CategoriaMapper;
import pe.com.maprsoft.facturador.model.Categoria;
import pe.com.maprsoft.facturador.model.ListaCategoria;

@SuppressWarnings("all")
@Repository
public class CategoriaRepositoryImpl implements CategoriaRepository {

	private final Logger logger = LoggerFactory.getLogger(CategoriaRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Categoria> list(Integer sucursal) throws Exception {
		List <Categoria> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select ca.codigo, ca.nombre, ca.estado ");		
		sql_find_user.append("from tbl_categoria ca where  ");
		sql_find_user.append("ca.codigo_restaurante_local = ? ");
		sql_find_user.append("order by 2 ");
		
		Object[] params = new Object[] { sucursal };
		lista = (List <Categoria>)jdbcTemplate.query(sql_find_user.toString(), params, new CategoriaMapper());
		return lista;
	}

	@Override
	public List<ListaCategoria> lista(Integer sucursal) throws Exception {
		List <ListaCategoria> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("SELECT c.codigo, c.nombre, ");
		sql_find_user.append("s.codigo cod_subcategoria, s.nombre nombre_subcategoria, ");
		sql_find_user.append("p.codigo cod_producto, p.nombre nombre_producto, p.precio, ");
		sql_find_user.append("p.moneda ");
		
		sql_find_user.append("FROM tbl_categoria c ");
		sql_find_user.append("left join tbl_subcategoria s on  c.codigo = s.codigo_categoria ");
		sql_find_user.append("left join tbl_producto p on s.codigo = p.codigo_subcategoria ");
		sql_find_user.append("WHERE c.codigo_restaurante_local = ? ");
		sql_find_user.append("order by c.codigo, s.codigo, p.nombre ");		
		
		Object[] params = new Object[] { sucursal };
		lista = (List <ListaCategoria>)jdbcTemplate.query(sql_find_user.toString(), params, new CategoriaAllMapper());
		return lista;
	}

	@Override
	public List<ListaCategoria> listAll(Integer sucursal) throws Exception {
		List <ListaCategoria> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("SELECT c.codigo, c.nombre, ");
		sql_find_user.append("s.codigo cod_subcategoria, s.nombre nombre_subcategoria, ");
		sql_find_user.append("p.codigo cod_producto, p.nombre nombre_producto, p.precio, ");
		sql_find_user.append("p.moneda, p.lugar_elaboracion, p.tipo ");
		sql_find_user.append("FROM tbl_categoria c, tbl_subcategoria s, tbl_producto p ");
		sql_find_user.append("WHERE c.estado = 1 ");
		sql_find_user.append("AND s.estado = 1 ");
		sql_find_user.append("AND p.estado = 2 ");
		sql_find_user.append("AND c.codigo = s.codigo_categoria ");
		sql_find_user.append("AND s.codigo = p.codigo_subcategoria ");
		sql_find_user.append("AND c.codigo_restaurante_local = ? ");
		sql_find_user.append("order by c.codigo, s.codigo, p.nombre ");		
		
		Object[] params = new Object[] { sucursal };
		lista = (List <ListaCategoria>)jdbcTemplate.query(sql_find_user.toString(), params, new CategoriaAllMapper());
		return lista;
	}

	@Override
	public boolean insert(Categoria categoria) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_categoria ");
		sql_insert_user.append("(codigo_restaurante_local, nombre, estado, fec_registro) ");
		sql_insert_user.append("VALUES (?,?,?,?) ");

		java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaHora = sdf.format(dt);

		Object[] params = new Object[] { categoria.getSucursal().getCodigo(), categoria.getNombre(), 
				categoria.getEstado(), fechaHora	};	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean delete(Categoria categoria) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("DELETE FROM tbl_categoria ");
		sql_insert_user.append("WHERE codigo = ? ");
		Object[] params = new Object[] { categoria.getId() };	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean update(Categoria categoria) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_categoria ");
		sql_insert_user.append("SET nombre=?, estado=? ");
		sql_insert_user.append("WHERE codigo = ? ");

		Object[] params = new Object[] { categoria.getNombre(),	categoria.getEstado(), categoria.getId() };	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean updateStatus(Categoria categoria) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
