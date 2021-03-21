package pe.com.maprsoft.facturador.service;

import java.util.List;

import pe.com.maprsoft.facturador.model.Categoria;
import pe.com.maprsoft.facturador.model.Producto;
import pe.com.maprsoft.facturador.model.SubCategoria;

public interface CategoriaService {

	public List<Categoria> listCategoria(String token) throws Exception;
	public List<SubCategoria> listSubCategoria(Categoria categoria) throws Exception;
	public List<Producto> listProducto(SubCategoria subCategoria) throws Exception;
	
	public List<Categoria> list(String token) throws Exception;
	public List<Categoria> listAll(String token) throws Exception;
	public void insert(Categoria categoria, String toke) throws Exception;
	public void update(Categoria categoria) throws Exception;
	public void delete(Categoria categoria) throws Exception;
}
