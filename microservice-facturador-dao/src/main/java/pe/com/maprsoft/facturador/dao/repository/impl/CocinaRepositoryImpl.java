package pe.com.maprsoft.facturador.dao.repository.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.maprsoft.facturador.dao.repository.CocinaRepository;
import pe.com.maprsoft.facturador.dao.repository.MozoRepository;
import pe.com.maprsoft.facturador.dao.repository.SalonRepository;
import pe.com.maprsoft.facturador.dao.repository.mapper.MozoMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.PedidoCocinaMapper;
import pe.com.maprsoft.facturador.dao.repository.mapper.SalonMapper;
import pe.com.maprsoft.facturador.model.Mozo;
import pe.com.maprsoft.facturador.model.PagoDetalle;
import pe.com.maprsoft.facturador.model.PedidoCocina;
import pe.com.maprsoft.facturador.model.Salon;


@SuppressWarnings("all")
@Repository
public class CocinaRepositoryImpl implements CocinaRepository {

	private final Logger logger = LoggerFactory.getLogger(CocinaRepositoryImpl.class);

	@Autowired
	@Resource(name = "jdbcTemplateMySql")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<PedidoCocina> listCocina(Integer sucursal) throws Exception {
		List <PedidoCocina> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select p.codigo, p.codigo_mesa, p.tipo_pedido,p.comensales,p.estado, ");
		sql_find_user.append("DATE_FORMAT(p.fec_pedido,'%d/%m/%Y %H:%i:%s') fec_pedido, p.codigo_mozo, ");
		sql_find_user.append("m.nombre, m.nombre_breve, m.capacidad, m.estado estado_mesa, ");
		sql_find_user.append("d.codigo codigo_detalle,d.codigo_producto,  d.comentario, d.estado, ");
		sql_find_user.append("DATE_FORMAT(d.fec_registro,'%H:%i:%s') fec_registro, ");
		sql_find_user.append("d.nombre, d.moneda, d.precio, concat(concat(mo.nombre,' '),mo.apellido) nombre_mozo, ");
		sql_find_user.append("d.lugar_elaboracion, d.tipo ");
		sql_find_user.append("from tbl_pedido p ");
		sql_find_user.append("inner join tbl_pedido_detalle d on p.codigo = d.codigo_pedido ");
		sql_find_user.append("left join tbl_mesa m on m.codigo = p.codigo_mesa ");
		sql_find_user.append("left join tbl_usuario mo on mo.codigo = p.codigo_mozo and mo.perfil='M' ");
		sql_find_user.append("where p.estado in (1,2,4) and p.codigo_restaurante_local = ? ");
		sql_find_user.append("and p.atendido_cocina = 0 ");
		sql_find_user.append("order by p.codigo, d.nombre ");
		Object[] params = new Object[] { sucursal };
		lista = (List <PedidoCocina>)jdbcTemplate.query(sql_find_user.toString(), params, new PedidoCocinaMapper());
		return lista;
	}
	
	
	@Override
	public List<PedidoCocina> listBarra(Integer sucursal) throws Exception {
		List <PedidoCocina> lista = null;
		StringBuilder sql_find_user = new StringBuilder();
		sql_find_user.append("select p.codigo, p.codigo_mesa, p.tipo_pedido,p.comensales,p.estado, ");
		sql_find_user.append("DATE_FORMAT(p.fec_pedido,'%d/%m/%Y %H:%i:%s') fec_pedido, p.codigo_mozo, ");
		sql_find_user.append("m.nombre, m.nombre_breve, m.capacidad, m.estado estado_mesa, ");
		sql_find_user.append("d.codigo codigo_detalle,d.codigo_producto,  d.comentario, d.estado, ");
		sql_find_user.append("DATE_FORMAT(d.fec_registro,'%H:%i:%s') fec_registro, ");
		sql_find_user.append("d.nombre, d.moneda, d.precio, concat(concat(mo.nombre,' '),mo.apellido) nombre_mozo, ");
		sql_find_user.append("d.lugar_elaboracion, d.tipo ");
		sql_find_user.append("from tbl_pedido p ");
		sql_find_user.append("inner join tbl_pedido_detalle d on p.codigo = d.codigo_pedido ");
		sql_find_user.append("left join tbl_mesa m on m.codigo = p.codigo_mesa ");
		sql_find_user.append("left join tbl_usuario mo on mo.codigo = p.codigo_mozo and mo.perfil='M' ");
		sql_find_user.append("where p.estado in (1,2,4) and p.codigo_restaurante_local = ? ");
		sql_find_user.append("and p.atendido_barra = 0 ");
		sql_find_user.append("order by p.codigo, d.nombre ");
		Object[] params = new Object[] { sucursal };
		lista = (List <PedidoCocina>)jdbcTemplate.query(sql_find_user.toString(), params, new PedidoCocinaMapper());
		return lista;
	}

}
