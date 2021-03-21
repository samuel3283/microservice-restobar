package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import pe.com.maprsoft.facturador.model.Insumo;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Sucursal;

public class InsumoMapper implements RowMapper<Insumo> {
	
	public Insumo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Insumo bean = new Insumo();
		bean.setCodigo(rs.getInt(1));
		bean.setMedida(rs.getString(3));
		bean.setNombre(rs.getString(4));
		//bean.setCantidad(rs.getInt(5));
		
		try {			
			BigDecimal cantidad = rs.getBigDecimal(5);
			cantidad = cantidad.setScale(2);
			bean.setCantidad(String.valueOf(cantidad));			
		} catch(Exception e) {
				bean.setCantidad("0.00");
		}

		try {			
			BigDecimal merma = rs.getBigDecimal(6);
			merma = merma.setScale(2);
			bean.setMerma(String.valueOf(merma));			
		} catch(Exception e) {
				bean.setMerma("0.00");
		}

		//bean.setStockMinimo(rs.getInt(7));
		BigDecimal stockMinimo = new BigDecimal("0.00");
		try {			
			stockMinimo = rs.getBigDecimal(7);
			stockMinimo = stockMinimo.setScale(2);
			bean.setStockMinimo(String.valueOf(stockMinimo));			
		} catch(Exception e) {
				bean.setStockMinimo("0.00");
				stockMinimo = new BigDecimal("0.00");
		}

		bean.setEstado(rs.getInt(8));

		BigDecimal cantidadReal = new BigDecimal("0.00");
		try {			
			cantidadReal = rs.getBigDecimal(9);
			cantidadReal = cantidadReal.setScale(2);
			bean.setCantidadReal(String.valueOf(cantidadReal));			
		} catch(Exception e) {
				bean.setCantidadReal("0.00");
		}
		BigDecimal resto = cantidadReal;
		if(stockMinimo.intValue()>0)
			resto = cantidadReal.subtract(stockMinimo);
		if(resto.intValue()>0) {
			bean.setLimite(0);
		} else {
			bean.setLimite(1);
		}
		
		bean.setLugarElaboracion(rs.getString(10));
		
		BigDecimal precioCompra = new BigDecimal("0.00");
		try {			
			precioCompra = rs.getBigDecimal(11);
			precioCompra = precioCompra.setScale(2);
			bean.setPrecioCompra(String.valueOf(precioCompra));			
		} catch(Exception e) {
				bean.setPrecioCompra("0.00");
		}
		
		Sucursal sucursal = new Sucursal();
		sucursal.setCodigo(rs.getInt(2));

		bean.setSucursal(sucursal);
				
		return bean;
	}
	
	  
			

}
