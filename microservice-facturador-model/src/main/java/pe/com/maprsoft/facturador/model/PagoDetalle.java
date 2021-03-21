package pe.com.maprsoft.facturador.model;

public class PagoDetalle {
	
	private Integer codigo;
	private Integer codigoPago;
	private Integer codigoProducto;
	private String nombre;
	private String moneda;
	private String precio;
	private Integer cantidad;
	private Integer estado;
	private String desEstado;
	private String igv;
	private String subTotal;
	private String subTotalBruto;
	private String descuento;
	
	public PagoDetalle() {
		super();
	}
	
	public String getDescuento() {
		return descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	public String getSubTotalBruto() {
		return subTotalBruto;
	}

	public void setSubTotalBruto(String subTotalBruto) {
		this.subTotalBruto = subTotalBruto;
	}

	public String getIgv() {
		return igv;
	}

	public void setIgv(String igv) {
		this.igv = igv;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}



	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public Integer getCodigoPago() {
		return codigoPago;
	}

	public void setCodigoPago(Integer codigoPago) {
		this.codigoPago = codigoPago;
	}

	public String getDesEstado() {
		return desEstado;
	}

	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}


	public Integer getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(Integer codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	

}
