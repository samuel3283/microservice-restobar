package pe.com.maprsoft.facturador.service;

import java.util.List;

import pe.com.maprsoft.facturador.model.Categoria;
import pe.com.maprsoft.facturador.model.Producto;
import pe.com.maprsoft.facturador.model.SubCategoria;

public interface ProductoService {

	public List<Producto> listAll(String token) throws Exception;
	public List<Producto> list(SubCategoria subCategoria) throws Exception;
	public void insert(Producto producto, String token) throws Exception;
	public void update(Producto producto) throws Exception;
	public void delete(Producto producto) throws Exception;
}
