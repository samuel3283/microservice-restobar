package pe.com.maprsoft.facturador.model;


public class Boleta {
	private String razonSocial;
	private String ruc;
	private String direccion;
	private String cliente;
	private String urlDescarga;
	
	private String razonSocialFranquicia;
	private String direccionFranquicia;
	private boolean franquicia;
	
    
	public String getUrlDescarga() {
		return urlDescarga;
	}


	public void setUrlDescarga(String urlDescarga) {
		this.urlDescarga = urlDescarga;
	}


	public String getCliente() {
		return cliente;
	}


	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


	public boolean isFranquicia() {
		return franquicia;
	}


	public void setFranquicia(boolean franquicia) {
		this.franquicia = franquicia;
	}


	public String getRazonSocial() {
		return razonSocial;
	}


	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}


	public String getRuc() {
		return ruc;
	}


	public void setRuc(String ruc) {
		this.ruc = ruc;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getRazonSocialFranquicia() {
		return razonSocialFranquicia;
	}


	public void setRazonSocialFranquicia(String razonSocialFranquicia) {
		this.razonSocialFranquicia = razonSocialFranquicia;
	}


	public String getDireccionFranquicia() {
		return direccionFranquicia;
	}


	public void setDireccionFranquicia(String direccionFranquicia) {
		this.direccionFranquicia = direccionFranquicia;
	}


	public Boleta() {
		super();
	}

}
