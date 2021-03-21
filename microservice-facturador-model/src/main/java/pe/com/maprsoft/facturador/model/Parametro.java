package pe.com.maprsoft.facturador.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Parametro {
	private Integer codigo;
	@JsonInclude(Include.NON_NULL)
	private String grupo;
	@JsonInclude(Include.NON_NULL)
	private String tipo;
	@JsonInclude(Include.NON_NULL)
	private String code;
	@JsonInclude(Include.NON_NULL)
	private String valor;
	@JsonInclude(Include.NON_NULL)
	private String valorBreve;
	@JsonInclude(Include.NON_NULL)
	private Integer estado;
	@JsonIgnore
	private Sucursal sucursal;

	public Parametro() {		
	}

	public Parametro(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValorBreve() {
		return valorBreve;
	}

	public void setValorBreve(String valorBreve) {
		this.valorBreve = valorBreve;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	

}
