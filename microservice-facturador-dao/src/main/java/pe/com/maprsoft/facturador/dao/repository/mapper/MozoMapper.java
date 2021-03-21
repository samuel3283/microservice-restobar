package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Sucursal;

public class MozoMapper implements RowMapper<Mozo> {
	
	public Mozo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Mozo bean = new Mozo();
		bean.setCodigo(rs.getInt(1));
		bean.setNombre(rs.getString(3));
		bean.setTurno(rs.getString(4));
		bean.setEstado(rs.getInt(5));
		
		Sucursal sucursal = new Sucursal();
		sucursal.setCodigo(rs.getInt(2));

		bean.setSucursal(sucursal);
				
		return bean;
	}
	
	  
			

}
