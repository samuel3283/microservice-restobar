package pe.com.maprsoft.facturador.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.maprsoft.facturador.dao.repository.DetallePedidoRepository;
import pe.com.maprsoft.facturador.dao.repository.MesaRepository;
import pe.com.maprsoft.facturador.dao.repository.PedidoRepository;
import pe.com.maprsoft.facturador.dao.repository.ReservaRepository;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.model.DetallePedido;
import pe.com.maprsoft.facturador.model.Pedido;
import pe.com.maprsoft.facturador.model.Reserva;
import pe.com.maprsoft.facturador.model.Session;
import pe.com.maprsoft.facturador.model.core.BooxException;
import pe.com.maprsoft.facturador.model.core.EstadoMesaCodeEnum;
import pe.com.maprsoft.facturador.model.core.EstadoPedidoCodeEnum;
import pe.com.maprsoft.facturador.model.core.MonedaCodeEnum;
import pe.com.maprsoft.facturador.service.PedidoService;
import pe.com.maprsoft.facturador.service.ReservaService;
import pe.com.maprsoft.facturador.service.core.Util;


@Service
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public Map<String, Object> insert(Reserva reserva, String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");
		
		Map<String, Object> mapa = new HashMap<>();
		reserva.setEstado(0);
		reserva.setCodigoSucursal(session.getCodigoRestauranteLocal());
		
		String fechaReserva = Util.getDateToFormat(reserva.getFechaReserva(), Util.PATTERN_DD_MM_YY_HH_MI_A, 
				Util.PATTERN_YYYY_MM_DD_HH_MI_SS);
		
		reserva.setFechaReserva(fechaReserva);
		Integer idReserva = reservaRepository.insert(reserva);
		
		mapa.put("idReserva", idReserva);
		return mapa;
	}

	@Override
	public Reserva get(Reserva reserva) throws Exception {

		Reserva bean = reservaRepository.getById(reserva);
		return bean;
	}

	@Override
	public List<Reserva> list(Reserva reserva, String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");
		
		reserva.setEstado(0);
		reserva.setCodigoSucursal(session.getCodigoRestauranteLocal());
		
		List<Reserva> lista = reservaRepository.list(reserva);
		return lista;
	}
	
	@Override
	public void updateStatus(Reserva reserva) throws Exception {

		reservaRepository.updateStatus(reserva);
		
	}

	@Override
	public void update(Reserva reserva) throws Exception {
	
		String fechaReserva = Util.getDateToFormat(reserva.getFechaReserva(), Util.PATTERN_DD_MM_YY_HH_MI_A, 
				Util.PATTERN_YYYY_MM_DD_HH_MI_SS);
		reserva.setFechaReserva(fechaReserva);
		reservaRepository.update(reserva);		
	}

	@Override
	public void delete(Reserva reserva) throws Exception {
		reservaRepository.delete(reserva);	
	}

}