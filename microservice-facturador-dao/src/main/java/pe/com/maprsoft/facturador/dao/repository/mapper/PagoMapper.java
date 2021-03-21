package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import pe.com.maprsoft.facturador.model.Mesa;
import pe.com.maprsoft.facturador.model.Pago;

public class PagoMapper implements RowMapper<Pago> {
	
	public Pago mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pago bean = new Pago();
		bean.setCodigo(rs.getInt(1));
		bean.setTipoDocumento(rs.getString(2));
		bean.setSerie(rs.getString(3));
		bean.setNumero(rs.getString(4));
		bean.setRuc(rs.getString(5));
		bean.setRazonSocial(rs.getString(6));
		bean.setDireccion(rs.getString(7));
		
		bean.setTipo(rs.getString(8));
		bean.setFechaEmision(rs.getString(9));

		try {			
		BigDecimal neto = rs.getBigDecimal(10);
		neto = neto.setScale(2);
		bean.setSubTotal(String.valueOf(neto));					
		} catch(Exception e) {
			bean.setSubTotal("0.00");
		}

		try {			
		BigDecimal igv = rs.getBigDecimal(11);
		igv = igv.setScale(2);
		bean.setIgv(String.valueOf(igv));					
		} catch(Exception e) {
			bean.setIgv("0.00");
		}
		
		try {			
		BigDecimal total = rs.getBigDecimal(12);
		total = total.setScale(2);
		bean.setTotal(String.valueOf(total));					
		} catch(Exception e) {
			bean.setTotal("0.00");
		}
		
		bean.setEstado(rs.getString(13));		
		bean.setMoneda(rs.getString(14));
		bean.setDescuento(rs.getString(15));
		bean.setUsuario(rs.getString(16));
		
		return bean;
	}

			

}
