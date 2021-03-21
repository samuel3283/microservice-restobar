package pe.com.maprsoft.facturador.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Caja {
	@JsonInclude(Include.NON_NULL)
	private Integer codigo;

	
	@JsonInclude(Include.NON_NULL)
	private String fechaRegistro;
	@JsonInclude(Include.NON_NULL)
	private String fechaApertura;
	@JsonInclude(Include.NON_NULL)
	private String montoInicial;
	@JsonInclude(Include.NON_NULL)
	private String fechaCierre;
	@JsonInclude(Include.NON_NULL)
	private String montoFinal;
	@JsonInclude(Include.NON_NULL)
	private String descuento;
	/*
	@JsonInclude(Include.NON_NULL)
	private String fechaOperacion;
	@JsonInclude(Include.NON_NULL)
	private String usuario;
	@JsonInclude(Include.NON_NULL)
	private String monto;
	@JsonInclude(Include.NON_NULL)
	private String operacion;
	*/
	
	@JsonInclude(Include.NON_NULL)
	private String usuario;
	@JsonInclude(Include.NON_NULL)
	private String horario;
	@JsonInclude(Include.NON_NULL)
	private Integer estado;
	@JsonInclude(Include.NON_NULL)
	private Integer codigoSucursal;
	@JsonInclude(Include.NON_NULL)
	private String identificador;
	
	
	public String getDescuento() {
		return descuento;
	}
	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public String getMontoInicial() {
		return montoInicial;
	}
	public void setMontoInicial(String montoInicial) {
		this.montoInicial = montoInicial;
	}
	public String getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public String getMontoFinal() {
		return montoFinal;
	}
	public void setMontoFinal(String montoFinal) {
		this.montoFinal = montoFinal;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Integer getCodigoSucursal() {
		return codigoSucursal;
	}
	public void setCodigoSucursal(Integer codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	

	
}
