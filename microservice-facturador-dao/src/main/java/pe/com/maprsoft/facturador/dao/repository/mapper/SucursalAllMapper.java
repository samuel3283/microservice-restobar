package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import pe.com.maprsoft.facturador.model.Franquicia;
import pe.com.maprsoft.facturador.model.Restaurante;
import pe.com.maprsoft.facturador.model.Sucursal;

public class SucursalAllMapper implements RowMapper<Sucursal> {
	
	public Sucursal mapRow(ResultSet rs, int rowNum) throws SQLException {
		Sucursal bean = new Sucursal();

		Franquicia franquicia = new Franquicia();
		Restaurante restaurante = new Restaurante();

		franquicia.setCodigo(rs.getInt(1));
		franquicia.setRazonSocial(rs.getString(2));
		franquicia.setRuc(rs.getString(3));
		franquicia.setDireccion(rs.getString(4));
		restaurante.setFranquicia(franquicia);
		
		restaurante.setCodigo(rs.getInt(5));
		restaurante.setRazon_social(rs.getString(6));
		restaurante.setRuc(rs.getString(7));
		restaurante.setDireccion(rs.getString(8));

		bean.setRestaurante(restaurante);
		bean.setCodigo(rs.getInt(9));
		bean.setNombre(rs.getString(10));
		bean.setDireccion(rs.getString(11));

		return bean;
	}

			

}
