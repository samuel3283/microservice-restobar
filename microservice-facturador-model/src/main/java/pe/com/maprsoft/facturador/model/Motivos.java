package pe.com.maprsoft.facturador.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Motivos {
	@JsonProperty("codigo")
	private Integer codigo;
	@JsonInclude(Include.NON_NULL)
	private String descripcion;
	@JsonInclude(Include.NON_NULL)
	private Integer estado;
	@JsonIgnore
	private Sucursal sucursal;

	public Motivos(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Motivos() {
		super();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}


}
