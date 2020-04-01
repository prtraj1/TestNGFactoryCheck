package com.prithvi.tech.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.prithvi.tech.tests.BaseClass;

public class ExcelUtil extends BaseClass{
	
	public ExcelUtil() {
		
	}
	
	public Workbook createWorkbook(String path) {
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(new FileInputStream(new File(path)));
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wb;
	}
	
	public String getData(String filePath, int rowNo, int colNo) {
		Workbook wb = createWorkbook(filePath);
		Sheet s = wb.getSheet("Sheet1");
		Row r = s.getRow(rowNo);
		Cell c = r.getCell(colNo);
		c.setCellType(CellType.STRING);
		return c.getStringCellValue();
	}
	
	public String getData(String filePath, String sheetName, int rowNo, int colNo) {
		Workbook wb = createWorkbook(filePath);
		Sheet s = wb.getSheet(sheetName);
		Row r = s.getRow(rowNo);
		Cell c = r.getCell(colNo);
		c.setCellType(CellType.STRING);
		return c.getStringCellValue();
	}
	
	public void datafileInterpreter() {
		
	}

}
