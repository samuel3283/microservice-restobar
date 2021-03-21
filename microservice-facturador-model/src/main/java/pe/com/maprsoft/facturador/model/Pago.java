package pe.com.maprsoft.facturador.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Pago {
	
	private Integer codigo;
	@JsonInclude(Include.NON_NULL)
	private Integer id;
	@JsonInclude(Include.NON_NULL)
	private Integer codigoSucursal;
	@JsonInclude(Include.NON_NULL)
	private Pedido pedido;
	@JsonInclude(Include.NON_NULL)
	private Mesa mesa;
	@JsonInclude(Include.NON_NULL)
	private Integer personas;
	@JsonInclude(Include.NON_NULL)
	private String fechaPedido;
	@JsonInclude(Include.NON_NULL)
	private String fechaRegistro;
	@JsonInclude(Include.NON_NULL)
	private String estado;
	@JsonInclude(Include.NON_NULL)
	private String tipo;
	private List<PagoDetalle> pedidos;	

	@JsonInclude(Include.NON_NULL)
	private String tipoDocumento;
	@JsonInclude(Include.NON_NULL)
	private String serie;
	@JsonInclude(Include.NON_NULL)
	private String numero;
	
	@JsonInclude(Include.NON_NULL)
	private String ruc;
	@JsonInclude(Include.NON_NULL)
	private String razonSocial;
	@JsonInclude(Include.NON_NULL)
	private String direccion;
	@JsonInclude(Include.NON_NULL)
	private String fechaEmision;
	@JsonInclude(Include.NON_NULL)
	private String subTotal;
	@JsonInclude(Include.NON_NULL)
	private String igv;
	@JsonInclude(Include.NON_NULL)
	private String total;
	@JsonInclude(Include.NON_NULL)
	private String moneda;

	@JsonInclude(Include.NON_NULL)
	private String vuelto;
	@JsonInclude(Include.NON_NULL)
	private String pagoSoles;
	@JsonInclude(Include.NON_NULL)
	private String pagoDolares;
	@JsonInclude(Include.NON_NULL)
	private String pagoVisa;
	@JsonInclude(Include.NON_NULL)
	private String pagoMasterCard;
	
	@JsonInclude(Include.NON_NULL)
	private String monto;
	@JsonInclude(Include.NON_NULL)
	private String descuento;
	@JsonInclude(Include.NON_NULL)
	private String usuario;
	
	
	public Pago() {
		super();
		this.pedidos = new ArrayList<PagoDetalle>();
	}

	
	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getDescuento() {
		return descuento;
	}


	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getMonto() {
		return monto;
	}


	public void setMonto(String monto) {
		this.monto = monto;
	}


	public String getVuelto() {
		return vuelto;
	}


	public void setVuelto(String vuelto) {
		this.vuelto = vuelto;
	}


	public Integer getCodigoSucursal() {
		return codigoSucursal;
	}


	public void setCodigoSucursal(Integer codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}


	public Pedido getPedido() {
		return pedido;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}


	public String getTipoDocumento() {
		return tipoDocumento;
	}


	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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


	public String getRuc() {
		return ruc;
	}


	public void setRuc(String ruc) {
		this.ruc = ruc;
	}




	public String getRazonSocial() {
		return razonSocial;
	}


	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getFechaEmision() {
		return fechaEmision;
	}


	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}





	public String getSubTotal() {
		return subTotal;
	}


	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
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


	public String getMoneda() {
		return moneda;
	}


	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}


	public String getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(String fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Integer getPersonas() {
		return personas;
	}

	public void setPersonas(Integer personas) {
		this.personas = personas;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<PagoDetalle> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PagoDetalle> pedidos) {
		this.pedidos = pedidos;
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


	public String getPagoVisa() {
		return pagoVisa;
	}


	public void setPagoVisa(String pagoVisa) {
		this.pagoVisa = pagoVisa;
	}


	public String getPagoMasterCard() {
		return pagoMasterCard;
	}


	public void setPagoMasterCard(String pagoMasterCard) {
		this.pagoMasterCard = pagoMasterCard;
	}
	
	

}
