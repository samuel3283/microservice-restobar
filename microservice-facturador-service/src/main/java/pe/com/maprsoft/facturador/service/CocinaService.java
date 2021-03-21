package pe.com.maprsoft.facturador.service;

import java.util.List;
import pe.com.maprsoft.facturador.model.Pedido;

public interface CocinaService {

	public List<Pedido> listPedidosCocina(String token) throws Exception;
	public List<Pedido> listPedidosBarra(String token) throws Exception;

}
