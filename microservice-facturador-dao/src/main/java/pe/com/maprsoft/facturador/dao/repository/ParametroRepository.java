package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Parametro;

public interface ParametroRepository {
	
	public List<Parametro> listGeneric(Parametro parametro) throws Exception;
	public List<Parametro> list(Parametro parametro) throws Exception;
	public List<Parametro> listAll(Parametro parametro) throws Exception;
	public boolean insert(Parametro parametro) throws Exception;
	public boolean delete(Parametro parametro) throws Exception;
	public boolean update(Parametro parametro) throws Exception;
	public boolean updateStatus(Parametro parametro) throws Exception;
	public boolean insertCode(Parametro parametro) throws Exception;
	
}
