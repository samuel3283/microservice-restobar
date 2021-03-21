package pe.com.maprsoft.facturador.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.maprsoft.facturador.dao.repository.ProductoRepository;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.dao.repository.SubCategoriaRepository;
import pe.com.maprsoft.facturador.model.Categoria;
import pe.com.maprsoft.facturador.model.Producto;
import pe.com.maprsoft.facturador.model.Session;
import pe.com.maprsoft.facturador.model.SubCategoria;
import pe.com.maprsoft.facturador.model.Sucursal;
import pe.com.maprsoft.facturador.model.core.BooxException;
import pe.com.maprsoft.facturador.service.CategoriaService;
import pe.com.maprsoft.facturador.service.ProductoService;
import pe.com.maprsoft.facturador.service.SubCategoriaService;
import pe.com.maprsoft.facturador.service.core.Util;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public List<Producto> list(SubCategoria subCategoria) throws Exception {
		
		return productoRepository.list(subCategoria.getId());
	}

	@Override
	public void insert(Producto producto, String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		producto.setSucursal(new Sucursal(session.getCodigoRestauranteLocal()));
		productoRepository.insert(producto);		
	}

	@Override
	public void update(Producto producto) throws Exception {
		productoRepository.update(producto);
	}

	@Override
	public void delete(Producto producto) throws Exception {
		productoRepository.delete(producto);		
	}

	@Override
	public List<Producto> listAll(String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");

		return productoRepository.listAll(session.getCodigoRestauranteLocal());
	}



}
