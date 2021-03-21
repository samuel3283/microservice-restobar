package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;
import pe.com.maprsoft.facturador.model.Franquicia;

public interface FranquiciaRepository {
	
	public List<Franquicia> list() throws Exception;
	public List<Franquicia> listAll() throws Exception;
	public boolean insert(Franquicia franquicia) throws Exception;
	public boolean delete(Franquicia franquicia) throws Exception;
	public boolean update(Franquicia franquicia) throws Exception;
	public boolean updateStatus(Franquicia franquicia) throws Exception;
	
}
