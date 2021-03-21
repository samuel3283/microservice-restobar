package pe.com.maprsoft.facturador.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.maprsoft.facturador.service.RestauranteService;
import pe.com.maprsoft.facturador.dao.repository.RestauranteRepository;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.model.Restaurante;

@Service
public class RestauranteServiceImpl implements RestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
		
	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public List<Restaurante> list(String token) throws Exception {
		/*
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");
		 */		
		List<Restaurante> list = restauranteRepository.list();		
		return list;
	}

	@Override
	public List<Restaurante> listAll(String token) throws Exception {
		List<Restaurante> list = restauranteRepository.listAll();		
		return list;
	}

	@Override
	public void insert(Restaurante restaurante) throws Exception {	
		restaurante.setEstado(1);
		restauranteRepository.insert(restaurante);
	}

	@Override
	public void delete(Restaurante restaurante) throws Exception {
		restauranteRepository.delete(restaurante);		
	}

	@Override
	public void update(Restaurante restaurante) throws Exception {
		restauranteRepository.update(restaurante);	
	}

	@Override
	public void updateStatus(Restaurante restaurante) throws Exception {
		restauranteRepository.updateStatus(restaurante);	
	}

}