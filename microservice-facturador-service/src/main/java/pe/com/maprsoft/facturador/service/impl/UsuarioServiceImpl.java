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
import pe.com.maprsoft.facturador.service.UsuarioService;
import pe.com.maprsoft.facturador.dao.repository.MesaRepository;
import pe.com.maprsoft.facturador.dao.repository.MozoRepository;
import pe.com.maprsoft.facturador.dao.repository.SalonRepository;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.dao.repository.UsuarioRepository;
import pe.com.maprsoft.facturador.model.ListaMesas;
import pe.com.maprsoft.facturador.model.Mesa;
import pe.com.maprsoft.facturador.model.Mozo;

@Service
public  class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public List<Usuario> list(String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		Usuario usuario = new Usuario();
		usuario.setCodigoRestauranteLocal(session.getCodigoRestauranteLocal());
		List<Usuario> list = usuarioRepository.list(usuario);

		List<Usuario> lista = new ArrayList<>();
		for(Usuario user: list) {
			if(!user.getPerfil().equals("Z"))
				lista.add(user);
		}
		
		return lista;
	}

	@Override
	public List<Usuario> listAll(String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		Usuario usuario = new Usuario();
		usuario.setCodigoRestauranteLocal(session.getCodigoRestauranteLocal());
		List<Usuario> list = usuarioRepository.listAll(usuario);

		List<Usuario> lista = new ArrayList<>();
		for(Usuario user: list) {
			if(!user.getPerfil().equals("Z"))
				lista.add(user);
		}

		return list;
	}

	@Override
	public void insert(Usuario usuario, String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		usuario.setCodigoRestauranteLocal(session.getCodigoRestauranteLocal());
		usuarioRepository.insert(usuario);
	}

	@Override
	public void insertAdmin(Usuario usuario, String token) throws Exception {
	
		usuarioRepository.insert(usuario);
	}

	@Override
	public void delete(Usuario usuario) throws Exception {
		usuarioRepository.delete(usuario);
	}

	@Override
	public void update(Usuario usuario) throws Exception {
		usuarioRepository.update(usuario);
	}

	@Override
	public void updateStatus(Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Usuario> listAdmin(String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		Usuario usuario = new Usuario();
		usuario.setCodigoRestauranteLocal(session.getCodigoRestauranteLocal());
		List<Usuario> list = usuarioRepository.listAdmin(usuario);
		return list;
	}


}
