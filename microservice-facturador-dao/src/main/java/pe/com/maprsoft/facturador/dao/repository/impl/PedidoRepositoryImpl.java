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
import pe.com.maprsoft.facturador.dao.repository.mapper.PedidoDeliveryMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.PedidoMapper;
import pe.com.maprsoft.facturador.model.Pedido;
import pe.com.maprsoft.facturador.model.core.StringUtil;


@SuppressWarnings("all")
@Repository
public class PedidoRepositoryImpl implements PedidoRepository {

	private final Logger logger = LoggerFactory.getLogger(PedidoRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;

	@Override
	public Integer insert(Pedido pedido) throws Exception {
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_pedido ");
		sql_insert_user.append("(codigo_restaurante_local,codigo_mesa, codigo_mozo, codigo_reserva,codigo_cliente,tipo_pedido,comensales,estado,fec_pedido, fec_registro) ");
		sql_insert_user.append("VALUES (?,?,?,?,?,?,?,?,?,?) ");
		//sql_insert_user.append("VALUES (?,?,?,?,STR_TO_DATE(?, '%d/%m/%Y %H:%i:%s'),?) ");
		
		
		String fechaHora = StringUtil.getFechaDateToFormat("yyyy-MM-dd HH:mm:ss");;
                        
		Object[] params = new Object[] {
		pedido.getCodigoSucursal(), pedido.getMesa().getId(), pedido.getMozo().getCodigo(), pedido.getReserva().getCodigo(),
		pedido.getCliente().getCodigo(), pedido.getTipo(), pedido.getPersonas(), pedido.getEstado(), fechaHora,fechaHora
		};
		
		jdbcTemplate.update(sql_insert_user.toString(), params);
		Integer valor = getIdPedido(pedido);
		return valor;
	}
	
	public Integer getIdPedido(Pedido pedido) throws Exception {
		Integer bean = new Integer(0);
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("SELECT max(codigo) as id from tbl_pedido ");
		sql_find_user.append("where codigo_mesa = ? and estado = ? ");
		
		Object[] params = new Object[] { pedido.getMesa().getId(), pedido.getEstado() };
		bean = jdbcTemplate.queryForObject(sql_find_user.toString(), params, Integer.class);
  		return bean;
	}
	
	
	@Override
	public Pedido get(Pedido pedido) throws Exception {
		List <Pedido> lista = null;
		Pedido bean = new Pedido();
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select p.codigo,p.codigo_mesa, p.tipo_pedido,p.comensales,p.estado, ");
		sql_find_user.append("DATE_FORMAT(p.fec_pedido,'%d/%m/%Y %H:%i:%s') fec_pedido, p.codigo_mozo, ");
		sql_find_user.append("m.nombre, m.nombre_breve, m.capacidad, m.estado estado_mesa  ");
		sql_find_user.append("from tbl_pedido p ");				  
		sql_find_user.append("left join tbl_mesa m on m.codigo = p.codigo_mesa ");
		sql_find_user.append("where p.codigo_mesa = ? and p.estado in (1,2) and codigo_restaurante_local = ? ");
		//1: Presencial 3: rReserva
		
		Object[] params = new Object[] {
		pedido.getMesa().getId(), pedido.getCodigoSucursal() };

		lista = (List <Pedido>)jdbcTemplate.query(sql_find_user.toString(), params, new PedidoMapper());		
    	if(lista!=null && lista.size()>0) {    		
    		bean = new Pedido();
    		bean = lista.get(0);
    	}	    	
		return bean;
	}


	@Override
	public Pedido getPedido(Pedido pedido) throws Exception {
		List <Pedido> lista = null;
		Pedido bean = new Pedido();
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select p.codigo,p.codigo_mesa, p.tipo_pedido,p.comensales,p.estado, ");
		sql_find_user.append("DATE_FORMAT(p.fec_pedido,'%d/%m/%Y %H:%i:%s') fec_pedido, p.codigo_mozo, ");
		sql_find_user.append("m.nombre, m.nombre_breve, m.capacidad, m.estado estado_mesa  ");
		sql_find_user.append("from tbl_pedido p ");				  
		sql_find_user.append("left join tbl_mesa m on m.codigo = p.codigo_mesa ");
		sql_find_user.append("where p.codigo_mesa = ? and p.estado in (1,3) and codigo_restaurante_local = ? ");
		//1: Presencial 3: rReserva
		
		Object[] params = new Object[] {
		pedido.getMesa().getId(), pedido.getCodigoSucursal() };

		lista = (List <Pedido>)jdbcTemplate.query(sql_find_user.toString(), params, new PedidoMapper());		
    	if(lista!=null && lista.size()>0) {    		
    		bean = new Pedido();
    		bean = lista.get(0);
    	}	    	
		return bean;
	}

	
	@Override
	public Pedido getById(Pedido pedido) throws Exception {
		List <Pedido> lista = null;
		Pedido bean = new Pedido();
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select p.codigo,p.codigo_mesa, p.tipo_pedido,p.comensales,p.estado, ");
		sql_find_user.append("DATE_FORMAT(p.fec_pedido,'%d/%m/%Y %H:%i:%s') fec_pedido, p.codigo_mozo ");
		sql_find_user.append("from tbl_pedido p ");				  
		sql_find_user.append("where p.codigo = ? ");
		
		Object[] params = new Object[] { pedido.getCodigo() };
		
		lista = (List <Pedido>)jdbcTemplate.query(sql_find_user.toString(), params, new PedidoMapper());
    	if(lista!=null && lista.size()>0) {    		
    		bean = new Pedido();
    		bean = lista.get(0);
    	}	    	
		return bean;
	}

	
	@Override
	public void updateStatus(Pedido pedido) throws Exception {
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_pedido ");
		sql_insert_user.append("SET estado = ? ");
		sql_insert_user.append("WHERE codigo = ? ");
		
		String fechaHora = StringUtil.getFechaDateToFormat("yyyy-MM-dd HH:mm:ss");;
                
		Object[] params = new Object[] {
		pedido.getEstado(),	 pedido.getCodigo() };
		
		jdbcTemplate.update(sql_insert_user.toString(), params);
	}

	@Override
	public void updateAtendidoCocina(Pedido pedido) throws Exception {
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_pedido ");
		sql_insert_user.append("SET atendido_cocina = ? ");
		sql_insert_user.append("WHERE codigo = ? ");
		
		Object[] params = new Object[] {
		pedido.getAtendidoCocina(),	 pedido.getCodigo() };		
		jdbcTemplate.update(sql_insert_user.toString(), params);
	}

	@Override
	public void updateAtendidoBarra(Pedido pedido) throws Exception {
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_pedido ");
		sql_insert_user.append("SET atendido_barra = ? ");
		sql_insert_user.append("WHERE codigo = ? ");
		
		Object[] params = new Object[] {
		pedido.getAtendidoBarra(),	 pedido.getCodigo() };		
		jdbcTemplate.update(sql_insert_user.toString(), params);
	}

	@Override
	public void update(Pedido pedido) throws Exception {
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_pedido ");
		sql_insert_user.append("SET comensales = ?, codigo_mozo = ? ");
		sql_insert_user.append("WHERE codigo = ? ");
		
		String fechaHora = StringUtil.getFechaDateToFormat("yyyy-MM-dd HH:mm:ss");;
                
		Object[] params = new Object[] {
		pedido.getPersonas(), pedido.getMozo().getCodigo(), pedido.getCodigo() };
		
		jdbcTemplate.update(sql_insert_user.toString(), params);
	}

	@Override
	public void cancelar(Pedido pedido) throws Exception {
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_pedido ");
		sql_insert_user.append("SET motivo_cancelacion = ?, estado = ? ");
		sql_insert_user.append("WHERE codigo = ? ");
		
		Object[] params = new Object[] {
		pedido.getMotivoCancelacion(), pedido.getEstado(), pedido.getCodigo() };
		
		jdbcTemplate.update(sql_insert_user.toString(), params);
	}

	@Override
	public List<Pedido> listDelivery(Pedido pedido) throws Exception {
		List <Pedido> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select p.codigo,p.codigo_mesa, p.tipo_pedido,p.comensales,p.estado, ");
		sql_find_user.append("DATE_FORMAT(p.fec_pedido,'%d/%m/%Y %H:%i:%s') fec_pedido, p.codigo_mozo, ");
		sql_find_user.append("c.codigo cod_cliente, c.nombre, c.direccion, c.telefono, c.referencia ");
		sql_find_user.append("from tbl_pedido p ");				  
		sql_find_user.append("left join tbl_cliente c on c.codigo = p.codigo_cliente ");
		sql_find_user.append("where p.estado in (1,2,4) and  p.tipo_pedido = 2  and  codigo_restaurante_local = ? ");
		sql_find_user.append("order by p.fec_pedido desc ");				  
		//2: Delivery						
		Object[] params = new Object[] { pedido.getCodigoSucursal() };
		lista = (List <Pedido>)jdbcTemplate.query(sql_find_user.toString(), params, new PedidoDeliveryMapper());		   	
		return lista;
	}
	
	
	@Override
	public boolean delete(Pedido pedido) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("delete from tbl_pedido ");
		sql_insert_user.append("where codigo = ? ");
		          
		Object[] params = new Object[] { pedido.getCodigo() };		
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

}
