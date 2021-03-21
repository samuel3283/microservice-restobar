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
import pe.com.maprsoft.facturador.model.Insumo;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Preparacion;
import pe.com.maprsoft.facturador.model.Producto;
import pe.com.maprsoft.facturador.model.Salon;
import pe.com.maprsoft.facturador.model.TransactionRs;
import pe.com.maprsoft.facturador.service.InsumoService;
import pe.com.maprsoft.facturador.service.MozoService;
import pe.com.maprsoft.facturador.service.PreparacionService;
import pe.com.maprsoft.facturador.service.SalonService;
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;


@RestController
@RequestMapping("/rest")
public class PreparacionController {

	private final Logger logger = LoggerFactory
			.getLogger(PreparacionController.class);

	@Autowired
	PreparacionService preparacionService;

	@Autowired
	private HeaderRqUtil headerRqUtil;


	@RequestMapping(value ="/service/preparacion/list", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Preparacion>> list(
			@RequestHeader HttpHeaders headers, @RequestBody Producto request) {
		
		TransactionRs<List<Preparacion>> response = new TransactionRs<List<Preparacion>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(preparacionService.list(request,headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/preparacion/listAll", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Preparacion>> listAll(
			@RequestHeader HttpHeaders headers, @RequestBody Producto request) {
		
		TransactionRs<List<Preparacion>> response = new TransactionRs<List<Preparacion>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(preparacionService.listAll(request, headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/preparacion/insert", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Preparacion> insert(
			@RequestHeader HttpHeaders headers, @RequestBody Preparacion request) {
		
		TransactionRs<Preparacion> response = new TransactionRs<Preparacion>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			preparacionService.insert(request, headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/preparacion/delete", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Preparacion> delete(
			@RequestHeader HttpHeaders headers, @RequestBody Preparacion request) {
		
		TransactionRs<Preparacion> response = new TransactionRs<Preparacion>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			preparacionService.delete(request, headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error delete. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	
	@RequestMapping(value ="/service/preparacion/update", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Preparacion> update(
			@RequestHeader HttpHeaders headers, @RequestBody Preparacion request) {
		
		TransactionRs<Preparacion> response = new TransactionRs<Preparacion>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			preparacionService.update(request, headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/preparacion/updateStatus", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Preparacion> updateStatus(
			@RequestHeader HttpHeaders headers, @RequestBody Preparacion request) {
		
		TransactionRs<Preparacion> response = new TransactionRs<Preparacion>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			preparacionService.updateStatus(request, headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

}
