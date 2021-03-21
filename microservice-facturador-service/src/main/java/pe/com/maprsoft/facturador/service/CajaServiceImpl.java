package pe.com.maprsoft.facturador.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import pe.com.maprsoft.facturador.dao.repository.CajaRepository;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.model.Balance;
import pe.com.maprsoft.facturador.model.BalanceCabecera;
import pe.com.maprsoft.facturador.model.Caja;
import pe.com.maprsoft.facturador.model.Session;
import pe.com.maprsoft.facturador.model.core.BooxException;
import pe.com.maprsoft.facturador.model.core.StringUtil;
import pe.com.maprsoft.facturador.service.core.Util;

@Service
public class CajaServiceImpl implements CajaService {

	@Autowired
	private CajaRepository cajaRepository;
	
	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public List<Caja> list(String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		Caja request = new Caja();
		request.setCodigoSucursal(session.getCodigoRestauranteLocal());
		List<Caja> lista = cajaRepository.listCaja(request);
		/*
		if(!lista.isEmpty()) {
			Caja bean = new Caja();
			bean = lista.get(0);
			lista = new ArrayList<Caja>();
			lista = cajaRepository.list(bean);
		}
		*/
		return lista;
	}
	
	@Override
	public List<Caja> listCaja(String token) throws Exception {
		
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		Caja request = new Caja();
		request.setCodigoSucursal(session.getCodigoRestauranteLocal());
		List<Caja> lista = cajaRepository.listCaja(request);
		
		return lista;
	}

	@Override
	public void apertura(Caja request, String token) throws Exception {

		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		//request.setOperacion("Apertura");
		String fechaHora = StringUtil.getFechaDateToFormat("yyyy-MM-dd HH:mm:ss");
		String identificador = StringUtil.getFechaDateToFormat("yyyyMMddHHmmss");
		request.setFechaRegistro(fechaHora);
		request.setFechaApertura(fechaHora);
		request.setEstado(0);
		//request.setUsuario(session.getUsuario());
		request.setIdentificador(identificador);
		request.setCodigoSucursal(session.getCodigoRestauranteLocal());

		try {			
		BigDecimal monto = new BigDecimal(request.getMontoInicial());
		monto = monto.setScale(2);
		request.setMontoInicial(String.valueOf(monto));					
		} catch(Exception e) {
			request.setMontoInicial("0.00");
		}
		//Insert Apertura
		cajaRepository.insert(request);
					
	}
	
	@Override
	public void cierre(Caja request, String token) throws Exception {

		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");
       
		String fechaHora = StringUtil.getFechaDateToFormat("yyyy-MM-dd HH:mm:ss");
		request.setFechaCierre(fechaHora);
		request.setEstado(1);
		//request.setUsuario(session.getUsuario());
		request.setCodigoSucursal(session.getCodigoRestauranteLocal());

		//Validar monto final
				
		try {		
		String inicio = Util.getDateToFormat(request.getFechaApertura(), "dd/MM/yyyy HH:mm:ss", "yyyyMMddHHmmss");
		request.setFechaApertura(inicio);
		String valor = cajaRepository.getMontoCierre(request);	
		BigDecimal monto = new BigDecimal(valor);
		monto = monto.setScale(2);
		request.setMontoFinal(String.valueOf(monto));					
		} catch(Exception e) {
			request.setMontoFinal("0.00");
		}

		try {		
		String inicio = Util.getDateToFormat(request.getFechaApertura(), "dd/MM/yyyy HH:mm:ss", "yyyyMMddHHmmss");
		request.setFechaApertura(inicio);
		String descuento = cajaRepository.getMontoDescuentoCierre(request);	
		BigDecimal montoDescuento = new BigDecimal(descuento);
		montoDescuento = montoDescuento.setScale(2);
		request.setDescuento(String.valueOf(montoDescuento));					
		} catch(Exception e) {
			request.setDescuento("0.00");
		}

		cajaRepository.update(request);
	}

	@Override
	public void insert(Caja request, String token) throws Exception {

		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		String fechaHora = StringUtil.getFechaDateToFormat("yyyy-MM-dd HH:mm:ss");
		String identificador = StringUtil.getFechaDateToFormat("yyyyMMddHHmmss");
		request.setFechaApertura(fechaHora);
		request.setEstado(1);
		request.setUsuario(session.getUsuario());
		request.setIdentificador(identificador);
		request.setCodigoSucursal(session.getCodigoRestauranteLocal());

		cajaRepository.insert(request);
		
	}

	@Override
	public void update(Caja request, String token) throws Exception {
		
		cajaRepository.update(request);
		
	}

	
	@Override
	public List<BalanceCabecera> listBalance(Caja caja, String token) throws Exception {
		
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		caja.setCodigoSucursal(session.getCodigoRestauranteLocal());
		
		if(caja.getEstado().intValue()==0) {			
			String inicio = Util.getDateToFormat(caja.getFechaApertura(), "dd/MM/yyyy HH:mm:ss", "yyyyMMddHHmmss");
			caja.setFechaApertura(inicio);
		}
		if(caja.getEstado().intValue()==1) {
			String inicio = Util.getDateToFormat(caja.getFechaApertura(), "dd/MM/yyyy HH:mm:ss", "yyyyMMddHHmmss");
			String fin = Util.getDateToFormat(caja.getFechaCierre(), "dd/MM/yyyy HH:mm:ss", "yyyyMMddHHmmss");
			caja.setFechaApertura(inicio);
			caja.setFechaCierre(fin);
		}
		List<Balance> lista = cajaRepository.balance(caja);
		
		List<BalanceCabecera> listado = new ArrayList<BalanceCabecera>();
		BalanceCabecera cabecera = new BalanceCabecera();
		
		Integer codigoPago = 0;
		for(Balance balance: lista) {
			
			if(codigoPago.intValue() != balance.getCodigo().intValue()) {
				cabecera = new BalanceCabecera();
				cabecera.setCodigo(balance.getCodigo());
				cabecera.setCodigoSucursal(balance.getCodigoSucursal());
				cabecera.setDocumento(balance.getDocumento());
				cabecera.setSerie(balance.getSerie());
				cabecera.setNumero(balance.getNumero());				
				
				cabecera.setFechaPago(balance.getFechaPago());
				cabecera.setSubtotal(balance.getSubtotal());
				cabecera.setIgv(balance.getIgv());
				cabecera.setTotal(balance.getTotal());
				cabecera.setDescuento(balance.getDescuento());

				cabecera.setPagoSoles(balance.getPagoSoles());
				cabecera.setPagoDolares(balance.getPagoDolares());
				cabecera.setPagoVisaCredito(balance.getPagoVisaCredito());
				cabecera.setPagoMasterCardCredito(balance.getPagoMasterCardCredito());
				cabecera.setPagoVuelto(balance.getPagoVuelto());
				listado.add(cabecera);
				
			}
		
			codigoPago = balance.getCodigo();
		}
		
		return listado;
	}

}
