package pe.com.maprsoft.facturador.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.maprsoft.facturador.model.core.EstadoMesaCodeEnum;
import pe.com.maprsoft.facturador.service.MesaService;
import pe.com.maprsoft.facturador.service.SalonService;
import pe.com.maprsoft.facturador.dao.repository.MesaRepository;
import pe.com.maprsoft.facturador.model.Mesa;

@Service
public class MesaServiceImpl implements MesaService {

	@Autowired
	private MesaRepository mesaRepository;

	@Override
	public void updateStatus(Mesa mesa) throws Exception {

		mesaRepository.updateStatus(mesa);
		
	}

	@Override
	public void update(Mesa mesa) throws Exception {
		mesaRepository.update(mesa);		
	}

	@Override
	public void delete(Mesa mesa) throws Exception {
		mesaRepository.delete(mesa);
	}

	@Override
	public void insert(Mesa mesa) throws Exception {
		mesa.setEstado(1);
		mesaRepository.insert(mesa);		
	}


}
