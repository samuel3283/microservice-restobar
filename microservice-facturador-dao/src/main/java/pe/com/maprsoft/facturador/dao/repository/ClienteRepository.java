package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;

import pe.com.maprsoft.facturador.model.Cliente;
import pe.com.maprsoft.facturador.model.Mozo;

public interface ClienteRepository {
	
	public List<Cliente> list(Cliente cliente) throws Exception;
	public Integer insert(Cliente cliente) throws Exception;
	public Integer getIdCliente(Cliente cliente) throws Exception;
	public boolean delete(Cliente cliente) throws Exception;
	public boolean update(Cliente cliente) throws Exception;
	
}
