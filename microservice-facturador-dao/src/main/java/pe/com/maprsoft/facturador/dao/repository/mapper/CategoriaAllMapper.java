package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import pe.com.maprsoft.facturador.model.Categoria;
import pe.com.maprsoft.facturador.model.ListaCategoria;
import pe.com.maprsoft.facturador.model.SubCategoria;
import pe.com.maprsoft.facturador.model.core.MonedaCodeEnum;

public class CategoriaAllMapper implements RowMapper<ListaCategoria> {
	
	public ListaCategoria mapRow(ResultSet rs, int rowNum) throws SQLException {
		ListaCategoria bean = new ListaCategoria();
		bean.setIdCategoria(rs.getInt(1));
		bean.setNombreCategoria(rs.getString(2));
		
		bean.setIdSubCategoria(rs.getInt(3));
		bean.setNombreSubCategoria(rs.getString(4));
		
		bean.setIdProducto(rs.getInt(5));
		bean.setNombreProducto(rs.getString(6));
		bean.setPrecioProducto(rs.getString(7));
		
		
		if(MonedaCodeEnum.MONEDA_SOLES_PERU.getCode().intValue()==rs.getInt(8)) {
			bean.setMonedaProducto(MonedaCodeEnum.MONEDA_SOLES_PERU.getMessage());	
		} else if(MonedaCodeEnum.MONEDA_DOLAR_AMERICANO.getCode().intValue()==rs.getInt(8)) {
			bean.setMonedaProducto(MonedaCodeEnum.MONEDA_DOLAR_AMERICANO.getMessage());	
		} else if(MonedaCodeEnum.MONEDA_EURO_EUROPA.getCode().intValue()==rs.getInt(8)) {
			bean.setMonedaProducto(MonedaCodeEnum.MONEDA_EURO_EUROPA.getMessage());	
		}	
		//bean.setMonedaProducto(rs.getString(8));
		bean.setLugarElaboracion(rs.getString(9));
		bean.setTipo(rs.getString(10));
				
		return bean;
	}

			

}
