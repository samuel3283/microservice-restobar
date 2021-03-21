package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;
import pe.com.maprsoft.facturador.model.Reserva;

public interface ReservaRepository {
	
	public Integer insert(Reserva reserva) throws Exception;
	public List<Reserva> list(Reserva reserva) throws Exception;
	public Reserva getById(Reserva reserva) throws Exception;
	public void updateStatus(Reserva reserva) throws Exception;
	void update(Reserva reserva) throws Exception;
	void delete(Reserva reserva) throws Exception;

	
}
