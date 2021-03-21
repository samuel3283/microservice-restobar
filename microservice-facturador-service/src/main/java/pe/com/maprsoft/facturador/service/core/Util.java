package pe.com.maprsoft.facturador.service.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;



public class Util {
	
	private final static Logger logger = LoggerFactory
			.getLogger(Util.class);

	public final static String PATTERN_YYYY_MM_DD_TIME = "yyyy-MM-dd'T'HH:mm:ss.SSSz";	
	public final static String PATTERN_YYYY_MM_DD_T = "yyyy-MM-dd'T'HH:mm:ss";	

	public final static String PATTERN_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";	
	public final static String PATTERN_YY_MM_DD_HH_MI_SS = "yyyyMMddHHmmss";	
	public final static String PATTERN_DD_MM_YY_HH_MI_SS = "dd/MM/yyyy HH:mm:ss";	
	public final static String PATTERN_DD_MM_YY_HH_MI_A = "dd/MM/yyyy hh:mm a";	
	public final static String PATTERN_DD_MM_YY = "dd/MM/yyyy";	
	public final static String PATTERN_YY_MM_DD = "yyyy-MM-dd";	
	public final static String PATTERN_HH_MI_SS = "HH:mm:ss";	
	public final static String PATTERN_HH_MI_AM = "hh:mm a";	
	public final static String PATTERN_DEFAULT = "dd/MM/yyyy";
	public final static String PATTERN_DD_MM_YYYY_TIME = "dd/MM/yyyy HH:mm:ss,SSS";	
	public final static String PATTERN_TIME_HHmmss = "HHmmss";
	public final static String PATTERN_TIME_hhmm_a = "hh:mm a";
	
	public final static String PATTERN_TIME_MM = "MM";
	public final static String PATTERN_TIME_YYYY = "yyyy";
	public final static String PATTERN_TIME_YYYYMMDD = "yyyyMMdd";

	public final static String PATTERN_DEFAULT_DDMMYY = "dd-MM-yyyy";
	public final static String PATTERN_DEFAULT_YYMMDD = "yyyy-MM-dd";


	public static Double formatearDecimales(Double numero, Integer numeroDecimales) {
		return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
	}

	public static long diferenciaMinutosFechas(String fechaActual, String formato) {				
		SimpleDateFormat formatter = new SimpleDateFormat(formato);
		String fecha ="";		 
		
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-5"));
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		Date dateActual = calendar.getTime();
		
        Date dateRegistro = new Date();;
		try {
			dateRegistro = formatter.parse(fechaActual);
		} catch (ParseException e) {
			e.printStackTrace();
		}	            
        fecha = Util.getTimeFormat(dateRegistro, formato);
        System.out.println("dateActual="+dateActual+"--dateRegistro="+dateRegistro);
        long valor = dateActual.getTime() - dateRegistro.getTime();
        
        //long dias = valor / (1000 * 60 * 60 * 24);
		//long hras = valor / (1000 * 60 * 60);
		long min = valor / (1000 * 60);	        
		return min;
	}

	
	public static String getFechaDateToFormat(String dateInString) {				
		String fecha ="";		 
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-5"));
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		Date date = calendar.getTime();
		try {      
	         fecha = Util.getTimeFormat(date, dateInString);
	    } catch (Exception e) {
	            e.printStackTrace();
	    }
	        
		return fecha;
	}

	
	public static String getFormatDescFecha(String fecha) {
		String anio = fecha.substring(0, 4);
        String mes = fecha.substring(4, 6);
        String mesDes = getDescripcionMes(mes);
        String val = mesDes.concat(" ".concat(anio));
		return val;
	}
	
		public static List<String> listFechaDateToFormat() {				
		List<String> lista = new ArrayList<String>();
		String fecha ="";		 
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-5"));
		for(int i=5;i>=0;i--) {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.add(Calendar.MONTH, -i);
		Date date = calendar.getTime();
		try {      
	         fecha = Util.getTimeFormat(date, "YYYYMM");
	         /*String anio = fecha.substring(1, 4);
	         String mes = fecha.substring(4, 6);
	         String mesDes = getDescripcionMes(mes);
	         String val = mesDes.concat(" ".concat(anio));
	         System.out.println("===>"+val);*/
	         lista.add(fecha);
	    } catch (Exception e) {
	            e.printStackTrace();
	    }
		}    
		return lista;
	}

	public static List<String> listFechaDateToFormatByDiaAnteriores(String fecha) {				
		List<String> lista = new ArrayList<String>();
		lista.add(getDateToFormat(fecha, "dd/MM/yyyy", "yyyyMMdd"));
		
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-5"));
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {		         
	         Date date = formatter.parse(fecha);	            
	         calendar.setTime(date);
	 	} catch (ParseException e) {
	            e.printStackTrace();
	    }
		  			
		for(int i=1;i<=5;i++) {
		int valor = 1*24;
		calendar.add(Calendar.HOUR, -valor); 
		Date date = calendar.getTime();
		try {      
	         fecha = Util.getTimeFormat(date, "YYYYMMdd");	         
	         lista.add(fecha);
	    } catch (Exception e) {
	            e.printStackTrace();
	    }
		}    
		return lista;
	}

	public static List<String> listFechaDateToFormatByDia() {				
		List<String> lista = new ArrayList<String>();
		String fecha ="";		 
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-5"));
		for(int i=5;i>=0;i--) {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		//calendar.add(Calendar.DAY_OF_YEAR, -i);
		int valor = i*24;
		calendar.add(Calendar.HOUR, -valor); 
		Date date = calendar.getTime();
		try {      
	         fecha = Util.getTimeFormat(date, "YYYYMMdd");	         
	         lista.add(fecha);
	    } catch (Exception e) {
	            e.printStackTrace();
	    }
		}    
		return lista;
	}

	public static List<String> listFechaDateToFormatByAnio() {				
		List<String> lista = new ArrayList<String>();
		String fecha ="";		 
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-5"));
		for(int i=5;i>=0;i--) {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		//calendar.add(Calendar.DAY_OF_YEAR, -i);
		
		calendar.add(Calendar.YEAR, -i); 
		Date date = calendar.getTime();
		try {      
	         fecha = Util.getTimeFormat(date, "YYYY");	         
	         lista.add(fecha);
	    } catch (Exception e) {
	            e.printStackTrace();
	    }
		}    
		return lista;
	}

	public static String getFechaDateToFormat(Date date, String dateInString) {		
		
		String fecha ="";		 
	    try {      
	         fecha = Util.getTimeFormat(date, dateInString);
	    } catch (Exception e) {
	            e.printStackTrace();
	    }
	        
		return fecha;
	}


	public static String getDateToFormat(String dateInString, String formatinput, String formatOutput) {
		
		SimpleDateFormat formatter = new SimpleDateFormat(formatinput);
		String fecha ="";
		 
	    try {
	         Date date = formatter.parse(dateInString);	            
	         fecha = Util.getTimeFormat(date, formatOutput);
	    } catch (ParseException e) {
	            e.printStackTrace();
	    }
	        
		return fecha;
	}


	public static String completar(int numero) {
		
		String valor = String.valueOf(numero);
		if(valor.length()==1)	valor = "00000".concat(valor);
		else if(valor.length()==2)	valor = "0000".concat(valor);
		else if(valor.length()==3)	valor = "000".concat(valor);
		else if(valor.length()==4)	valor = "00".concat(valor);
		else if(valor.length()==5)	valor = "0".concat(valor);
		return valor;
	}

	public static String getDateToFormat(String dateInString) {
		
		SimpleDateFormat formatter = new SimpleDateFormat(Util.PATTERN_DEFAULT_DDMMYY);
		String fecha ="";
		 
	    try {
	         Date date = formatter.parse(dateInString);	            
	         fecha = Util.getTimeFormat(date, Util.PATTERN_DEFAULT_YYMMDD);
	    } catch (ParseException e) {
	            e.printStackTrace();
	    }
	        
		return fecha;
	}

	
	public static String getDateToFormatStr(String dateInString,String patronInput, String patronOutput) {
		
		SimpleDateFormat formatter = new SimpleDateFormat(patronInput);
		String fecha ="";
		 
	    try {
	         Date date = formatter.parse(dateInString);	            
	         fecha = Util.getTimeFormat(date, patronOutput);
	    } catch (ParseException e) {
	            e.printStackTrace();
	    }
	        
		return fecha;
	}

	
	public static String getFechaOperacion(String valor){
		String date=null;
		String day=null;
		String moth=null;
		String year=null;
		String[] d=null;
		if(valor!=null && !valor.isEmpty()){
			try{		
			d=valor.split("/");
			if(d!=null && d.length==3){
				day=d[0];
				moth=getDescripcionMes(d[1]) ;
				year=d[2];
				
				if (day!=null && moth!=null && year!=null){
					date =day +" "+ moth +" " + year;
				}
			}
			} catch (Exception e) {
				date=null;
				//LoggerService.logException("Error DataUtil - getFormatoFechaMail", e);
			}
		}
		return date;
	}

	/**
	 * Metodo Obtener Hora de Operacion en el formato 
	 * @param horaOperacion
	 * @return horaOperacion
	 * @author 
	 * @since 01/03/2017
	 */
	public static String getHoraOperacion(String horaOperacion) {
		SimpleDateFormat sdf;
		DateFormat formatter;
		Date date = new Date();
		try {
			sdf = new SimpleDateFormat(PATTERN_TIME_hhmm_a);
			formatter = new SimpleDateFormat(PATTERN_TIME_HHmmss);
			date = formatter.parse(horaOperacion);
			return sdf.format(date);
		} catch (Exception e) {
			//LoggerService.logException("Error DataUtil - getHoraOperacion", e);
			return null;
		}
	}

	
	/**
	 * M�todo para obtener la descripcion del Mes
	 * @param numero del mes
	 * @return descripcion del mes
	 * @author 
	 * @since 01/03/2017
	 */
	public static String getDescripcionMes(String mes) {
		String descripcionMes = null;
		int m = 0;
		try {
			m = Integer.parseInt(mes);
			switch (m) {
			case 1:
				descripcionMes = "Ene";
				break;
			case 2:
				descripcionMes = "Feb";
				break;
			case 3:
				descripcionMes = "Mar";
				break;
			case 4:
				descripcionMes = "Abr";
				break;
			case 5:
				descripcionMes = "May";
				break;
			case 6:
				descripcionMes = "Jun";
				break;
			case 7:
				descripcionMes = "Jul";
				break;
			case 8:
				descripcionMes = "Ago";
				break;
			case 9:
				descripcionMes = "Sep";
				break;
			case 10:
				descripcionMes = "Oct";
				break;
			case 11:
				descripcionMes = "Nov";
				break;
			case 12:
				descripcionMes = "Dic";
				break;
			default:
				descripcionMes = null;
				break;
			}
		} catch (Exception e) {
			m = 0;
			descripcionMes = null;
			//LoggerService.logException("Error DataUtil - getDescripcionMes", e);
		}
		return descripcionMes;
	}

	public static String getPeriodo(String periodo) {
		String descripcionMes = null;
		
		String anio = periodo.substring(0,4);
		
		int m = 0;
		try {
			m = Integer.parseInt(periodo.substring(4, 6));
			switch (m) {
			case 1:
				descripcionMes = "Enero";
				break;
			case 2:
				descripcionMes = "Febreo";
				break;
			case 3:
				descripcionMes = "Marzo";
				break;
			case 4:
				descripcionMes = "Abril";
				break;
			case 5:
				descripcionMes = "Mayo";
				break;
			case 6:
				descripcionMes = "Junio";
				break;
			case 7:
				descripcionMes = "Julio";
				break;
			case 8:
				descripcionMes = "Agosto";
				break;
			case 9:
				descripcionMes = "Septiembre";
				break;
			case 10:
				descripcionMes = "Octubre";
				break;
			case 11:
				descripcionMes = "Noviembre";
				break;
			case 12:
				descripcionMes = "Diciembre";
				break;
			default:
				descripcionMes = null;
				break;
			}
		} catch (Exception e) {
			m = 0;
			descripcionMes = null;
			//LoggerService.logException("Error DataUtil - getDescripcionMes", e);
		}
		return descripcionMes.concat(" ".concat(anio));
	}

	public static String getTime(String format) {
	    Date ahora = new Date();
		SimpleDateFormat sm = new SimpleDateFormat(format);
		return sm.format(ahora);
	}

	public static String getTimetoDate(String dateInString) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String fecha ="";
		 
	    try {
	         Date date = formatter.parse(dateInString);	            
	         fecha = Util.getTimeFormat(date, Util.PATTERN_DEFAULT_DDMMYY);
	    } catch (ParseException e) {
	            e.printStackTrace();
	    }
	        
		return fecha;
	}

	public static String getTimeFormat(Date fecha, String format) {
		SimpleDateFormat sm = new SimpleDateFormat(format);
		return sm.format(fecha);
	}
	
	public static List<String> getUltimosMeses(int meses) {
		List<String> lista = new ArrayList();
		Calendar ahora = Calendar.getInstance();
		lista.add(Util.getTimeFormat(ahora.getTime(), "YYYYMM"));
		for(int i=1;i<meses;i++) {
			ahora.add(Calendar.MONTH, -1);			
			lista.add(Util.getTimeFormat(ahora.getTime(), "YYYYMM"));
		}
		return lista;
	}
	
	public static String getTime() {
	    Date ahora = new Date();
		SimpleDateFormat sm = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sm.format(ahora);
	}

	public static String getFecha(String patron) {
	    Date ahora = new Date();
		SimpleDateFormat sm = new SimpleDateFormat(patron);
		return sm.format(ahora);
	}

	public static String completa(String cadena, int rango) {
	    		
		for(int i=0;i<rango;i++) 
			cadena = "0".concat(cadena);

		return cadena;
	}
	
	/*
	public static List<BeanUtil> lstTipoCarga() {
		List<BeanUtil> lista = new ArrayList<BeanUtil>();
		lista.add(new BeanUtil("CS","Carga Seca",false));
		lista.add(new BeanUtil("LI","Liquidos",false));
		lista.add(new BeanUtil("GA","Gases",false));
		lista.add(new BeanUtil("QU","Químicos",false));
		lista.add(new BeanUtil("GR","Granel",false));
		lista.add(new BeanUtil("AV","Animales Vivos",false));
		lista.add(new BeanUtil("VE","Vehiculos",false));
		lista.add(new BeanUtil("MP","Maquina Pesada",false));
		
		return lista;
	}
	
	public static boolean existeBean(List<BeanUtil> lista, BeanUtil bean) {
		boolean indicador = false;
		
		for(BeanUtil tmp: lista) {
			if(tmp.getCodigo().equals(bean.getCodigo()))
				indicador = true;
		}
		return indicador;
	}
	
	public static List<BeanUtil> setearListMultiple(List<BeanUtil> lista) {
		List<BeanUtil> total = lstTipoCarga();
		List<BeanUtil> listaFinal = new ArrayList<BeanUtil>();
		for(BeanUtil bean: total) {			
			if(existeBean(lista,bean)) {
				bean.setCheck(Boolean.TRUE);
			}
			listaFinal.add(bean);						
		}
		
		return listaFinal;
	}
	
	public static String getTipoCarga(String tipoCarga) {
		String resultado ="";
		List<BeanUtil> lista = lstTipoCarga();
		
		for(BeanUtil bean: lista ) {
			
			if(bean.getCodigo().equals(tipoCarga)) {
				resultado = bean.getDescripcion();
			}			
			
		}
		return resultado;
	}

	public static String getTipoCamion(String tipoCamion) {
		String resultado ="";
		List<BeanUtil> lista = new ArrayList<BeanUtil>();
		lista.add(new BeanUtil("C","Camión"));
		lista.add(new BeanUtil("T","Trailer"));
		lista.add(new BeanUtil("TC","Tren de Carretera"));
		
		for(BeanUtil bean: lista ) {
			
			if(bean.getCodigo().equals(tipoCamion)) {
				resultado = bean.getDescripcion();
			}			
			
		}
		return resultado;
	}

	*/
	public static String getExtension(String foto) throws Exception {
		String extension =null;
		
		if(foto!=null && foto!="") {
			if(foto.length()>3) {
		      extension = foto.substring(foto.lastIndexOf("."), foto.length());
			} 
		} 

		return extension;
	}
	
	public static String getExtensionProfile(String foto) throws Exception {
		String extension ="jpg";
		return extension;
	}

	public static String getImageEncode64(String foto,Environment env) throws Exception {
		String encodedImage = null;

		if(foto!=null && foto!="") {
			foto = foto;
		} else {
			foto = "default.jpg";
		}
		
		InputStream in = null;
		byte[] imagen = null;
		
		try {
			logger.info("Init path.");
			String path = env.getProperty("path.transportista.foto");
			logger.info("Obteniendo path."+path);
			String pathImg = Paths.get(path, foto).toString();
			logger.info("Obteniendo imágenes."+pathImg);
			if (pathImg != null && !pathImg.isEmpty()) {
				File fileVehiculo = new File(pathImg);
				//if(fileVehiculo.exists()) {
				Path ruta = Paths.get(pathImg);
				in = Files.newInputStream(ruta);
				imagen = IOUtils.toByteArray(in);
				encodedImage = Base64.encode(imagen);
				/*} else {
					path = env.getProperty("path.user.di");
					pathImg = Paths.get(path, "vehiculosinimagen.jpg").toString();							
					Path ruta = Paths.get(pathImg);
					in = Files.newInputStream(ruta);
					imagen = IOUtils.toByteArray(in);
					encodedImage = Base64.encode(imagen);							
				}*/
				
			}
			
		} catch (Exception e) {
			logger.error("Error obteniendo imágenes", e.getMessage());
		} finally {
			if (in != null) {
				in.close();
			}
		}

		return encodedImage;	
	}

	public static String getImageProfileEncode64(String foto,Environment env) throws Exception {
		String encodedImage = null;

		if(foto!=null && foto!="") {
			foto = foto;
		} else {
			foto = "profile.jpg";
		}
		
		InputStream in = null;
		byte[] imagen = null;
		
		try {
			logger.info("Init path.");
			String path = env.getProperty("path.transportista.foto");
			logger.info("Obteniendo path."+path);
			String pathImg = Paths.get(path, foto).toString();
			logger.info("Obteniendo imágenes."+pathImg);
			if (pathImg != null && !pathImg.isEmpty()) {
				Path ruta = Paths.get(pathImg);
				in = Files.newInputStream(ruta);
				imagen = IOUtils.toByteArray(in);
				encodedImage = Base64.encode(imagen);
			}
			
		} catch (Exception e) {
			logger.error("Error obteniendo imágenes", e.getMessage());
		} finally {
			if (in != null) {
				in.close();
			}
		}

		return encodedImage;	
	}

	public static String ucFirst(String str) {
	    if (str.isEmpty()) {
	        return str;            
	    } else {
	        return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase(); 
	    }
	}


	
	public static String getCargaExtension(String foto) throws Exception {
		String extension =null;
		
		if(foto!=null && foto!="") {
			if(foto.length()>3) {
		      extension = foto.substring(foto.lastIndexOf("."), foto.length());
			} 
		} 
		extension = extension.replace(".", "");
		return extension;
	}
	
	public static String getCargaImageEncode64(String foto,Environment env) throws Exception {
		String encodedImage = null;

		if(foto!=null && foto!="") {
			foto = foto;
		} else {
			foto = "default.jpg";
		}
		
		InputStream in = null;
		byte[] imagen = null;
		
		try {
			logger.info("Init path.");
			String path = env.getProperty("path.carga.foto");
			logger.info("Obteniendo path."+path);
			String pathImg = Paths.get(path, foto).toString();
			logger.info("Obteniendo imágenes."+pathImg);
			if (pathImg != null && !pathImg.isEmpty()) {
				Path ruta = Paths.get(pathImg);
				in = Files.newInputStream(ruta);
				imagen = IOUtils.toByteArray(in);
				encodedImage = Base64.encode(imagen);
			}
			
		} catch (Exception e) {
			logger.error("Error obteniendo imágenes", e.getMessage());
		} finally {
			if (in != null) {
				in.close();
			}
		}

		return encodedImage;	
	}

	
	public static List<String> listColor() {
		
		List<String> lista = new ArrayList<String>();
		lista.add("bg-maroon");
		lista.add("bg-purple");
		lista.add("bg-olive");
		lista.add("bg-navy");
		lista.add("bg-red");
		lista.add("bg-blue");
		lista.add("bg-yellow");
		lista.add("bg-green");
				
		return lista;
	}

	public static List<String> listColorGraph() {
		
		List<String> lista = new ArrayList<String>();
		lista.add("#f56954");
		lista.add("#00a65a");

		lista.add("#f39c12");
		lista.add("#00c0ef");
		lista.add("#3c8dbc");
		lista.add("#d2d6de");
		lista.add("#f56954");
		lista.add("#00a65a");

		lista.add("#f39c12");
		lista.add("#00c0ef");
		lista.add("#3c8dbc");
		lista.add("#d2d6de");


		return lista;
	}

}
