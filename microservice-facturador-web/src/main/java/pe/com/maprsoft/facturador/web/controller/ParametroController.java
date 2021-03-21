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
import pe.com.maprsoft.facturador.model.Parametro;
import pe.com.maprsoft.facturador.model.Salon;
import pe.com.maprsoft.facturador.model.TransactionRs;
import pe.com.maprsoft.facturador.model.Parametro;
import pe.com.maprsoft.facturador.service.MozoService;
import pe.com.maprsoft.facturador.service.ParametroService;
import pe.com.maprsoft.facturador.service.SalonService;
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;


@RestController
@RequestMapping("/rest")
public class ParametroController {

	private final Logger logger = LoggerFactory
			.getLogger(ParametroController.class);

	@Autowired
	ParametroService parametroService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	@RequestMapping(value ="/service/parametro/list", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Parametro>> list(
			@RequestHeader HttpHeaders headers, @RequestBody Parametro request) {
		
		TransactionRs<List<Parametro>> response = new TransactionRs<List<Parametro>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(parametroService.list(request, headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/parametro/listAll", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Parametro>> listAll(
			@RequestHeader HttpHeaders headers, @RequestBody Parametro request) {
		
		TransactionRs<List<Parametro>> response = new TransactionRs<List<Parametro>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(parametroService.listAll(request,headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error listAll. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/parametro/insert", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Parametro> insert(
			@RequestHeader HttpHeaders headers, @RequestBody Parametro request) {
		
		TransactionRs<Parametro> response = new TransactionRs<Parametro>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		parametroService.insert(request, headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/parametro/insertCode", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Parametro> insertCode(
			@RequestHeader HttpHeaders headers, @RequestBody Parametro request) {
		
		TransactionRs<Parametro> response = new TransactionRs<Parametro>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		parametroService.insertCode(request, headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/parametro/delete", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Parametro> delete(
			@RequestHeader HttpHeaders headers, @RequestBody Parametro request) {
		
		TransactionRs<Parametro> response = new TransactionRs<Parametro>();
		try {
		parametroService.delete(request);		
		} catch (Exception e) {
			logger.error("Error delete. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	
	@RequestMapping(value ="/service/parametro/update", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Parametro> update(
			@RequestHeader HttpHeaders headers, @RequestBody Parametro request) {
		
		TransactionRs<Parametro> response = new TransactionRs<Parametro>();
		try {
		parametroService.update(request);		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/parametro/updateStatus", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Parametro> updateStatus(
			@RequestHeader HttpHeaders headers, @RequestBody Parametro request) {
		
		TransactionRs<Parametro> response = new TransactionRs<Parametro>();
		try {
		parametroService.updateStatus(request);		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

}
