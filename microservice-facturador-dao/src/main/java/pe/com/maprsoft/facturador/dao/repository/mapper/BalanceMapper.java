package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import pe.com.maprsoft.facturador.model.Balance;
import pe.com.maprsoft.facturador.model.Caja;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Sucursal;

public class BalanceMapper implements RowMapper<Balance> {
	
	public Balance mapRow(ResultSet rs, int rowNum) throws SQLException {
		Balance bean = new Balance();
		bean.setCodigo(rs.getInt(1));
		bean.setCodigoSucursal(rs.getInt(2));

		bean.setDocumento(rs.getString(3));
		bean.setSerie(rs.getString(4));
		bean.setNumero(rs.getString(5));

		bean.setFechaPago(rs.getString(6));

		try {			
		BigDecimal subTotal = rs.getBigDecimal(7);
		subTotal = subTotal.setScale(2);
		bean.setSubtotal(String.valueOf(subTotal));					
		} catch(Exception e) {
			bean.setSubtotal("0.00");
		}		
		try {			
		BigDecimal monto = rs.getBigDecimal(8);
		monto = monto.setScale(2);
		bean.setIgv(String.valueOf(monto));					
		} catch(Exception e) {
			bean.setIgv("0.00");
		}		
		try {			
		BigDecimal monto = rs.getBigDecimal(9);
		monto = monto.setScale(2);
		bean.setTotal(String.valueOf(monto));					
		} catch(Exception e) {
			bean.setTotal("0.00");
		}			
		try {			
		BigDecimal monto = rs.getBigDecimal(10);
		monto = monto.setScale(2);
		bean.setDescuento(String.valueOf(monto));					
		} catch(Exception e) {
			bean.setDescuento("0.00");
		}		

//("select pa.codigo, pa.codigo_restaurante_local, pa.documento, pa.serie, pa.numero, ");
//("DATE_FORMAT(pa.fec_emision,'%d/%m/%Y %H:%i:%s') fecha_emision, pa.subtotal, pa.igv, pa.total, pa.descuento, ");
//("pa.pago_soles, pa.pago_dolares, pa.pago_visa_credito, pa.pago_master_credito, pa.vuelto, ");
//("pi.codigo, pi.tipo_pago, pi.total ");
		try {	BigDecimal monto = rs.getBigDecimal(11);
				monto = monto.setScale(2);
				bean.setPagoSoles(String.valueOf(monto));					
		} catch(Exception e) {	bean.setPagoSoles("0.00"); }		

		try {	BigDecimal monto = rs.getBigDecimal(12);
		monto = monto.setScale(2);
		bean.setPagoDolares(String.valueOf(monto));					
		} catch(Exception e) {	bean.setPagoDolares("0.00"); }		
		
		try {	BigDecimal monto = rs.getBigDecimal(13);
		monto = monto.setScale(2);
		bean.setPagoVisaCredito(String.valueOf(monto));					
		} catch(Exception e) {	bean.setPagoVisaCredito("0.00"); }		

		try {	BigDecimal monto = rs.getBigDecimal(14);
		monto = monto.setScale(2);
		bean.setPagoMasterCardCredito(String.valueOf(monto));					
		} catch(Exception e) {	bean.setPagoMasterCardCredito("0.00"); }		

		try {	BigDecimal monto = rs.getBigDecimal(15);
		monto = monto.setScale(2);
		bean.setPagoVuelto(String.valueOf(monto));					
		} catch(Exception e) {	bean.setPagoVuelto("0.00"); }		

		bean.setItemTipoPago(rs.getString(17));
		try {	BigDecimal monto = rs.getBigDecimal(18);
		monto = monto.setScale(2);
		bean.setItemTotal(String.valueOf(monto));					
		} catch(Exception e) {	bean.setItemTotal("0.00"); }		
		
		return bean;
	}
	
	  
			

}
