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
import pe.com.maprsoft.facturador.model.Pedido;
import pe.com.maprsoft.facturador.model.Salon;
import pe.com.maprsoft.facturador.model.TransactionRs;
import pe.com.maprsoft.facturador.service.CocinaService;
import pe.com.maprsoft.facturador.service.MozoService;
import pe.com.maprsoft.facturador.service.SalonService;
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;


@RestController
@RequestMapping("/rest")
public class CocinaController {

	private final Logger logger = LoggerFactory
			.getLogger(CocinaController.class);

	@Autowired
	CocinaService cocinaService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	@RequestMapping(value ="/service/cocina/listPedidosCocina", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Pedido>> listPedidos(
			@RequestHeader HttpHeaders headers, @RequestBody Pedido request) {
		
		TransactionRs<List<Pedido>> response = new TransactionRs<List<Pedido>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(cocinaService.listPedidosCocina(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error listPedidos. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}


	@RequestMapping(value ="/service/cocina/listPedidosBarra", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Pedido>> listPedidosBarra(
			@RequestHeader HttpHeaders headers, @RequestBody Pedido request) {
		
		TransactionRs<List<Pedido>> response = new TransactionRs<List<Pedido>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(cocinaService.listPedidosBarra(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error listPedidos. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

}
