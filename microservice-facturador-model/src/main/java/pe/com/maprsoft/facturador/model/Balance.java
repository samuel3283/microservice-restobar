package pe.com.maprsoft.facturador.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Balance {
	@JsonInclude(Include.NON_NULL)
	private Integer codigo;
	@JsonInclude(Include.NON_NULL)
	private Integer codigoSucursal;
	@JsonInclude(Include.NON_NULL)
	private String documento;
	@JsonInclude(Include.NON_NULL)
	private String serie;
	@JsonInclude(Include.NON_NULL)
	private String numero;

	@JsonInclude(Include.NON_NULL)
	private String fechaPago;
	@JsonInclude(Include.NON_NULL)
	private String subtotal;
	@JsonInclude(Include.NON_NULL)
	private String igv;
	@JsonInclude(Include.NON_NULL)
	private String total;
	@JsonInclude(Include.NON_NULL)
	private String descuento;

	@JsonInclude(Include.NON_NULL)
	private String pagoSoles;
	@JsonInclude(Include.NON_NULL)
	private String pagoDolares;
	@JsonInclude(Include.NON_NULL)
	private String pagoVisaCredito;
	@JsonInclude(Include.NON_NULL)
	private String pagoMasterCardCredito;
	@JsonInclude(Include.NON_NULL)
	private String pagoVuelto;	
	
	@JsonInclude(Include.NON_NULL)
	private Integer itemCodigo;
	@JsonInclude(Include.NON_NULL)
	private String itemTipoPago;
	@JsonInclude(Include.NON_NULL)
	private String itemTotal;
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Integer getCodigoSucursal() {
		return codigoSucursal;
	}
	public void setCodigoSucursal(Integer codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
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
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public String getIgv() {
		return igv;
	}
	public void setIgv(String igv) {
		this.igv = igv;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getDescuento() {
		return descuento;
	}
	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}
	public String getPagoSoles() {
		return pagoSoles;
	}
	public void setPagoSoles(String pagoSoles) {
		this.pagoSoles = pagoSoles;
	}
	public String getPagoDolares() {
		return pagoDolares;
	}
	public void setPagoDolares(String pagoDolares) {
		this.pagoDolares = pagoDolares;
	}
	public String getPagoVisaCredito() {
		return pagoVisaCredito;
	}
	public void setPagoVisaCredito(String pagoVisaCredito) {
		this.pagoVisaCredito = pagoVisaCredito;
	}
	public String getPagoMasterCardCredito() {
		return pagoMasterCardCredito;
	}
	public void setPagoMasterCardCredito(String pagoMasterCardCredito) {
		this.pagoMasterCardCredito = pagoMasterCardCredito;
	}
	public String getPagoVuelto() {
		return pagoVuelto;
	}
	public void setPagoVuelto(String pagoVuelto) {
		this.pagoVuelto = pagoVuelto;
	}
	public Integer getItemCodigo() {
		return itemCodigo;
	}
	public void setItemCodigo(Integer itemCodigo) {
		this.itemCodigo = itemCodigo;
	}
	public String getItemTipoPago() {
		return itemTipoPago;
	}
	public void setItemTipoPago(String itemTipoPago) {
		this.itemTipoPago = itemTipoPago;
	}
	public String getItemTotal() {
		return itemTotal;
	}
	public void setItemTotal(String itemTotal) {
		this.itemTotal = itemTotal;
	}

	

	
}
