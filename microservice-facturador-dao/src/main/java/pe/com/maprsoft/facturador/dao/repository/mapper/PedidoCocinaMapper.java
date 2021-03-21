package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import pe.com.maprsoft.facturador.model.DetallePedido;
import pe.com.maprsoft.facturador.model.PedidoCocina;
import pe.com.maprsoft.facturador.model.core.EstadoPedidoCodeEnum;

public class PedidoCocinaMapper implements RowMapper<PedidoCocina> {
	
	public PedidoCocina mapRow(ResultSet rs, int rowNum) throws SQLException {
		PedidoCocina bean = new PedidoCocina();
		
		bean.setCodigoPedido(rs.getInt(1));		
		bean.setCodigoMesa(rs.getInt(2));		
		bean.setTipoPedido(rs.getString(3));
		bean.setPersonas(rs.getInt(4));
		bean.setEstadoPedido(rs.getInt(5));

		bean.setFechaPedido(rs.getString(6));
		bean.setCodigoMozo(rs.getInt(7));		
		
		bean.setNombreMesa(rs.getString(8));
		bean.setNombreBreveMesa(rs.getString(9));
		bean.setCapacidadMesa(rs.getInt(10));		
		bean.setEstadoMesa(rs.getInt(11));
		
		bean.setCodigoPedidoDetalle(rs.getInt(12));
		bean.setCodigoProducto(rs.getInt(13));
		bean.setComentarioPedidoDetalle(rs.getString(14));
		bean.setEstadoPedidoDetalle(rs.getInt(15));
		bean.setFechaPedidoDetalle(rs.getString(16));
		bean.setNombreProducto(rs.getString(17));
		bean.setMonedaProducto(rs.getString(18));
		//bean.setPrecioProducto(rs.getString(19));		
		try {			
			BigDecimal precio = rs.getBigDecimal(19);
			precio = precio.setScale(2);
			bean.setPrecioProducto(String.valueOf(precio));			
		} catch(Exception e) {
				bean.setPrecioProducto("0.00");
		}
		
		bean.setNombreMozo(rs.getString(20));
		bean.setLugarElaboracion(rs.getString(21));
		bean.setTipo(rs.getString(22));
		
		/*
		if(bean.getEstado().intValue() == EstadoPedidoCodeEnum.PEDIDO_PENDIENTE.getCode().intValue()) {
			bean.setDesEstado(EstadoPedidoCodeEnum.PEDIDO_PENDIENTE.getMessage());
		} else if(bean.getEstado().intValue() == EstadoPedidoCodeEnum.PEDIDO_PRCESO.getCode().intValue()) {
			bean.setDesEstado(EstadoPedidoCodeEnum.PEDIDO_PRCESO.getMessage());
		} else if(bean.getEstado().intValue() == EstadoPedidoCodeEnum.PEDIDO_ATENDIDO.getCode().intValue()) {
			bean.setDesEstado(EstadoPedidoCodeEnum.PEDIDO_ATENDIDO.getMessage());
		} else if(bean.getEstado().intValue() == EstadoPedidoCodeEnum.PEDIDO_CANCELADO.getCode().intValue()) {
			bean.setDesEstado(EstadoPedidoCodeEnum.PEDIDO_CANCELADO.getMessage());
		} 			
		*/
		
		return bean;
	}

			

}
