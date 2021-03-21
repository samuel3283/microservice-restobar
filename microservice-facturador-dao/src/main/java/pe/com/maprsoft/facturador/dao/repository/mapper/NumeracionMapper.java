package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Numeracion;
import pe.com.maprsoft.facturador.model.Sucursal;

public class NumeracionMapper implements RowMapper<Numeracion> {
	
	public Numeracion mapRow(ResultSet rs, int rowNum) throws SQLException {
		Numeracion bean = new Numeracion();
		bean.setCodigo(rs.getInt(1));

		bean.setPeriodo(rs.getString(3));
		bean.setDocumento(rs.getString(4));
		bean.setSerie(rs.getString(5));
		bean.setInicio(rs.getInt(6));
		bean.setFin(rs.getInt(7));
		bean.setCorrelativo(rs.getInt(8));
		bean.setEstado(rs.getInt(10));
		
		Sucursal sucursal = new Sucursal();
		sucursal.setCodigo(rs.getInt(2));
		bean.setSucursal(sucursal);
				
		return bean;
	}
	
	  
			

}
