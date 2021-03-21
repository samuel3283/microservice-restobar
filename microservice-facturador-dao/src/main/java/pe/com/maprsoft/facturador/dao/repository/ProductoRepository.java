package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;
import java.util.Map;

import pe.com.maprsoft.facturador.model.Categoria;
import pe.com.maprsoft.facturador.model.Producto;


public interface ProductoRepository {
	public List<Producto> listByCategoria(Categoria categoria) throws Exception;
	public List<Producto> listAll(Integer idSucursal) throws Exception;
	public List<Producto> list(Integer idSubCategoria) throws Exception;
	public boolean deleteAll(Integer idSubCategoria) throws Exception;
	public boolean delete(Producto producto) throws Exception;
	public boolean update(Producto producto) throws Exception;
	public boolean insert(Producto producto) throws Exception;
	public boolean updateStatus(Producto producto) throws Exception;
	public List<Map<String, Object>> listConsumoByProducto(Producto producto) throws Exception;
}
