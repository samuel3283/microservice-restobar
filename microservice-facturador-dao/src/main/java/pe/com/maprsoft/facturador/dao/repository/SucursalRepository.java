package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;
import pe.com.maprsoft.facturador.model.Restaurante;
import pe.com.maprsoft.facturador.model.Sucursal;

public interface SucursalRepository {
	
	public List<Sucursal> listByRestaurante(Restaurante restaurante) throws Exception;
	public List<Sucursal> list() throws Exception;
	public List<Sucursal> listAll() throws Exception;
	public Sucursal getAll(Integer codigoLocal) throws Exception;
	public boolean insert(Sucursal sucursal) throws Exception;
	public boolean update(Sucursal sucursal) throws Exception;
	public boolean delete(Sucursal sucursal) throws Exception;
}
