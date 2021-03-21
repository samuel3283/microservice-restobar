package pe.com.maprsoft.facturador.service;

import java.util.List;
import java.util.Map;

import pe.com.maprsoft.facturador.model.Filter;
import pe.com.maprsoft.facturador.model.Pago;
import pe.com.maprsoft.facturador.model.Pedido;

public interface PagoService {
	public String generaBoleta(Pago pago, String token) throws Exception ;
	public Map<String, Object> insert(Pago pago, String token) throws Exception;
	public List<Pago> insertList(List<Pago> pagos, String token) throws Exception;
	public String generaComprobante(Pago pago, String token) throws Exception ;
	public String generPreCuenta(Pedido pedido, String token) throws Exception ;
	public List<Pago> listAll(Filter filter, String token) throws Exception;
	public List<Pago> listByFilter(Filter filter, String token) throws Exception;
}
