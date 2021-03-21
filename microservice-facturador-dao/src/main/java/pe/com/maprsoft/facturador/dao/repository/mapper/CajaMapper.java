package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import pe.com.maprsoft.facturador.model.Caja;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Sucursal;

public class CajaMapper implements RowMapper<Caja> {
	
	public Caja mapRow(ResultSet rs, int rowNum) throws SQLException {
		Caja bean = new Caja();
		bean.setCodigo(rs.getInt(1));
		bean.setCodigoSucursal(rs.getInt(2));
		bean.setFechaApertura(rs.getString(3));
		bean.setUsuario(rs.getString(4));
				
		try {			
		BigDecimal monto = rs.getBigDecimal(5);
		monto = monto.setScale(2);
		bean.setMontoInicial(String.valueOf(monto));					
		} catch(Exception e) {
			bean.setMontoInicial("0.00");
		}		
		bean.setIdentificador(rs.getString(6));
		bean.setHorario(rs.getString(7));
		bean.setEstado(rs.getInt(8));
		
		bean.setFechaCierre(rs.getString(9));
		try {			
		BigDecimal montoFinal = rs.getBigDecimal(10);
		montoFinal = montoFinal.setScale(2);
		bean.setMontoFinal(String.valueOf(montoFinal));					
		} catch(Exception e) {
			bean.setMontoFinal("0.00");
		}		

		try {			
		BigDecimal descuento = rs.getBigDecimal(11);
		descuento = descuento.setScale(2);
		bean.setDescuento(String.valueOf(descuento));					
		} catch(Exception e) {
			bean.setDescuento("0.00");
		}		

		return bean;
	}
	
	  
			

}
