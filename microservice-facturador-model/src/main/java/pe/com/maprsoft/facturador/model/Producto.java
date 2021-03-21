package pe.com.maprsoft.facturador.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Producto extends Filter {
	@JsonProperty("codigo")
	private Integer id;
	private Integer estado;

	private String nombre;
	private String precio;
	private String moneda;	
	private String monedaDes;	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer codigoSubCategoria;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer codigoCategoria;
	
	@JsonIgnore
	private Sucursal sucursal;
	private String tipo;
	private String elaboracion;
	
	
	public Producto(Integer id) {
		super();
		this.id = id;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public String getMonedaDes() {
		return monedaDes;
	}

	public void setMonedaDes(String monedaDes) {
		this.monedaDes = monedaDes;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getElaboracion() {
		return elaboracion;
	}

	public void setElaboracion(String elaboracion) {
		this.elaboracion = elaboracion;
	}

	public Integer getCodigoSubCategoria() {
		return codigoSubCategoria;
	}

	public void setCodigoSubCategoria(Integer codigoSubCategoria) {
		this.codigoSubCategoria = codigoSubCategoria;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Producto() {
		super();
	}
	
	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public Integer getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(Integer codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}
	


}
