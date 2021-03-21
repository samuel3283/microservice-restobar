package pe.com.maprsoft.facturador.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.maprsoft.facturador.model.Salon;
import pe.com.maprsoft.facturador.model.Session;
import pe.com.maprsoft.facturador.model.Sucursal;
import pe.com.maprsoft.facturador.model.Usuario;
import pe.com.maprsoft.facturador.model.core.BooxException;
import pe.com.maprsoft.facturador.model.core.EstadoMesaCodeEnum;
import pe.com.maprsoft.facturador.service.MozoService;
import pe.com.maprsoft.facturador.service.ParametroService;
import pe.com.maprsoft.facturador.service.SalonService;
import pe.com.maprsoft.facturador.dao.repository.MesaRepository;
import pe.com.maprsoft.facturador.dao.repository.MozoRepository;
import pe.com.maprsoft.facturador.dao.repository.ParametroRepository;
import pe.com.maprsoft.facturador.dao.repository.SalonRepository;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.dao.repository.UsuarioRepository;
import pe.com.maprsoft.facturador.model.ListaMesas;
import pe.com.maprsoft.facturador.model.Mesa;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Parametro;

@Service
public class ParametroServiceImpl implements ParametroService {

	@Autowired
	private ParametroRepository parametroRepository;

	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public List<Parametro> list(Parametro parametro, String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		parametro.setSucursal(new Sucursal(session.getCodigoRestauranteLocal()));
		List<Parametro> list = parametroRepository.list(parametro);
		return list;
	}

	@Override
	public List<Parametro> listAll(Parametro parametro, String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		parametro.setSucursal(new Sucursal(session.getCodigoRestauranteLocal()));
		List<Parametro> list = parametroRepository.listAll(parametro);
		return list;
	}

	@Override
	public void insert(Parametro parametro, String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		parametro.setEstado(1);
		parametro.setSucursal(new Sucursal(session.getCodigoRestauranteLocal()));
		parametroRepository.insert(parametro);		
	}

	@Override
	public void insertCode(Parametro parametro, String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		parametro.setEstado(1);
		parametro.setSucursal(new Sucursal(session.getCodigoRestauranteLocal()));
		parametroRepository.insertCode(parametro);		
	}

	@Override
	public void delete(Parametro parametro) throws Exception {
		parametroRepository.delete(parametro);		
	}

	@Override
	public void update(Parametro parametro) throws Exception {
		parametroRepository.update(parametro);
	}

	@Override
	public void updateStatus(Parametro parametro) throws Exception {
		parametroRepository.updateStatus(parametro);
	}


}
