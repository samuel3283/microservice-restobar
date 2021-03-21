package pe.com.maprsoft.facturador.model;

public class PedidoCocina {

	private Integer codigoPedido;
	private Integer codigoMesa;
	private String tipoPedido;
	private Integer personas;
	private Integer estadoPedido;
	private String fechaPedido;
	private Integer codigoMozo;
	private String nombreMesa;
	private String nombreBreveMesa;
	private Integer capacidadMesa;
	private Integer estadoMesa;
	
	private Integer codigoPedidoDetalle;
	private Integer codigoProducto;
	private String comentarioPedidoDetalle;
	private Integer estadoPedidoDetalle;
	private String fechaPedidoDetalle;
	private String nombreProducto;
	private String monedaProducto;
	private String precioProducto;
	private String nombreMozo;
	private String tipo;
	private String lugarElaboracion;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getLugarElaboracion() {
		return lugarElaboracion;
	}
	public void setLugarElaboracion(String lugarElaboracion) {
		this.lugarElaboracion = lugarElaboracion;
	}
	public String getNombreMozo() {
		return nombreMozo;
	}
	public void setNombreMozo(String nombreMozo) {
		this.nombreMozo = nombreMozo;
	}
	public Integer getCodigoPedido() {
		return codigoPedido;
	}
	public void setCodigoPedido(Integer codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	public Integer getCodigoMesa() {
		return codigoMesa;
	}
	public void setCodigoMesa(Integer codigoMesa) {
		this.codigoMesa = codigoMesa;
	}
	public String getTipoPedido() {
		return tipoPedido;
	}
	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}
	public Integer getPersonas() {
		return personas;
	}
	public void setPersonas(Integer personas) {
		this.personas = personas;
	}
	public Integer getEstadoPedido() {
		return estadoPedido;
	}
	public void setEstadoPedido(Integer estadoPedido) {
		this.estadoPedido = estadoPedido;
	}
	public String getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(String fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public Integer getCodigoMozo() {
		return codigoMozo;
	}
	public void setCodigoMozo(Integer codigoMozo) {
		this.codigoMozo = codigoMozo;
	}
	public String getNombreMesa() {
		return nombreMesa;
	}
	public void setNombreMesa(String nombreMesa) {
		this.nombreMesa = nombreMesa;
	}
	public String getNombreBreveMesa() {
		return nombreBreveMesa;
	}
	public void setNombreBreveMesa(String nombreBreveMesa) {
		this.nombreBreveMesa = nombreBreveMesa;
	}
	public Integer getCapacidadMesa() {
		return capacidadMesa;
	}
	public void setCapacidadMesa(Integer capacidadMesa) {
		this.capacidadMesa = capacidadMesa;
	}
	public Integer getEstadoMesa() {
		return estadoMesa;
	}
	public void setEstadoMesa(Integer estadoMesa) {
		this.estadoMesa = estadoMesa;
	}
	public Integer getCodigoPedidoDetalle() {
		return codigoPedidoDetalle;
	}
	public void setCodigoPedidoDetalle(Integer codigoPedidoDetalle) {
		this.codigoPedidoDetalle = codigoPedidoDetalle;
	}
	public Integer getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(Integer codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getComentarioPedidoDetalle() {
		return comentarioPedidoDetalle;
	}
	public void setComentarioPedidoDetalle(String comentarioPedidoDetalle) {
		this.comentarioPedidoDetalle = comentarioPedidoDetalle;
	}
	public Integer getEstadoPedidoDetalle() {
		return estadoPedidoDetalle;
	}
	public void setEstadoPedidoDetalle(Integer estadoPedidoDetalle) {
		this.estadoPedidoDetalle = estadoPedidoDetalle;
	}
	public String getFechaPedidoDetalle() {
		return fechaPedidoDetalle;
	}
	public void setFechaPedidoDetalle(String fechaPedidoDetalle) {
		this.fechaPedidoDetalle = fechaPedidoDetalle;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getMonedaProducto() {
		return monedaProducto;
	}
	public void setMonedaProducto(String monedaProducto) {
		this.monedaProducto = monedaProducto;
	}
	public String getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(String precioProducto) {
		this.precioProducto = precioProducto;
	}

	
}