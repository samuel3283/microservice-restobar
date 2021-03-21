package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import pe.com.maprsoft.facturador.model.Categoria;

public class CategoriaMapper implements RowMapper<Categoria> {
	
	public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {
		Categoria bean = new Categoria();
		bean.setId(rs.getInt(1));
		bean.setNombre(rs.getString(2));
		bean.setEstado(rs.getInt(3));
		
		return bean;
	}

			

}
