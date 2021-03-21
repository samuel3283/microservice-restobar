package pe.com.maprsoft.facturador.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Restaurante {
	@JsonProperty("codigo")
	private Integer codigo;
	@JsonInclude(Include.NON_NULL)
	private String nombre;
	@JsonInclude(Include.NON_NULL)
	private String razon_social;
	@JsonInclude(Include.NON_NULL)
	private String ruc;
	@JsonInclude(Include.NON_NULL)
	private String direccion;
	private Integer estado;
	@JsonInclude(Include.NON_NULL)
	private String fecRegistro;
	@JsonInclude(Include.NON_NULL)	
	private Franquicia franquicia;
	
	public Restaurante() {
		super();
	}

	public Restaurante(Integer codigo) {
		super();
		this.codigo = codigo;
	}

	public Franquicia getFranquicia() {
		return franquicia;
	}

	public void setFranquicia(Franquicia franquicia) {
		this.franquicia = franquicia;
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

	public String getRazon_social() {
		return razon_social;
	}

	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
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

	public String getFecRegistro() {
		return fecRegistro;
	}

	public void setFecRegistro(String fecRegistro) {
		this.fecRegistro = fecRegistro;
	}

	
}
