package pe.com.maprsoft.facturador.dao.repository.impl;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.maprsoft.facturador.dao.repository.DetallePedidoRepository;
import pe.com.maprsoft.facturador.dao.repository.PedidoRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.DetallePedidoMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.DetallePedidoToPagoMapper;
import pe.com.maprsoft.facturador.model.DetallePedido;
import pe.com.maprsoft.facturador.model.Pedido;


@SuppressWarnings("all")
@Repository
public class DetallePedidoRepositoryImpl implements DetallePedidoRepository {

	private final Logger logger = LoggerFactory.getLogger(DetallePedidoRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean insert(DetallePedido detallePedido, Integer idPedido) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_pedido_detalle ");
		sql_insert_user.append("(codigo_producto, codigo_pedido, comentario, nombre, moneda, precio, lugar_elaboracion, tipo, estado, fec_registro) ");
		sql_insert_user.append("VALUES (?,?,?,?,?,?,?,?,?,?) ");
		  
		java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaHora = sdf.format(dt);

        BigDecimal precio = new BigDecimal("0.00");
        try {
        	precio = new BigDecimal(detallePedido.getPrecio());
        } catch(Exception e) {
        	precio = new BigDecimal("0.00");
        	logger.info("Error convert bigdecimal: "+e.getMessage());
        }            
        
		Object[] params = new Object[] {
		detallePedido.getCodigoProducto(), idPedido, detallePedido.getComentario(),
		detallePedido.getNombre(), detallePedido.getMoneda(), precio,
		detallePedido.getLugarElaboracion(), detallePedido.getTipo(),
		detallePedido.getEstado(), fechaHora
		};
		
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	
	@Override
	public boolean deleteItem(DetallePedido detallePedido) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("delete from tbl_pedido_detalle ");
		sql_insert_user.append("where codigo = ? ");
		          
		Object[] params = new Object[] { detallePedido.getCodigo() };		
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}


	@Override
	public List<DetallePedido> list(Pedido pedido) throws Exception {
		List <DetallePedido> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_producto, codigo_pedido, comentario, estado, ");
		sql_find_user.append("DATE_FORMAT(fec_registro,'%d/%m/%Y %H:%i:%s') fec_registro, nombre, moneda, precio, ");
		sql_find_user.append("lugar_elaboracion, tipo ");	
		sql_find_user.append("from tbl_pedido_detalle ");
		sql_find_user.append("where codigo_pedido = ?  order by 1 ");

		Object[] params = new Object[] { pedido.getCodigo() };
		
		lista = (List <DetallePedido>)jdbcTemplate.query(sql_find_user.toString(), params, new DetallePedidoMapper());		
		return lista;
	}


	@Override
	public List<DetallePedido> listPago(Pedido pedido) throws Exception {
			List <DetallePedido> lista = null;
			StringBuilder sql_find_user = new StringBuilder();
			sql_find_user.append("SELECT p.codigo_producto, p.nombre, p.moneda, p.precio, count(*) cantidad ");
			sql_find_user.append("FROM tbl_pedido_detalle p ");
			sql_find_user.append("WHERE p.codigo_pedido = ? ");
			//sql_find_user.append("AND p.estado= ? ");
			sql_find_user.append("GROUP BY p.codigo_producto, p.nombre, p.moneda, p.precio ");
			sql_find_user.append("ORDER BY p.nombre ");				  
			
			
			Object[] params = new Object[] {
			pedido.getCodigo() };

			lista = (List <DetallePedido>)jdbcTemplate.query(sql_find_user.toString(), params, new DetallePedidoToPagoMapper());		  	
			return lista;
		}

	@Override
	public List<DetallePedido> listPagoMultpile(Pedido pedido) throws Exception {
			List <DetallePedido> lista = null;
			StringBuilder sql_find_user = new StringBuilder();
			sql_find_user.append("SELECT p.codigo_producto, p.nombre, p.moneda, p.precio, 1 cantidad ");
			sql_find_user.append("FROM tbl_pedido_detalle p ");
			sql_find_user.append("WHERE p.codigo_pedido = ? ");
			//sql_find_user.append("AND p.estado= ? ");
			//sql_find_user.append("GROUP BY p.codigo_producto, p.nombre, p.moneda, p.precio ");
			sql_find_user.append("ORDER BY p.nombre ");				  
			
			
			Object[] params = new Object[] {
			pedido.getCodigo() };

			lista = (List <DetallePedido>)jdbcTemplate.query(sql_find_user.toString(), params, new DetallePedidoToPagoMapper());		  	
			return lista;
		}


	@Override
	public boolean updateItem(DetallePedido detallePedido) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean updateStatus(DetallePedido detallePedido) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_pedido_detalle ");
		sql_insert_user.append("SET estado  = ? ");
		sql_insert_user.append("WHERE codigo = ? ");
		          
		Object[] params = new Object[] { detallePedido.getEstado(), detallePedido.getCodigo() };		
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public boolean delete(Integer codigoPedido) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("DELETE FROM tbl_pedido_detalle ");
		sql_insert_user.append("WHERE codigo_pedido = ? ");		          
		Object[] params = new Object[] { codigoPedido };		
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	
	
}
