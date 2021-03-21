package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;
import pe.com.maprsoft.facturador.model.PedidoCocina;

public interface CocinaRepository {
	
	public List<PedidoCocina> listCocina(Integer sucursal) throws Exception;
	public List<PedidoCocina> listBarra(Integer sucursal) throws Exception;
}
