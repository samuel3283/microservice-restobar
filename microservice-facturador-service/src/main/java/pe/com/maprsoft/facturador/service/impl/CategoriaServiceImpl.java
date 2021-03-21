package pe.com.maprsoft.facturador.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.maprsoft.facturador.dao.repository.CategoriaRepository;
import pe.com.maprsoft.facturador.dao.repository.ProductoRepository;
import pe.com.maprsoft.facturador.dao.repository.SessionRepository;
import pe.com.maprsoft.facturador.dao.repository.SubCategoriaRepository;
import pe.com.maprsoft.facturador.model.Categoria;
import pe.com.maprsoft.facturador.model.ListaCategoria;
import pe.com.maprsoft.facturador.model.Producto;
import pe.com.maprsoft.facturador.model.Session;
import pe.com.maprsoft.facturador.model.SubCategoria;
import pe.com.maprsoft.facturador.model.Sucursal;
import pe.com.maprsoft.facturador.model.core.BooxException;
import pe.com.maprsoft.facturador.service.CategoriaService;
import pe.com.maprsoft.facturador.service.core.Util;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private SubCategoriaRepository subCategoriaRepository;
	
	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public List<Categoria> listCategoria(String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");
				
		List<Categoria> listCategoria = categoriaRepository.list(session.getCodigoRestauranteLocal());
		return listCategoria;
	}

	@Override
	public List<SubCategoria> listSubCategoria(Categoria categoria) throws Exception {
				
		List<SubCategoria> listSubCategoria = subCategoriaRepository.list(categoria.getId());
		return listSubCategoria;
	}
	
	@Override
	public List<Producto> listProducto(SubCategoria subCategoria) throws Exception {
				
		List<Producto> listProducto = productoRepository.list(subCategoria.getId());
		return listProducto;
	}

	/*
	@Override
	public List<Categoria> list(String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");
				
		List<Categoria> listCategoriaTmp = categoriaRepository.list(session.getCodigoRestauranteLocal());
		List<Categoria> listCategoria = new ArrayList<Categoria>(); 
		
		List<String> listaColores =  Util.listColor();
		
		int indice = 0;
		for(Categoria categoria: listCategoriaTmp) {			
			
			List<SubCategoria> listSubCategoriaTmp = subCategoriaRepository.list(categoria.getId());
			List<SubCategoria> listSubCategoria = new ArrayList<SubCategoria>(); 
			for(SubCategoria subCategoria: listSubCategoriaTmp) {			
				subCategoria.setProducto(productoRepository.list(subCategoria.getId()));
				listSubCategoria.add(subCategoria);
			}
			categoria.setSubcategoria(listSubCategoria);
			categoria.setColor(listaColores.get(indice));
			listCategoria.add(categoria);
			indice++;
		}
		
		return listCategoria;
	}
*/

	
	@Override
	public List<Categoria> list(String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");
				
		List<ListaCategoria> listCategoriaTmp = categoriaRepository.lista(session.getCodigoRestauranteLocal());
		
		
		List<Categoria> listCategoria = new ArrayList<Categoria>(); 
		Categoria categoria = new Categoria();
		int i = 0;
		Integer idCategoria = 0;
		
		for(ListaCategoria bean: listCategoriaTmp) {
			
			if(i==0) {
				idCategoria = bean.getIdCategoria();
				categoria = new Categoria();
				categoria.setId(bean.getIdCategoria());
				categoria.setNombre(bean.getNombreCategoria());
				listCategoria.add(categoria);
			}			
			if(idCategoria.intValue()!=bean.getIdCategoria().intValue()) {
				idCategoria = bean.getIdCategoria();
				categoria = new Categoria();
				categoria.setId(bean.getIdCategoria());
				categoria.setNombre(bean.getNombreCategoria());
				listCategoria.add(categoria);
			}
						
			i++;
		}

		

		List<Categoria> listCategoria2 = new ArrayList<Categoria>(); 
		SubCategoria subCategoria = new SubCategoria();
		idCategoria = 0;
		
		for(Categoria bean1: listCategoria) {
			
		idCategoria = bean1.getId();
			
		i = 0;
		Integer idsubCategoria = 0;
		for(ListaCategoria bean2: listCategoriaTmp) {
			/*
			if(idCategoria.intValue()==bean2.getIdCategoria().intValue()) {
				idsubCategoria = bean2.getIdSubCategoria();
				subCategoria = new SubCategoria();
				subCategoria.setId(bean2.getIdSubCategoria());
				subCategoria.setNombre(bean2.getNombreSubCategoria());
				bean1.getSubcategoria().add(subCategoria);
			}		*/
			if(/*idsubCategoria.intValue()>0 && */idCategoria.intValue()==bean2.getIdCategoria().intValue() 
					&& idsubCategoria.intValue()!=bean2.getIdSubCategoria().intValue()) {
				idsubCategoria = bean2.getIdSubCategoria();
				subCategoria = new SubCategoria();
				subCategoria.setId(bean2.getIdSubCategoria());
				subCategoria.setNombre(bean2.getNombreSubCategoria());
				bean1.getSubcategoria().add(subCategoria);
			}
			
			i++;
		}
		
			listCategoria2.add(bean1);
		}
		
				
		List<Categoria> listCategoria3 = new ArrayList<Categoria>(); 
		List<SubCategoria> listSubCategoria4 = new ArrayList<SubCategoria>(); 
		Integer idsubCategoria = 0;
		Producto producto = new Producto();
		List<String> listaColores =  Util.listColor();
		
		int indice = 0;
		for(Categoria bean1: listCategoria2) {
			categoria = new Categoria();
			categoria = bean1;
			listSubCategoria4 = new ArrayList<SubCategoria>(); 
			
			for(SubCategoria bean2: bean1.getSubcategoria()) {
				subCategoria = new SubCategoria();
				subCategoria = bean2;
				
				for(ListaCategoria bean3: listCategoriaTmp) {
					
					if(categoria.getId().intValue() == bean3.getIdCategoria().intValue()
							 && subCategoria.getId().intValue() == bean3.getIdSubCategoria().intValue()) {						
						producto = new Producto();
						producto.setId(bean3.getIdProducto());
						producto.setNombre(bean3.getNombreProducto());
						producto.setPrecio(bean3.getPrecioProducto());
						producto.setMoneda(bean3.getMonedaProducto());						
						subCategoria.getProducto().add(producto);
					}
					
				}				
				listSubCategoria4.add(subCategoria);
				
			}
			categoria.setSubcategoria(listSubCategoria4);
			categoria.setColor(listaColores.get(indice));
			listCategoria3.add(categoria);
			indice++;
		}	
		
		return listCategoria3;
	}
	
	
	@Override
	public List<Categoria> listAll(String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");
				
		List<ListaCategoria> listCategoriaTmp = categoriaRepository.listAll(session.getCodigoRestauranteLocal());
		
		
		List<Categoria> listCategoria = new ArrayList<Categoria>(); 
		Categoria categoria = new Categoria();
		int i = 0;
		Integer idCategoria = 0;
		
		for(ListaCategoria bean: listCategoriaTmp) {
			
			if(i==0) {
				idCategoria = bean.getIdCategoria();
				categoria = new Categoria();
				categoria.setId(bean.getIdCategoria());
				categoria.setNombre(bean.getNombreCategoria());
				listCategoria.add(categoria);
			}			
			if(idCategoria.intValue()!=bean.getIdCategoria().intValue()) {
				idCategoria = bean.getIdCategoria();
				categoria = new Categoria();
				categoria.setId(bean.getIdCategoria());
				categoria.setNombre(bean.getNombreCategoria());
				listCategoria.add(categoria);
			}
						
			i++;
		}

		

		List<Categoria> listCategoria2 = new ArrayList<Categoria>(); 
		SubCategoria subCategoria = new SubCategoria();
		idCategoria = 0;
		
		for(Categoria bean1: listCategoria) {
			
		idCategoria = bean1.getId();
			
		i = 0;
		Integer idsubCategoria = 0;
		for(ListaCategoria bean2: listCategoriaTmp) {
			/*
			if(idCategoria.intValue()==bean2.getIdCategoria().intValue()) {
				idsubCategoria = bean2.getIdSubCategoria();
				subCategoria = new SubCategoria();
				subCategoria.setId(bean2.getIdSubCategoria());
				subCategoria.setNombre(bean2.getNombreSubCategoria());
				bean1.getSubcategoria().add(subCategoria);
			}		*/
			if(/*idsubCategoria.intValue()>0 && */idCategoria.intValue()==bean2.getIdCategoria().intValue() 
					&& idsubCategoria.intValue()!=bean2.getIdSubCategoria().intValue()) {
				idsubCategoria = bean2.getIdSubCategoria();
				subCategoria = new SubCategoria();
				subCategoria.setId(bean2.getIdSubCategoria());
				subCategoria.setNombre(bean2.getNombreSubCategoria());
				bean1.getSubcategoria().add(subCategoria);
			}
			
			i++;
		}
		
			listCategoria2.add(bean1);
		}
		
		
		
				
		List<Categoria> listCategoria3 = new ArrayList<Categoria>(); 
		List<SubCategoria> listSubCategoria4 = new ArrayList<SubCategoria>(); 
		Integer idsubCategoria = 0;
		Producto producto = new Producto();
		List<String> listaColores =  Util.listColor();
		
		int indice = 0;
		for(Categoria bean1: listCategoria2) {
			categoria = new Categoria();
			categoria = bean1;
			listSubCategoria4 = new ArrayList<SubCategoria>(); 
			
			for(SubCategoria bean2: bean1.getSubcategoria()) {
				subCategoria = new SubCategoria();
				subCategoria = bean2;
				
				for(ListaCategoria bean3: listCategoriaTmp) {
					
					if(categoria.getId().intValue() == bean3.getIdCategoria().intValue()
							 && subCategoria.getId().intValue() == bean3.getIdSubCategoria().intValue()) {						
						producto = new Producto();
						producto.setId(bean3.getIdProducto());
						producto.setNombre(bean3.getNombreProducto());
						producto.setPrecio(bean3.getPrecioProducto());
						producto.setMoneda(bean3.getMonedaProducto());	
						producto.setElaboracion(bean3.getLugarElaboracion());
						producto.setTipo(bean3.getTipo());
						subCategoria.getProducto().add(producto);
					}
					
				}				
				listSubCategoria4.add(subCategoria);
				
			}
			categoria.setSubcategoria(listSubCategoria4);
			categoria.setColor(listaColores.get(indice));
			listCategoria3.add(categoria);
			indice++;
		}	
		
		return listCategoria3;
	}

	@Override
	public void insert(Categoria categoria, String token) throws Exception {
		Session session = sessionRepository.getByToken(token);
		if(session==null)
			throw new BooxException("5001","Error sesión inválida.");
		
		categoria.setEstado(1);
		categoria.setSucursal(new Sucursal(session.getCodigoRestauranteLocal()));
		categoriaRepository.insert(categoria);
		
	}

	@Override
	public void update(Categoria categoria) throws Exception {
		categoriaRepository.update(categoria);
	}

	@Override
	public void delete(Categoria categoria) throws Exception {
		categoriaRepository.delete(categoria);
	}

}
