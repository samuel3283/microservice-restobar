package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;

import pe.com.maprsoft.facturador.model.Usuario;

public interface UsuarioRepository {

	void insert(Usuario usuario) throws Exception;
	boolean update(Usuario usuario) throws Exception;
	List<Usuario> list(Usuario usuario) throws Exception;
	List<Usuario> listAll(Usuario usuario) throws Exception;
	Usuario get(Usuario usuario) throws Exception;
	List<Usuario> listAdmin(Usuario usuario) throws Exception;	
	public boolean updateStatusMozo(Usuario usuario) throws Exception;
	
	boolean insertMozo(Usuario usuario) throws Exception;
	boolean updateMozo(Usuario usuario) throws Exception;
	List<Usuario> listMozo(Usuario usuario) throws Exception;
	List<Usuario> listAllMozo(Usuario usuario) throws Exception;
	boolean delete(Usuario usuario) throws Exception;
	
	Usuario login(Usuario usuario) throws Exception;
	
}
