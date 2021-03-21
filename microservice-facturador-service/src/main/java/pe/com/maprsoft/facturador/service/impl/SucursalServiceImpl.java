package pe.com.maprsoft.facturador.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.dao.repository.SucursalRepository;
import pe.com.maprsoft.facturador.dao.repository.UsuarioRepository;
import pe.com.maprsoft.facturador.model.HeaderRq;
import pe.com.maprsoft.facturador.model.Restaurante;
import pe.com.maprsoft.facturador.model.Session;
import pe.com.maprsoft.facturador.model.Sucursal;
import pe.com.maprsoft.facturador.model.Usuario;
import pe.com.maprsoft.facturador.model.core.BooxException;
import pe.com.maprsoft.facturador.service.SessionService;
import pe.com.maprsoft.facturador.service.SucursalService;
import pe.com.maprsoft.facturador.service.core.StringEncrypt;



@Service
public class SucursalServiceImpl implements SucursalService {
	
	@Autowired
	SucursalRepository sucursalRepository;
	
	@Autowired
	private Environment env;

	private final Logger logger = LoggerFactory
			.getLogger(SucursalServiceImpl.class);

	@Override
	public List<Sucursal> list() throws Exception {
		return sucursalRepository.list();
	}

	@Override
	public List<Sucursal> listAll() throws Exception {
		return sucursalRepository.listAll();
	}

	
	@Override
	public List<Sucursal> listByRestaurante(Restaurante restaurante) throws Exception {
		return sucursalRepository.listByRestaurante(restaurante);
	}

	@Override
	public void delete(Sucursal sucursal) throws Exception {
		sucursalRepository.delete(sucursal);
	}

	@Override
	public void update(Sucursal sucursal) throws Exception {
		sucursalRepository.update(sucursal);		
	}

	@Override
	public void insert(Sucursal sucursal) throws Exception {
		sucursalRepository.insert(sucursal);		
	}


}