package pe.com.maprsoft.facturador.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Pedido {
	
	@JsonInclude(Include.NON_NULL)
	private Integer codigo;
	@JsonInclude(Include.NON_NULL)
	private Integer codigoSucursal;
	@JsonInclude(Include.NON_NULL)
	private Mesa mesa;
	@JsonInclude(Include.NON_NULL)
	private Mozo mozo;
	@JsonInclude(Include.NON_NULL)
	private String minutos;
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
	private List<DetallePedido> pedidos;	
	
	@JsonInclude(Include.NON_NULL)
	private Reserva reserva;
	@JsonInclude(Include.NON_NULL)
	private Cliente cliente;

	@JsonInclude(Include.NON_NULL)
	private String motivoCancelacion;
	@JsonInclude(Include.NON_NULL)
	private Integer atendidoCocina;
	@JsonInclude(Include.NON_NULL)
	private Integer atendidoBarra;

	@JsonInclude(Include.NON_NULL)
	private String moneda;
	@JsonInclude(Include.NON_NULL)
	private String total;
	@JsonInclude(Include.NON_NULL)
	private String igv;
	@JsonInclude(Include.NON_NULL)
	private String subTotal;
	@JsonInclude(Include.NON_NULL)
	private String tipoDocumento;

	public Pedido() {
		super();
		this.pedidos = new ArrayList<DetallePedido>();
		this.reserva = new Reserva();
		this.cliente = new Cliente();
	}

	
	public Pedido(Integer codigo) {
		super();
		this.codigo = codigo;
	}

	

	public Integer getAtendidoCocina() {
		return atendidoCocina;
	}


	public void setAtendidoCocina(Integer atendidoCocina) {
		this.atendidoCocina = atendidoCocina;
	}


	public Integer getAtendidoBarra() {
		return atendidoBarra;
	}


	public void setAtendidoBarra(Integer atendidoBarra) {
		this.atendidoBarra = atendidoBarra;
	}


	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}


	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public String getMinutos() {
		return minutos;
	}

	public void setMinutos(String minutos) {
		this.minutos = minutos;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Mozo getMozo() {
		return mozo;
	}

	public void setMozo(Mozo mozo) {
		this.mozo = mozo;
	}

	public Integer getCodigoSucursal() {
		return codigoSucursal;
	}

	public void setCodigoSucursal(Integer codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getIgv() {
		return igv;
	}

	public void setIgv(String igv) {
		this.igv = igv;
	}



	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
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

	public List<DetallePedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<DetallePedido> pedidos) {
		this.pedidos = pedidos;
	}
	

}
