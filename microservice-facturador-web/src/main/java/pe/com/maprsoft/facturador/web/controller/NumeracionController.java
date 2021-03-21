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
import pe.com.maprsoft.facturador.model.Numeracion;
import pe.com.maprsoft.facturador.model.Salon;
import pe.com.maprsoft.facturador.model.TransactionRs;
import pe.com.maprsoft.facturador.service.MozoService;
import pe.com.maprsoft.facturador.service.NumeracionService;
import pe.com.maprsoft.facturador.service.SalonService;
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;


@RestController
@RequestMapping("/rest")
public class NumeracionController {

	private final Logger logger = LoggerFactory
			.getLogger(NumeracionController.class);

	@Autowired
	NumeracionService numeracionService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	@RequestMapping(value ="/service/numeracion/list", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Numeracion>> list(
			@RequestHeader HttpHeaders headers, @RequestBody Numeracion request) {
		
		TransactionRs<List<Numeracion>> response = new TransactionRs<List<Numeracion>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(numeracionService.list(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}
	@RequestMapping(value ="/service/numeracion/insert", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Numeracion> insert(
			@RequestHeader HttpHeaders headers, @RequestBody Numeracion request) {
		
		TransactionRs<Numeracion> response = new TransactionRs<Numeracion>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			numeracionService.insert(request, headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value ="/service/numeracion/update", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Numeracion> update(
			@RequestHeader HttpHeaders headers, @RequestBody Numeracion request) {
		
		TransactionRs<Numeracion> response = new TransactionRs<Numeracion>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			numeracionService.update(request);		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value ="/service/numeracion/delete", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Numeracion> delete(
			@RequestHeader HttpHeaders headers, @RequestBody Numeracion request) {
		
		TransactionRs<Numeracion> response = new TransactionRs<Numeracion>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			numeracionService.delete(request);		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}
	
}
