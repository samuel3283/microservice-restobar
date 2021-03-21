package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import pe.com.maprsoft.facturador.model.Franquicia;

public class FranquiciaMapper implements RowMapper<Franquicia> {
	
	public Franquicia mapRow(ResultSet rs, int rowNum) throws SQLException {
		Franquicia bean = new Franquicia();
		bean.setCodigo(rs.getInt(1));
		bean.setNombre(rs.getString(2));
		bean.setRazonSocial(rs.getString(3));
		bean.setRuc(rs.getString(4));
		bean.setDireccion(rs.getString(5));
		bean.setEstado(rs.getInt(6));

		return bean;
	}
	
	  
			

}
