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

import pe.com.maprsoft.facturador.model.BalanceCabecera;
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
public class CajaController {

	private final Logger logger = LoggerFactory
			.getLogger(CajaController.class);

	@Autowired
	CajaService cajaService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	@RequestMapping(value ="/service/caja/list", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Caja>> list(
			@RequestHeader HttpHeaders headers, @RequestBody Caja request) {
		
		TransactionRs<List<Caja>> response = new TransactionRs<List<Caja>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(cajaService.list(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/caja/listCaja", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Caja>> listCaja(
			@RequestHeader HttpHeaders headers, @RequestBody Caja request) {
		
		TransactionRs<List<Caja>> response = new TransactionRs<List<Caja>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(cajaService.listCaja(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error listCaja. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/caja/apertura", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Caja> apertura(
			@RequestHeader HttpHeaders headers, @RequestBody Caja request) {
		
		TransactionRs<Caja> response = new TransactionRs<Caja>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		
		try {
		cajaService.apertura(request, headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error apertura. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/caja/cierre", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Caja> cierre(
			@RequestHeader HttpHeaders headers, @RequestBody Caja request) {
		
		TransactionRs<Caja> response = new TransactionRs<Caja>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		
		
		try {
		cajaService.cierre(request, headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error cierre. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	
	
	@RequestMapping(value ="/service/balance/list", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<BalanceCabecera>> listBalance(
			@RequestHeader HttpHeaders headers, @RequestBody Caja request) {		
		TransactionRs<List<BalanceCabecera>> response = new TransactionRs<List<BalanceCabecera>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(cajaService.listBalance(request,headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}
	
}
