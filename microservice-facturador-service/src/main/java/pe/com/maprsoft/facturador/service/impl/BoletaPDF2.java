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

public class BoletaPDF2 {

    public static final String RESULT = "C:/boleta2.pdf";
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
		

		BoletaPDF pdf = new BoletaPDF();
		pdf.generaBoletaPDF(boleta, pago);

		System.out.println(Utiles.getImageEncode64(pdf.generaBoletaPDF(boleta,pago)));
		
		
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

        //BaseFont bf_times = BaseFont.createFont(
        //    "c:/windows/fonts/times.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
        //Font times = new Font(bf_times, 12);
        Font times = new Font(FontFamily.TIMES_ROMAN, 12);
        
        //Margen 36 	ANCHO 559     ALTO  806
        int margen = 36;
        int top = 810;
        int right = 360;
        
        int alturaHeader = 20;
        int alturaSubHeader = 80;

        
        Image img = Image.getInstance(RESOURCE);
        img.scaleAbsolute(120,55);
        img.setAbsolutePosition((float)margen+5, (float)top-70);
        document.add(img);

        Image img2 = Image.getInstance(RESOURCE2);
        img2.scaleAbsolute(260,100);
        img2.setAbsolutePosition((float)margen+25, (float)top-385-35-15);
        document.add(img2);

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
        //under.saveState();
        //under.setRGBColorFill(0xFF, 0xD7, 0x00);
        //under.setRGBColorFill(230, 233, 243);   //Color plomo
       /* *******
       under.setRGBColorFill(196, 229, 248  );   //Color azulado
        //margen, top-alturaHeader, margen+300, top
        //under.rectangle(margen, top-15, margen+265, top-10);
        under.rectangle(margen, 794, margen+264, 17);
        under.fill();
        *****/
       
       //under.restoreState();
        //int margen = 36; int top = 810;  int right = 559;
        //int alturaHeader = 20;  int alturaSubHeader = 80;

        /* *********
        under.setRGBColorFill(230, 233, 243);   //Color Texto
        under.rectangle(margen, 710, margen+264, 80);
        under.fill();

        under.rectangle(margen, 565, right-37, 25);
        under.fill();
        ************/
        
/*        canvas.showTextAligned(Element.ALIGN_CENTER, "UNIDAD DE", 65, detalleAlto2-10, 0);
        canvas.showTextAligned(Element.ALIGN_CENTER, "MEDIDA", 65, detalleAlto2-20, 0);
        canvas.showTextAligned(Element.ALIGN_CENTER, "CANTIDAD", 120, detalleAlto2-15, 0);
        canvas.showTextAligned(Element.ALIGN_CENTER, "DESCRIPCIÓN", 250, detalleAlto2-15, 0);
        canvas.showTextAligned(Element.ALIGN_CENTER, "PRECIO UNITARIO", 435, detalleAlto2-15, 0);
        canvas.showTextAligned(Element.ALIGN_CENTER, "IMPORTE", 515, detalleAlto2-15, 0);
 */        
        
        
        
       /*
        rect = new Rectangle(margen, top-(alturaHeader+alturaSubHeader), margen+300, top-alturaHeader+3); //left   abajo, der, arriba
        rect.setBorder(Rectangle.NO_BORDER);
        rect.setBorderWidth(0);
        canvas.rectangle(rect);
        canvas.saveState();

        
        canvas.stroke();
        canvas.restoreState();
        */
        rect = new Rectangle(margen, top-alturaSubHeader-350 - 35 - 15, right, top-5); 
        rect.setBorder(Rectangle.BOX);
        rect.setBorderWidth(1);
        canvas.rectangle(rect);

        canvas.moveTo(margen+20, top-90);
        canvas.lineTo(right-20, top-90);
        
        canvas.moveTo(margen+20, top-90-110);
        canvas.lineTo(right-20, top-90-110);

        /*
        canvas.moveTo(margen+20, top-90-110-50-15);
        canvas.lineTo(right-20, top-90-110-50-15);
         */
        canvas.moveTo(margen+20, top-90-110-70-35-15);
        canvas.lineTo(right-20, top-90-110-70-35-15);

        int angulo = 0;
        //PANER IZQUIERDO
        canvas.beginText();
        canvas.setFontAndSize(bf_helv, 14);

        canvas.showTextAligned(Element.ALIGN_CENTER, boleta.getRazonSocial(),margen+190, top-alturaHeader-10, angulo);

        canvas.setFontAndSize(bf_helv, 12);
        canvas.showTextAligned(Element.ALIGN_CENTER,  "R.U.C."+"  "+boleta.getRuc(), margen+154+40, top-alturaHeader-30, angulo);
        canvas.setFontAndSize(bf_helv, 9);
        canvas.showTextAligned(Element.ALIGN_CENTER, boleta.getRazonSocialFranquicia(), margen+154+40, top-alturaHeader-45, angulo);

        canvas.setFontAndSize(bf_helv, 9);
        canvas.showTextAligned(Element.ALIGN_CENTER, boleta.getDireccionFranquicia(), margen+154+40, top-alturaHeader-60, angulo);


        
        canvas.setFontAndSize(bf_helv, 12);
        canvas.showTextAligned(Element.ALIGN_CENTER, "BOLETA  ELECTRONICA N° "+ pago.getSerie()+'-'+pago.getNumero(), margen+154+10, top-alturaHeader-90, angulo);
        
        canvas.setFontAndSize(bf_helv, 10);
        canvas.showTextAligned(Element.ALIGN_LEFT, "DIRECCION", margen+25, top-alturaHeader-110, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, ":"+ "   "+boleta.getDireccion(), margen+25+60, top-alturaHeader-110, angulo);
      
        canvas.showTextAligned(Element.ALIGN_LEFT, "TERMINAL", margen+25, top-alturaHeader-125, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, ":"+ "   "+"001", margen+25+60, top-alturaHeader-125, angulo);

        canvas.showTextAligned(Element.ALIGN_LEFT, "FECHA", margen+25, top-alturaHeader-140, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, ":"+ "   "+"28/08/2018 14:10:00", margen+25+60, top-alturaHeader-140, angulo);

        canvas.showTextAligned(Element.ALIGN_LEFT, "CLIENTE", margen+25, top-alturaHeader-155, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, ":"+ "   "+"  -  ", margen+25+60, top-alturaHeader-155, angulo);

        canvas.showTextAligned(Element.ALIGN_LEFT, "CAJA", margen+25, top-alturaHeader-170, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, ":"+ "   "+"MARIA VARGAS", margen+25+60, top-alturaHeader-170, angulo);

        canvas.showTextAligned(Element.ALIGN_LEFT, "CANT.", margen+25, top-alturaHeader-200, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, "DESCRIPCION", margen+25+60, top-alturaHeader-200, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, "MONTO", margen+25+50+180, top-alturaHeader-200, angulo);

        
        
        

        int alturaDetalle = 0;
        for(PagoDetalle bean: pago.getPedidos()) {
        	alturaDetalle = alturaDetalle + 15;
            canvas.showTextAligned(Element.ALIGN_LEFT, bean.getCantidad().toString(), margen+25, top-alturaHeader-200-alturaDetalle, angulo);
            canvas.showTextAligned(Element.ALIGN_LEFT, bean.getNombre(), margen+25+60, top-alturaHeader-200-alturaDetalle, angulo);
            canvas.showTextAligned(Element.ALIGN_LEFT, bean.getSubTotal(), margen+25+60+180, top-alturaHeader-200-alturaDetalle, angulo);        	
        }
        alturaDetalle = alturaDetalle + 20;
        canvas.showTextAligned(Element.ALIGN_LEFT, "IMPORTE TOTAL  S/.", margen+25, top-alturaHeader-200-alturaDetalle, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, pago.getTotal(), margen+25+60+180, top-alturaHeader-200-alturaDetalle, angulo);        	

        /*
        canvas.showTextAligned(Element.ALIGN_LEFT, "DNI", margen+25, top-alturaHeader-200, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, ":"+ "   "+"46669234", margen+25+60, top-alturaHeader-200, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, "PACIENTE", margen+25, top-alturaHeader-215, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, ":"+ "   "+"JOSE LAZO RIVERA", margen+25+60, top-alturaHeader-215, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, "HORARIO", margen+25, top-alturaHeader-230, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, ":"+ "   "+"08:00 - 13:00   TURNO :  14", margen+25+60, top-alturaHeader-230, angulo);

        
        canvas.showTextAligned(Element.ALIGN_LEFT, "CODIGO", margen+25, top-alturaHeader-200-45-15, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, "DESCRIPCION", margen+25+60, top-alturaHeader-200-45-15, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, "MONTO", margen+25+60+180, top-alturaHeader-200-45-15, angulo);

        canvas.showTextAligned(Element.ALIGN_LEFT, "201808", margen+25, top-alturaHeader-220-45-15, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, "Medicina General", margen+25+60, top-alturaHeader-220-45-15, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, "10.00", margen+25+60+180, top-alturaHeader-220-45-15, angulo);
        //canvas.showTextAligned(Element.ALIGN_LEFT, "Horario: 08:00 - 13:00 - Turno: 14", margen+25+60, top-alturaHeader-235-45-15, angulo);
        */
        
        canvas.showTextAligned(Element.ALIGN_CENTER, "Visualice este documento en", margen+125+30, top-alturaHeader-385-35-15, angulo);
        canvas.showTextAligned(Element.ALIGN_CENTER, "www.maprsoft.com", margen+125+30, top-alturaHeader-400-35-15, angulo);

        canvas.endText();
        
        
        //PANEL DERECHO
        /*
        canvas.beginText();
        canvas.setFontAndSize(bf_helv, 14);
        canvas.showTextAligned(Element.ALIGN_CENTER, "R.U.C."+"  "+boleta.getRuc(), 450, top-25, angulo);

        canvas.setFontAndSize(bf_helv, 16);
        canvas.showTextAligned(Element.ALIGN_CENTER, "FACTURA", margen+414, top-50, angulo);

        canvas.setFontAndSize(bf_helv, 14);
        canvas.showTextAligned(Element.ALIGN_CENTER, boleta.getNumeroFactura(), margen+414, top-75, angulo);
        canvas.endText();
        */
        canvas.stroke();
        canvas.restoreState();

        /*
        canvas.beginText();
        //Margen 36 	ANCHO 559     ALTO  806
        canvas.setFontAndSize(bf_helv, 10);
        int alturaDatos = 140;
        canvas.showTextAligned(Element.ALIGN_LEFT, "Señor (es):", margen, top-alturaDatos, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, "Fecha de emisión:", margen+252, top-alturaDatos, angulo);

        canvas.showTextAligned(Element.ALIGN_LEFT, "RUC N°:", margen, top-(alturaDatos+25), angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, "Guía de Remisión del Remitente:", margen+252, top-(alturaDatos+25), angulo);

        canvas.showTextAligned(Element.ALIGN_LEFT, "Dirección:", margen, top-(alturaDatos+50), angulo);

        canvas.endText();
        
        
        canvas.saveState();
        canvas.setLineWidth(0.05f);

        canvas.moveTo(96, top-alturaDatos);
        canvas.lineTo(266, top-alturaDatos);

        canvas.moveTo(378, top-alturaDatos);
        canvas.lineTo(556, top-alturaDatos);
        
        canvas.moveTo(96, top-(alturaDatos+25));
        canvas.lineTo(266, top-(alturaDatos+25));

        canvas.moveTo(440, top-(alturaDatos+25));
        canvas.lineTo(556, top-(alturaDatos+25));

        canvas.moveTo(96, top-(alturaDatos+50));
        canvas.lineTo(266, top-(alturaDatos+50));

        canvas.moveTo(285, top-(alturaDatos+50));
        canvas.lineTo(556, top-(alturaDatos+50));


        canvas.stroke();
        canvas.restoreState();

        
        canvas.beginText();
        //Margen 36 	ANCHO 559     ALTO  806
        canvas.setFontAndSize(bf_helv, 8);

        canvas.showTextAligned(Element.ALIGN_LEFT, boleta.getRazonSocialAdquirente(), 99, top-alturaDatos+2, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, boleta.getFechaEmision(), 381, top-alturaDatos+2, angulo);

        canvas.showTextAligned(Element.ALIGN_LEFT, boleta.getRucAdquirente(), 99, top-(alturaDatos+25)+2, angulo);
        canvas.showTextAligned(Element.ALIGN_LEFT, "", 443, top-(alturaDatos+25)+2, angulo);
        
        canvas.showTextAligned(Element.ALIGN_LEFT, "Av. Peru # 1244 - SMP @@@", 99, top-(alturaDatos+50)+2, angulo);

        canvas.endText();
        
        
        //Margen 36 	ANCHO 559     ALTO  806
        int alturaDetalle = 420;  //380    36, 380, 559, 570
        int alturaDetalle2 = alturaDetalle-200;  //570  

        int detalleAlto1 = top-alturaDetalle;  //380   para agregar altura de detalle -100 
        int detalleAlto2 = top-alturaDetalle2;  //570

        rect = new Rectangle(margen, detalleAlto1, right, detalleAlto2); //left   abajo, der, arriba
        rect.setBorder(Rectangle.BOX);
        rect.setBorderWidth(1);
        canvas.rectangle(rect);

        
        canvas.saveState();
        canvas.setLineWidth(0.05f);

        //Lineas de detalle de factura   545     
        canvas.moveTo(margen, detalleAlto2-25);
        canvas.lineTo(right, detalleAlto2-25);

        //Divisiones verticales de facturas   380=top-alturaDetalle   570=top-(alturaDetalle-190)
        canvas.moveTo(95, detalleAlto2);
        canvas.lineTo(95, detalleAlto1);
        
        canvas.moveTo(145, detalleAlto2);
        canvas.lineTo(145, detalleAlto1);

        canvas.moveTo(395, detalleAlto2);
        canvas.lineTo(395, detalleAlto1);

        canvas.moveTo(475, detalleAlto2);
        canvas.lineTo(475, detalleAlto1);


        //int alturaDetalle = 420;  //380    36, 380, 559, 570
        //int alturaDetalle2 = alturaDetalle-190;  //570  

        //Divisiones horizontales IGV
        canvas.moveTo(395, detalleAlto1+45); //425
        canvas.lineTo(558, detalleAlto1+45);

        canvas.moveTo(395, detalleAlto1+30); //410
        canvas.lineTo(558, detalleAlto1+30);

        canvas.moveTo(395, detalleAlto1+15); //395
        canvas.lineTo(558, detalleAlto1+15);
        
        
        canvas.stroke();
        canvas.restoreState();

        canvas.beginText();
        //Margen 36 	ANCHO 559     ALTO  806
        canvas.setFontAndSize(bf_helv, 7);
        canvas.showTextAligned(Element.ALIGN_CENTER, "UNIDAD DE", 65, detalleAlto2-10, 0);
        canvas.showTextAligned(Element.ALIGN_CENTER, "MEDIDA", 65, detalleAlto2-20, 0);
        canvas.showTextAligned(Element.ALIGN_CENTER, "CANTIDAD", 120, detalleAlto2-15, 0);
        canvas.showTextAligned(Element.ALIGN_CENTER, "DESCRIPCIÓN", 250, detalleAlto2-15, 0);
        canvas.showTextAligned(Element.ALIGN_CENTER, "PRECIO UNITARIO", 435, detalleAlto2-15, 0);
        canvas.showTextAligned(Element.ALIGN_CENTER, "IMPORTE", 515, detalleAlto2-15, 0);
        

        canvas.showTextAligned(Element.ALIGN_CENTER, "VALOR DE VENTA", 436, detalleAlto1+35, 0);
        canvas.showTextAligned(Element.ALIGN_CENTER, "IGV 18%", 436, detalleAlto1+20, 0);
        canvas.showTextAligned(Element.ALIGN_CENTER, "IMPORTE TOTAL", 436, detalleAlto1+5, 0);

        canvas.showTextAligned(Element.ALIGN_LEFT, "Son: "+boleta.getDesccripcionImporteVenta(), 100, detalleAlto1+5, 0);

        canvas.endText();



        canvas.beginText();        
        canvas.setFontAndSize(bf_helv, 7);
        int detalleAlturas = detalleAlto2-35;
        int rangoAltura = 11;
        for(Producto producto: boleta.getProductos()) {
            canvas.showTextAligned(Element.ALIGN_CENTER, producto.getUnidadMedida(), 65, detalleAlturas, 0);        
            canvas.showTextAligned(Element.ALIGN_CENTER, String.valueOf(producto.getCantidadUnidades()), 120, detalleAlturas, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, producto.getDescripcion(), 148, detalleAlturas, 0);
            canvas.showTextAligned(Element.ALIGN_RIGHT, Util.formatNumber(String.valueOf(producto.getPrecioUnitarioReal())), 470, detalleAlturas, 0);           
            canvas.showTextAligned(Element.ALIGN_RIGHT, Util.formatNumber(String.valueOf(producto.getPrecioxCantidadReal())), 553, detalleAlturas, 0);
        	detalleAlturas = detalleAlturas - rangoAltura;
        }
  */
        /*
        canvas.showTextAligned(Element.ALIGN_CENTER, "Pieza", 65, detalleAlto2-35, 0);        
        canvas.showTextAligned(Element.ALIGN_CENTER, "1.00", 120, detalleAlto2-35, 0);
        canvas.showTextAligned(Element.ALIGN_LEFT, "APPLE - IPHONE X 64 GB", 148, detalleAlto2-35, 0);
        canvas.showTextAligned(Element.ALIGN_RIGHT, "22,931.03", 470, detalleAlto2-35, 0);
        canvas.showTextAligned(Element.ALIGN_RIGHT, "22,931.03", 553, detalleAlto2-35, 0);

        canvas.showTextAligned(Element.ALIGN_CENTER, "Pieza", 65, detalleAlto2-46, 0);        
        canvas.showTextAligned(Element.ALIGN_CENTER, "1.00", 120, detalleAlto2-46, 0);
        canvas.showTextAligned(Element.ALIGN_LEFT, "APPLE - IPHONE X 64 GB DDR HDD 2 TB", 148, detalleAlto2-46, 0);
        canvas.showTextAligned(Element.ALIGN_RIGHT, "6,931.03", 470, detalleAlto2-46, 0);
        canvas.showTextAligned(Element.ALIGN_RIGHT, "6,931.03", 553, detalleAlto2-46, 0);
         */
/*        
        canvas.showTextAligned(Element.ALIGN_LEFT, "S/", 480, detalleAlto1+35, 0);
        canvas.showTextAligned(Element.ALIGN_LEFT, "S/", 480, detalleAlto1+20, 0);
        canvas.showTextAligned(Element.ALIGN_LEFT, "S/", 480, detalleAlto1+5, 0);

        canvas.showTextAligned(Element.ALIGN_RIGHT, Util.formatNumber(String.valueOf(boleta.getImporteVenta())), 553, detalleAlto1+35, 0);
        canvas.showTextAligned(Element.ALIGN_RIGHT, Util.formatNumber(String.valueOf(boleta.getIgvVenta())), 553, detalleAlto1+20, 0);
        canvas.showTextAligned(Element.ALIGN_RIGHT, Util.formatNumber(String.valueOf(boleta.getImporteVenta())), 553, detalleAlto1+5, 0);

        canvas.endText();
*/
        
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
