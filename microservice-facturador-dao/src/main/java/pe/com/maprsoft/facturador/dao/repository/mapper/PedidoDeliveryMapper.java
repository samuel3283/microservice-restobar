package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import pe.com.maprsoft.facturador.model.Cliente;
import pe.com.maprsoft.facturador.model.Mesa;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Pedido;

public class PedidoDeliveryMapper implements RowMapper<Pedido> {
	
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
			bean.setCliente(new Cliente());
			bean.getCliente().setCodigo(rs.getInt(8));
			bean.getCliente().setNombre(rs.getString(9));
			bean.getCliente().setDireccion(rs.getString(10));
			bean.getCliente().setTelefono(rs.getString(11));
			bean.getCliente().setReferencia(rs.getString(12));
					
		} catch(Exception e) {
			bean.setCliente(null);
		}

		return bean;
	}

			

}
