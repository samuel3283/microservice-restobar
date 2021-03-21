package pe.com.maprsoft.facturador.service;

import pe.com.maprsoft.facturador.model.Mesa;

public interface MesaService {

	public void updateStatus(Mesa mesa) throws Exception;
	public void update(Mesa mesa) throws Exception;
	public void delete(Mesa mesa) throws Exception;
	public void insert(Mesa mesa) throws Exception;
	
}
