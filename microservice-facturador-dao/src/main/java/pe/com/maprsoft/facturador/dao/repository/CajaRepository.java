package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;

import pe.com.maprsoft.facturador.model.Balance;
import pe.com.maprsoft.facturador.model.Caja;

public interface CajaRepository {

	public void insert(Caja request) throws Exception;
	public void update(Caja request) throws Exception;
	public List<Caja> list(Caja request) throws Exception;
	public List<Caja> listCaja(Caja request) throws Exception;
	public Caja get(Caja request) throws Exception;

	public List<Balance> balance(Caja caja) throws Exception;
	public String getMontoCierre(Caja caja) throws Exception;
	public List<Caja> listCajaByDia(Caja request) throws Exception;
	public String getMontoDescuentoCierre(Caja caja) throws Exception ;
}
