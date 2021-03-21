package pe.com.maprsoft.facturador.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Insumo {

	@JsonInclude(Include.NON_NULL)
	private Integer codigo;
	@JsonInclude(Include.NON_NULL)
	private Integer limite;
	@JsonInclude(Include.NON_NULL)
	private String medida;
	@JsonInclude(Include.NON_NULL)
	private String nombre;
	@JsonInclude(Include.NON_NULL)
	private String cantidad;
	@JsonInclude(Include.NON_NULL)
	private String cantidadReal;
	@JsonInclude(Include.NON_NULL)
	private String merma;	
	@JsonInclude(Include.NON_NULL)
	private String stockMinimo;
	@JsonInclude(Include.NON_NULL)
	private Integer estado;
	@JsonInclude(Include.NON_NULL)
	private String fechaRegistro;	
	@JsonIgnore
	private Sucursal sucursal;
	@JsonInclude(Include.NON_NULL)
	private String lugarElaboracion;
	@JsonInclude(Include.NON_NULL)
	private String precioCompra;	

	
	
	public String getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(String precioCompra) {
		this.precioCompra = precioCompra;
	}

	public String getLugarElaboracion() {
		return lugarElaboracion;
	}

	public void setLugarElaboracion(String lugarElaboracion) {
		this.lugarElaboracion = lugarElaboracion;
	}

	public Integer getLimite() {
		return limite;
	}

	public void setLimite(Integer limite) {
		this.limite = limite;
	}

	public String getCantidadReal() {
		return cantidadReal;
	}

	public void setCantidadReal(String cantidadReal) {
		this.cantidadReal = cantidadReal;
	}

	public Insumo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Insumo() {
		super();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getMerma() {
		return merma;
	}

	public void setMerma(String merma) {
		this.merma = merma;
	}

	public String getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(String stockMinimo) {
		this.stockMinimo = stockMinimo;
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

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}



}
