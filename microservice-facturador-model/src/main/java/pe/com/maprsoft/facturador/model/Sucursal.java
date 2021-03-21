package pe.com.maprsoft.facturador.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Sucursal {
	
	@JsonProperty("codigo")
	private Integer codigo;
	@JsonInclude(Include.NON_NULL)
	private String nombre;
	@JsonInclude(Include.NON_NULL)
	private String direccion;
	private Integer estado;
	@JsonInclude(Include.NON_NULL)
	private Restaurante restaurante;
	
	public Sucursal() {
		super();
	}

	public Sucursal(Integer codigo) {
		super();
		this.codigo = codigo;
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

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	
}
