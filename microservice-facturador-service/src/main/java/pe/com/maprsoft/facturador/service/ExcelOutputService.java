package pe.com.maprsoft.facturador.service;

import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

public interface ExcelOutputService {

	public WritableWorkbook createExcelOutputExcel(HttpServletResponse response);
	
}
