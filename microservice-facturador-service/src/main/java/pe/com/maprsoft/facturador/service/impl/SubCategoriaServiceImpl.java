package pe.com.maprsoft.facturador.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.dao.repository.SubCategoriaRepository;
import pe.com.maprsoft.facturador.model.Categoria;
import pe.com.maprsoft.facturador.model.Producto;
import pe.com.maprsoft.facturador.model.Session;
import pe.com.maprsoft.facturador.model.SubCategoria;
import pe.com.maprsoft.facturador.model.Sucursal;
import pe.com.maprsoft.facturador.model.core.BooxException;
import pe.com.maprsoft.facturador.service.CategoriaService;
import pe.com.maprsoft.facturador.service.SubCategoriaService;
import pe.com.maprsoft.facturador.service.core.Util;

@Service
public class SubCategoriaServiceImpl implements SubCategoriaService {
	
	@Autowired
	private SubCategoriaRepository subCategoriaRepository;
	
	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public List<SubCategoria> list(Categoria categoria) throws Exception {
		return subCategoriaRepository.list(categoria.getId());
	}

	@Override
	public void insert(SubCategoria subCategoria, String toke) throws Exception {
		subCategoria.setEstado(1);
		subCategoriaRepository.insert(subCategoria);	
	}

	@Override
	public void update(SubCategoria subCategoria) throws Exception {
		subCategoriaRepository.update(subCategoria);		
	}

	@Override
	public void delete(SubCategoria subCategoria) throws Exception {
		subCategoriaRepository.delete(subCategoria);		
	}


}
