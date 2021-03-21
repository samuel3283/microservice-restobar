package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;

import pe.com.maprsoft.facturador.model.Pedido;

public interface PedidoRepository {
	
	public Integer insert(Pedido pedido) throws Exception;
	public Pedido get(Pedido pedido) throws Exception;
	public Pedido getById(Pedido pedido) throws Exception;
	public void updateStatus(Pedido pedido) throws Exception;
	void update(Pedido pedido) throws Exception;
	public void updateAtendidoCocina(Pedido pedido) throws Exception;
	public void updateAtendidoBarra(Pedido pedido) throws Exception;
	public List<Pedido> listDelivery(Pedido pedido) throws Exception;
	public boolean delete(Pedido pedido) throws Exception;
	public Pedido getPedido(Pedido pedido) throws Exception;
	public void cancelar(Pedido pedido) throws Exception;

}
