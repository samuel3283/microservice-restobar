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
import pe.com.maprsoft.facturador.model.Franquicia;
import pe.com.maprsoft.facturador.model.TransactionRs;
import pe.com.maprsoft.facturador.service.FranquiciaService;
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;


@RestController
@RequestMapping("/rest")
public class FranquiciaController {

	private final Logger logger = LoggerFactory
			.getLogger(FranquiciaController.class);

	@Autowired
	FranquiciaService franquiciaService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	@RequestMapping(value ="/service/franquicia/listAll", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Franquicia>> listAll(
			@RequestHeader HttpHeaders headers, @RequestBody Franquicia request) {
		
		TransactionRs<List<Franquicia>> response = new TransactionRs<List<Franquicia>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(franquiciaService.listAll(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error listAll. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/franquicia/list", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Franquicia>> list(
			@RequestHeader HttpHeaders headers, @RequestBody Franquicia request) {
		
		TransactionRs<List<Franquicia>> response = new TransactionRs<List<Franquicia>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(franquiciaService.list(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	
	@RequestMapping(value ="/service/franquicia/insert", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Franquicia> insert(
			@RequestHeader HttpHeaders headers, @RequestBody Franquicia request) {
		
		TransactionRs<Franquicia> response = new TransactionRs<Franquicia>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			franquiciaService.insert(request);		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value ="/service/franquicia/update", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Franquicia> update(
			@RequestHeader HttpHeaders headers, @RequestBody Franquicia request) {
		
		TransactionRs<Franquicia> response = new TransactionRs<Franquicia>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			franquiciaService.update(request);		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value ="/service/franquicia/delete", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Franquicia> delete(
			@RequestHeader HttpHeaders headers, @RequestBody Franquicia request) {
		
		TransactionRs<Franquicia> response = new TransactionRs<Franquicia>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			franquiciaService.delete(request);		
		} catch (Exception e) {
			logger.error("Error delete. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}
	
}
