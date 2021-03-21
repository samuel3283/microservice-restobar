package pe.com.maprsoft.facturador.web.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Demos2 {

	public static Double formatearDecimales(Double numero, Integer numeroDecimales) {
		return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
	}
	
	public static void main(String[] args) {
		
		double precio =  200.00;
		int cantidad = 7;
		double igv = 1.18;
		
		double subtotal = precio / igv;
		double valor_igv =  precio - subtotal;
		
		double precioC = subtotal + valor_igv;
		System.out.println("precioC::::"+precioC);
		
		double subtotalRound = formatearDecimales(subtotal, 2);
		double valor_igvRound = formatearDecimales(valor_igv, 2);
		double precioRound = subtotalRound + valor_igvRound;
		System.out.println("subtotalRound::::"+subtotalRound);		
		System.out.println("valor_igvRound::::"+valor_igvRound);		
		System.out.println("precioRound::::"+precioRound);		

		//double precioOtro = Double.valueOf(String.format("%.2f", subtotal)) + Double.valueOf(String.format("%.2f", valor_igv));
		//System.out.println("precioOtro::::"+precioOtro);
		System.out.println("subtotal::::"+subtotal);		
		System.out.println("subtotal::::"+String.format("%.2f", subtotal));		
		System.out.println("valor_igv::::"+valor_igv);
		System.out.println("valor_igv::::"+String.format("%.2f", valor_igv));
		
		
		
		/*
		// TODO Auto-generated method stub
		BigDecimal precio = new BigDecimal("8.50");
		BigDecimal factorIgv = new BigDecimal("1.18");
		
		BigDecimal uno = new BigDecimal("1.00");
		
		BigDecimal igv = new BigDecimal("0.18");

		BigDecimal precioBruto = precio.divide(factorIgv, 2);

		BigDecimal totalIgv = precioBruto.multiply(igv).setScale(2, RoundingMode.DOWN);
		System.out.println("totalIgv::::"+totalIgv);
		//totalIgv = totalIgv.setScale(2);
		//totalIgv = totalIgv.divide(uno, 2);
				 
		//double val = Math.round(new Double("1.2978"));
		BigDecimal igv2 = new BigDecimal("1.245").setScale(2, RoundingMode.FLOOR);
		System.out.println("igv2::::"+igv2);

		//System.out.println("val::::"+val);
				
		System.out.println("totalIgv::::"+totalIgv);
		System.out.println("precioBruto::::"+precioBruto);
		*/
		
		/*
		float p = new Float("8.50");
		float i = new Float("1.18");
		float precuiB = p / i;
		System.out.println("precuiB::::"+precuiB);*/

		
		
	}

}
