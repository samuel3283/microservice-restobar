package pe.com.maprsoft.facturador.service;

import java.util.List;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Usuario;

public interface UsuarioService {

	public List<Usuario> listAdmin(String token) throws Exception;
	public List<Usuario> list(String token) throws Exception;
	public List<Usuario> listAll(String token) throws Exception;
	public void insert(Usuario usuario, String token) throws Exception;
	public void insertAdmin(Usuario usuario, String token) throws Exception;
	public void delete(Usuario usuario) throws Exception;
	public void update(Usuario usuario) throws Exception;
	public void updateStatus(Usuario usuario) throws Exception;

}
