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
import pe.com.maprsoft.facturador.service.SalonService;
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;


@RestController
@RequestMapping("/rest")
public class SalonController {

	private final Logger logger = LoggerFactory
			.getLogger(SalonController.class);

	@Autowired
	SalonService salonService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	@RequestMapping(value ="/service/salon/listAll", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Salon>> listAll(
			@RequestHeader HttpHeaders headers, @RequestBody Salon request) {
		
		TransactionRs<List<Salon>> response = new TransactionRs<List<Salon>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);

		try {
		response.setRespuesta(salonService.listAll(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error listAll. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/salon/list", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Salon>> list(
			@RequestHeader HttpHeaders headers, @RequestBody Salon request) {
		
		logger.info("list.");
		TransactionRs<List<Salon>> response = new TransactionRs<List<Salon>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);

		try {
		response.setRespuesta(salonService.list(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	
	@RequestMapping(value ="/service/salon/update", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Salon> update(
			@RequestHeader HttpHeaders headers, @RequestBody Salon request) {
		
		TransactionRs<Salon> response = new TransactionRs<Salon>();
		try {
			salonService.update(request);		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/salon/updateStatus", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Salon> updateStatus(
			@RequestHeader HttpHeaders headers, @RequestBody Salon request) {
		
		TransactionRs<Salon> response = new TransactionRs<Salon>();
		try {
			salonService.updateStatus(request);		
		} catch (Exception e) {
			logger.error("Error updateStatus. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/salon/insert", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Salon> insert(
			@RequestHeader HttpHeaders headers, @RequestBody Salon request) {
		
		TransactionRs<Salon> response = new TransactionRs<Salon>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			salonService.insert(request, headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/salon/delete", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Salon> delete(
			@RequestHeader HttpHeaders headers, @RequestBody Salon request) {
		
		TransactionRs<Salon> response = new TransactionRs<Salon>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			salonService.delete(request, headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error delete. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

}
