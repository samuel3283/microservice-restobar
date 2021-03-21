package pe.com.maprsoft.facturador.model.core;


public enum EstadoPedidoCodeEnum {
	  PEDIDO_PREREGISTRO(0, "Pre-Registro"),
	  PEDIDO_PENDIENTE(1, "Pendiente"), 
	  PEDIDO_PRCESO(2, "En Proceso"), 
	  PEDIDO_ATENDIDO(3, "Atendido"), 
	  PEDIDO_CANCELADO(4, "Cancelado");
	 
	  private final Integer code;
	  private final String message;
	  
	  EstadoPedidoCodeEnum(Integer code, String message) {
	     this.code = code;
	     this.message = message;
	  }

	  public Integer getCode() { return code; }
	  
	  public String getMessage() {
		  return message;		  
	  }
}
