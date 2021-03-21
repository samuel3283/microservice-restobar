package pe.com.maprsoft.facturador.model;

public class Label {

	private Integer id;
	private Integer codigo;
	private String valor;
	
	
	public Label(Integer id, Integer codigo, String valor) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.valor = valor;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
}
