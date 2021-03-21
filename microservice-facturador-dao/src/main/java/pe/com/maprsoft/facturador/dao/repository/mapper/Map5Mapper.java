package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import pe.com.maprsoft.facturador.model.Categoria;

public class Map5Mapper implements RowMapper<Map<String, Object>> {
	
	public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("value1",rs.getString(1));
		mapa.put("value2",rs.getString(2));
		mapa.put("value3",rs.getString(3));
		mapa.put("value4",rs.getString(4));
		mapa.put("value5",rs.getString(5));
		//("select pa.documento, pa.serie, sum(pa.total), min(pa.numero), max(pa.numero) ");
		return mapa;
	}

			

}
