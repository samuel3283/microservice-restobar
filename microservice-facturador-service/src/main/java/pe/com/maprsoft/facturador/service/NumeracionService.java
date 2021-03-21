package pe.com.maprsoft.facturador.service;

import java.util.List;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Numeracion;

public interface NumeracionService {

	public List<Numeracion> list(String token) throws Exception;
	public void insert(Numeracion numeracion, String token) throws Exception;
	public void delete(Numeracion numeracion) throws Exception;
	public void update(Numeracion numeracion) throws Exception;
}
