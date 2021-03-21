package pe.com.maprsoft.facturador.service;

import java.util.List;

import pe.com.maprsoft.facturador.model.Categoria;
import pe.com.maprsoft.facturador.model.Producto;
import pe.com.maprsoft.facturador.model.SubCategoria;

public interface SubCategoriaService {

	public List<SubCategoria> list(Categoria categoria) throws Exception;
	public void insert(SubCategoria subCategoria, String toke) throws Exception;
	public void update(SubCategoria subCategoria) throws Exception;
	public void delete(SubCategoria subCategoria) throws Exception;
}
