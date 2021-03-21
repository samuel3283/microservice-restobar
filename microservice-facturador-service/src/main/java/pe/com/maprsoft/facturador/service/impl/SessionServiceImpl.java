package pe.com.maprsoft.facturador.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import pe.com.maprsoft.facturador.dao.repository.CajaRepository;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.dao.repository.SucursalRepository;
import pe.com.maprsoft.facturador.dao.repository.UsuarioRepository;
import pe.com.maprsoft.facturador.model.Caja;
import pe.com.maprsoft.facturador.model.HeaderRq;
import pe.com.maprsoft.facturador.model.Session;
import pe.com.maprsoft.facturador.model.Sucursal;
import pe.com.maprsoft.facturador.model.Usuario;
import pe.com.maprsoft.facturador.model.core.BooxException;
import pe.com.maprsoft.facturador.service.SessionService;
import pe.com.maprsoft.facturador.service.core.StringEncrypt;



@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	SessionRepository sessionRepository;

	@Autowired
	SucursalRepository sucursalRepository;

	@Autowired
	CajaRepository cajaRepository;
	
	@Autowired
	private Environment env;

	private final Logger logger = LoggerFactory
			.getLogger(SessionServiceImpl.class);

	@Override
	public Session login(Usuario usuario, HeaderRq headerRq) throws Exception {
		Usuario bean = usuarioRepository.login(usuario);
		
		if(bean==null)
			throw new BooxException("5001","Error Usuario y/o Contraseña inválidos.");
				
		Session session = new Session();
		
		session.setDevice(headerRq.getDevice());
		session.setDeviceType(headerRq.getDeviceType());
		
		session.setCodigo(bean.getCodigo());
		session.setApellido(bean.getApellido());
		session.setUsuario(bean.getUsuario());
		session.setEmail(bean.getEmail());
		session.setNombre(bean.getNombre());
		session.setNumDocumento(bean.getNumDocumento());
		session.setTipoDocumento(bean.getTipoDocumento());
		session.setPerfil(bean.getPerfil());
		session.setTelefono(bean.getTelefono());
		java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        String fechaHora = sdf.format(dt);
        
		String cadena = bean.getUsuario().concat("-").concat(fechaHora);
		String token = StringEncrypt.encrypt(cadena);
		session.setCodigoRestauranteLocal(usuario.getCodigoRestauranteLocal());
		session.setToken(token);
		sessionRepository.insert(session);

		Session cuenta = sessionRepository.getByToken(session);
		cuenta.setMapa(null);
		if(cuenta==null)
			throw new Exception("Error Cuenta not found");
				 
		Sucursal sucursal = sucursalRepository.getAll(cuenta.getCodigoRestauranteLocal());
		cuenta.setSucursal(sucursal);
		cuenta.setCodigoUsuario(bean.getCodigo());
		cuenta.setAperturaCaja(1);
		
		if(session.getPerfil().equals("T") || session.getPerfil().equals("A")) {
			Caja caja = new Caja();
			caja.setCodigoSucursal(cuenta.getCodigoRestauranteLocal());
			List<Caja> listCaja = cajaRepository.listCaja(caja);
			
			
			if(listCaja!=null) {	
				if (listCaja.size()>0) {
					Caja dato = listCaja.get(0);
					if(dato.getEstado().intValue()==1)
						cuenta.setAperturaCaja(1);
					else
						cuenta.setAperturaCaja(0);
				} else {
					cuenta.setAperturaCaja(1);
				}	
			} else {
				cuenta.setAperturaCaja(1);
			}

		}		
		
		return cuenta;
	}

	@Override
	public Session loginDirect(Usuario usuario, HeaderRq headerRq) throws Exception {
		Usuario bean = usuarioRepository.login(usuario);
		
		if(bean==null)
			throw new BooxException("5001","Error Usuario y/o Contraseña inválidos.");
				
		Session session = new Session();
		
		session.setDevice(headerRq.getDevice());
		session.setDeviceType(headerRq.getDeviceType());
		
		session.setCodigo(bean.getCodigo());
		session.setApellido(bean.getApellido());
		session.setUsuario(bean.getUsuario());
		session.setEmail(bean.getEmail());
		session.setNombre(bean.getNombre());
		session.setNumDocumento(bean.getNumDocumento());
		session.setTipoDocumento(bean.getTipoDocumento());
		session.setPerfil(bean.getPerfil());
		session.setTelefono(bean.getTelefono());
		java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        String fechaHora = sdf.format(dt);
        
		String cadena = bean.getUsuario().concat("-").concat(fechaHora);
		String token = StringEncrypt.encrypt(cadena);
		session.setCodigoRestauranteLocal(bean.getCodigoRestauranteLocal());
		session.setToken(token);
		sessionRepository.insert(session);

		Session cuenta = sessionRepository.getByToken(session);
		cuenta.setMapa(null);
		if(cuenta==null)
			throw new Exception("Error Cuenta not found");
				 
		Sucursal sucursal = sucursalRepository.getAll(cuenta.getCodigoRestauranteLocal());
		cuenta.setSucursal(sucursal);
		cuenta.setCodigoUsuario(bean.getCodigo());
		cuenta.setAperturaCaja(1);
		
		if(session.getPerfil().equals("T") || session.getPerfil().equals("A")) {
			Caja caja = new Caja();
			caja.setCodigoSucursal(cuenta.getCodigoRestauranteLocal());
			List<Caja> listCaja = cajaRepository.listCaja(caja);
			
			
			if(listCaja!=null) {	
				if (listCaja.size()>0) {
					Caja dato = listCaja.get(0);
					if(dato.getEstado().intValue()==1)
						cuenta.setAperturaCaja(1);
					else
						cuenta.setAperturaCaja(0);
				} else {
					cuenta.setAperturaCaja(1);
				}	
			} else {
				cuenta.setAperturaCaja(1);
			}

		}		
		
		return cuenta;
	}

	@Override
	public Session getSessionByToken(String token) throws Exception {
		Session session = new Session();
		session.setToken(token);
		Session bean = sessionRepository.getByToken(session);
		if(bean==null)
			throw new Exception("Error Session not found");
		
		return bean;
	}

	@Override
	public void delete(String token) throws Exception {
		Session session = new Session();
		session.setToken(token);
		sessionRepository.delete(session);
	}
	
}