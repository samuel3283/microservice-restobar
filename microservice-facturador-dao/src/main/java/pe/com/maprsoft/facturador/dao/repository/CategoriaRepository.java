package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;

import pe.com.maprsoft.facturador.model.Categoria;
import pe.com.maprsoft.facturador.model.ListaCategoria;


public interface CategoriaRepository {
	
	public List<Categoria> list(Integer sucursal) throws Exception;
	public List<ListaCategoria> lista(Integer sucursal) throws Exception;
	public List<ListaCategoria> listAll(Integer sucursal) throws Exception;
	public boolean insert(Categoria categoria) throws Exception;
	public boolean delete(Categoria categoria) throws Exception;
	public boolean update(Categoria categoria) throws Exception;
	public boolean updateStatus(Categoria categoria) throws Exception;
	
}
