package pe.com.maprsoft.facturador.service;

import java.util.List;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Parametro;
import pe.com.maprsoft.facturador.model.Usuario;

public interface ParametroService {

	public List<Parametro> list(Parametro parametro,String token) throws Exception;
	public List<Parametro> listAll(Parametro parametro,String token) throws Exception;
	public void insertCode(Parametro parametro, String token) throws Exception;
	public void insert(Parametro parametro, String token) throws Exception;
	public void delete(Parametro parametro) throws Exception;
	public void update(Parametro parametron) throws Exception;
	public void updateStatus(Parametro parametro) throws Exception;
	
}
