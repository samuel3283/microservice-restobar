package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;

import pe.com.maprsoft.facturador.model.DetallePedido;
import pe.com.maprsoft.facturador.model.Pedido;

public interface DetallePedidoRepository {
	
	public boolean insert(DetallePedido detallePedido, Integer idPedido) throws Exception;
	public boolean deleteItem(DetallePedido detallePedido) throws Exception;
	public boolean updateItem(DetallePedido detallePedido) throws Exception;
	public boolean updateStatus(DetallePedido detallePedido) throws Exception;
	public List<DetallePedido> list(Pedido pedido) throws Exception;
	public List<DetallePedido> listPago(Pedido pedido) throws Exception;
	public List<DetallePedido> listPagoMultpile(Pedido pedido) throws Exception;
	public boolean delete(Integer codigoPedido) throws Exception;
	
}
