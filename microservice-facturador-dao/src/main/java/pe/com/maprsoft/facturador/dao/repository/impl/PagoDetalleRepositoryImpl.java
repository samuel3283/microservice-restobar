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
import pe.com.maprsoft.facturador.dao.repository.PagoDetalleRepository;
import pe.com.maprsoft.facturador.dao.repository.PedidoRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.DetallePedidoMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.DetallePedidoToPagoMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.PagoDetalleMapper;
import pe.com.maprsoft.facturador.model.DetallePedido;
import pe.com.maprsoft.facturador.model.Pago;
import pe.com.maprsoft.facturador.model.PagoDetalle;
import pe.com.maprsoft.facturador.model.Pedido;
import pe.com.maprsoft.facturador.model.core.StringUtil;


@SuppressWarnings("all")
@Repository
public class PagoDetalleRepositoryImpl implements PagoDetalleRepository {

	private final Logger logger = LoggerFactory.getLogger(PagoDetalleRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;

	public Double formatearDecimales(Double numero, Integer numeroDecimales) {
		return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
	}

	@Override
	public boolean insert(PagoDetalle pagoDetalle) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_pago_detalle ");
		sql_insert_user.append("(codigo_pago, descripcion, cantidad, precio_unitario,precio_bruto_unitario,subtotal,subtotal_bruto, igv, descuento, estado, moneda) ");
		sql_insert_user.append("VALUES (?,?,?,?,?,?,?,?,?,?,?) ");

		String fechaHora = StringUtil.getFechaDateToFormat("yyyy-MM-dd HH:mm:ss");;
        
        BigDecimal precio = new BigDecimal("0.00");
        BigDecimal igv = new BigDecimal("0.00");
        BigDecimal total = new BigDecimal("0.00");
        BigDecimal subTotalBruto = new BigDecimal("0.00");

		BigDecimal descuento = new BigDecimal("0.00");
		if(pagoDetalle.getDescuento()==null)
			pagoDetalle.setDescuento("0");

        try {
        	descuento = new BigDecimal(pagoDetalle.getDescuento());
        } catch(Exception e) {
        	descuento = new BigDecimal("0.00");
        }            
		
        double precioBruto = Double.parseDouble(pagoDetalle.getPrecio()) / Double.parseDouble(String.valueOf("1.18"));
        precioBruto = formatearDecimales(precioBruto, 2);
        try {
        	precio = new BigDecimal(pagoDetalle.getPrecio());
        } catch(Exception e) {
        	precio = new BigDecimal("0.00");
        }            
        try {
        	igv = new BigDecimal(pagoDetalle.getIgv());
        } catch(Exception e) {
        	igv = new BigDecimal("0.00");
        }            
        try {
        	total = new BigDecimal(pagoDetalle.getSubTotal());
        } catch(Exception e) {
        	total = new BigDecimal("0.00");
        }            
        try {
        	subTotalBruto = new BigDecimal(pagoDetalle.getSubTotalBruto());
        } catch(Exception e) {
        	subTotalBruto = new BigDecimal("0.00");
        }
		Object[] params = new Object[] {
		pagoDetalle.getCodigoPago(), pagoDetalle.getNombre(), pagoDetalle.getCantidad(),
		precio,  precioBruto, total, subTotalBruto, igv, descuento, pagoDetalle.getEstado(), pagoDetalle.getMoneda() };
		
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = Boolean.TRUE;
		return resultado;
	}

	@Override
	public List<PagoDetalle> list(Pago pago) throws Exception {
		List <PagoDetalle> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_pago, descripcion, cantidad, precio_unitario, subtotal, ");
		sql_find_user.append("estado, moneda, descuento ");
		sql_find_user.append("from tbl_pago_detalle ");
		sql_find_user.append("where codigo_pago = ?  order by 1 ");
		
		Object[] params = new Object[] { pago.getCodigo() };
		
		lista = (List <PagoDetalle>)jdbcTemplate.query(sql_find_user.toString(), params, new PagoDetalleMapper());		
		return lista;	}

	

}
