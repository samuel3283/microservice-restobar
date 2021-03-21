package pe.com.maprsoft.facturador.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class DetallePedido {
	
	@JsonInclude(Include.NON_NULL)
	private Integer codigo;
	@JsonInclude(Include.NON_NULL)
	private Integer codigoPedido;
	@JsonInclude(Include.NON_NULL)
	private Integer codigoProducto;
	private String nombre;
	private String moneda;
	@JsonInclude(Include.NON_NULL)
	private String precio;
	@JsonInclude(Include.NON_NULL)
	private Integer estado;
	@JsonInclude(Include.NON_NULL)
	private String desEstado;
	@JsonInclude(Include.NON_NULL)
	private String comentario;
	@JsonInclude(Include.NON_NULL)
	private String fechaRegistro;
	
	@JsonInclude(Include.NON_NULL)
	private String id;
	@JsonInclude(Include.NON_NULL)
	private Integer indice;

	@JsonInclude(Include.NON_NULL)
	private Integer cantidad;
	@JsonInclude(Include.NON_NULL)
	private String subTotal;
	@JsonInclude(Include.NON_NULL)
	private String igv;
	@JsonInclude(Include.NON_NULL)
	private String igvBruto;
	@JsonInclude(Include.NON_NULL)
	private String descuento;

	@JsonInclude(Include.NON_NULL)
	private String precioBruto;
	@JsonInclude(Include.NON_NULL)
	private String subTotalBruto;
	@JsonInclude(Include.NON_NULL)
	private String color;
	
	@JsonInclude(Include.NON_NULL)
	private String tipo;
	@JsonInclude(Include.NON_NULL)
	private String lugarElaboracion;
	
	
	public DetallePedido() {
		super();
	}
	
	
	public String getDescuento() {
		return descuento;
	}


	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getLugarElaboracion() {
		return lugarElaboracion;
	}


	public void setLugarElaboracion(String lugarElaboracion) {
		this.lugarElaboracion = lugarElaboracion;
	}


	public String getIgv() {
		return igv;
	}

	public void setIgv(String igv) {
		this.igv = igv;
	}

	public String getIgvBruto() {
		return igvBruto;
	}

	public void setIgvBruto(String igvBruto) {
		this.igvBruto = igvBruto;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPrecioBruto() {
		return precioBruto;
	}


	public void setPrecioBruto(String precioBruto) {
		this.precioBruto = precioBruto;
	}


	public String getSubTotalBruto() {
		return subTotalBruto;
	}


	public void setSubTotalBruto(String subTotalBruto) {
		this.subTotalBruto = subTotalBruto;
	}


	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public Integer getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(Integer codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public String getDesEstado() {
		return desEstado;
	}

	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
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

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}
	
	

}
