package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import pe.com.maprsoft.facturador.model.DetallePedido;
import pe.com.maprsoft.facturador.model.core.EstadoPedidoCodeEnum;

public class DetallePedidoMapper implements RowMapper<DetallePedido> {
	
	public DetallePedido mapRow(ResultSet rs, int rowNum) throws SQLException {
		DetallePedido bean = new DetallePedido();
		bean.setCodigo(rs.getInt(1));
		
		bean.setId(rs.getString(1));
		bean.setIndice(rs.getInt(1));
		
		bean.setCodigoProducto(rs.getInt(2));
		
		bean.setComentario(rs.getString(4));
		bean.setEstado(rs.getInt(5));	
		
		if(bean.getEstado().intValue() == EstadoPedidoCodeEnum.PEDIDO_PENDIENTE.getCode().intValue()) {
			bean.setDesEstado(EstadoPedidoCodeEnum.PEDIDO_PENDIENTE.getMessage());
		} else if(bean.getEstado().intValue() == EstadoPedidoCodeEnum.PEDIDO_PRCESO.getCode().intValue()) {
			bean.setDesEstado(EstadoPedidoCodeEnum.PEDIDO_PRCESO.getMessage());
		} else if(bean.getEstado().intValue() == EstadoPedidoCodeEnum.PEDIDO_ATENDIDO.getCode().intValue()) {
			bean.setDesEstado(EstadoPedidoCodeEnum.PEDIDO_ATENDIDO.getMessage());
		} else if(bean.getEstado().intValue() == EstadoPedidoCodeEnum.PEDIDO_CANCELADO.getCode().intValue()) {
			bean.setDesEstado(EstadoPedidoCodeEnum.PEDIDO_CANCELADO.getMessage());
		} 
		
		bean.setFechaRegistro(rs.getString(6));
		
		bean.setNombre(rs.getString(7));
		bean.setMoneda(rs.getString(8));
		
		try {			
			BigDecimal precio = rs.getBigDecimal(9);
			precio = precio.setScale(2);
			bean.setPrecio(String.valueOf(precio));			
		} catch(Exception e) {
				bean.setPrecio("0.00");
		}
		
		bean.setLugarElaboracion(rs.getString(10));
		bean.setTipo(rs.getString(11));
		
		return bean;
	}

			

}
