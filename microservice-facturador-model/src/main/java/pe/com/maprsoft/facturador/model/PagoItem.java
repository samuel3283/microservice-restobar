package pe.com.maprsoft.facturador.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class PagoItem {
	
	private Integer codigo;
	@JsonInclude(Include.NON_NULL)
	private Integer codigPago;
	@JsonInclude(Include.NON_NULL)
	private String tipo;
	@JsonInclude(Include.NON_NULL)
	private String total;
	@JsonInclude(Include.NON_NULL)
	private Integer estado;
	@JsonInclude(Include.NON_NULL)
	private String fechaRegistro;
	
	public PagoItem() {
		super();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigPago() {
		return codigPago;
	}

	public void setCodigPago(Integer codigPago) {
		this.codigPago = codigPago;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}


}
