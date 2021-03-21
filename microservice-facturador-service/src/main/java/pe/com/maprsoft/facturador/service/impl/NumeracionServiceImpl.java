package pe.com.maprsoft.facturador.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.maprsoft.facturador.model.Session;
import pe.com.maprsoft.facturador.model.Sucursal;
import pe.com.maprsoft.facturador.model.core.BooxException;
import pe.com.maprsoft.facturador.service.NumeracionService;
import pe.com.maprsoft.facturador.dao.repository.NumeracionRepository;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.model.Numeracion;

@Service
public class NumeracionServiceImpl implements NumeracionService {
	
	@Autowired
	private NumeracionRepository numeracionRepository;
		
	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public List<Numeracion> list(String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		List<Numeracion> list = numeracionRepository.list(session.getCodigoRestauranteLocal());
		
		return list;
	}

	@Override
	public void insert(Numeracion numeracion, String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		Sucursal sucursal = new Sucursal(session.getCodigoRestauranteLocal());
		numeracion.setSucursal(sucursal);
		numeracionRepository.insert(numeracion);
	}

	@Override
	public void delete(Numeracion numeracion) throws Exception {
		numeracionRepository.delete(numeracion);		
	}

	@Override
	public void update(Numeracion numeracion) throws Exception {
		numeracion.setEstado(1);
		numeracionRepository.update(numeracion);	
	}

}