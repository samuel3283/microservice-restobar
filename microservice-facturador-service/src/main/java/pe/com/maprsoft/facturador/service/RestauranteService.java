package pe.com.maprsoft.facturador.service;

import java.util.List;
import pe.com.maprsoft.facturador.model.Restaurante;

public interface RestauranteService {

	public List<Restaurante> list(String token) throws Exception;
	public List<Restaurante> listAll(String token) throws Exception;
	public void insert(Restaurante restaurante) throws Exception;
	public void delete(Restaurante restaurante) throws Exception;
	public void update(Restaurante restaurante) throws Exception;
	public void updateStatus(Restaurante restaurante) throws Exception;	
	
}
