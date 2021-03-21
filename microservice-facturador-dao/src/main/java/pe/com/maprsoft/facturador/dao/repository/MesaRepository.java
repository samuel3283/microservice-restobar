package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;

import pe.com.maprsoft.facturador.model.Mesa;


public interface MesaRepository {

	public boolean updateStatus(Mesa mesa) throws Exception;
	public List<Mesa> list(Integer idSalon) throws Exception;
	public boolean deleteAll(Integer codigoSalon) throws Exception;
	public boolean update(Mesa mesa) throws Exception;
	public boolean delete(Mesa mesa) throws Exception;
	public boolean insert(Mesa mesa) throws Exception;	
	public List<Mesa> listAll(Integer idSalon) throws Exception;

}
