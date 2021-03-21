package pe.com.maprsoft.facturador.dao.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import pe.com.maprsoft.facturador.model.Usuario;

public class UsuarioMapper implements RowMapper<Usuario> {

	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		Usuario bean = new Usuario();
		try{

			bean.setCodigo(rs.getInt(1));
			bean.setCodigoRestauranteLocal(rs.getInt(2));			
			bean.setNombre(rs.getString(3));
			bean.setApellido(rs.getString(4));
			bean.setEmail(rs.getString(5));
			bean.setPerfil(rs.getString(6));
			
			bean.setTipoDocumento(rs.getString(7));
			bean.setNumDocumento(rs.getString(8));
			bean.setTelefono(rs.getString(9));			
			bean.setEstado(rs.getInt(10));
			bean.setUsuario(rs.getString(11));
			
			/*SELECT u.codigo, u.codigo_restaurante_local, u.nombre, u.apellido, u.email, u.perfil, ");
			u.tipodoc, u.numdoc, u.telefono,  u.estado, ");
			r.codigo cod_rest, r.nombre nombre_rest, r.razon_social, r.ruc, r.direccion direccion_rest, ");
			l.codigo cod_local, l.nombre nombre_local, l.direccion direccion_local ");
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}				
          return bean;    
	}

}
