package pe.com.maprsoft.facturador.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Franquicia {
	
	@JsonProperty("codigo")
	private Integer codigo;
	@JsonInclude(Include.NON_NULL)
	private String nombre;
	@JsonInclude(Include.NON_NULL)
	private String razonSocial;
	@JsonInclude(Include.NON_NULL)
	private String ruc;
	@JsonInclude(Include.NON_NULL)
	private String direccion;
	private Integer estado;
	
	
	public Franquicia(Integer codigo) {
		super();
		this.codigo = codigo;
	}

	public Franquicia() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	
}
