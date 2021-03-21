package pe.com.maprsoft.facturador.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListaCategoria {
	private Integer idCategoria;
	private String nombreCategoria;
	private String color;
	private Integer idSubCategoria;
	private String nombreSubCategoria;

	private Integer idProducto;
	private String nombreProducto;
	private String precioProducto;
	private String monedaProducto;
	private String lugarElaboracion;
	private String tipo;
	
	
	public ListaCategoria() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getLugarElaboracion() {
		return lugarElaboracion;
	}


	public void setLugarElaboracion(String lugarElaboracion) {
		this.lugarElaboracion = lugarElaboracion;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getIdSubCategoria() {
		return idSubCategoria;
	}
	public void setIdSubCategoria(Integer idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}
	public String getNombreSubCategoria() {
		return nombreSubCategoria;
	}
	public void setNombreSubCategoria(String nombreSubCategoria) {
		this.nombreSubCategoria = nombreSubCategoria;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(String precioProducto) {
		this.precioProducto = precioProducto;
	}
	public String getMonedaProducto() {
		return monedaProducto;
	}
	public void setMonedaProducto(String monedaProducto) {
		this.monedaProducto = monedaProducto;
	}

	
	

}
