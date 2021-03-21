package pe.com.maprsoft.facturador.service;

import java.util.List;

import pe.com.maprsoft.facturador.model.Insumo;

public interface InsumoService {

	public List<Insumo> list(String token) throws Exception;
	public List<Insumo> listAll(String token) throws Exception;
	public void insert(Insumo insumo, String token) throws Exception;
	public void delete(Insumo insumo, String token) throws Exception;
	public void update(Insumo insumo, String token) throws Exception;
	public void updateStatus(Insumo insumo, String token) throws Exception;
}
