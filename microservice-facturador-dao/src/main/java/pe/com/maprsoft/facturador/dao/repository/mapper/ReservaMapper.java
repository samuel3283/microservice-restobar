package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import pe.com.maprsoft.facturador.model.Mesa;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Pedido;
import pe.com.maprsoft.facturador.model.Reserva;

public class ReservaMapper implements RowMapper<Reserva> {
	
	public Reserva mapRow(ResultSet rs, int rowNum) throws SQLException {
		Reserva bean = new Reserva();
		bean.setCodigo(rs.getInt(1));
		bean.setCodigoSucursal(rs.getInt(2));
		
		bean.setPersonas(rs.getInt(5));
		bean.setEstado(rs.getInt(6));
		bean.setContacto(rs.getString(7));
		bean.setComentario(rs.getString(8));
		bean.setFechaReserva(rs.getString(9));
		bean.setHoraReserva(rs.getString(10));
		bean.setFechaRegistro(rs.getString(11));
	
		bean.setTipoDocumento(rs.getString(12));
		bean.setNumDocumento(rs.getString(13));
		bean.setTelefono(rs.getString(14));

		return bean;
	}

			

}
