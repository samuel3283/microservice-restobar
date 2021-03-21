package pe.com.maprsoft.facturador.web.controller;

import java.util.HashMap;
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

import pe.com.maprsoft.facturador.model.DetallePedido;
import pe.com.maprsoft.facturador.model.Filter;
import pe.com.maprsoft.facturador.model.HeaderRq;
import pe.com.maprsoft.facturador.model.Pago;
import pe.com.maprsoft.facturador.model.Pedido;
import pe.com.maprsoft.facturador.model.TransactionRs;
import pe.com.maprsoft.facturador.service.PagoService;
import pe.com.maprsoft.facturador.service.PedidoService;
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;

@RestController
@RequestMapping("/rest")
public class PagoController {

	private final Logger logger = LoggerFactory
			.getLogger(PagoController.class);

	@Autowired
	PagoService pagoService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	@RequestMapping(value ="/service/pago/insert", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Map<String, Object>> insert(
			@RequestHeader HttpHeaders headers, @RequestBody Pago request) {
		
		TransactionRs<Map<String, Object>> response = new TransactionRs<Map<String, Object>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		
		try {
		Map<String, Object> mapa = new HashMap<>();
		mapa = pagoService.insert(request, headerRq.getToken());
		response.setRespuesta(mapa);
		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}


	@RequestMapping(value ="/service/pago/insertList", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Pago>> insertList(
			@RequestHeader HttpHeaders headers, @RequestBody List<Pago> request) {
		
		TransactionRs<List<Pago>> response = new TransactionRs<List<Pago>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		
		try {
		
		response.setRespuesta(pagoService.insertList(request, headerRq.getToken()));
		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}


	@RequestMapping(value ="/service/pago/generaComprobante", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<String> generaComprobante(
			@RequestHeader HttpHeaders headers, @RequestBody Pago request) {
		
		TransactionRs<String> response = new TransactionRs<String>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);		
		try {
		String valor  = pagoService.generaComprobante(request, headerRq.getToken());
		response.setRespuesta(valor);		
		} catch (Exception e) {
			logger.error("Error generaBoleta. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}


	@RequestMapping(value ="/service/pago/generaBoleta", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<String> generaBoleta(
			@RequestHeader HttpHeaders headers, @RequestBody Pago request) {
		
		TransactionRs<String> response = new TransactionRs<String>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);		
		try {
		String valor  = pagoService.generaBoleta(request, headerRq.getToken());
		response.setRespuesta(valor);		
		} catch (Exception e) {
			logger.error("Error generaBoleta. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	
	@RequestMapping(value ="/service/pago/generaPreCuenta", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<String> generaPreCuenta(
			@RequestHeader HttpHeaders headers, @RequestBody Pedido request) {
		
		TransactionRs<String> response = new TransactionRs<String>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);		
		try {
		String valor  = pagoService.generPreCuenta(request, headerRq.getToken());
		response.setRespuesta(valor);		
		} catch (Exception e) {
			logger.error("Error generaBoleta. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	
	@RequestMapping(value ="/service/pago/listAll", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Pago>> listAll(
			@RequestHeader HttpHeaders headers, @RequestBody Filter request) {
		
		TransactionRs<List<Pago>> response = new TransactionRs<List<Pago>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		
		try {
		
		response.setRespuesta(pagoService.listAll(request,headerRq.getToken()));
		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}


	@RequestMapping(value ="/service/pago/listByFilter", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Pago>> listByFilter(
			@RequestHeader HttpHeaders headers, @RequestBody Filter request) {
		
		TransactionRs<List<Pago>> response = new TransactionRs<List<Pago>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		
		try {
		
		response.setRespuesta(pagoService.listByFilter(request,headerRq.getToken()));
		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}


}
