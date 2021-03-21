package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import pe.com.maprsoft.facturador.model.Insumo;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Preparacion;
import pe.com.maprsoft.facturador.model.Producto;
import pe.com.maprsoft.facturador.model.Sucursal;
import pe.com.maprsoft.facturador.model.core.MonedaCodeEnum;

public class PreparacionMapper implements RowMapper<Preparacion> {
	
	public Preparacion mapRow(ResultSet rs, int rowNum) throws SQLException {
		Preparacion bean = new Preparacion();
		bean.setCodigo(rs.getInt(1));
		bean.setMedida(rs.getString(2));
		bean.setCantidad(rs.getString(3));
		bean.setEstado(rs.getInt(4));

		Insumo insumo = new Insumo();
		insumo.setCodigo(rs.getInt(1));
		insumo.setSucursal(new Sucursal(rs.getInt(6)));
		insumo.setMedida(rs.getString(7));
		insumo.setNombre(rs.getString(8));
		//insumo.setCantidad(rs.getInt(9));
		try {			
			BigDecimal cantidad = rs.getBigDecimal(9);
			cantidad = cantidad.setScale(2);
			insumo.setCantidad(String.valueOf(cantidad));			
		} catch(Exception e) {
			insumo.setCantidad("0.00");
		}		

		try {			
			BigDecimal merma = rs.getBigDecimal(10);
			merma = merma.setScale(2);
			insumo.setMerma(String.valueOf(merma));			
		} catch(Exception e) {
			insumo.setMerma("0.00");
		}		
		
		try {			
			BigDecimal stockMinimo = rs.getBigDecimal(11);
			stockMinimo = stockMinimo.setScale(2);
			insumo.setStockMinimo(String.valueOf(stockMinimo));			
		} catch(Exception e) {
			insumo.setStockMinimo("0.00");
		}
		//insumo.setStockMinimo(rs.getInt(11));
		insumo.setEstado(rs.getInt(12));
		
		Producto producto = new Producto();
		producto.setId(rs.getInt(13));
		producto.setNombre(rs.getString(14));
		producto.setTipo(rs.getString(15));
		try {			
		BigDecimal precio = rs.getBigDecimal(17);
		precio = precio.setScale(2);
		producto.setPrecio(String.valueOf(precio));					
		} catch(Exception e) {
			producto.setPrecio("0.00");
		}

		
		
		producto.setMoneda(rs.getString(18));
		
		
		/*sql_find_user.append("select pi.codigo cod_preparacion, pi.medida,pi.cantidad_preparacion, pi.estado, ");
		sql_find_user.append("pi.codigo_insumo5, i.codigo_restaurante_local6,i.medida medida_insumo7, i.nombre nombre_insumo8, ");
		sql_find_user.append("i.cantidad cantidad_insumo9, i.merma10, i.stock_minimo11, i.estado estado_insumo12, ");
		sql_find_user.append("pi.codigo_producto13, p.nombre nombre_prod14, p.tipo tipo_prod15, p.descripcion desc_prod16, ");
		sql_find_user.append("p.precio precio_prod17, p.moneda18, p.estado19, p.lugar_elaboracion20 ");
		 */

		if(MonedaCodeEnum.MONEDA_SOLES_PERU.getCode().intValue()==rs.getInt(4)) {
			producto.setMonedaDes(MonedaCodeEnum.MONEDA_SOLES_PERU.getMessage());	
		} else if(MonedaCodeEnum.MONEDA_DOLAR_AMERICANO.getCode().intValue()==rs.getInt(4)) {
			producto.setMonedaDes(MonedaCodeEnum.MONEDA_DOLAR_AMERICANO.getMessage());	
		} else if(MonedaCodeEnum.MONEDA_EURO_EUROPA.getCode().intValue()==rs.getInt(4)) {
			producto.setMonedaDes(MonedaCodeEnum.MONEDA_EURO_EUROPA.getMessage());	
		}		
		producto.setEstado(rs.getInt(19));
		producto.setElaboracion(rs.getString(20));

		
		bean.setInsumo(insumo);
		bean.setProducto(producto);
		
				
		return bean;
	}
	
	  
			

}
