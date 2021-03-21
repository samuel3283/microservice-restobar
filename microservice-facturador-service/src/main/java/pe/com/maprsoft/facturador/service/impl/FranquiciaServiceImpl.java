package pe.com.maprsoft.facturador.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.maprsoft.facturador.model.Session;
import pe.com.maprsoft.facturador.model.Sucursal;
import pe.com.maprsoft.facturador.model.core.BooxException;
import pe.com.maprsoft.facturador.service.FranquiciaService;
import pe.com.maprsoft.facturador.dao.repository.FranquiciaRepository;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.model.Franquicia;

@Service
public class FranquiciaServiceImpl implements FranquiciaService {
	
	@Autowired
	private FranquiciaRepository franquiciaRepository;
		
	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public List<Franquicia> list(String token) throws Exception {
		/*
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");
		 */		
		List<Franquicia> list = franquiciaRepository.list();		
		return list;
	}

	@Override
	public List<Franquicia> listAll(String token) throws Exception {
		List<Franquicia> list = franquiciaRepository.listAll();		
		return list;
	}

	@Override
	public void insert(Franquicia franquicia) throws Exception {	
		franquicia.setEstado(1);
		franquiciaRepository.insert(franquicia);
	}

	@Override
	public void delete(Franquicia franquicia) throws Exception {
		franquiciaRepository.delete(franquicia);		
	}

	@Override
	public void update(Franquicia franquicia) throws Exception {
		franquiciaRepository.update(franquicia);	
	}

	@Override
	public void updateStatus(Franquicia franquicia) throws Exception {
		franquiciaRepository.updateStatus(franquicia);	
	}

}