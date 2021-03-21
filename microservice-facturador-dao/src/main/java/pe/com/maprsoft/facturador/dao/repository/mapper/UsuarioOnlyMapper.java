package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import pe.com.maprsoft.facturador.model.Restaurante;
import pe.com.maprsoft.facturador.model.Sucursal;
import pe.com.maprsoft.facturador.model.Usuario;

public class UsuarioOnlyMapper implements RowMapper<Usuario> {

	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		Usuario bean = new Usuario();
		try{
			bean.setCodigo(rs.getInt(1));
			bean.setCodigoRestauranteLocal(rs.getInt(2));	
			bean.setUsuario(rs.getString(3));
			
			bean.setNombre(rs.getString(4));
			bean.setApellido(rs.getString(5));
			bean.setEmail(rs.getString(6));
			
			bean.setTipoDocumento(rs.getString(7));
			bean.setNumDocumento(rs.getString(8));
			bean.setTelefono(rs.getString(9));			
			bean.setPerfil(rs.getString(10));
			bean.setFechaRegistro(rs.getString(11));
			bean.setEstado(rs.getInt(12));
			bean.setEstadoTbl(rs.getInt(13));
			
			bean.setSucursal(new Sucursal(rs.getInt(2)));	
			/*
			bean.getSucursal().setRestaurante(new Restaurante(rs.getInt(14)));
			bean.setCodigoRestaurante(rs.getInt(14));	
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}				
          return bean;    
	}

}
