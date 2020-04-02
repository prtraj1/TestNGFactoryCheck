package com.prithvi.tech.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.prithvi.tech.tests.BaseClass;

public class ExcelUtil extends BaseClass {

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

	public void closeWorkbook(Workbook wb) {
		try {
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getData(String filePath, int rowNo, int colNo) {
		Workbook wb = createWorkbook(filePath);
		Sheet s = wb.getSheet("Sheet1");
		Row r = s.getRow(rowNo);
		Cell c = r.getCell(colNo);
		c.setCellType(CellType.STRING);
		String val = c.getStringCellValue();
		return val;
	}

	public String getData(String filePath, String sheetName, int rowNo, int colNo) {
		Workbook wb = createWorkbook(filePath);
		Sheet s = wb.getSheet(sheetName);
		Row r = s.getRow(rowNo);
		Cell c = r.getCell(colNo);
		c.setCellType(CellType.STRING);
		String val = c.getStringCellValue();
		return val;
	}
	
	public int getExcelColumnsSize(String file) {
		return createWorkbook(file).getSheetAt(0).getRow(0).getPhysicalNumberOfCells();
	}
	
	public int getExcelColumnsSize(String file, int row) {
		return createWorkbook(file).getSheetAt(0).getRow(row).getPhysicalNumberOfCells();
	}
	
	public int getExcelRowSize(String file) {
		return createWorkbook(file).getSheetAt(0).getPhysicalNumberOfRows();
	}

	public Map<String, List<String>> datafileInterpreter(String dataFile, int iteration) {	
		Workbook wb = createWorkbook(dataFile);
		Sheet s = wb.getSheetAt(0);
		int rowNum = s.getPhysicalNumberOfRows();
		closeWorkbook(wb);
		String key = null;
		Map<String, List<String>> dataMap = new HashMap<String, List<String>>();
		for (int i = 1; i < rowNum; i++) {
			List<String> dataList = new ArrayList<String>();
			key = getData(dataFile, i, 0);
			int colNum = 1;
			while(colNum <= iteration) {
				dataList.add(getData(dataFile, i, colNum));
				colNum++;
			}
			dataMap.put(key, dataList);
		}
		return dataMap;
	}
	
	public List<String> getDataByColNo(String file, int colNo) {
		Workbook wb = createWorkbook(file);
		Sheet s = wb.getSheetAt(0);
		int rowNum = s.getLastRowNum();
		List<String> dataList = new ArrayList<String>();
		for(int i=1; i<=rowNum; i++) {
			dataList.add(getData(file, i, colNo));
		}
		return dataList;
	}

}
