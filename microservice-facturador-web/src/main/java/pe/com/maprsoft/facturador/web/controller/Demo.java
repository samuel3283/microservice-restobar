package pe.com.maprsoft.facturador.web.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import pe.com.maprsoft.facturador.service.core.Util;

public class Demo {

	public static void main(String[] args) {
		
		String valor ="";
		List<String> valores = Util.listFechaDateToFormatByDiaAnteriores("04/11/2018");
		
		
		for(String val: valores) {
			System.out.println("valor1::::"+val);
			
		}
		/*
		// TODO Auto-generated method stub
		BigDecimal precio = new BigDecimal("5.00");

		BigDecimal merma = new BigDecimal("10.00");
		BigDecimal cien = new BigDecimal("100.00");

		BigDecimal valor = merma.divide(cien).setScale(2);
		System.out.println("valor1::::"+valor);
		
		valor = valor.multiply(precio).setScale(2);
		System.out.println("valor2::::"+valor);
		
		valor = precio.subtract(valor).setScale(2);
		System.out.println("valor3::::"+valor);
		*/
		
		/*
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
