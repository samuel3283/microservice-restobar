package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;
import pe.com.maprsoft.facturador.model.Preparacion;
import pe.com.maprsoft.facturador.model.Producto;

public interface PreparacionRepository {
	public List<Preparacion> listIngredientes(Producto producto) throws Exception;
	public List<Preparacion> list(Producto producto,Integer sucursal) throws Exception;
	public List<Preparacion> listAll(Producto producto,Integer sucursal) throws Exception;
	public boolean insert(Preparacion preparacion) throws Exception;
	public boolean delete(Preparacion preparacion) throws Exception;
	public boolean update(Preparacion preparacion) throws Exception;
	public boolean updateStatus(Preparacion preparacion) throws Exception;
	
}
