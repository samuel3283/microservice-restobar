package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import pe.com.maprsoft.facturador.model.DetallePedido;
import pe.com.maprsoft.facturador.model.PagoDetalle;
import pe.com.maprsoft.facturador.model.core.EstadoPedidoCodeEnum;

public class PagoDetalleMapper implements RowMapper<PagoDetalle> {
	
	public PagoDetalle mapRow(ResultSet rs, int rowNum) throws SQLException {
		PagoDetalle bean = new PagoDetalle();
		bean.setCodigo(rs.getInt(1));
		bean.setCodigoPago(rs.getInt(2));
		bean.setNombre(rs.getString(3));
		bean.setCantidad(rs.getInt(4));
		
		try {			
			BigDecimal precio_unitario = rs.getBigDecimal(5);
			precio_unitario = precio_unitario.setScale(2);
			bean.setPrecio(String.valueOf(precio_unitario));			
		} catch(Exception e) {
				bean.setPrecio("0.00");
		}
		try {			
			BigDecimal subTotal = rs.getBigDecimal(6);
			subTotal = subTotal.setScale(2);
			bean.setSubTotal(String.valueOf(subTotal));			
		} catch(Exception e) {
				bean.setPrecio("0.00");
		}
		bean.setEstado(rs.getInt(7));
		bean.setMoneda(rs.getString(8));
		
		bean.setDescuento(rs.getString(9));
		return bean;
	}

			

}
