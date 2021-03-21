package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;
import pe.com.maprsoft.facturador.model.Salon;

public interface SalonRepository {
	
	public List<Salon> listAll(Integer sucursal) throws Exception;
	public List<Salon> list(Integer sucursal) throws Exception;
	public boolean update(Salon salon) throws Exception;
	public boolean updateStatus(Salon salon) throws Exception;
	public boolean insert(Salon salon) throws Exception;
	public boolean delete(Salon salon) throws Exception;
}
