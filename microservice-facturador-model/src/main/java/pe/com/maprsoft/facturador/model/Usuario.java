package pe.com.maprsoft.facturador.model;

public class Usuario {

	private Integer codigo;
	private Integer codigoRestauranteLocal;
	private Integer codigoRestaurante;
	private String usuario;
	private String nombre;
	private String apellido;
	private String email;
	private String password;
	private String tipoDocumento;
	private String numDocumento;
	private String telefono;
	private String fechaRegistro;
	private String fechaModifica;
	private Integer estado;
	private Integer estadoTbl;
	private String perfil;
	private String olvido;

	private Sucursal sucursal;
	
	public Usuario() {
		super();
		this.estado = new Integer(1);
	}


	public Usuario(Integer codigoRestauranteLocal) {
		super();
		this.codigoRestauranteLocal = codigoRestauranteLocal;
	}


	public Integer getCodigoRestaurante() {
		return codigoRestaurante;
	}


	public void setCodigoRestaurante(Integer codigoRestaurante) {
		this.codigoRestaurante = codigoRestaurante;
	}


	public Sucursal getSucursal() {
		return sucursal;
	}


	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}


	public Integer getEstadoTbl() {
		return estadoTbl;
	}


	public void setEstadoTbl(Integer estadoTbl) {
		this.estadoTbl = estadoTbl;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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


	public String getOlvido() {
		return olvido;
	}


	public void setOlvido(String olvido) {
		this.olvido = olvido;
	}
	
	
}
