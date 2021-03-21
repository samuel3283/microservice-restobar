package pe.com.maprsoft.facturador.service;

import java.util.List;
import java.util.Map;
import pe.com.maprsoft.facturador.model.Reserva;

public interface ReservaService {

	public Map<String, Object> insert(Reserva reserva, String token) throws Exception;
	public Reserva get(Reserva reserva) throws Exception;
	public List<Reserva> list(Reserva reserva, String token) throws Exception;
	public void updateStatus(Reserva reserva) throws Exception;
	public void update(Reserva reserva) throws Exception;
	public void delete(Reserva reserva) throws Exception;
	
}
