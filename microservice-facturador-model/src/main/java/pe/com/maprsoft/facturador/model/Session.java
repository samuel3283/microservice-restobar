package pe.com.maprsoft.facturador.model;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Session {

	@JsonIgnore
	private Integer codigo;
	private Integer codigoUsuario;
	private Integer codigoRestauranteLocal;
	private String token;
	private String device;
	private String deviceType;

	private String usuario;
	private String nombre;
	private String apellido;
	private String email;
	private String tipoDocumento;
	private String numDocumento;
	private String telefono;
	@JsonIgnore
	private String fechaRegistro;
	@JsonIgnore
	private String fechaModifica;
	@JsonIgnore
	private String fechaExpira;
	@JsonIgnore
	private Integer estado;
	private String perfil;
	@JsonInclude(Include.NON_NULL)
	private Integer aperturaCaja;

	@JsonInclude(Include.NON_NULL)
	private Map<String, Object> mapa;

	@JsonInclude(Include.NON_NULL)
	private Sucursal sucursal;

	public Session() {
		this.codigo = new Integer(0);
		this.estado = new Integer(0);
	}

	public Integer getAperturaCaja() {
		return aperturaCaja;
	}

	public void setAperturaCaja(Integer aperturaCaja) {
		this.aperturaCaja = aperturaCaja;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigoRestauranteLocal() {
		return codigoRestauranteLocal;
	}

	public void setCodigoRestauranteLocal(Integer codigoRestauranteLocal) {
		this.codigoRestauranteLocal = codigoRestauranteLocal;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getFechaModifica() {
		return fechaModifica;
	}

	public void setFechaModifica(String fechaModifica) {
		this.fechaModifica = fechaModifica;
	}

	public String getFechaExpira() {
		return fechaExpira;
	}

	public void setFechaExpira(String fechaExpira) {
		this.fechaExpira = fechaExpira;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public Map<String, Object> getMapa() {
		return mapa;
	}

	public void setMapa(Map<String, Object> mapa) {
		this.mapa = mapa;
	}
	
}
