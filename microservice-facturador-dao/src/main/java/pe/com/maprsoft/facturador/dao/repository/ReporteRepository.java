package pe.com.maprsoft.facturador.dao.repository;

import java.util.List;
import java.util.Map;

import pe.com.maprsoft.facturador.model.Filter;
import pe.com.maprsoft.facturador.model.Usuario;


public interface ReporteRepository {
	
	public List<Map<String, Object>> list(Integer sucursal, Map<String, Object> mapa) throws Exception;
	public Map<String, Object> listPagoxMes(Integer sucursal, String periodo, Map<String, Object> mapa) throws Exception;
	public Map<String, Object> listPagoxDia(Integer sucursal, String periodo, Map<String, Object> mapa) throws Exception ;
	public Map<String, Object> listPagoxAnio(Integer sucursal, String periodo, Map<String, Object> mapa) throws Exception ;
	public List<Map<String, Object>> pedidoMasVendidos(Integer sucursal, Filter filter) throws Exception;
	public List<Map<String, Object>> ventasPorMozo(Integer sucursal, Filter filter) throws Exception;
	public List<Map<String, Object>> ventasPorDocumento(Integer sucursal, Filter filter) throws Exception;
	public List<Map<String, Object>> ventasPorMaxMin(Integer sucursal, Filter filter) throws Exception;
	public Map<String, Object> listDescuentoxDia(Integer sucursal, String periodo, Map<String, Object> mapa) throws Exception;
	public List<Map<String, Object>> ventasPorDiaPorMozo(Usuario usuario, String periodo) throws Exception;
	public List<Map<String, Object>> montoByProducto(Map<String, Object> mapa) throws Exception;
	public List<Map<String, Object>> reporteIncurrencias(Integer sucursal, Filter filter) throws Exception;	
}
