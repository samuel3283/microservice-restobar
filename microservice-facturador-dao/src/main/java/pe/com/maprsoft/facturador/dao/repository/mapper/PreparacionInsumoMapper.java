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

public class PreparacionInsumoMapper implements RowMapper<Preparacion> {
	
	public Preparacion mapRow(ResultSet rs, int rowNum) throws SQLException {
		Preparacion bean = new Preparacion();
		bean.setCodigo(rs.getInt(1));
		bean.setMedida(rs.getString(2));
		bean.setCantidad(rs.getString(3));
		bean.setEstado(rs.getInt(4));
		bean.setCodigoInsumo(rs.getInt(5));
		Insumo insumo = new Insumo();
		insumo.setCodigo(rs.getInt(5));				
		Producto producto = new Producto();
		producto.setId(rs.getInt(6));
		bean.setCodigoProducto(rs.getInt(6));
		bean.setInsumo(insumo);
		bean.setProducto(producto);				
		return bean;
	}
	
	  
			

}
