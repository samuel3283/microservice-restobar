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

import pe.com.maprsoft.facturador.model.Caja;
import pe.com.maprsoft.facturador.model.HeaderRq;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Salon;
import pe.com.maprsoft.facturador.model.TransactionRs;
import pe.com.maprsoft.facturador.model.Usuario;
import pe.com.maprsoft.facturador.service.CajaService;
import pe.com.maprsoft.facturador.service.MozoService;
import pe.com.maprsoft.facturador.service.SalonService;
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;


@RestController
@RequestMapping("/rest")
public class MozoController {

	private final Logger logger = LoggerFactory
			.getLogger(MozoController.class);

	@Autowired
	MozoService mozoService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	@RequestMapping(value ="/service/mozo/list", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Usuario>> list(
			@RequestHeader HttpHeaders headers, @RequestBody Mozo request) {
		
		TransactionRs<List<Usuario>> response = new TransactionRs<List<Usuario>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(mozoService.listMozo(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/mozo/listAll", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Usuario>> listAll(
			@RequestHeader HttpHeaders headers, @RequestBody Mozo request) {
		
		TransactionRs<List<Usuario>> response = new TransactionRs<List<Usuario>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(mozoService.listAllMozo(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	/*
	@RequestMapping(value ="/service/mozo/list", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Mozo>> list(
			@RequestHeader HttpHeaders headers, @RequestBody Mozo request) {
		
		TransactionRs<List<Mozo>> response = new TransactionRs<List<Mozo>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(mozoService.list(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/mozo/listAll", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Mozo>> listAll(
			@RequestHeader HttpHeaders headers, @RequestBody Mozo request) {
		
		TransactionRs<List<Mozo>> response = new TransactionRs<List<Mozo>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(mozoService.listAll(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}
*/
	@RequestMapping(value ="/service/mozo/insert", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Mozo> insert(
			@RequestHeader HttpHeaders headers, @RequestBody Usuario request) {
		
		TransactionRs<Mozo> response = new TransactionRs<Mozo>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		mozoService.insertMozo(request, headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/mozo/delete", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Usuario> delete(
			@RequestHeader HttpHeaders headers, @RequestBody Usuario request) {
		
		TransactionRs<Usuario> response = new TransactionRs<Usuario>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		mozoService.deleteMozo(request, headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error delete. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	
	@RequestMapping(value ="/service/mozo/update", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Usuario> update(
			@RequestHeader HttpHeaders headers, @RequestBody Usuario request) {
		
		TransactionRs<Usuario> response = new TransactionRs<Usuario>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		mozoService.updateMozo(request);		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/mozo/updateStatus", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Mozo> updateStatus(
			@RequestHeader HttpHeaders headers, @RequestBody Mozo request) {
		
		TransactionRs<Mozo> response = new TransactionRs<Mozo>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		mozoService.updateStatus(request, headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

}