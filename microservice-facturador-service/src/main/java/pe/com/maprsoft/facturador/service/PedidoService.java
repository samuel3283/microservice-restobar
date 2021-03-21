package pe.com.maprsoft.facturador.service;

import java.util.List;
import java.util.Map;

import pe.com.maprsoft.facturador.model.DetallePedido;
import pe.com.maprsoft.facturador.model.Pedido;

public interface PedidoService {

	public Map<String, Object> insert(Pedido pedido, String token) throws Exception;
	public void update(Pedido pedido) throws Exception;
	public void updateDelivery(Pedido pedido) throws Exception;
	public void updateAtendidoCocina(Pedido pedido) throws Exception;
	public void updateAtendidoBarra(Pedido pedido) throws Exception;
	public Pedido get(Pedido pedido, String token) throws Exception;
	public List<DetallePedido> getDetalle(Pedido pedido) throws Exception;
	public void deleteItemPedido(DetallePedido detallePedido, String token) throws Exception;
	public void insertItem(DetallePedido detallePedido, String token) throws Exception;
	public Pedido getPrePago(Pedido pedido, String token) throws Exception;
	public Pedido getPrePagoMultiple(Pedido pedido, String token) throws Exception;
	public void updateStatusItem(DetallePedido detallePedido, String token) throws Exception;
	public void updateStatus(Pedido pedido, String token) throws Exception;
	public List<Pedido> listDelivery(String token) throws Exception;
	public List<DetallePedido> listDetallePedido(Pedido pedido) throws Exception;
	public void delete(Pedido pedido) throws Exception;
	public void validateDelivery(Pedido pedido, String token) throws Exception;
	public void cancelar(Pedido pedido, String token) throws Exception;
}
