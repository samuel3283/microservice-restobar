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

import pe.com.maprsoft.facturador.model.Restaurante;
import pe.com.maprsoft.facturador.model.Salon;
import pe.com.maprsoft.facturador.model.Sucursal;
import pe.com.maprsoft.facturador.model.TransactionRs;
import pe.com.maprsoft.facturador.service.SalonService;
import pe.com.maprsoft.facturador.service.SucursalService;
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;


@RestController
@RequestMapping("/rest")
public class SucursalController {

	private final Logger logger = LoggerFactory
			.getLogger(SucursalController.class);

	@Autowired
	SucursalService sucursalService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	@RequestMapping(value ="/service/sucursal/list", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Sucursal>> list(
			@RequestHeader HttpHeaders headers, @RequestBody Salon request) {
		
		logger.info("list.");
		TransactionRs<List<Sucursal>> response = new TransactionRs<List<Sucursal>>();
		//HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);

		try {
		response.setRespuesta(sucursalService.list());		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/sucursal/listAll", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Sucursal>> listAll(
			@RequestHeader HttpHeaders headers, @RequestBody Salon request) {
		
		TransactionRs<List<Sucursal>> response = new TransactionRs<List<Sucursal>>();
		try {
		response.setRespuesta(sucursalService.listAll());		
		} catch (Exception e) {
			logger.error("Error listAll. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/sucursal/listByRestaurant", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Sucursal>> listByRestaurant(
			@RequestHeader HttpHeaders headers, @RequestBody Restaurante request) {
		
		logger.info("list.");
		TransactionRs<List<Sucursal>> response = new TransactionRs<List<Sucursal>>();
		//HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);

		try {
		response.setRespuesta(sucursalService.listByRestaurante(request));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}


	@RequestMapping(value ="/service/sucursal/insert", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Sucursal> insert(
			@RequestHeader HttpHeaders headers, @RequestBody Sucursal request) {
		
		TransactionRs<Sucursal> response = new TransactionRs<Sucursal>();
		try {
		sucursalService.insert(request);		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/sucursal/update", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Sucursal> update(
			@RequestHeader HttpHeaders headers, @RequestBody Sucursal request) {
		
		TransactionRs<Sucursal> response = new TransactionRs<Sucursal>();
		try {
		sucursalService.update(request);		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/sucursal/delete", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Sucursal> delete(
			@RequestHeader HttpHeaders headers, @RequestBody Sucursal request) {
		
		TransactionRs<Sucursal> response = new TransactionRs<Sucursal>();
		try {
		sucursalService.delete(request);		
		} catch (Exception e) {
			logger.error("Error delete. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}
}
