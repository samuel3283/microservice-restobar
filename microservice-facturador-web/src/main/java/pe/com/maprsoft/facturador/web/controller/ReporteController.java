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

import pe.com.maprsoft.facturador.model.Caja;
import pe.com.maprsoft.facturador.model.HeaderRq;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.Reserva;
import pe.com.maprsoft.facturador.model.Salon;
import pe.com.maprsoft.facturador.model.TransactionRs;
import pe.com.maprsoft.facturador.service.MozoService;
import pe.com.maprsoft.facturador.service.ReporteService;
import pe.com.maprsoft.facturador.service.ReservaService;
import pe.com.maprsoft.facturador.service.SalonService;
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;
import pe.com.maprsoft.facturador.web.view.ExcelView;


@RestController
@RequestMapping("/rest")
public class ReporteController {

	private final Logger logger = LoggerFactory
			.getLogger(ReporteController.class);

	@Autowired
	private ReporteService reporteService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	
	@RequestMapping(value ="/service/reporte/listEstados", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Map<String, Object>>> listEstados(
			@RequestHeader HttpHeaders headers, @RequestBody Map<String, Object> request) {
		
		TransactionRs<List<Map<String, Object>>> response = new TransactionRs<List<Map<String, Object>>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(reporteService.listEstados(headerRq.getToken(),request));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value ="/service/reporte/listPagoPorMes", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Map<String, Object>>> listPagoPorMes(
			@RequestHeader HttpHeaders headers, @RequestBody Map<String, Object> request) {
		
		TransactionRs<List<Map<String, Object>>> response = new TransactionRs<List<Map<String, Object>>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			//logger.info("periodo:"+request.get("periodo"));
		response.setRespuesta(reporteService.listxPago(headerRq.getToken(),request));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}


	@RequestMapping(value ="/service/reporte/ventaDiario", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Map<String, Object>> ventaDiario(
			@RequestHeader HttpHeaders headers, @RequestBody Map<String, Object> request) {
		
		TransactionRs<Map<String, Object>> response = new TransactionRs<Map<String, Object>>();		
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			//logger.info("periodo:"+request.get("periodo"));
		response.setRespuesta(reporteService.ventaByDia(headerRq.getToken(),request));		
		} catch (Exception e) {
			logger.error("Error ventaDiario. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}


	@RequestMapping(value ="/service/reporte/descuentoDiario", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Map<String, Object>> descuentoDiario(
			@RequestHeader HttpHeaders headers, @RequestBody Map<String, Object> request) {
		
		TransactionRs<Map<String, Object>> response = new TransactionRs<Map<String, Object>>();		
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			response.setRespuesta(reporteService.descuentoByDia(headerRq.getToken(),request));		
		} catch (Exception e) {
			logger.error("Error descuentoDiario. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value ="/service/reporte/ventaByMozo", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Map<String, Object>>> ventaByMozo(
			@RequestHeader HttpHeaders headers, @RequestBody Map<String, Object> request) {
		
		TransactionRs<List<Map<String, Object>>> response = new TransactionRs<List<Map<String, Object>>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			//logger.info("periodo:"+request.get("periodo"));
		response.setRespuesta(reporteService.ventaByMozo(headerRq.getToken(),request));		
		} catch (Exception e) {
			logger.error("Error ventaByMozo. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	
	@RequestMapping(value ="/service/reporte/ventaCategorias", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<List<Map<String, Object>>>> ventaCategorias(
			@RequestHeader HttpHeaders headers, @RequestBody Map<String, Object> request) {
		
		TransactionRs<List<List<Map<String, Object>>>> response = new TransactionRs<List<List<Map<String, Object>>>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(reporteService.ventasByProducto(headerRq.getToken(),request));		
		} catch (Exception e) {
			logger.error("Error ventasByProducto. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}


	@RequestMapping(value ="/service/reporte/incurrencias", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Map<String, Object>>> incurrencias(
			@RequestHeader HttpHeaders headers, @RequestBody Map<String, Object> request) {
		
		TransactionRs<List<Map<String, Object>>> response = new TransactionRs<List<Map<String, Object>>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(reporteService.reporteIncurrencias(headerRq.getToken(),request));		
		} catch (Exception e) {
			logger.error("Error ventaByMozo. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	
	@RequestMapping(value ="/service/reporte/cajaByDia", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Caja>> cajaByDia(
			@RequestHeader HttpHeaders headers, @RequestBody Map<String, Object> request) {
		
		TransactionRs<List<Caja>> response = new TransactionRs<List<Caja>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(reporteService.reporteCajaByDia(headerRq.getToken(),request));		
		} catch (Exception e) {
			logger.error("Error cajaByDia. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}


	@RequestMapping(value ="/service/reporte/ventaByDocumento", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Map<String, Object>>> ventaByDocumento(
			@RequestHeader HttpHeaders headers, @RequestBody Map<String, Object> request) {
		
		TransactionRs<List<Map<String, Object>>> response = new TransactionRs<List<Map<String, Object>>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			//logger.info("periodo:"+request.get("periodo"));
		response.setRespuesta(reporteService.emisionComprobantes(headerRq.getToken(),request));		
		} catch (Exception e) {
			logger.error("Error ventaByDocumento. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}


}
