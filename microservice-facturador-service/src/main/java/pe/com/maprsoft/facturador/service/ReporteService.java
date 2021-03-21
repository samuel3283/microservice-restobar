package pe.com.maprsoft.facturador.service;

import java.util.List;
import java.util.Map;

import pe.com.maprsoft.facturador.model.Caja;
import pe.com.maprsoft.facturador.model.Mesa;

public interface ReporteService {

	public List<Map<String, Object>> listEstados(String token, Map<String, Object> mapa) throws Exception;
	public List<Map<String, Object>> listxPago(String token, Map<String, Object> mapa) throws Exception;
	public Map<String, Object> ventaByDia(String token, Map<String, Object> mapa) throws Exception;
	public List<Map<String, Object>> ventaDiario(String token, Map<String, Object> mapa) throws Exception;
	public List<Map<String, Object>> ventaByMozo(String token, Map<String, Object> mapa) throws Exception;
	public List<List<Map<String, Object>>> ventasByProducto(String token, Map<String, Object> mapa) throws Exception;
	public List<Map<String, Object>> descuentoDiario(String token, Map<String, Object> mapa) throws Exception;	
	public List<Map<String, Object>> emisionComprobantes(String token, Map<String, Object> mapa) throws Exception;
	public Map<String, Object> descuentoByDia(String token, Map<String, Object> mapa) throws Exception;
	public List<Map<String, Object>> reporteIncurrencias(String token, Map<String, Object> mapa) throws Exception;
	public List<Caja> reporteCajaByDia(String token, Map<String, Object> mapa) throws Exception;
	
}
