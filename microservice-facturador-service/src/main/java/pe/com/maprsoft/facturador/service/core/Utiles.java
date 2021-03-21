package pe.com.maprsoft.facturador.service.core;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.Security;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.core.env.Environment;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;



public class Utiles {


	public static final String formato_yyyy_MM_dd="yyyy-MM-dd";
	
	public static String getImageEncode64(String path,String foto) throws Exception {
		String encodedImage = null;

		if(foto!=null && foto!="") {
			foto = foto;
		} else {
			foto = "default.jpg";
		}
		
		InputStream in = null;
		byte[] imagen = null;
		
		try {
			//logger.info("Init path.");
			//String path = env.getProperty("path.transportista.foto");
			//logger.info("Obteniendo path."+path);
			String pathImg = Paths.get(path, foto).toString();
			//logger.info("Obteniendo imágenes."+pathImg);
			if (pathImg != null && !pathImg.isEmpty()) {
				Path ruta = Paths.get(pathImg);
				in = Files.newInputStream(ruta);
				imagen = IOUtils.toByteArray(in);
				encodedImage = Base64.encode(imagen);
			}
			
		} catch (Exception e) {
			//logger.error("Error obteniendo imágenes", e.getMessage());
		} finally {
			if (in != null) {
				in.close();
			}
		}

		return encodedImage;	
	}


	public static String getImageEncode64(byte[] imagen) throws Exception {
		String encodedImage = null;
		encodedImage = Base64.encode(imagen);			
		return encodedImage;	
	}

	public static String formatNumber(String number) {

		Double num = Double.parseDouble(number);

		DecimalFormatSymbols sym = new DecimalFormatSymbols();
		sym.setDecimalSeparator('.');
		sym.setGroupingSeparator(',');

		DecimalFormat formateador = new DecimalFormat(
				"###,###,###,###,###,###,##0.00;-###,###,###,###,###,###,##0.00");
		formateador.setDecimalFormatSymbols(sym);

		return formateador.format(num);
	}

	public static XMLGregorianCalendar stringToXMLGregorianCalendar(String fecha, String formato) {		
		GregorianCalendar calendar = new GregorianCalendar();
    	try {
    	DateFormat format = new SimpleDateFormat(formato);
    	Date date = format.parse(fecha);
        calendar.setTime(date);
   		XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);   	    
   	    return xmlDate;
    	} catch (Exception e) {
    		return null;
        }
	}
	
	public static String getStringToDateToStringFormat(String dateInString) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String fecha ="";		 
	    try {
	         Date date = formatter.parse(dateInString);	            
	         fecha = Utiles.getTimeFormat(date, "dd/MM/yyyy HH:mm:ss");
	    } catch (ParseException e) {
	            e.printStackTrace();
	    }
	        
		return fecha;
	}

	public static String getTimeFormat(Date fecha, String format) {
		SimpleDateFormat sm = new SimpleDateFormat(format);
		return sm.format(fecha);
	}
	
	// Converts to java.security
	public static java.security.cert.X509Certificate convertToJava(javax.security.cert.X509Certificate cert) {
	    try {
	        byte[] encoded = cert.getEncoded();
	        ByteArrayInputStream bis = new ByteArrayInputStream(encoded);
	        java.security.cert.CertificateFactory cf
	            = java.security.cert.CertificateFactory.getInstance("X.509");
	        return (java.security.cert.X509Certificate)cf.generateCertificate(bis);
	    } catch (java.security.cert.CertificateEncodingException e) {
	    } catch (javax.security.cert.CertificateEncodingException e) {
	    } catch (java.security.cert.CertificateException e) {
	    }
	    return null;
	}

	// Converts to javax.security
	public static javax.security.cert.X509Certificate convertToJavax(java.security.cert.X509Certificate cert) {
	    try {
	        byte[] encoded = cert.getEncoded();
	        return javax.security.cert.X509Certificate.getInstance(encoded);
	    } catch (java.security.cert.CertificateEncodingException e) {
	    } catch (javax.security.cert.CertificateEncodingException e) {
	    } catch (javax.security.cert.CertificateException e) {
	    }
	    return null;
	}

	public static Document createBlankDocument() throws Exception {
    	DocumentBuilderFactory	factory  = DocumentBuilderFactory.newInstance();
        DocumentBuilder 		builder	 = factory.newDocumentBuilder();
        Document				document = builder.newDocument();
        return document;
	}	


}
