package pe.com.maprsoft.facturador.dao.repository;

import pe.com.maprsoft.facturador.model.Session;

public interface SessionRepository {

	void insert(Session session) throws Exception;
	Session getByToken(Session session) throws Exception;
	public Session getByToken(String token) throws Exception;
	void delete(Session session) throws Exception;

}
