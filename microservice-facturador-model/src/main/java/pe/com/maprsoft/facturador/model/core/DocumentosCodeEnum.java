package pe.com.maprsoft.facturador.model.core;


public enum DocumentosCodeEnum {
	  TOCKET("T", "Ticket"), 
	  BOLETA("B", "Boleta de venta"), 
	  FACTURA("F", "Factura");
	 
	  private final String code;
	  private final String message;
	  
	 
	  DocumentosCodeEnum(String code, String message) {
	     this.code = code;
	     this.message = message;
	  }

		 public static String getDocumento(String code) {
			for (DocumentosCodeEnum documentos : DocumentosCodeEnum.values()) {
				if(String.valueOf(documentos.getCode()).equals(code)){
					return String.valueOf(documentos.getMessage());
			}
			}
			return "";
		}
		 
		 
	  public String getCode() { return code; }
	  
	  public String getMessage() {
		  return message;		  
	  }
}
