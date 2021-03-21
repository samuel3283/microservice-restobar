package pe.com.maprsoft.facturador.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pe.com.maprsoft.facturador.dao.repository.SucursalRepository;
import pe.com.maprsoft.facturador.dao.repository.impl.SucursalRepositoryImpl;
import pe.com.maprsoft.facturador.model.Boleta;
import pe.com.maprsoft.facturador.model.Franquicia;
import pe.com.maprsoft.facturador.model.Pago;
import pe.com.maprsoft.facturador.model.PagoDetalle;
import pe.com.maprsoft.facturador.model.Restaurante;
import pe.com.maprsoft.facturador.model.Sucursal;

public class Genera {

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ssssssssss");
		Pago pago = new Pago();
		pago.setSerie("B001");
		pago.setNumero("0010003");
		pago.setTotal("100.00");
		pago.setSubTotal("82.00");
		pago.setIgv("18.00");
		pago.setMoneda("S/");
		pago.setTipoDocumento("B");
		
        List<PagoDetalle> pedidos = new ArrayList<PagoDetalle>();        
        PagoDetalle detalle = new PagoDetalle();
        detalle.setCantidad(1);
        detalle.setNombre("Arroz con pollo");
        detalle.setSubTotal("18.00");      
        pedidos.add(detalle);
        
        detalle = new PagoDetalle();
        detalle.setCantidad(1);
        detalle.setNombre("Arroz con pato");
        detalle.setSubTotal("12.00");        
        pedidos.add(detalle);
        
        detalle = new PagoDetalle();
        detalle.setCantidad(1);
        detalle.setNombre("Sopa con pollo");
        detalle.setSubTotal("11.00");      
        pedidos.add(detalle);
        
        detalle = new PagoDetalle();
        detalle.setCantidad(1);
        detalle.setNombre("Seco con frejoles");
        detalle.setSubTotal("14.00");        
        pedidos.add(detalle);

        pago.setPedidos(pedidos);

		
        
        
        
		Sucursal sucursal = new Sucursal();
		Franquicia franquicia = new Franquicia();
		Restaurante restaurante = new Restaurante();
		franquicia.setCodigo(1);
		franquicia.setRazonSocial("MACKDONALD");
		restaurante.setFranquicia(franquicia);
		restaurante.setCodigo(1);
		restaurante.setRazon_social("Restaurante Gesto");
		restaurante.setRuc("12345678900");
		restaurante.setDireccion("Av direccin 123");
		sucursal.setRestaurante(restaurante);
		sucursal.setCodigo(1);
		sucursal.setNombre("Rest Alcazar");
		sucursal.setDireccion("Av alcazar 123 - Rimac");

		
		Boleta boleta = new Boleta();
		
		boleta.setRazonSocial(sucursal.getRestaurante().getRazon_social());
		boleta.setRuc(sucursal.getRestaurante().getRuc());
        boleta.setDireccion(sucursal.getDireccion());
        boleta.setCliente("-");
        boleta.setUrlDescarga("www.maprsoft.com");
        if(sucursal.getRestaurante().getFranquicia().getCodigo().intValue()<=0) {
        	boleta.setFranquicia(Boolean.FALSE);
        } else {
            boleta.setRazonSocialFranquicia(sucursal.getRestaurante().getFranquicia().getRazonSocial());
            boleta.setDireccionFranquicia(sucursal.getRestaurante().getFranquicia().getDireccion());
        	boleta.setFranquicia(Boolean.TRUE);        	
        }
        
        
        /*
        SucursalRepository sucursalRepository = new SucursalRepositoryImpl();
        Sucursal sucursal = sucursalRepository.getAll(1);
         */
        //TicketPDF pdf = new TicketPDF();
        //BoletaPDF pdf = new BoletaPDF();
        FacturaPDF pdf = new FacturaPDF();
		pdf.generaBoletaPDF(boleta, pago);

	}

}
