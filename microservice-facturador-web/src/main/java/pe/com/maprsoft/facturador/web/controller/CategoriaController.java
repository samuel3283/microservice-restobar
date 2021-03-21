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
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;


@RestController
@RequestMapping("/rest")
public class CategoriaController {

	private final Logger logger = LoggerFactory
			.getLogger(CategoriaController.class);

	@Autowired
	CategoriaService categoriaService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	@RequestMapping(value ="/service/categoria/listCategoria", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Categoria>> listCategoria(
			@RequestHeader HttpHeaders headers, @RequestBody Categoria request) {
		
		TransactionRs<List<Categoria>> response = new TransactionRs<List<Categoria>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);		
		try {
		response.setRespuesta(categoriaService.listCategoria(headerRq.getToken()));
		
		} catch (Exception e) {
			logger.error("Error listCategoria. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/categoria/listSubCategoria", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<SubCategoria>> listSubCategoria(
			@RequestHeader HttpHeaders headers, @RequestBody Categoria request) {
		
		TransactionRs<List<SubCategoria>> response = new TransactionRs<List<SubCategoria>>();		
		try {
		response.setRespuesta(categoriaService.listSubCategoria(request));		
		} catch (Exception e) {
			logger.error("Error listSubCategoria. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/categoria/listProducto", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Producto>> listSubCategoria(
			@RequestHeader HttpHeaders headers, @RequestBody SubCategoria request) {
		
		TransactionRs<List<Producto>> response = new TransactionRs<List<Producto>>();		
		try {
		response.setRespuesta(categoriaService.listProducto(request));		
		} catch (Exception e) {
			logger.error("Error listSubCategoria. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	
	@RequestMapping(value ="/service/categoria/list", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Categoria>> list(
			@RequestHeader HttpHeaders headers, @RequestBody Categoria request) {
		
		logger.info("list.");
		TransactionRs<List<Categoria>> response = new TransactionRs<List<Categoria>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);

		try {
		response.setRespuesta(categoriaService.list(headerRq.getToken()));
		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	//Lista las categorias para pedidos
	@RequestMapping(value ="/service/categoria/listAll", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<Categoria>> listAll(
			@RequestHeader HttpHeaders headers, @RequestBody Categoria request) {
		
		logger.info("list.");
		TransactionRs<List<Categoria>> response = new TransactionRs<List<Categoria>>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);

		try {
		response.setRespuesta(categoriaService.listAll(headerRq.getToken()));
		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	
	@RequestMapping(value ="/service/categoria/insert", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Categoria> insert(
			@RequestHeader HttpHeaders headers, @RequestBody Categoria request) {
		
		TransactionRs<Categoria> response = new TransactionRs<Categoria>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);

		try {
		categoriaService.insert(request, headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/categoria/update", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Categoria> update(
			@RequestHeader HttpHeaders headers, @RequestBody Categoria request) {
		
		TransactionRs<Categoria> response = new TransactionRs<Categoria>();
		try {
		categoriaService.update(request);		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}
	
	@RequestMapping(value ="/service/categoria/delete", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<Categoria> delete(
			@RequestHeader HttpHeaders headers, @RequestBody Categoria request) {
		
		TransactionRs<Categoria> response = new TransactionRs<Categoria>();
		try {
		categoriaService.delete(request);		
		} catch (Exception e) {
			logger.error("Error delete. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		
		return response;
	}

}
