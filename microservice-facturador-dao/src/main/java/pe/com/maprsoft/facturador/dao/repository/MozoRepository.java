package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;
import pe.com.maprsoft.facturador.model.Mozo;

public interface MozoRepository {
	
	public List<Mozo> list(Integer sucursal) throws Exception;
	public List<Mozo> listAll(Integer sucursal) throws Exception;
	public boolean insert(Mozo mozo) throws Exception;
	public boolean delete(Mozo mozo) throws Exception;
	public boolean update(Mozo mozo) throws Exception;
	public boolean updateStatus(Mozo mozo) throws Exception;
	
}
