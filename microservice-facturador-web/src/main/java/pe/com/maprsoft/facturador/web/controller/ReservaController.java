package pe.com.maprsoft.facturador.web.controller;

import java.util.List;
import java.util.Map;

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
import pe.com.maprsoft.facturador.model.Reserva;
import pe.com.maprsoft.facturador.model.Salon;
import pe.com.maprsoft.facturador.model.TransactionRs;
import pe.com.maprsoft.facturador.service.MozoService;
import pe.com.maprsoft.facturador.service.ReservaService;
import pe.com.maprsoft.facturador.service.SalonService;
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;


@RestController
@RequestMapping("/rest")
public class ReservaController {

	private final Logger logger = LoggerFactory
			.getLogger(ReservaController.class);

	@Autowired
	private ReservaService reservaService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	@RequestMapping(value ="/service/reserva/list", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Reserva>> list(
			@RequestHeader HttpHeaders headers, @RequestBody Reserva request) {
		
		TransactionRs<List<Reserva>> response = new TransactionRs<List<Reserva>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(reservaService.list(request,headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value ="/service/reserva/insert", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public TransactionRs<Map<String, Object>> insert(
			@RequestHeader HttpHeaders headers, @RequestBody Reserva request) {
		
		TransactionRs<Map<String, Object>> response = new TransactionRs<Map<String, Object>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			response.setRespuesta(reservaService.insert(request, headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/reserva/updateStatus", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Reserva> updateStatus(
			@RequestHeader HttpHeaders headers, @RequestBody Reserva request) {		
		TransactionRs<Reserva> response = new TransactionRs<Reserva>();
		try {
		reservaService.updateStatus(request);		
		} catch (Exception e) {
			logger.error("Error updateStatus. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/reserva/update", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Reserva> update(
			@RequestHeader HttpHeaders headers, @RequestBody Reserva request) {		
		TransactionRs<Reserva> response = new TransactionRs<Reserva>();
		try {
		reservaService.update(request);		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/reserva/delete", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Reserva> delete(
			@RequestHeader HttpHeaders headers, @RequestBody Reserva request) {		
		TransactionRs<Reserva> response = new TransactionRs<Reserva>();
		try {
		reservaService.delete(request);		
		} catch (Exception e) {
			logger.error("Error delete. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

}
