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
import pe.com.maprsoft.facturador.model.HeaderRq;
import pe.com.maprsoft.facturador.model.Pedido;
import pe.com.maprsoft.facturador.model.TransactionRs;
import pe.com.maprsoft.facturador.service.PedidoService;
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;

@RestController
@RequestMapping("/rest")
public class PedidoController {

	private final Logger logger = LoggerFactory
			.getLogger(PedidoController.class);

	@Autowired
	PedidoService pedidoService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	@RequestMapping(value ="/service/pedido/insert", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Map<String, Object>> insert(
			@RequestHeader HttpHeaders headers, @RequestBody Pedido request) {
		
		TransactionRs<Map<String, Object>> response = new TransactionRs<Map<String, Object>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		
		try {
		Map<String, Object> mapa = new HashMap<>();
		mapa = pedidoService.insert(request,headerRq.getToken());
		response.setRespuesta(mapa);
		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/pedido/update", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Map<String, Object>> update(
			@RequestHeader HttpHeaders headers, @RequestBody Pedido request) {
		
		TransactionRs<Map<String, Object>> response = new TransactionRs<Map<String, Object>>();
		try {
		pedidoService.update(request);		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/pedido/updateDelivery", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Map<String, Object>> updateDelivery(
			@RequestHeader HttpHeaders headers, @RequestBody Pedido request) {
		
		TransactionRs<Map<String, Object>> response = new TransactionRs<Map<String, Object>>();
		try {
		pedidoService.updateDelivery(request);		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}
	
	@RequestMapping(value ="/service/pedido/updateCocina", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody	
	public TransactionRs<Map<String, Object>> updateAtendidoCocina(
			@RequestHeader HttpHeaders headers, @RequestBody Pedido request) {
		
		TransactionRs<Map<String, Object>> response = new TransactionRs<Map<String, Object>>();
		try {
		pedidoService.updateAtendidoCocina(request);		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/pedido/updateBarra", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Map<String, Object>> updateAtendidoBarra(
			@RequestHeader HttpHeaders headers, @RequestBody Pedido request) {
		
		TransactionRs<Map<String, Object>> response = new TransactionRs<Map<String, Object>>();
		try {
		pedidoService.updateAtendidoBarra(request);		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/pedido/deleteItem", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Map<String, Object>> deleteItem(
			@RequestHeader HttpHeaders headers, @RequestBody DetallePedido request) {
		
		TransactionRs<Map<String, Object>> response = new TransactionRs<Map<String, Object>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			pedidoService.deleteItemPedido(request,headerRq.getToken());
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/pedido/delete", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Map<String, Object>> delete(
			@RequestHeader HttpHeaders headers, @RequestBody Pedido request) {
		
		TransactionRs<Map<String, Object>> response = new TransactionRs<Map<String, Object>>();
		try {
			pedidoService.delete(request);
		} catch (Exception e) {
			logger.error("Error delete. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/pedido/updateStatusItem", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Map<String, Object>> updateStatusItem(
			@RequestHeader HttpHeaders headers, @RequestBody DetallePedido request) {
		
		TransactionRs<Map<String, Object>> response = new TransactionRs<Map<String, Object>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			pedidoService.updateStatusItem(request,headerRq.getToken());
		} catch (Exception e) {
			logger.error("Error updateStatusItem. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/pedido/updateStatus", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Map<String, Object>> updateStatus(
			@RequestHeader HttpHeaders headers, @RequestBody Pedido request) {
		
		TransactionRs<Map<String, Object>> response = new TransactionRs<Map<String, Object>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			pedidoService.updateStatus(request,headerRq.getToken());
		} catch (Exception e) {
			logger.error("Error updateStatusItem. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/pedido/validateDelivery", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Map<String, Object>> validateDelivery(
			@RequestHeader HttpHeaders headers, @RequestBody Pedido request) {
		
		TransactionRs<Map<String, Object>> response = new TransactionRs<Map<String, Object>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
			pedidoService.validateDelivery(request,headerRq.getToken());
		} catch (Exception e) {
			logger.error("Error updateStatusItem. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}


	@RequestMapping(value ="/service/pedido/insertItem", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Map<String, Object>> insertItem(
			@RequestHeader HttpHeaders headers, @RequestBody DetallePedido request) {
		
		TransactionRs<Map<String, Object>> response = new TransactionRs<Map<String, Object>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		pedidoService.insertItem(request,headerRq.getToken());
		
		} catch (Exception e) {
			logger.error("Error insertItem. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/pedido/get", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Pedido> get(
			@RequestHeader HttpHeaders headers, @RequestBody Pedido request) {
		
		TransactionRs<Pedido> response = new TransactionRs<Pedido>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		
		try {
		response.setRespuesta(pedidoService.get(request,headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error get. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}


	@RequestMapping(value ="/service/pedido/cancel", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Pedido> cancel(
			@RequestHeader HttpHeaders headers, @RequestBody Pedido request) {
		
		TransactionRs<Pedido> response = new TransactionRs<Pedido>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);		
		try {
			pedidoService.cancelar(request,headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error cancel. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value ="/service/pedido/getDetalle", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<DetallePedido>> getDetalle(
			@RequestHeader HttpHeaders headers, @RequestBody Pedido request) {
		
		TransactionRs<List<DetallePedido>> response = new TransactionRs<List<DetallePedido>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		
		try {
		response.setRespuesta(pedidoService.getDetalle(request));		
		} catch (Exception e) {
			logger.error("Error getDetalle. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/pedido/getPago", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Pedido> getPago(
			@RequestHeader HttpHeaders headers, @RequestBody Pedido request) {
		
		TransactionRs<Pedido> response = new TransactionRs<Pedido>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		
		try {
		response.setRespuesta(pedidoService.getPrePago(request,headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error get. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/pedido/getPagoMultiple", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Pedido> getPagoMultiple(
			@RequestHeader HttpHeaders headers, @RequestBody Pedido request) {
		
		TransactionRs<Pedido> response = new TransactionRs<Pedido>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		
		try {
		response.setRespuesta(pedidoService.getPrePagoMultiple(request,headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error get. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	
	
	
	@RequestMapping(value ="/service/pedido/listDelivery", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Pedido>> listDelivery(
			@RequestHeader HttpHeaders headers, @RequestBody Pedido request) {
		
		TransactionRs<List<Pedido>> response = new TransactionRs<List<Pedido>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		
		try {
		response.setRespuesta(pedidoService.listDelivery(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error listDelivery. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/pedido/listDetallePedido", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<DetallePedido>> listDetallePedido(
			@RequestHeader HttpHeaders headers, @RequestBody Pedido request) {
		
		TransactionRs<List<DetallePedido>> response = new TransactionRs<List<DetallePedido>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		
		try {
		response.setRespuesta(pedidoService.listDetallePedido(request));		
		} catch (Exception e) {
			logger.error("Error listDetallePedido. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}
	
}
