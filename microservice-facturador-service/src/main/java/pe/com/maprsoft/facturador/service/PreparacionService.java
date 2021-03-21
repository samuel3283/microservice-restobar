package pe.com.maprsoft.facturador.service;

import java.util.List;

import pe.com.maprsoft.facturador.model.Insumo;
import pe.com.maprsoft.facturador.model.Preparacion;
import pe.com.maprsoft.facturador.model.Producto;

public interface PreparacionService {

	public List<Preparacion> list(Producto producto,String token) throws Exception;
	public List<Preparacion> listAll(Producto producto,String token) throws Exception;
	public void insert(Preparacion preparacion, String token) throws Exception;
	public void delete(Preparacion preparacion, String token) throws Exception;
	public void update(Preparacion preparacion, String token) throws Exception;
	public void updateStatus(Preparacion preparacion, String token) throws Exception;
}
