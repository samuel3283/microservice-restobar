package pe.com.maprsoft.facturador.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Mozo {
	@JsonProperty("codigo")
	private Integer codigo;
	@JsonInclude(Include.NON_NULL)
	private String nombre;
	@JsonInclude(Include.NON_NULL)
	private String turno;	
	@JsonInclude(Include.NON_NULL)
	private Integer estado;
	@JsonIgnore
	private Sucursal sucursal;

	public Mozo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Mozo() {
		super();
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
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
