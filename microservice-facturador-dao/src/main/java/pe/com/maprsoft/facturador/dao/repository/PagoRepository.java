package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;

import pe.com.maprsoft.facturador.model.Filter;
import pe.com.maprsoft.facturador.model.Pago;
import pe.com.maprsoft.facturador.model.PagoItem;

public interface PagoRepository {
	
	public Pago insert(Pago pago) throws Exception;
	public Pago get(Pago pago) throws Exception;
	public List<Pago> listAll(Integer susucrsal) throws Exception;
	public List<Pago> listByFilter(Integer susucrsal, Filter filter) throws Exception;
	public void confirmPay(Pago pago) throws Exception;	
	public Pago getPago(Pago pago) throws Exception;

	public boolean insertItem(PagoItem pagoItem) throws Exception;

}
