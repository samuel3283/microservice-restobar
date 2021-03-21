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
import pe.com.maprsoft.facturador.service.SalonService;
import pe.com.maprsoft.facturador.dao.repository.MesaRepository;
import pe.com.maprsoft.facturador.dao.repository.MozoRepository;
import pe.com.maprsoft.facturador.dao.repository.SalonRepository;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.dao.repository.UsuarioRepository;
import pe.com.maprsoft.facturador.model.ListaMesas;
import pe.com.maprsoft.facturador.model.Mesa;
import pe.com.maprsoft.facturador.model.Mozo;

@Service
public class MozoServiceImpl implements MozoService {

	@Autowired
	private MozoRepository mozoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public List<Mozo> list(String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		List<Mozo> list = mozoRepository.list(session.getCodigoRestauranteLocal());

		return list;
	}

	@Override
	public void insert(Mozo mozo, String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		Sucursal sucursal = new Sucursal(session.getCodigoRestauranteLocal());
		mozo.setEstado(1);
		mozo.setSucursal(sucursal);
		mozoRepository.insert(mozo);
		
	}

	@Override
	public void delete(Mozo mozo, String token) throws Exception {
		mozoRepository.delete(mozo);
		
	}

	@Override
	public void update(Mozo mozo, String token) throws Exception {
		mozoRepository.update(mozo);		
	}

	@Override
	public void updateStatus(Mozo mozo, String token) throws Exception {
		mozoRepository.updateStatus(mozo);
		
	}

	@Override
	public List<Mozo> listAll(String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		List<Mozo> list = mozoRepository.listAll(session.getCodigoRestauranteLocal());

		return list;
	}

	@Override
	public List<Usuario> listMozo(String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		List<Usuario> list = usuarioRepository.listMozo(new Usuario(session.getCodigoRestauranteLocal()));
		return list;
	}

	@Override
	public List<Usuario> listAllMozo(String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		List<Usuario> list = usuarioRepository.listAllMozo(new Usuario(session.getCodigoRestauranteLocal()));
		return list;
	}

	@Override
	public void deleteMozo(Usuario usuario, String token) throws Exception {
		usuarioRepository.delete(usuario);
		
	}

	@Override
	public void updateMozo(Usuario usuario) throws Exception {
		usuarioRepository.updateMozo(usuario);
		
	}

	@Override
	public void insertMozo(Usuario usuario, String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		usuario.setPerfil("M");
		usuario.setCodigoRestauranteLocal(session.getCodigoRestauranteLocal());
		usuarioRepository.insertMozo(usuario);
		
	}


}
