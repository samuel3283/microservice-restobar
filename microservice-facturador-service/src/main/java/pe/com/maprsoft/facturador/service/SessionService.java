package pe.com.maprsoft.facturador.service;

import pe.com.maprsoft.facturador.model.HeaderRq;
import pe.com.maprsoft.facturador.model.Session;
import pe.com.maprsoft.facturador.model.Usuario;

public interface SessionService {
	
	Session login(Usuario usuario,HeaderRq headerRq) throws Exception;
	Session loginDirect(Usuario usuario,HeaderRq headerRq) throws Exception;
	Session getSessionByToken(String token) throws Exception;
	void delete(String token) throws Exception;
	
}
