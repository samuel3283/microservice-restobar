package pe.com.maprsoft.facturador.dao.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.com.maprsoft.facturador.dao.repository.ProductoRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.MapMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.ProductoMapper;
import pe.com.maprsoft.facturador.model.Categoria;
import pe.com.maprsoft.facturador.model.Producto;

@SuppressWarnings("all")
@Repository
public class ProductoRepositoryImpl implements ProductoRepository {

	private final Logger logger = LoggerFactory.getLogger(ProductoRepositoryImpl.class);
	
	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Producto> listByCategoria(Categoria categoria) throws Exception {
		List <Producto> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select pr.codigo, pr.nombre, pr.precio, pr.moneda, pr.estado, pr.tipo, pr.lugar_elaboracion ");		
		sql_find_user.append("from tbl_categoria ca ");
		sql_find_user.append("inner join tbl_subcategoria sc on sc.codigo_categoria = ca.codigo ");
		sql_find_user.append("inner join tbl_producto pr on pr.codigo_subcategoria = sc.codigo ");
		sql_find_user.append("where ca.codigo_restaurante_local = ? and ca.codigo = ? ");
		
		Object[] params = new Object[] { categoria.getSucursal().getCodigo(), categoria.getId() };
		lista = (List <Producto>)jdbcTemplate.query(sql_find_user.toString(), params, new ProductoMapper());
		return lista;
	}
	
	@Override
	public List<Map<String, Object>> listConsumoByProducto(Producto producto) throws Exception {
		List <Map<String, Object>> lista = null;
		StringBuilder sql_find_user = new StringBuilder();		
		sql_find_user.append("select pd.nombre, sum(pd.precio), count(pd.nombre) ");
		sql_find_user.append("from tbl_categoria ca ");
		sql_find_user.append("inner join tbl_subcategoria sc on sc.codigo_categoria = ca.codigo ");
		sql_find_user.append("inner join tbl_producto pr on pr.codigo_subcategoria = sc.codigo ");
		sql_find_user.append("inner join tbl_pedido_detalle pd on pd.codigo_producto = pr.codigo ");
		sql_find_user.append("inner join tbl_pedido pe on pe.codigo = pd.codigo_pedido ");
		sql_find_user.append("where ca.codigo_restaurante_local = ? and ca.codigo = ? ");
		//sql_find_user.append("and pd.codigo_producto = ? and pe.estado = 5 ");
		sql_find_user.append("and pe.estado = 5 ");
		sql_find_user.append("and DATE_FORMAT(pd.fec_registro,'%Y%m%d') = ? ");
		sql_find_user.append("group by pd.nombre ");
		
		Object[] params = new Object[] { producto.getSucursal().getCodigo(), 
				producto.getCodigoCategoria(), producto.getId(), producto.getInicio() };
		lista = (List <Map<String, Object>>)jdbcTemplate.query(sql_find_user.toString(), params, new MapMapper());
		return lista;
	}
	
	@Override
	public List<Producto> listAll(Integer idSucursal) throws Exception {
		List <Producto> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select p.codigo, p.nombre, p.precio, p.moneda, p.estado, p.tipo, p.lugar_elaboracion ");		
		sql_find_user.append("from tbl_producto p where ");
		sql_find_user.append("p.codigo_restaurante_local = ? ");
		sql_find_user.append("order by p.lugar_elaboracion, p.nombre ");
		
		Object[] params = new Object[] { idSucursal };
		lista = (List <Producto>)jdbcTemplate.query(sql_find_user.toString(), params, new ProductoMapper());
		return lista;
	}

	@Override
	public List<Producto> list(Integer idSubCategoria) throws Exception {
		List <Producto> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select p.codigo, p.nombre, p.precio, p.moneda, p.estado, p.tipo, p.lugar_elaboracion ");		
		sql_find_user.append("from tbl_producto p where ");
		sql_find_user.append("codigo_subcategoria = ? ");
		sql_find_user.append("order by p.lugar_elaboracion, p.nombre ");
		
		Object[] params = new Object[] { idSubCategoria };
		lista = (List <Producto>)jdbcTemplate.query(sql_find_user.toString(), params, new ProductoMapper());
		return lista;
	}

	@Override
	public boolean delete(Producto producto) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("DELETE FROM tbl_producto ");
		sql_insert_user.append("WHERE codigo = ? ");        
		Object[] params = new Object[] { producto.getId() };	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;	
	}


	@Override
	public boolean update(Producto producto) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_producto ");
		sql_insert_user.append("SET nombre = ?, precio = ?, estado = ?, ");
		sql_insert_user.append("lugar_elaboracion = ?, tipo = ?, moneda = ? ");
		sql_insert_user.append("WHERE codigo = ? ");        
		Object[] params = new Object[] { producto.getNombre(), producto.getPrecio(), producto.getEstado(),
				producto.getElaboracion(), producto.getTipo(), producto.getMoneda(), producto.getId() };	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;	
	}

	@Override
	public boolean insert(Producto producto) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_producto ");
		sql_insert_user.append("(codigo_subcategoria, codigo_restaurante_local,tipo, nombre, precio, moneda, lugar_elaboracion, estado, fec_registro) ");
		sql_insert_user.append("VALUES (?,?,?,?,?,?,?,?,?) ");
		

		  
		java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaHora = sdf.format(dt);
		Object[] params = new Object[] { 
				producto.getCodigoSubCategoria(), producto.getSucursal().getCodigo(),
				producto.getTipo(), producto.getNombre(), 
				producto.getPrecio(), producto.getMoneda(), producto.getElaboracion(),
				producto.getEstado(), fechaHora };	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;	
	}

	@Override
	public boolean updateStatus(Producto producto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAll(Integer idSubCategoria) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("DELETE FROM tbl_producto ");
		sql_insert_user.append("WHERE codigo_subcategoria = ? ");        
		Object[] params = new Object[] { idSubCategoria };	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;	
	}

}
