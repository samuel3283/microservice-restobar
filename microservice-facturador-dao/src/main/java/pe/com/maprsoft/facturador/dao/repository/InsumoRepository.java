package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;
import pe.com.maprsoft.facturador.model.Insumo;
import pe.com.maprsoft.facturador.model.Preparacion;

public interface InsumoRepository {
	
	public List<Insumo> list(Integer sucursal) throws Exception;
	public List<Insumo> listAll(Integer sucursal) throws Exception;
	public boolean insert(Insumo insumo) throws Exception;
	public boolean delete(Insumo insumo) throws Exception;
	public boolean update(Insumo insumo) throws Exception;
	public boolean updateInsumo(Preparacion preparacion) throws Exception;
	public boolean updateStatus(Insumo insumo) throws Exception;
	
}
