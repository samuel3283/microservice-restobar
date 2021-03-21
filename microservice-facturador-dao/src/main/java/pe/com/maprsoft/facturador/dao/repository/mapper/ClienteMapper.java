package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import pe.com.maprsoft.facturador.model.Cliente;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Sucursal;

public class ClienteMapper implements RowMapper<Cliente> {
	
	public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cliente bean = new Cliente();
		bean.setCodigo(rs.getInt(1));
		bean.setNombre(rs.getString(2));
		bean.setDireccion(rs.getString(3));
		bean.setTelefono(rs.getString(4));
		bean.setReferencia(rs.getString(5));
		
		return bean;
	}
	
	  
			

}
