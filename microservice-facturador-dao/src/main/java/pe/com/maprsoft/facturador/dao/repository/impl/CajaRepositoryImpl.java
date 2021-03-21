package pe.com.maprsoft.facturador.dao.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.maprsoft.facturador.dao.repository.CajaRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.BalanceMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.CajaMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.MozoMapper;
import pe.com.maprsoft.facturador.model.Balance;
import pe.com.maprsoft.facturador.model.Caja;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Pedido;
import pe.com.maprsoft.facturador.model.core.StringUtil;

@SuppressWarnings("all")
@Repository
public class CajaRepositoryImpl implements CajaRepository {

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insert(Caja request) throws Exception {
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("INSERT INTO tbl_caja ");
		//sql_insert_user.append("(codigo_restaurante_local, fecha_operacion, usuario, monto, operacion, identificador, horario, estado) ");
		//sql_insert_user.append("VALUES (?,?,?,?,?,?,?,?) ");
		
		sql_insert_user.append("(codigo_restaurante_local, fecha_apertura, usuario, monto_inicial, identificador, horario, estado,fecha_registro) ");
		sql_insert_user.append("VALUES (?,?,?,?,?,?,?,?) ");
				
		//monto_final, fecha_cierre
				 
        Object[] params = new Object[] {
				request.getCodigoSucursal(), request.getFechaApertura(),
				request.getUsuario(), request.getMontoInicial(), 
				request.getIdentificador(), request.getHorario(), request.getEstado(), request.getFechaRegistro() };
			
		jdbcTemplate.update(sql_insert_user.toString(), params);
	
	}

	
	@Override
	public void update(Caja request) throws Exception {
		StringBuilder sql_insert_user = new StringBuilder();
		sql_insert_user.append("UPDATE tbl_caja ");
		sql_insert_user.append("SET estado = ?, fecha_cierre = ?, monto_final = monto_inicial + ?, descuento = ? ");
		sql_insert_user.append("WHERE codigo = ? and estado = 0 ");
        String fechaHora = StringUtil.getFechaDateToFormat("yyyy-MM-dd HH:mm:ss");;
        
        
        request.setFechaCierre(fechaHora);
		Object[] params = new Object[] { request.getEstado(),
				request.getFechaCierre(), request.getMontoFinal(), 
				request.getDescuento(), request.getCodigo() };
			
		jdbcTemplate.update(sql_insert_user.toString(), params);
	}

	@Override
	public List<Caja> list(Caja request) throws Exception {
		List <Caja> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_restaurante_local, DATE_FORMAT(fecha_apertura,'%d/%m/%Y %H:%i:%s') fec_apertura, usuario, monto_inicial, ");
		sql_find_user.append("identificador, horario, estado, DATE_FORMAT(fecha_cierre,'%d/%m/%Y %H:%i:%s') fec_cierre, monto_final, descuento ");
		sql_find_user.append("from tbl_caja where codigo_restaurante_local = ? and identificador = ? ");
		sql_find_user.append("order by 1 ");
		Object[] params = new Object[] { request.getCodigoSucursal(), request.getIdentificador() };
		lista = (List <Caja>)jdbcTemplate.query(sql_find_user.toString(), params, new CajaMapper());
		return lista;
	}

	@Override
	public List<Caja> listCaja(Caja request) throws Exception {
		List <Caja> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_restaurante_local, DATE_FORMAT(fecha_apertura,'%d/%m/%Y %H:%i:%s') fec_apertura, usuario, monto_inicial, ");
		sql_find_user.append("identificador, horario, estado, DATE_FORMAT(fecha_cierre,'%d/%m/%Y %H:%i:%s') fec_cierre, monto_final, descuento  ");
		sql_find_user.append("from tbl_caja where codigo_restaurante_local = ? "); // and estado = 0 ");
		
		sql_find_user.append("ORDER by 1 DESC ");
		sql_find_user.append("LIMIT 1 ");
		
		Object[] params = new Object[] { request.getCodigoSucursal() };
		lista = (List <Caja>)jdbcTemplate.query(sql_find_user.toString(), params, new CajaMapper());
		return lista;
	}

	@Override
	public Caja get(Caja request) throws Exception {
		List <Caja> lista = null;
		Caja bean = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_restaurante_local, DATE_FORMAT(fecha_apertura,'%d/%m/%Y %H:%i:%s') fec_apertura, usuario, monto_inicial, ");
		sql_find_user.append("identificador, horario, estado, DATE_FORMAT(fecha_cierre,'%d/%m/%Y %H:%i:%s') fec_cierre, monto_final, descuento  ");

		sql_find_user.append("from tbl_caja where codigo_restaurante_local = ? and identificador = ? and estado = 0 ");
		sql_find_user.append("order by 1 ");
		Object[] params = new Object[] { request.getCodigoSucursal(), request.getIdentificador() };
		lista = (List <Caja>)jdbcTemplate.query(sql_find_user.toString(), params, new CajaMapper());
		
		if(!lista.isEmpty()) {
			bean = lista.get(0);
		}
		return bean;
	}


	@Override
	public List<Caja> listCajaByDia(Caja request) throws Exception {
		List <Caja> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select codigo, codigo_restaurante_local, DATE_FORMAT(fecha_apertura,'%d/%m/%Y %H:%i:%s') fec_apertura, usuario, monto_inicial, ");
		sql_find_user.append("identificador, horario, estado, DATE_FORMAT(fecha_cierre,'%d/%m/%Y %H:%i:%s') fec_cierre, monto_final, descuento ");
		sql_find_user.append("from tbl_caja where codigo_restaurante_local = ? ");
		sql_find_user.append("and DATE_FORMAT(fecha_registro,'%Y%m%d') = ? ");
		sql_find_user.append("order by codigo ");
		Object[] params = new Object[] { request.getCodigoSucursal(), request.getFechaRegistro() };
		lista = (List <Caja>)jdbcTemplate.query(sql_find_user.toString(), params, new CajaMapper());
		return lista;
	}

	
	@Override
	public List<Balance> balance(Caja caja) throws Exception {
		List <Balance> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		
		sql_find_user.append("select pa.codigo, pa.codigo_restaurante_local, pa.documento, pa.serie, pa.numero, ");
		sql_find_user.append("DATE_FORMAT(pa.fec_emision,'%d/%m/%Y %H:%i:%s') fecha_emision, pa.subtotal, pa.igv, pa.total, pa.descuento, ");
		sql_find_user.append("pa.pago_soles, pa.pago_dolares, pa.pago_visa_credito, pa.pago_master_credito, pa.vuelto, ");
		sql_find_user.append("pi.codigo, pi.tipo_pago, pi.total ");
		sql_find_user.append("from tbl_pago pa inner join tbl_pago_item pi on pa.codigo = pi.codigo_pago ");
		sql_find_user.append("where pa.estado=1 and pa.codigo_restaurante_local = ? ");
		sql_find_user.append("and DATE_FORMAT(pa.fec_emision,'%Y%m%d%H%i%s')>=? ");
		
		if(caja.getEstado().intValue()==1)
			sql_find_user.append("and DATE_FORMAT(pa.fec_emision,'%Y%m%d%H%i%s')<=? ");
		
		sql_find_user.append("order by 1 ");
		Object[] params = null;
		if(caja.getEstado().intValue()==1)
			params = new Object[] { caja.getCodigoSucursal(), caja.getFechaApertura(), caja.getFechaCierre() };
		else
			params = new Object[] { caja.getCodigoSucursal(), caja.getFechaApertura() };
		
		lista = (List <Balance>)jdbcTemplate.query(sql_find_user.toString(), params, new BalanceMapper());
		return lista;
	}

	
	@Override
	public String getMontoCierre(Caja caja) throws Exception {
		//Integer bean = new Integer(0);
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select sum(pa.total) ");
		sql_find_user.append("from tbl_pago pa ");
		sql_find_user.append("where pa.estado=1 and pa.codigo_restaurante_local = ? ");
		sql_find_user.append("and DATE_FORMAT(pa.fec_emision,'%Y%m%d%H%i%s')>=? ");
		
		Object[] params = new Object[] { caja.getCodigoSucursal(), caja.getFechaApertura() };
		String bean = jdbcTemplate.queryForObject(sql_find_user.toString(), params, String.class);
  		return bean;
	}

	@Override
	public String getMontoDescuentoCierre(Caja caja) throws Exception {
		//Integer bean = new Integer(0);
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select sum(pa.descuento) ");
		sql_find_user.append("from tbl_pago pa ");
		sql_find_user.append("where pa.estado=1 and pa.codigo_restaurante_local = ? ");
		sql_find_user.append("and DATE_FORMAT(pa.fec_emision,'%Y%m%d%H%i%s')>=? ");
		
		Object[] params = new Object[] { caja.getCodigoSucursal(), caja.getFechaApertura() };
		String bean = jdbcTemplate.queryForObject(sql_find_user.toString(), params, String.class);
  		return bean;
	}

}
