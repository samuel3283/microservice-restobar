package pe.com.maprsoft.facturador.dao.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.maprsoft.facturador.dao.repository.MesaRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.MesaMapper;
import pe.com.maprsoft.facturador.model.Mesa;

@SuppressWarnings("all")
@Repository
public class MesaRepositoryImpl implements MesaRepository {

	private final Logger logger = LoggerFactory.getLogger(MesaRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Mesa> list(Integer idSalon) throws Exception {
		List <Mesa> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, nombre, nombre_breve, capacidad, estado, estado_tbl ");		
		sql_find_user.append("from tbl_mesa where codigo_salon = ? and estado_tbl = 1 ");
		sql_find_user.append("order by 1 ");
		
		Object[] params = new Object[] { idSalon };
		lista = (List <Mesa>)jdbcTemplate.query(sql_find_user.toString(), params, new MesaMapper());
		return lista;
	}

	@Override
	public List<Mesa> listAll(Integer idSalon) throws Exception {
		List <Mesa> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, nombre, nombre_breve, capacidad, estado, estado_tbl ");		
		sql_find_user.append("from tbl_mesa where codigo_salon = ? ");
		sql_find_user.append("order by 1 ");
		
		Object[] params = new Object[] { idSalon };
		lista = (List <Mesa>)jdbcTemplate.query(sql_find_user.toString(), params, new MesaMapper());
		return lista;
	}

	@Override
	public boolean deleteAll(Integer codigoSalon) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("DELETE FROM tbl_mesa ");
		sql_insert_user.append("WHERE codigo_salon = ? ");		
		Object[] params = new Object[] { codigoSalon };	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean updateStatus(Mesa mesa) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_mesa ");
		sql_insert_user.append("SET estado = ?  ");
		sql_insert_user.append("WHERE codigo = ? ");
		Object[] params = new Object[] { mesa.getEstado(), mesa.getId()	};	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean update(Mesa mesa) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_mesa ");
		sql_insert_user.append("SET nombre = ?, nombre_breve = ?, capacidad = ?, estado_tbl = ?  ");
		sql_insert_user.append("WHERE codigo = ? ");
		Object[] params = new Object[] { mesa.getNombre(), mesa.getNombreBreve(), mesa.getCapacidad(), 
				mesa.getEstadoTbl(), mesa.getId()	};	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean delete(Mesa mesa) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("DELETE FROM tbl_mesa ");
		sql_insert_user.append("WHERE codigo = ? ");
		Object[] params = new Object[] { mesa.getId()	};	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean insert(Mesa mesa) throws Exception {
		boolean resultado = Boolean.FALSE;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_mesa ");
		sql_insert_user.append("(codigo_salon,nombre, nombre_breve, capacidad, estado, fec_registro) ");
		sql_insert_user.append("VALUES (?,?,?,?,?,?) ");

		java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaHora = sdf.format(dt);

		Object[] params = new Object[] { mesa.getCodigoSalon(), mesa.getNombre(), mesa.getNombreBreve(), mesa.getCapacidad(),
				mesa.getEstado(), fechaHora	};	
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}


}
