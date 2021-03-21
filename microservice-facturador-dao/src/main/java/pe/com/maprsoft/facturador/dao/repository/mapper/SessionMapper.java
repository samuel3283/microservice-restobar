package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import pe.com.maprsoft.facturador.model.Session;


public class SessionMapper implements RowMapper<Session> {

	public Session mapRow(ResultSet rs, int rowNum) throws SQLException {
		Session bean = new Session();
		try{
			bean.setCodigo(rs.getInt(1));
			bean.setCodigoRestauranteLocal(rs.getInt(2));
			
			bean.setDevice(rs.getString(3));
			bean.setDeviceType(rs.getString(4));
			bean.setToken(rs.getString(5));
			bean.setUsuario(rs.getString(6));
			
			bean.setNombre(rs.getString(7));
			bean.setApellido(rs.getString(8));
			bean.setEmail(rs.getString(9));
			bean.setTelefono(rs.getString(10));
			bean.setPerfil(rs.getString(11));
			bean.setTipoDocumento(rs.getString(12));
			bean.setNumDocumento(rs.getString(13));

		} catch (Exception e) {
			e.printStackTrace();
		}				
          return bean;    
	}

}
