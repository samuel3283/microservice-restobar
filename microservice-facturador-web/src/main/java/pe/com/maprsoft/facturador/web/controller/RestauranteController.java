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
import pe.com.maprsoft.facturador.model.Restaurante;
import pe.com.maprsoft.facturador.model.TransactionRs;
import pe.com.maprsoft.facturador.service.RestauranteService;
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;


@RestController
@RequestMapping("/rest")
public class RestauranteController {

	private final Logger logger = LoggerFactory
			.getLogger(RestauranteController.class);

	@Autowired
	RestauranteService restauranteService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	@RequestMapping(value ="/service/restaurante/listAll", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Restaurante>> listAll(
			@RequestHeader HttpHeaders headers, @RequestBody Restaurante request) {
		
		TransactionRs<List<Restaurante>> response = new TransactionRs<List<Restaurante>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(restauranteService.listAll(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error listAll. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/restaurante/list", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Restaurante>> list(
			@RequestHeader HttpHeaders headers, @RequestBody Restaurante request) {
		
		TransactionRs<List<Restaurante>> response = new TransactionRs<List<Restaurante>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(restauranteService.list(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	
	@RequestMapping(value ="/service/restaurante/insert", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Restaurante> insert(
			@RequestHeader HttpHeaders headers, @RequestBody Restaurante request) {
		
		TransactionRs<Restaurante> response = new TransactionRs<Restaurante>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			restauranteService.insert(request);		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	
	@RequestMapping(value ="/service/restaurante/update", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Restaurante> update(
			@RequestHeader HttpHeaders headers, @RequestBody Restaurante request) {
		
		TransactionRs<Restaurante> response = new TransactionRs<Restaurante>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			restauranteService.update(request);		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value ="/service/restaurante/delete", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Restaurante> delete(
			@RequestHeader HttpHeaders headers, @RequestBody Restaurante request) {
		
		TransactionRs<Restaurante> response = new TransactionRs<Restaurante>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			restauranteService.delete(request);		
		} catch (Exception e) {
			logger.error("Error delete. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}
	
}
