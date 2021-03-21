package pe.com.maprsoft.facturador.service;

import java.util.List;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Usuario;

public interface MozoService {

	public List<Mozo> list(String token) throws Exception;
	public List<Mozo> listAll(String token) throws Exception;
	public void insert(Mozo mozo, String token) throws Exception;
	public void delete(Mozo mozo, String token) throws Exception;
	public void update(Mozo mozo, String token) throws Exception;
	public void updateStatus(Mozo mozo, String token) throws Exception;

	public void insertMozo(Usuario usuario, String token) throws Exception;
	public void deleteMozo(Usuario usuario, String token) throws Exception;
	public void updateMozo(Usuario usuario) throws Exception;
	public List<Usuario> listMozo(String token) throws Exception;
	public List<Usuario> listAllMozo(String token) throws Exception;
	

}
