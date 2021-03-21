package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;
import pe.com.maprsoft.facturador.model.Numeracion;

public interface NumeracionRepository {
	
	public List<Numeracion> listActivos(Integer sucursal) throws Exception;
	public List<Numeracion> list(Integer sucursal) throws Exception;
	public boolean delete(Numeracion numeracion) throws Exception;
	public boolean update(Numeracion numeracion) throws Exception;
	public boolean insert(Numeracion numeracion) throws Exception;
	public Numeracion get(Numeracion numeracion) throws Exception;
	boolean updateCorrelatibo(Numeracion numeracion) throws Exception;	
	
}
