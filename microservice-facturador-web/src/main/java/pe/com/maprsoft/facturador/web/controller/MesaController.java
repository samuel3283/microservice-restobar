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
import pe.com.maprsoft.facturador.model.Mesa;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Salon;
import pe.com.maprsoft.facturador.model.TransactionRs;
import pe.com.maprsoft.facturador.service.MesaService;
import pe.com.maprsoft.facturador.service.SalonService;
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;


@RestController
@RequestMapping("/rest")
public class MesaController {

	private final Logger logger = LoggerFactory
			.getLogger(MesaController.class);

	@Autowired
	MesaService mesaService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	
	@RequestMapping(value ="/service/mesa/update", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Mesa> update(
			@RequestHeader HttpHeaders headers, @RequestBody Mesa request) {
		
		TransactionRs<Mesa> response = new TransactionRs<Mesa>();
		try {
			mesaService.update(request);		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/mesa/updateStatus", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Mesa> updateStatus(
			@RequestHeader HttpHeaders headers, @RequestBody Mesa request) {		
		TransactionRs<Mesa> response = new TransactionRs<Mesa>();
		try {
			mesaService.updateStatus(request);		
		} catch (Exception e) {
			logger.error("Error updateStatus. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/mesa/insert", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Mesa> insert(
			@RequestHeader HttpHeaders headers, @RequestBody Mesa request) {
		TransactionRs<Mesa> response = new TransactionRs<Mesa>();
		try {
			mesaService.insert(request);		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/mesa/delete", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Mesa> delete(
			@RequestHeader HttpHeaders headers, @RequestBody Mesa request) {
		
		TransactionRs<Mesa> response = new TransactionRs<Mesa>();
		try {
			mesaService.delete(request);		
		} catch (Exception e) {
			logger.error("Error delete. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

}
