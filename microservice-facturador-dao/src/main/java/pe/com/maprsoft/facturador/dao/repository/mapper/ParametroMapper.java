package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Parametro;
import pe.com.maprsoft.facturador.model.Sucursal;

public class ParametroMapper implements RowMapper<Parametro> {
	
	public Parametro mapRow(ResultSet rs, int rowNum) throws SQLException {
		Parametro bean = new Parametro();
		bean.setCodigo(rs.getInt(1));
		bean.setGrupo(rs.getString(3));
		bean.setTipo(rs.getString(4));

		bean.setCode(rs.getString(5));
		bean.setValor(rs.getString(6));
		bean.setValorBreve(rs.getString(7));

		bean.setEstado(rs.getInt(8));
		bean.setSucursal(new Sucursal(rs.getInt(2)));				
		return bean;
	}
	
	  
			

}
