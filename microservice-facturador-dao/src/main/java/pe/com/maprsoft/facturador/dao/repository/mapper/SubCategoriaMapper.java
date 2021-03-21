package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import pe.com.maprsoft.facturador.model.SubCategoria;

public class SubCategoriaMapper implements RowMapper<SubCategoria> {
	
	public SubCategoria mapRow(ResultSet rs, int rowNum) throws SQLException {
		SubCategoria bean = new SubCategoria();
		bean.setId(rs.getInt(1));
		bean.setNombre(rs.getString(2));
		bean.setEstado(rs.getInt(3));
		
		return bean;
	}

			

}
