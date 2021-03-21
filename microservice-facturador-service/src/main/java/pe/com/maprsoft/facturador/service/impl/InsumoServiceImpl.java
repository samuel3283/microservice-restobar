package pe.com.maprsoft.facturador.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.maprsoft.facturador.model.Salon;
import pe.com.maprsoft.facturador.model.Session;
import pe.com.maprsoft.facturador.model.Sucursal;
import pe.com.maprsoft.facturador.model.core.BooxException;
import pe.com.maprsoft.facturador.model.core.EstadoMesaCodeEnum;
import pe.com.maprsoft.facturador.service.InsumoService;
import pe.com.maprsoft.facturador.service.MozoService;
import pe.com.maprsoft.facturador.service.SalonService;
import pe.com.maprsoft.facturador.dao.repository.InsumoRepository;
import pe.com.maprsoft.facturador.dao.repository.MesaRepository;
import pe.com.maprsoft.facturador.dao.repository.MozoRepository;
import pe.com.maprsoft.facturador.dao.repository.SalonRepository;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.model.Insumo;
import pe.com.maprsoft.facturador.model.ListaMesas;
import pe.com.maprsoft.facturador.model.Mesa;
import pe.com.maprsoft.facturador.model.Mozo;

@Service
public class InsumoServiceImpl implements InsumoService {

	@Autowired
	private InsumoRepository insumoRepository;
		
	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public List<Insumo> list(String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		List<Insumo> list = insumoRepository.list(session.getCodigoRestauranteLocal());
		return list;
	}

	@Override
	public List<Insumo> listAll(String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		List<Insumo> list = insumoRepository.listAll(session.getCodigoRestauranteLocal());
		return list;
	}

	@Override
	public void insert(Insumo insumo, String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		Sucursal sucursal = new Sucursal(session.getCodigoRestauranteLocal());
		insumo.setEstado(1);
		insumo.setSucursal(sucursal);
		
		BigDecimal merma = new BigDecimal(insumo.getMerma());
		BigDecimal cien = new BigDecimal("100.00");

		BigDecimal cantidad = new BigDecimal(insumo.getCantidad());
		BigDecimal valor = merma.divide(cien).setScale(2);
		
		valor = valor.multiply(cantidad).setScale(2);
		valor = cantidad.subtract(valor).setScale(2);
		
		insumo.setCantidadReal(String.valueOf(valor));

		try {
			BigDecimal precio = new BigDecimal(insumo.getPrecioCompra());
			precio = precio.setScale(2);
			insumo.setPrecioCompra(precio.toString());
		} catch(Exception e) {
			insumo.setPrecioCompra("0.00");
		}
		
		
		insumoRepository.insert(insumo);
	}

	@Override
	public void delete(Insumo insumo, String token) throws Exception {
		insumoRepository.delete(insumo);		
	}

	@Override
	public void update(Insumo insumo, String token) throws Exception {
		insumoRepository.update(insumo);		
	}

	@Override
	public void updateStatus(Insumo insumo, String token) throws Exception {
		insumoRepository.updateStatus(insumo);		
	}


}
