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

import pe.com.maprsoft.facturador.model.Categoria;
import pe.com.maprsoft.facturador.model.HeaderRq;
import pe.com.maprsoft.facturador.model.Producto;
import pe.com.maprsoft.facturador.model.SubCategoria;
import pe.com.maprsoft.facturador.model.TransactionRs;
import pe.com.maprsoft.facturador.service.CategoriaService;
import pe.com.maprsoft.facturador.service.ProductoService;
import pe.com.maprsoft.facturador.service.SubCategoriaService;
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;


@RestController
@RequestMapping("/rest")
public class ProductController {

	private final Logger logger = LoggerFactory
			.getLogger(ProductController.class);

	@Autowired
	ProductoService productoService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	@RequestMapping(value ="/service/producto/listAll", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Producto>> listAll(
			@RequestHeader HttpHeaders headers, @RequestBody SubCategoria request) {
		
		TransactionRs<List<Producto>> response = new TransactionRs<List<Producto>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
		try {
		response.setRespuesta(productoService.listAll(headerRq.getToken()));		
		} catch (Exception e) {
			logger.error("Error listAll. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}


	@RequestMapping(value ="/service/producto/list", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Producto>> list(
			@RequestHeader HttpHeaders headers, @RequestBody SubCategoria request) {
		
		TransactionRs<List<Producto>> response = new TransactionRs<List<Producto>>();
		try {
		response.setRespuesta(productoService.list(request));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}


	@RequestMapping(value ="/service/producto/insert", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Producto> insert(
			@RequestHeader HttpHeaders headers, @RequestBody Producto request) {
		
		TransactionRs<Producto> response = new TransactionRs<Producto>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);

		try {
			productoService.insert(request, headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/producto/update", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Producto> update(
			@RequestHeader HttpHeaders headers, @RequestBody Producto request) {
		
		TransactionRs<Producto> response = new TransactionRs<Producto>();
		try {
			productoService.update(request);		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}
	
	@RequestMapping(value ="/service/producto/delete", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Producto> delete(
			@RequestHeader HttpHeaders headers, @RequestBody Producto request) {
		TransactionRs<Producto> response = new TransactionRs<Producto>();		
		try {
			productoService.delete(request);		
		} catch (Exception e) {
			logger.error("Error delete. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		
		return response;
	}

}
