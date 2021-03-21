package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import pe.com.maprsoft.facturador.model.DetallePedido;
import pe.com.maprsoft.facturador.model.core.EstadoPedidoCodeEnum;

public class DetallePedidoToPagoMapper implements RowMapper<DetallePedido> {
	
	public DetallePedido mapRow(ResultSet rs, int rowNum) throws SQLException {
		DetallePedido bean = new DetallePedido();
		
		bean.setCodigoProducto(rs.getInt(1));
		bean.setNombre(rs.getString(2));
		bean.setMoneda(rs.getString(3));
		bean.setCantidad(rs.getInt(5));
		
		try {			
			BigDecimal precio = rs.getBigDecimal(4);
			precio = precio.setScale(2);
			bean.setPrecio(String.valueOf(precio));			
		} catch(Exception e) {
				bean.setPrecio("0.00");
		}
		return bean;
	}

			

}
