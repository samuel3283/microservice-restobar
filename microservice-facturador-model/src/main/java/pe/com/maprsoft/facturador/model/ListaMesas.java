package pe.com.maprsoft.facturador.model;

import java.util.ArrayList;
import java.util.List;

public class ListaMesas {

	private List<Mesa> lista;
	
	public ListaMesas() {
		super();
		this.lista = new ArrayList<Mesa>();
	}

	public List<Mesa> getLista() {
		return lista;
	}

	public void setLista(List<Mesa> lista) {
		this.lista = lista;
	}
	

}
