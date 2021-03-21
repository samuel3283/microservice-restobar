package pe.com.maprsoft.facturador.web.controller;


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
import pe.com.maprsoft.facturador.model.Session;
import pe.com.maprsoft.facturador.model.TransactionRs;
import pe.com.maprsoft.facturador.model.Usuario;
import pe.com.maprsoft.facturador.service.SessionService;
import pe.com.maprsoft.facturador.service.core.HeaderRqUtil;


@RestController
@RequestMapping("/rest")
public class SessionController {

	@Autowired
	private SessionService sessionService;

	@Autowired
	private HeaderRqUtil headerRqUtil;

	private final Logger logger = LoggerFactory
			.getLogger(SessionController.class);


	
		@RequestMapping(value ="/service/session/login", 
				method = RequestMethod.POST, produces = { "application/json" })
		@ResponseBody
		public TransactionRs<Session> login(
				@RequestHeader HttpHeaders headers, @RequestBody Usuario request) {
			
			TransactionRs<Session> response = new TransactionRs<Session>();			
			try {
				HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
				response.setRespuesta(sessionService.loginDirect(request,headerRq));				
			} catch (Exception e) {
				logger.error("Error login", e.getMessage());
				response.setCodigoError("5000");
				response.setDescripcion(e.getMessage());
			}
			
			return response;
		}




		@RequestMapping(value ="/service/session/logout", 
				method = RequestMethod.POST, produces = { "application/json" })
		@ResponseBody
		public TransactionRs<Session> logout(
				@RequestHeader HttpHeaders headers) {
			
			TransactionRs<Session> response = new TransactionRs<Session>();			
			try {
				HeaderRq headerRq = headerRqUtil.getHttpHeader(headers);
				sessionService.delete(headerRq.getToken());				
			} catch (Exception e) {
				logger.error("Error logout ",e.getMessage());
				response.setCodigoError("5000");
				response.setDescripcion("Error Session not found");
			}
			
			return response;
		}

}