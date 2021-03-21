package pe.com.maprsoft.facturador.model.core;


public enum EstadoMesaCodeEnum {
	  MESA_LIBRE(1, "bg-aqua"), 
	  MESA_POR_SALIR(2, "bg-yellow"), 
	  MESA_OCUPADA(3, "bg-red"), 
	  MESA_RESERVADA(4, "bg-green");
	 
	  private final Integer code;
	  private final String message;
	  
	  EstadoMesaCodeEnum(Integer code, String message) {
	     this.code = code;
	     this.message = message;
	  }

	  public Integer getCode() { return code; }
	  
	  public String getMessage() {
		  return message;		  
	  }
}
