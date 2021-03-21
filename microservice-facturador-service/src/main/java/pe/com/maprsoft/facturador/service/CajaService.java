package pe.com.maprsoft.facturador.service;

import java.util.List;

import pe.com.maprsoft.facturador.model.BalanceCabecera;
import pe.com.maprsoft.facturador.model.Caja;

public interface CajaService {
	
	public List<Caja> list(String token) throws Exception;
	public List<Caja> listCaja(String token) throws Exception;
	public void apertura(Caja request, String token) throws Exception;
	public void cierre(Caja request, String token) throws Exception;
	public void insert(Caja request, String token) throws Exception;
	public void update(Caja request, String token) throws Exception;
	public List<BalanceCabecera> listBalance(Caja caja, String token) throws Exception;

}
