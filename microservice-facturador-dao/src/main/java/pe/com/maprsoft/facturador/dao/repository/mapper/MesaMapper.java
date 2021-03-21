package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.maprsoft.facturador.model.Mesa;

public class MesaMapper implements RowMapper<Mesa> {
	
	public Mesa mapRow(ResultSet rs, int rowNum) throws SQLException {
		Mesa bean = new Mesa();
		bean.setId(rs.getInt(1));
		bean.setNombre(rs.getString(2));
		bean.setNombreBreve(rs.getString(3));
		bean.setCapacidad(rs.getInt(4));
		bean.setEstado(rs.getInt(5));		
		bean.setEstadoTbl(rs.getInt(6));		
		return bean;
	}

			

}
