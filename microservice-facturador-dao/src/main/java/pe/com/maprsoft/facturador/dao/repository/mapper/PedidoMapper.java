package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import pe.com.maprsoft.facturador.model.Mesa;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Pedido;

public class PedidoMapper implements RowMapper<Pedido> {
	
	public Pedido mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pedido bean = new Pedido();
		bean.setCodigo(rs.getInt(1));
				
		bean.setTipo(rs.getString(3));
		bean.setPersonas(rs.getInt(4));
		bean.setEstado(rs.getString(5));
		bean.setFechaPedido(rs.getString(6));

		Mozo mozo = new Mozo();
		mozo.setCodigo(rs.getInt(7));
		bean.setMozo(mozo);

		try {
			bean.setMesa(new Mesa());
			bean.getMesa().setId(rs.getInt(2));
			bean.getMesa().setNombre(rs.getString(8));
			bean.getMesa().setNombreBreve(rs.getString(9));
			bean.getMesa().setCapacidad(rs.getInt(10));
			bean.getMesa().setEstado(rs.getInt(11));		
		} catch(Exception e) {
			bean.setMesa(null);
		}

		return bean;
	}

			

}
