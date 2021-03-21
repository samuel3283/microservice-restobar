package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import pe.com.maprsoft.facturador.model.Categoria;

public class MapMapper implements RowMapper<Map<String, Object>> {
	
	public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("value",rs.getString(3));
		mapa.put("label",rs.getString(2));
		mapa.put("code",rs.getString(1));
		//pd.nombre, sum(pd.precio), count(pd.nombre)		
		return mapa;
	}

			

}
