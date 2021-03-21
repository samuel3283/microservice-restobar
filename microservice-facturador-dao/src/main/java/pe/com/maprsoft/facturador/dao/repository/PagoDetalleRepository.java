package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;
import pe.com.maprsoft.facturador.model.Pago;
import pe.com.maprsoft.facturador.model.PagoDetalle;


public interface PagoDetalleRepository {
	
	public boolean insert(PagoDetalle pagoDetalle) throws Exception;
	public List<PagoDetalle> list(Pago pago) throws Exception;
	
}
