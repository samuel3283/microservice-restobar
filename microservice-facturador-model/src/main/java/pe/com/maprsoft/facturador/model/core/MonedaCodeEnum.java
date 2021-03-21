package pe.com.maprsoft.facturador.model.core;


public enum MonedaCodeEnum {
	  MONEDA_SOLES_PERU(1, "S/."), 
	  MONEDA_DOLAR_AMERICANO(2, "US$"),
	  MONEDA_EURO_EUROPA(3, "€");
	 
	  private final Integer code;
	  private final String message;
	  
	  MonedaCodeEnum(Integer code, String message) {
	     this.code = code;
	     this.message = message;
	  }

	  public Integer getCode() { return code; }
	  
	  public String getMessage() {
		  return message;		  
	  }
}
