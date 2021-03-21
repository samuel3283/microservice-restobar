package pe.com.maprsoft.facturador.service;

import java.util.List;
import pe.com.maprsoft.facturador.model.Franquicia;

public interface FranquiciaService {

	public List<Franquicia> list(String token) throws Exception;
	public List<Franquicia> listAll(String token) throws Exception;
	public void insert(Franquicia franquicia) throws Exception;
	public void delete(Franquicia franquicia) throws Exception;
	public void update(Franquicia franquicia) throws Exception;
	public void updateStatus(Franquicia franquicia) throws Exception;	
	
}
