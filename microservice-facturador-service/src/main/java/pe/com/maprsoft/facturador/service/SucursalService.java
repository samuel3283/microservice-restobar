package pe.com.maprsoft.facturador.service;

import java.util.List;

import pe.com.maprsoft.facturador.model.Restaurante;
import pe.com.maprsoft.facturador.model.Salon;
import pe.com.maprsoft.facturador.model.Sucursal;

public interface SucursalService {

	public List<Sucursal> list() throws Exception;
	public List<Sucursal> listAll() throws Exception;
	public List<Sucursal> listByRestaurante(Restaurante restaurante) throws Exception;
	public void delete(Sucursal sucursal) throws Exception;
	public void update(Sucursal sucursal) throws Exception;
	public void insert(Sucursal sucursal) throws Exception;
}
