package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import pe.com.maprsoft.facturador.model.Franquicia;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Restaurante;
import pe.com.maprsoft.facturador.model.Sucursal;

public class RestauranteMapper implements RowMapper<Restaurante> {
	
	public Restaurante mapRow(ResultSet rs, int rowNum) throws SQLException {
		Restaurante bean = new Restaurante();
		bean.setCodigo(rs.getInt(1));
		
		bean.setFranquicia(new Franquicia(rs.getInt(2)));
		
		bean.setNombre(rs.getString(3));
		bean.setRazon_social(rs.getString(4));
		bean.setRuc(rs.getString(5));
		bean.setDireccion(rs.getString(6));
		bean.setEstado(rs.getInt(7));

		return bean;
	}
	
	  
			

}
