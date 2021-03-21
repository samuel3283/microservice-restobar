package pe.com.maprsoft.facturador.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Numeracion {
	@JsonProperty("codigo")
	private Integer codigo;
	@JsonInclude(Include.NON_NULL)
	private String periodo;
	
	@JsonInclude(Include.NON_NULL)
	private Integer estado;
	@JsonInclude(Include.NON_NULL)
	private String documento;
	@JsonInclude(Include.NON_NULL)
	private String serie;
	@JsonInclude(Include.NON_NULL)
	private Integer correlativo;
		
	@JsonInclude(Include.NON_NULL)
	private Integer inicio;
	@JsonInclude(Include.NON_NULL)
	private Integer fin;
	 	  	
	@JsonIgnore
	private Sucursal sucursal;

	public Numeracion() {
		super();
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public Integer getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(Integer correlativo) {
		this.correlativo = correlativo;
	}

	public Integer getInicio() {
		return inicio;
	}

	public void setInicio(Integer inicio) {
		this.inicio = inicio;
	}

	public Integer getFin() {
		return fin;
	}

	public void setFin(Integer fin) {
		this.fin = fin;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

}
