package pe.com.maprsoft.facturador.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import pe.com.maprsoft.facturador.model.Boleta;
import pe.com.maprsoft.facturador.model.Pago;
import pe.com.maprsoft.facturador.model.PagoDetalle;
import pe.com.maprsoft.facturador.service.core.Utiles;

public class TicketPDF {

    public static final String RESULT = "C:/ticket.pdf";
    public static final String RESOURCE = "C:/logo2.png";
    public static final String RESOURCE2 = "C:/bara.png";
    
    /*
	public static void main(String[] args) throws Exception {

		Pago pago = new Pago();
		pago.setSerie("B001");
		pago.setNumero("0010003");
		
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

        
		Boleta boleta = new Boleta();
		boleta.setRazonSocial("Servicios Médicos");
		boleta.setRuc("20100454523");
        boleta.setDireccion("Av. Jr de la Unión # 159");
        boleta.setRazonSocialFranquicia("Servicios Médicos integrales");
        boleta.setDireccionFranquicia("Av. Los Precursores # 1245");
		

        TicketPDF pdf = new TicketPDF();
		pdf.generaBoletaPDF(boleta, pago);

		//System.out.println(Utiles.getImageEncode64(pdf.generaBoletaPDF(boleta,pago)));
		
		
	}
	*/
	

	public byte[] generaBoletaPDF(Boleta boleta, Pago pago) throws Exception {


		ByteArrayOutputStream out = new ByteArrayOutputStream();


		Document document = new Document();
		//PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
		PdfWriter writer = PdfWriter.getInstance(document, out);

        document.open();

        Font helvetica = new Font(FontFamily.HELVETICA, 12);
        BaseFont bf_helv = helvetica.getCalculatedBaseFont(false);

        Font times = new Font(FontFamily.TIMES_ROMAN, 12);
        
        //Margen 36 	ANCHO 559     ALTO  806
        //Margen 10 	ANCHO 300     ALTO  806
        int margen = 10;
        int top = 810;
        top = 830;
        //int right = 360;
        int right = 190;
        
        int alturaHeader = 20;
        int alturaSubHeader = 80;


        PdfContentByte canvas = writer.getDirectContent();
        canvas.saveState();


        
        Rectangle rect = new Rectangle(margen, top-alturaHeader-200, margen+300, top); //left   abajo, der, arriba
        rect.setBorder(Rectangle.NO_BORDER);
        rect.setBorderWidth(0);
        
        //canvas.setRGBColorFill(0, 0, 0);  		 //Color Texto
        //canvas.setRGBColorFill(227, 219, 217);   //Color Texto
        //canvas.setRGBColorStroke(227, 219, 217); //Borde Rectangulo 
        
        BaseFont bf = BaseFont.createFont();
        //canvas.setTextRenderingMode(PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE);
        //canvas.setLineWidth(1.5f);
        //canvas.setRGBColorStroke(0xFF, 0x00, 0x00);
        //canvas.setRGBColorFill(0xFF, 0xFF, 0xFF);
        //canvas.setFontAndSize(bf, 36);
        
        canvas.rectangle(rect);
        canvas.fill();
        //canvas.fillStroke();
        canvas.saveState();

        
        //canvas.stroke();
        canvas.restoreState();
        
       PdfContentByte under = writer.getDirectContentUnder();
        
        
        
        rect = new Rectangle(margen, top-alturaSubHeader-350 - 35 - 15, right, top-5); 
        //rect.setBorder(Rectangle.BOX);
        rect.setBorder(Rectangle.NO_BORDER);
        rect.setBorderWidth(0);
        canvas.rectangle(rect);

        canvas.moveTo(margen+20, top-90);
        canvas.lineTo(right-20, top-90);
        
        canvas.moveTo(margen+20, top-90-80);
        canvas.lineTo(right-20, top-90-80);
        /*
        canvas.moveTo(margen+20, top-90-80-70-35-15);
        canvas.lineTo(right-20, top-90-80-70-35-15);
         */
        int angulo = 0;
        //PANER IZQUIERDO
        canvas.beginText();
        canvas.setFontAndSize(bf_helv, 11);
        canvas.showTextAligned(Element.ALIGN_CENTER, boleta.getRazonSocialFranquicia(),margen+90, top-alturaHeader-10, angulo);
     
        canvas.setFontAndSize(bf_helv, 11);
        canvas.showTextAligned(Element.ALIGN_CENTER, boleta.getRazonSocial() , margen+54+40, top-alturaHeader-25, angulo);
        canvas.setFontAndSize(bf_helv, 8);
        canvas.showTextAligned(Element.ALIGN_CENTER, "R.U.C."+"  "+boleta.getRuc(), margen+54+40, top-alturaHeader-40, angulo);
        
        canvas.setFontAndSize(bf_helv, 8);
        canvas.showTextAligned(Element.ALIGN_CENTER, boleta.getDireccion(), margen+54+40, top-alturaHeader-55, angulo);


        
        canvas.setFontAndSize(bf_helv, 7);
        canvas.showTextAligned(Element.ALIGN_CENTER, "TICKET ELECTRONICO N° "+ pago.getSerie()+'-'+pago.getNumero(), margen+50+40, top-alturaHeader-90, angulo);
        
        canvas.showTextAligned(Element.ALIGN_LEFT, "FECHA", margen+15+5, top-alturaHeader-110, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, ":"+ "   "+"28/08/2018 14:10:00", margen+15+35, top-alturaHeader-110, angulo);

        canvas.showTextAligned(Element.ALIGN_LEFT, "CLIENTE", margen+15+5, top-alturaHeader-125, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, ":"+ "   "+boleta.getCliente(), margen+15+35, top-alturaHeader-125, angulo);

        canvas.showTextAligned(Element.ALIGN_LEFT, "CAJA", margen+15+5, top-alturaHeader-140, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, ":"+ "   "+"MARIA VARGAS", margen+15+35, top-alturaHeader-140, angulo);

        canvas.setFontAndSize(bf_helv, 7);
        canvas.showTextAligned(Element.ALIGN_LEFT, "CANT.", margen+15, top-alturaHeader-170, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, "ARTICULO", margen+15+30, top-alturaHeader-170, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, "MONTO", margen+15+20+100, top-alturaHeader-170, angulo);

        
        canvas.setFontAndSize(bf_helv, 7);
        int alturaDetalle = 0;
        for(PagoDetalle bean: pago.getPedidos()) {
        	alturaDetalle = alturaDetalle + 15;
            canvas.showTextAligned(Element.ALIGN_LEFT, bean.getCantidad().toString(), margen+15, top-alturaHeader-170-alturaDetalle, angulo);
            canvas.showTextAligned(Element.ALIGN_LEFT, bean.getNombre(), margen+15+20, top-alturaHeader-170-alturaDetalle, angulo);
            canvas.showTextAligned(Element.ALIGN_RIGHT, bean.getSubTotal(), margen+95+20+40, top-alturaHeader-170-alturaDetalle, angulo);        	
        
        }
        alturaDetalle = alturaDetalle + 20;
        canvas.showTextAligned(Element.ALIGN_RIGHT, "IMPORTE TOTAL", margen+95, top-alturaHeader-170-alturaDetalle, angulo);
        canvas.showTextAligned(Element.ALIGN_RIGHT, pago.getMoneda()+" "+pago.getTotal(), margen+95+20+40, top-alturaHeader-170-alturaDetalle, angulo);        	

        canvas.endText();
        
        

        canvas.stroke();
        canvas.restoreState();



        
        // step 5
        document.close();
        InputStream in =  new ByteArrayInputStream(out.toByteArray());
        byte[] pdf = null;
        pdf = IOUtils.toByteArray(in);
        
        return pdf;

		
		
		
		
		/*
        
        
        int alturaDetalle = 0;
        for(PagoDetalle bean:  pago.getPedidos()) {
        	alturaDetalle = alturaDetalle + 15;
            canvas.showTextAligned(Element.ALIGN_LEFT, bean.getCantidad().toString(), margen+25, top-alturaHeader-200-alturaDetalle, angulo);
            canvas.showTextAligned(Element.ALIGN_LEFT, bean.getNombre(), margen+25+60, top-alturaHeader-200-alturaDetalle, angulo);
            canvas.showTextAligned(Element.ALIGN_LEFT, bean.getSubTotal(), margen+25+60+180, top-alturaHeader-200-alturaDetalle, angulo);        	
        }
        alturaDetalle = alturaDetalle + 20;
        canvas.showTextAligned(Element.ALIGN_LEFT, "IMPORTE TOTAL  S/.", margen+25, top-alturaHeader-200-alturaDetalle, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, "45.50", margen+25+60+180, top-alturaHeader-200-alturaDetalle, angulo);        	

        
        canvas.showTextAligned(Element.ALIGN_CENTER, "Visualice este documento en", margen+125+30, top-alturaHeader-385-35-15, angulo);
        canvas.showTextAligned(Element.ALIGN_CENTER, "www.varoscompany.com", margen+125+30, top-alturaHeader-400-35-15, angulo);

        canvas.endText();
        */

	}
	
}
