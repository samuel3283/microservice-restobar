package pe.com.maprsoft.facturador.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import pe.com.maprsoft.facturador.service.ExcelOutputService;
import pe.com.maprsoft.facturador.service.MozoService;
import pe.com.maprsoft.facturador.service.ReporteService;
import pe.com.maprsoft.facturador.service.ReservaService;
import pe.com.maprsoft.facturador.service.SalonService;
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;


@RestController
@RequestMapping("/rest")
public class ExcelController {

	private final Logger logger = LoggerFactory
			.getLogger(ExcelController.class);

	@Autowired
	private ReporteService reporteService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	@Autowired 
	private ExcelOutputService excelOutputService;
	
	@RequestMapping(value ="/service/excel/listado", 
			method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Map<String, Object>>> listEstados(
			//@RequestHeader HttpHeaders headers, @RequestBody Map<String, Object> 
			@RequestHeader HttpHeaders headers, HttpServletResponse response) {
		
		excelOutputService.createExcelOutputExcel(response);
		
		return null;
	}
	

}
