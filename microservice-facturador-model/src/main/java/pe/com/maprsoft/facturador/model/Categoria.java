package pe.com.maprsoft.facturador.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Categoria {
	@JsonProperty("codigo")
	private Integer id;
	private String nombre;
	private List<SubCategoria> subcategoria;
	private String color;

	@JsonInclude(Include.NON_NULL)
	private Integer estado;
	@JsonIgnore
	private Sucursal sucursal;
	
	
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

	public List<SubCategoria> getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(List<SubCategoria> subcategoria) {
		this.subcategoria = subcategoria;
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

	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Categoria() {
		super();
		this.subcategoria = new ArrayList<SubCategoria>();
	}
	
	

}