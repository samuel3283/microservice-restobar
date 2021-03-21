package pe.com.maprsoft.facturador.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Reserva {
	
	@JsonInclude(Include.NON_NULL)
	private Integer codigo;
	@JsonInclude(Include.NON_NULL)
	private Integer codigoSucursal;
	@JsonInclude(Include.NON_NULL)
	private Mesa mesa;
	@JsonInclude(Include.NON_NULL)
	private Mozo mozo;
	@JsonInclude(Include.NON_NULL)
	private Integer personas;
	@JsonInclude(Include.NON_NULL)
	private String fechaReserva;
	@JsonInclude(Include.NON_NULL)
	private String horaReserva;
	@JsonInclude(Include.NON_NULL)
	private String fechaRegistro;
	@JsonInclude(Include.NON_NULL)
	private Integer estado;
	@JsonInclude(Include.NON_NULL)
	private List<DetallePedido> pedidos;	
	@JsonInclude(Include.NON_NULL)
	private String contacto;
	@JsonInclude(Include.NON_NULL)
	private String comentario;
	@JsonInclude(Include.NON_NULL)
	private String tipoDocumento;
	@JsonInclude(Include.NON_NULL)
	private String numDocumento;
	@JsonInclude(Include.NON_NULL)
	private String telefono;

	public Reserva() {
		super();
		this.pedidos = new ArrayList<DetallePedido>();
	}
	

	public String getTipoDocumento() {
		return tipoDocumento;
	}


	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	public String getNumDocumento() {
		return numDocumento;
	}


	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getHoraReserva() {
		return horaReserva;
	}

	public void setHoraReserva(String horaReserva) {
		this.horaReserva = horaReserva;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

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

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Mozo getMozo() {
		return mozo;
	}

	public void setMozo(Mozo mozo) {
		this.mozo = mozo;
	}

	public Integer getPersonas() {
		return personas;
	}

	public void setPersonas(Integer personas) {
		this.personas = personas;
	}

	public String getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(String fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public List<DetallePedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<DetallePedido> pedidos) {
		this.pedidos = pedidos;
	}


}
