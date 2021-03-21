package pe.com.maprsoft.facturador.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Mesa {
	@JsonProperty("codigo")
	private Integer id;
	@JsonInclude(Include.NON_NULL)
	private Integer codigoSalon;
	@JsonInclude(Include.NON_NULL)
	private String nombre;
	@JsonInclude(Include.NON_NULL)
	private String nombreBreve;
	@JsonInclude(Include.NON_NULL)
	private Integer capacidad;
	@JsonInclude(Include.NON_NULL)
	private String color;
	@JsonInclude(Include.NON_NULL)
	private Integer estado;
	@JsonInclude(Include.NON_NULL)
	private Integer estadoTbl;

	public Mesa(Integer id) {
		this.id = id;
	}
	public Mesa() {
		super();
	}

	public Integer getEstadoTbl() {
		return estadoTbl;
	}

	public void setEstadoTbl(Integer estadoTbl) {
		this.estadoTbl = estadoTbl;
	}

	public Integer getCodigoSalon() {
		return codigoSalon;
	}

	public void setCodigoSalon(Integer codigoSalon) {
		this.codigoSalon = codigoSalon;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreBreve() {
		return nombreBreve;
	}

	public void setNombreBreve(String nombreBreve) {
		this.nombreBreve = nombreBreve;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getEstado() {
		return estado;
	}
	
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	

}
