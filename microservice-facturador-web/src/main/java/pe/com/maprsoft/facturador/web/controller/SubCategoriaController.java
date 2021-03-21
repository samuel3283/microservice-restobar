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
import pe.com.maprsoft.facturador.service.SubCategoriaService;
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;


@RestController
@RequestMapping("/rest")
public class SubCategoriaController {

	private final Logger logger = LoggerFactory
			.getLogger(SubCategoriaController.class);

	@Autowired
	SubCategoriaService subCategoriaService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	@RequestMapping(value ="/service/subcategoria/list", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<List<SubCategoria>> listCategoria(
			@RequestHeader HttpHeaders headers, @RequestBody Categoria request) {
		
		TransactionRs<List<SubCategoria>> response = new TransactionRs<List<SubCategoria>>();
		try {
		response.setRespuesta(subCategoriaService.list(request));		
		} catch (Exception e) {
			logger.error("Error list. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	
	@RequestMapping(value ="/service/subcategoria/insert", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<SubCategoria> insert(
			@RequestHeader HttpHeaders headers, @RequestBody SubCategoria request) {
		
		TransactionRs<SubCategoria> response = new TransactionRs<SubCategoria>();
		HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);

		try {
		subCategoriaService.insert(request, headerRq.getToken());		
		} catch (Exception e) {
			logger.error("Error insert. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value ="/service/subcategoria/update", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<SubCategoria> update(
			@RequestHeader HttpHeaders headers, @RequestBody SubCategoria request) {
		
		TransactionRs<SubCategoria> response = new TransactionRs<SubCategoria>();
		try {
			subCategoriaService.update(request);		
		} catch (Exception e) {
			logger.error("Error update. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}

		return response;
	}
	
	@RequestMapping(value ="/service/subcategoria/delete", 
			method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody		
	public TransactionRs<SubCategoria> delete(
			@RequestHeader HttpHeaders headers, @RequestBody SubCategoria request) {
		TransactionRs<SubCategoria> response = new TransactionRs<SubCategoria>();		
		try {
			subCategoriaService.delete(request);		
		} catch (Exception e) {
			logger.error("Error delete. ",e.getMessage());
			response.setCodigoError("5000");
			response.setDescripcion(e.getMessage());
		}
		
		return response;
	}

}
