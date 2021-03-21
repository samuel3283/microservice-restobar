package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import pe.com.maprsoft.facturador.model.Producto;
import pe.com.maprsoft.facturador.model.core.MonedaCodeEnum;

public class ProductoMapper implements RowMapper<Producto> {
	
	public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
		Producto bean = new Producto();
		bean.setId(rs.getInt(1));
		bean.setNombre(rs.getString(2));
		bean.setMoneda(rs.getString(4));
		
		if(MonedaCodeEnum.MONEDA_SOLES_PERU.getCode().intValue()==rs.getInt(4)) {
			bean.setMonedaDes(MonedaCodeEnum.MONEDA_SOLES_PERU.getMessage());	
		} else if(MonedaCodeEnum.MONEDA_DOLAR_AMERICANO.getCode().intValue()==rs.getInt(4)) {
			bean.setMonedaDes(MonedaCodeEnum.MONEDA_DOLAR_AMERICANO.getMessage());	
		} else if(MonedaCodeEnum.MONEDA_EURO_EUROPA.getCode().intValue()==rs.getInt(4)) {
			bean.setMonedaDes(MonedaCodeEnum.MONEDA_EURO_EUROPA.getMessage());	
		}		
		bean.setEstado(rs.getInt(5));
		bean.setTipo(rs.getString(6));
		bean.setElaboracion(rs.getString(7));
		try {
			
		BigDecimal precio = rs.getBigDecimal(3);
		precio = precio.setScale(2);
		bean.setPrecio(String.valueOf(precio));			
		
		} catch(Exception e) {
			bean.setPrecio("0.00");
		}
		return bean;
	}

			

}
