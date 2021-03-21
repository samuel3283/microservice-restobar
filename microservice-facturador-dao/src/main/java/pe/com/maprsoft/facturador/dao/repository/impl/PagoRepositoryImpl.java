package pe.com.maprsoft.facturador.dao.repository.impl;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.maprsoft.facturador.dao.repository.PagoRepository;
import pe.com.maprsoft.facturador.dao.repository.PedidoRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.PagoMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.PedidoMapper;
import pe.com.maprsoft.facturador.model.Filter;
import pe.com.maprsoft.facturador.model.Pago;
import pe.com.maprsoft.facturador.model.PagoItem;
import pe.com.maprsoft.facturador.model.Pedido;
import pe.com.maprsoft.facturador.model.core.StringUtil;


@SuppressWarnings("all")
@Repository
public class PagoRepositoryImpl implements PagoRepository {

	private final Logger logger = LoggerFactory.getLogger(PagoRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;

	@Override
	public Pago insert(Pago pago) throws Exception {
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_pago ");
		sql_insert_user.append("(codigo_restaurante_local,codigo_pedido,documento, serie,numero,ruc,razon_social, direccion, ");
		sql_insert_user.append("tipo_pago, fec_emision,subtotal,igv,total, descuento,estado,moneda,pago_soles, pago_dolares, pago_visa_credito, pago_master_credito, vuelto,usuario) ");
		sql_insert_user.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		//sql_insert_user.append("VALUES (?,?,?,?,STR_TO_DATE(?, '%d/%m/%Y %H:%i:%s'),?) ");

		BigDecimal vuelto = new BigDecimal("0.00");
		BigDecimal pagoSoles = new BigDecimal("0.00");
		BigDecimal pagoDolares = new BigDecimal("0.00");
		BigDecimal pagoVisaCredito = new BigDecimal("0.00");
		BigDecimal pagoMasterCredito = new BigDecimal("0.00");
		
		BigDecimal descuento = new BigDecimal("0.00");
		if(pago.getDescuento()==null)
			pago.setDescuento("0");
		
		try {			
			descuento = new BigDecimal(pago.getDescuento());			
		} catch(Exception e) {
			descuento = new BigDecimal("0.00");
		}
		try {			
			vuelto = new BigDecimal(pago.getVuelto());
			vuelto = vuelto.setScale(2);
		} catch(Exception e) {
			vuelto = new BigDecimal("0.00");
		}
		try {			
		pagoSoles = new BigDecimal(pago.getPagoSoles());
		pagoSoles = pagoSoles.setScale(2);
		} catch(Exception e) {
			pagoSoles = new BigDecimal("0.00");
		}
		
		int res = pagoSoles.compareTo(vuelto);
		if( res == 1 ) {
			pagoSoles = pagoSoles.subtract(vuelto);
		}
		
		try {			
			pagoDolares = new BigDecimal(pago.getPagoDolares());
			pagoDolares = pagoDolares.setScale(2);
		} catch(Exception e) {
			pagoDolares = new BigDecimal("0.00");
			pago.setPagoDolares("0");
		}
		try {			
			pagoVisaCredito = new BigDecimal(pago.getPagoVisa());
			pagoVisaCredito = pagoVisaCredito.setScale(2);
		} catch(Exception e) {
			pagoVisaCredito = new BigDecimal("0.00");
		}		
		try {			
			pagoMasterCredito = new BigDecimal(pago.getPagoMasterCard());
			pagoMasterCredito = pagoMasterCredito.setScale(2);
		} catch(Exception e) {
			pagoMasterCredito = new BigDecimal("0.00");
		}
		
        
        String fechaHora = StringUtil.getFechaDateToFormat("yyyy-MM-dd HH:mm:ss");;
                
        
		Object[] params = new Object[] {
		pago.getCodigoSucursal(), pago.getPedido().getCodigo(),
		pago.getTipoDocumento(),pago.getSerie(), pago.getNumero(), 
		pago.getRuc(),	pago.getRazonSocial(),pago.getDireccion(),
		pago.getTipo(),fechaHora,pago.getSubTotal(),pago.getIgv(),pago.getTotal(),descuento,
		pago.getEstado(),pago.getMoneda(),
		pagoSoles, pagoDolares, pagoVisaCredito, pagoMasterCredito,vuelto, pago.getUsuario()
		};
		
		jdbcTemplate.update(sql_insert_user.toString(), params);
		//Integer valor = getIdPago(pago);
		Pago bean = getPago(pago);
		
		PagoItem pagoItem = new PagoItem();
		pagoItem.setCodigPago(bean.getCodigo());
		pagoItem.setEstado(1);
		pagoItem.setFechaRegistro(fechaHora);
		
		if(!pago.getPagoSoles().equals("0")) {
			pagoItem.setTotal(pagoSoles.toString());
			pagoItem.setTipo("ES");
			insertItem(pagoItem);
		}
		if(!pago.getPagoDolares().equals("0")) {
			pagoItem.setTotal(pagoDolares.toString());
			pagoItem.setTipo("ED");
			insertItem(pagoItem);
		}		
		if(!pago.getPagoVisa().equals("0")) {
			pagoItem.setTotal(pagoVisaCredito.toString());
			pagoItem.setTipo("VC");
			insertItem(pagoItem);
		}
		if(!pago.getPagoMasterCard().equals("0")) {
			pagoItem.setTotal(pagoMasterCredito.toString());
			pagoItem.setTipo("MC");
			insertItem(pagoItem);
		}
		
		return bean;
	}

	public Integer getIdPago(Pago pago) throws Exception {
		Integer bean = new Integer(0);
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("SELECT max(codigo) as id from tbl_pago ");
		sql_find_user.append("where codigo_restaurante_local = ? and serie = ? and numero = ? and estado = ? ");
		
		Object[] params = new Object[] { pago.getCodigoSucursal(), pago.getSerie(), pago.getNumero(), pago.getEstado() };
		bean = jdbcTemplate.queryForObject(sql_find_user.toString(), params, Integer.class);
  		return bean;
	}
	
	@Override
	public void confirmPay(Pago pago) throws Exception {
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_pago ");
		sql_insert_user.append("SET estado = ? ");
		sql_insert_user.append("WHERE codigo_pedido = ? ");
		                
		Object[] params = new Object[] { pago.getEstado(),pago.getPedido().getCodigo() };		
		jdbcTemplate.update(sql_insert_user.toString(), params);
	}
	
	@Override
	public Pago get(Pago pago) throws Exception {
		List <Pago> lista = null;
		Pago bean = new Pago();
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select p.codigo, p.documento, p.serie,p.numero,p.ruc,");
		sql_find_user.append("p.razon_social, p.direccion, p.tipo_pago, ");
		sql_find_user.append("DATE_FORMAT(p.fec_emision,'%d/%m/%Y %H:%i:%s') fec_emision, ");
		sql_find_user.append("p.subtotal, p.igv, p.total, p.estado, p.moneda,p.descuento, p.usuario ");
		sql_find_user.append("from tbl_pago p ");				  
		sql_find_user.append("where p.codigo = ? ");
		
		Object[] params = new Object[] { pago.getCodigo() };

		lista = (List <Pago>)jdbcTemplate.query(sql_find_user.toString(), params, new PagoMapper());		
    	if(lista!=null && lista.size()>0) {    		
    		bean = new Pago();
    		bean = lista.get(0);
    	}	    	
		return bean;
	}

	
	@Override
	public Pago getPago(Pago pago) throws Exception {
		List <Pago> lista = null;
		Pago bean = new Pago();
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select p.codigo, p.documento, p.serie,p.numero,p.ruc,");
		sql_find_user.append("p.razon_social, p.direccion, p.tipo_pago, ");
		sql_find_user.append("DATE_FORMAT(p.fec_emision,'%d/%m/%Y %H:%i:%s') fec_emision, ");
		sql_find_user.append("p.subtotal, p.igv, p.total, p.estado, p.moneda, p.descuento, p.usuario ");
		sql_find_user.append("from tbl_pago p ");				  
		sql_find_user.append("where p.codigo_restaurante_local = ? and p.serie = ? and p.numero = ? ");
		//sql_find_user.append("and p.estado = ? ");
		
		Object[] params = new Object[] { pago.getCodigoSucursal(), pago.getSerie(), pago.getNumero() };
		//, pago.getEstado() };

		lista = (List <Pago>)jdbcTemplate.query(sql_find_user.toString(), params, new PagoMapper());		
    	if(lista!=null && lista.size()>0) {    		
    		bean = new Pago();
    		bean = lista.get(0);
    	}	    	
		return bean;
	}

	@Override
	public boolean insertItem(PagoItem pagoItem) throws Exception {
		boolean resultado = false;
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_pago_item ");
		sql_insert_user.append("(codigo_pago,tipo_pago,total,fec_registro,estado) ");
		sql_insert_user.append("VALUES (?,?,?,?,?) ");

		BigDecimal monto = new BigDecimal("0.00");
		try {			
			monto = new BigDecimal(pagoItem.getTotal());
			monto = monto.setScale(2);
		} catch(Exception e) {
			monto = new BigDecimal("0.00");
		}				
		
		String fechaHora = StringUtil.getFechaDateToFormat("yyyy-MM-dd HH:mm:ss");;
        pagoItem.setEstado(1);
		Object[] params = new Object[] {
		pagoItem.getCodigPago(), pagoItem.getTipo(), monto, fechaHora, pagoItem.getEstado()	};
		
		jdbcTemplate.update(sql_insert_user.toString(), params);
		resultado = true;
		return resultado;
	}

	@Override
	public List<Pago> listAll(Integer susucrsal) throws Exception {
		List <Pago> lista = null;
		Pago bean = new Pago();
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select p.codigo, p.documento, p.serie,p.numero,p.ruc,");
		sql_find_user.append("p.razon_social, p.direccion, p.tipo_pago, ");
		sql_find_user.append("DATE_FORMAT(p.fec_emision,'%d/%m/%Y %H:%i:%s') fec_emision, ");
		sql_find_user.append("p.subtotal, p.igv, p.total, p.estado, p.moneda,p.descuento, p.usuario ");
		sql_find_user.append("from tbl_pago p where p.codigo_restaurante_local = ? ");				  
		sql_find_user.append("order by p.codigo desc ");
		
		Object[] params = new Object[] { susucrsal };

		lista = (List <Pago>)jdbcTemplate.query(sql_find_user.toString(), params, new PagoMapper());		
		return lista;
	}

	@Override
	public List<Pago> listByFilter(Integer susucrsal, Filter filter) throws Exception {
		List <Pago> lista = null;
		Pago bean = new Pago();
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select p.codigo, p.documento, p.serie,p.numero,p.ruc,");
		sql_find_user.append("p.razon_social, p.direccion, p.tipo_pago, ");
		sql_find_user.append("DATE_FORMAT(p.fec_emision,'%d/%m/%Y %H:%i:%s') fec_emision, ");
		sql_find_user.append("p.subtotal, p.igv, p.total, p.estado, p.moneda,p.descuento, p.usuario");
		sql_find_user.append("from tbl_pago p where p.codigo_restaurante_local = ? ");				  
		sql_find_user.append("and DATE_FORMAT(p.fec_emision,'%Y%m%d') >= ? ");				  
		sql_find_user.append("and DATE_FORMAT(p.fec_emision,'%Y%m%d') <= ? ");				  
		sql_find_user.append("order by p.codigo desc ");
		
		
		Object[] params = new Object[] { susucrsal, filter.getInicio(), filter.getFin() };

		lista = (List <Pago>)jdbcTemplate.query(sql_find_user.toString(), params, new PagoMapper());		
		return lista;
	}

}
