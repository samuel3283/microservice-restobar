package pe.com.maprsoft.facturador.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Preparacion {

	@JsonInclude(Include.NON_NULL)
	private Integer codigo;
	@JsonInclude(Include.NON_NULL)
	private Integer codigoProducto;
	@JsonInclude(Include.NON_NULL)
	private Integer codigoInsumo;

	@JsonInclude(Include.NON_NULL)
	private Producto producto;
	@JsonInclude(Include.NON_NULL)
	private Insumo insumo;
	
	@JsonInclude(Include.NON_NULL)
	private String medida;
	@JsonInclude(Include.NON_NULL)
	private String cantidad;
	
	@JsonInclude(Include.NON_NULL)
	private Integer estado;
	@JsonInclude(Include.NON_NULL)
	private String fechaRegistro;	

	public Preparacion(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Preparacion() {
		super();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(Integer codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public Integer getCodigoInsumo() {
		return codigoInsumo;
	}

	public void setCodigoInsumo(Integer codigoInsumo) {
		this.codigoInsumo = codigoInsumo;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}



}
