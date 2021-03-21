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
import pe.com.maprsoft.facturador.service.SalonService;
import pe.com.maprsoft.facturador.dao.repository.MesaRepository;
import pe.com.maprsoft.facturador.dao.repository.SalonRepository;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.model.ListaMesas;
import pe.com.maprsoft.facturador.model.Mesa;

@Service
public class SalonServiceImpl implements SalonService {

	@Autowired
	private SalonRepository salonRepository;
	
	@Autowired
	private MesaRepository mesaRepository;
	
	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public List<Salon> list(String token) throws Exception {
	
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");
		
		List<Salon> listSalonTmp = salonRepository.list(session.getCodigoRestauranteLocal());
		List<Salon> listSalon = new ArrayList<Salon>(); 
		
		for(Salon salon: listSalonTmp) {			
			
			List<Mesa> listMesa = mesaRepository.list(salon.getId());
			
			List<List<Mesa>> listAllMesas = new ArrayList<List<Mesa>>();
			
			List<Mesa> listMesaTmp = new ArrayList<Mesa>();
			
			
			List<ListaMesas> listaMesasAll = new ArrayList<ListaMesas>();
			List<ListaMesas> listaMesasTmp = new ArrayList<ListaMesas>();
			ListaMesas listaMesas = new ListaMesas();
			
			
			int indice = 0;
			for(Mesa mesa: listMesa) {				
				indice++;
				
				if(mesa.getEstado().intValue() == EstadoMesaCodeEnum.MESA_LIBRE.getCode().intValue()) {
					mesa.setColor(EstadoMesaCodeEnum.MESA_LIBRE.getMessage());
				} else if(mesa.getEstado().intValue() == EstadoMesaCodeEnum.MESA_OCUPADA.getCode().intValue()) {
					mesa.setColor(EstadoMesaCodeEnum.MESA_OCUPADA.getMessage());
				} else if(mesa.getEstado().intValue() == EstadoMesaCodeEnum.MESA_POR_SALIR.getCode().intValue()) {
					mesa.setColor(EstadoMesaCodeEnum.MESA_POR_SALIR.getMessage());
				} else if(mesa.getEstado().intValue() == EstadoMesaCodeEnum.MESA_RESERVADA.getCode().intValue()) {
					mesa.setColor(EstadoMesaCodeEnum.MESA_RESERVADA.getMessage());
				}
								
				listMesaTmp.add(mesa);
				
				if(indice==4) {
					indice = 0;
					//listAllMesas.add(listMesaTmp);
					
					listaMesas.setLista(listMesaTmp);
					listMesaTmp = new ArrayList<Mesa>();
					
					salon.getMesas().add(listaMesas);
					listaMesas = new ListaMesas();
					
				}
				
			}
			
			if(listMesaTmp.size()>0) {
				//listAllMesas.add(listMesaTmp);
				listaMesas.setLista(listMesaTmp);
				listMesaTmp = new ArrayList<Mesa>();
				
				salon.getMesas().add(listaMesas);
				listaMesas = new ListaMesas();
			}
			//salon.setMesas(listAllMesas);
			listSalon.add(salon);
		}
		
		return listSalon;
	}

		
	
	@Override
	public List<Salon> listAll(String token) throws Exception {
	
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");
		
		List<Salon> listSalonTmp = salonRepository.listAll(session.getCodigoRestauranteLocal());
		List<Salon> listSalon = new ArrayList<Salon>(); 
		List<Mesa> listMesa =  new ArrayList<Mesa>(); 					
		
		for(Salon salon: listSalonTmp) {			
			listMesa =  new ArrayList<Mesa>(); 		
			listMesa = mesaRepository.listAll(salon.getId());						
			salon.setLista(listMesa);
			listSalon.add(salon);
		}
		
		return listSalon;
	}



	@Override
	public void update(Salon salon) throws Exception {
		salonRepository.update(salon);		
	}



	@Override
	public void updateStatus(Salon salon) throws Exception {
		salonRepository.updateStatus(salon);		
	}

	@Override
	public void insert(Salon salon, String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		Sucursal sucursal = new Sucursal(session.getCodigoRestauranteLocal());
		salon.setSucursal(sucursal);
		salon.setEstado(1);
		salonRepository.insert(salon);		
	}

	@Override
	public void delete(Salon salon, String token) throws Exception {

		if(mesaRepository.deleteAll(salon.getId())) {
			salonRepository.delete(salon);		
		} else {
			throw new BooxException("5001","Ocurrió un error favor de volver a intentarlo.");
		}

	}

}
