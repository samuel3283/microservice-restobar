package pe.com.maprsoft.facturador.service.impl;

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
import pe.com.maprsoft.facturador.service.PreparacionService;
import pe.com.maprsoft.facturador.service.SalonService;
import pe.com.maprsoft.facturador.dao.repository.InsumoRepository;
import pe.com.maprsoft.facturador.dao.repository.MesaRepository;
import pe.com.maprsoft.facturador.dao.repository.MozoRepository;
import pe.com.maprsoft.facturador.dao.repository.PreparacionRepository;
import pe.com.maprsoft.facturador.dao.repository.SalonRepository;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.model.Insumo;
import pe.com.maprsoft.facturador.model.ListaMesas;
import pe.com.maprsoft.facturador.model.Mesa;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Preparacion;
import pe.com.maprsoft.facturador.model.Producto;

@Service
public class PreparacionServiceImpl implements PreparacionService {

	@Autowired
	private PreparacionRepository preparacionRepository;
		
	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public List<Preparacion> list(Producto producto, String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		List<Preparacion> list = preparacionRepository.list(producto,session.getCodigoRestauranteLocal());
		return list;
	}

	@Override
	public List<Preparacion> listAll(Producto producto,String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		List<Preparacion> list = preparacionRepository.listAll(producto,session.getCodigoRestauranteLocal());
		return list;
	}

	@Override
	public void insert(Preparacion preparacion, String token) throws Exception {
		preparacionRepository.insert(preparacion);
	}

	@Override
	public void delete(Preparacion preparacion, String token) throws Exception {
		preparacionRepository.delete(preparacion);		
	}

	@Override
	public void update(Preparacion preparacion, String token) throws Exception {
		preparacionRepository.update(preparacion);		
	}

	@Override
	public void updateStatus(Preparacion preparacion, String token) throws Exception {
		preparacionRepository.updateStatus(preparacion);		
	}


}
