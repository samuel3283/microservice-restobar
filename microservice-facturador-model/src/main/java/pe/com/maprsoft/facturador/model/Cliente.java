package pe.com.maprsoft.facturador.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Cliente {

	@JsonInclude(Include.NON_NULL)
	private Integer codigo;
	@JsonInclude(Include.NON_NULL)
	private String nombre;
	@JsonInclude(Include.NON_NULL)
	private String direccion;
	@JsonInclude(Include.NON_NULL)
	private String referencia;
	@JsonInclude(Include.NON_NULL)
	private String telefono;
	@JsonInclude(Include.NON_NULL)
	private String fechaRegistro;
	@JsonInclude(Include.NON_NULL)
	private Integer estado;
	
	
	
	public Cliente(Integer codigo) {
		super();
		this.codigo = codigo;
	}
	public Cliente() {
		super();
	}
	
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	
}
