package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Restaurante;

public interface RestauranteRepository {
	
	public List<Restaurante> list() throws Exception;
	public List<Restaurante> listAll() throws Exception;
	public boolean insert(Restaurante restaurante) throws Exception;
	public boolean delete(Restaurante restaurante) throws Exception;
	public boolean update(Restaurante restaurante) throws Exception;
	public boolean updateStatus(Restaurante restaurante) throws Exception;
	
}
