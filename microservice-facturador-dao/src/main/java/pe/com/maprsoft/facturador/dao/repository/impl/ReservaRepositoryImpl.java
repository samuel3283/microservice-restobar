package pe.com.maprsoft.facturador.dao.repository.impl;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.com.maprsoft.facturador.dao.repository.PedidoRepository;
import pe.com.maprsoft.facturador.dao.repository.ReservaRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.PedidoMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.ReservaMapper;
import pe.com.maprsoft.facturador.model.Pedido;
import pe.com.maprsoft.facturador.model.Reserva;


@SuppressWarnings("all")
@Repository
public class ReservaRepositoryImpl implements ReservaRepository {

	private final Logger logger = LoggerFactory.getLogger(ReservaRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;

	@Override
	public Integer insert(Reserva reserva) throws Exception {
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_reserva ");
		sql_insert_user.append("(codigo_restaurante_local,codigo_mesa, codigo_mozo, comensales,estado, ");
		sql_insert_user.append("tipo_documento,numero_documento, telefono, ");
		sql_insert_user.append("contacto,comentario, fec_reserva, fec_registro) ");
		sql_insert_user.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ");
		//sql_insert_user.append("VALUES (?,?,?,?,STR_TO_DATE(?, '%d/%m/%Y %H:%i:%s'),?) ");
		//codigo_cliente

		java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaHora = sdf.format(dt);
                
		Object[] params = new Object[] {
		reserva.getCodigoSucursal(), null, null, reserva.getPersonas(), reserva.getEstado(), 
		reserva.getTipoDocumento(), reserva.getNumDocumento(), reserva.getTelefono(),
		reserva.getContacto(), reserva.getComentario(), reserva.getFechaReserva(), fechaHora
		};
		
		jdbcTemplate.update(sql_insert_user.toString(), params);
		Integer valor = getIdReserva(reserva);
		return valor;
	}

	public Integer getIdReserva(Reserva reserva) throws Exception {
		Integer bean = new Integer(0);
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("SELECT max(codigo) as id from tbl_reserva ");
		sql_find_user.append("where codigo_restaurante_local = ? and estado = ? ");		
		Object[] params = new Object[] { reserva.getCodigoSucursal(), reserva.getEstado() };
		bean = jdbcTemplate.queryForObject(sql_find_user.toString(), params, Integer.class);
		
  		return bean;
	}
	
	
	@Override
	public List<Reserva> list(Reserva reserva) throws Exception {
		List <Reserva> lista = null;
		Reserva bean = new Reserva();
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_restaurante_local,codigo_mesa, codigo_mozo, comensales, ");
		sql_find_user.append("estado, contacto,comentario, ");
		sql_find_user.append("DATE_FORMAT(fec_reserva,'%d/%m/%Y') fec_reserva, ");
		sql_find_user.append("DATE_FORMAT(fec_reserva,'%h:%i %p') hor_reserva, ");
		sql_find_user.append("DATE_FORMAT(fec_registro,'%d/%m/%Y %H:%i:%s') fec_registro, ");	
		sql_find_user.append("tipo_documento, numero_documento, telefono ");
		
		sql_find_user.append("from tbl_reserva ");				  
		sql_find_user.append("where estado = ? and codigo_restaurante_local = ? ");
		sql_find_user.append("order by fec_reserva desc ");				  				
		Object[] params = new Object[] { reserva.getEstado(), reserva.getCodigoSucursal() };
		lista = (List <Reserva>)jdbcTemplate.query(sql_find_user.toString(), params, new ReservaMapper());    	
		return lista;
	}

	
	@Override
	public Reserva getById(Reserva reserva) throws Exception {
		List <Reserva> lista = null;
		Reserva bean = new Reserva();
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_restaurante_local,codigo_mesa, codigo_mozo, comensales, ");
		sql_find_user.append("estado, contacto,comentario, ");
		sql_find_user.append("DATE_FORMAT(fec_reserva,'%d/%m/%Y %H:%i:%s') fec_reserva, ");
		sql_find_user.append("DATE_FORMAT(fec_reserva,'%h:%i %p') hor_reserva, ");
		sql_find_user.append("DATE_FORMAT(fec_registro,'%d/%m/%Y %H:%i:%s') fec_registro, ");	
		sql_find_user.append("tipo_documento, numero_documento, telefono ");
		sql_find_user.append("from tbl_reserva ");				  
		sql_find_user.append("where codigo = ? ");
						
		Object[] params = new Object[] { reserva.getCodigo() };
		lista = (List <Reserva>)jdbcTemplate.query(sql_find_user.toString(), params, new ReservaMapper());    	
    	if(lista!=null && lista.size()>0) {    		
    		bean = new Reserva();
    		bean = lista.get(0);
    	}	    	
		return bean;
	}

	
	@Override
	public void updateStatus(Reserva reserva) throws Exception {
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_reserva ");
		sql_insert_user.append("SET estado = ? ");
		sql_insert_user.append("WHERE codigo = ? ");
		
		java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaHora = sdf.format(dt);
                
		Object[] params = new Object[] { reserva.getEstado(), reserva.getCodigo() };
		
		jdbcTemplate.update(sql_insert_user.toString(), params);
	}

	
	@Override
	public void update(Reserva reserva) throws Exception {
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_reserva ");
		sql_insert_user.append("SET comensales = ?, contacto = ?, comentario= ?, ");
		sql_insert_user.append("fec_reserva = ? ");
		sql_insert_user.append("WHERE codigo = ? ");
		
                
		Object[] params = new Object[] { reserva.getPersonas(), reserva.getContacto(), reserva.getComentario(),
				reserva.getFechaReserva(), reserva.getCodigo()};
		
		jdbcTemplate.update(sql_insert_user.toString(), params);
	}

	@Override
	public void delete(Reserva reserva) throws Exception {
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("delete from tbl_reserva ");
		sql_insert_user.append("WHERE codigo = ? ");
		
                
		Object[] params = new Object[] { reserva.getCodigo()};
		
		jdbcTemplate.update(sql_insert_user.toString(), params);
	}

	
}
