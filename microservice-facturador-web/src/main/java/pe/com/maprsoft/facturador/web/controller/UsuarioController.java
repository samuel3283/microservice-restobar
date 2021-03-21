package pe.com.maprsoft.facturador.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.maprsoft.facturador.model.HeaderRq;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Salon;
import pe.com.maprsoft.facturador.model.TransactionRs;
import pe.com.maprsoft.facturador.model.Usuario;
import pe.com.maprsoft.facturador.service.SalonService;
import pe.com.maprsoft.facturador.service.UsuarioService;
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;


@RestController
@RequestMapping("/rest")
public class UsuarioController {

	private final Logger logger = LoggerFactory
			.getLogger(UsuarioController.class);

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	@RequestMapping(value ="/service/usuario/list", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Usuario>> list(
			@RequestHeader HttpHeaders headers, @RequestBody Usuario request) {
		
		TransactionRs<List<Usuario>> response = new TransactionRs<List<Usuario>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(usuarioService.list(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/usuario/listAdmin", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Usuario>> listAdmin(
			@RequestHeader HttpHeaders headers, @RequestBody Usuario request) {
		
		TransactionRs<List<Usuario>> response = new TransactionRs<List<Usuario>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(usuarioService.listAdmin(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error listAdmin. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/usuario/listAll", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Usuario>> listAll(
			@RequestHeader HttpHeaders headers, @RequestBody Mozo request) {
		
		TransactionRs<List<Usuario>> response = new TransactionRs<List<Usuario>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(usuarioService.listAll(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/usuario/insert", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Usuario> insert(
			@RequestHeader HttpHeaders headers, @RequestBody Usuario request) {
		
		TransactionRs<Usuario> response = new TransactionRs<Usuario>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		usuarioService.insert(request, headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/usuario/insertAdmin", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Usuario> insertAdmin(
			@RequestHeader HttpHeaders headers, @RequestBody Usuario request) {
		
		TransactionRs<Usuario> response = new TransactionRs<Usuario>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		usuarioService.insertAdmin(request, headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/usuario/delete", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Usuario> delete(
			@RequestHeader HttpHeaders headers, @RequestBody Usuario request) {
		
		TransactionRs<Usuario> response = new TransactionRs<Usuario>();
		try {
		usuarioService.delete(request);		
		} catch (Exception e) {
			logger.error("Error delete. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	
	@RequestMapping(value ="/service/usuario/update", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Usuario> update(
			@RequestHeader HttpHeaders headers, @RequestBody Usuario request) {
		
		TransactionRs<Usuario> response = new TransactionRs<Usuario>();
		try {
		usuarioService.update(request);		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/usuario/updateStatus", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Usuario> updateStatus(
			@RequestHeader HttpHeaders headers, @RequestBody Usuario request) {
		
		TransactionRs<Usuario> response = new TransactionRs<Usuario>();
		try {
		usuarioService.updateStatus(request);		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

}
