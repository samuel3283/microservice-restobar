package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;
import pe.com.maprsoft.facturador.model.SubCategoria;


public interface SubCategoriaRepository {

	
	public boolean update(SubCategoria subCategoria) throws Exception;
	public boolean insert(SubCategoria subCategoria) throws Exception;
	public List<SubCategoria> list(Integer idCategoria) throws Exception;
	
	public boolean delete(SubCategoria subCategoria) throws Exception;
	public boolean deleteAll(Integer idCategoria) throws Exception;

}
