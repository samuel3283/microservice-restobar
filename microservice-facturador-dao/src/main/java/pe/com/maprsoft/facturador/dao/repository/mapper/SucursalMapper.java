package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import pe.com.maprsoft.facturador.model.Sucursal;

public class SucursalMapper implements RowMapper<Sucursal> {
	
	public Sucursal mapRow(ResultSet rs, int rowNum) throws SQLException {
		Sucursal bean = new Sucursal();
		bean.setCodigo(rs.getInt(1));
		bean.setNombre(rs.getString(3));
		bean.setEstado(rs.getInt(4));
		bean.setDireccion(rs.getString(5));
		return bean;
	}

			

}
