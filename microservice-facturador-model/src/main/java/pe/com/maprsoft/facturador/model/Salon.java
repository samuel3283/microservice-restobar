package pe.com.maprsoft.facturador.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Salon {
	@JsonProperty("codigo")
	private Integer id;
	private Integer estado;
	private String nombre;
	private List<ListaMesas> mesas;
	@JsonIgnore
	private Sucursal sucursal;
	@JsonInclude(Include.NON_NULL)
	private List<Mesa> lista;
	
	public Salon() {
		super();
		this.mesas = new ArrayList<ListaMesas>();
	}
	
	
	public Integer getEstado() {
		return estado;
	}


	public void setEstado(Integer estado) {
		this.estado = estado;
	}


	public List<Mesa> getLista() {
		return lista;
	}

	public void setLista(List<Mesa> lista) {
		this.lista = lista;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
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

	public List<ListaMesas> getMesas() {
		return mesas;
	}

	public void setMesas(List<ListaMesas> mesas) {
		this.mesas = mesas;
	}
	
	

}
