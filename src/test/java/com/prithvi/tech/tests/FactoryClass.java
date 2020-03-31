package com.prithvi.tech.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.ITestContext;
import org.testng.annotations.Factory;

public class FactoryClass {

	@Factory
	public Object[] testClassSelector(ITestContext itc) {
		List<Object> list = new ArrayList<Object>();
		if (itc.getCurrentXmlTest().getParameter("enabled").equalsIgnoreCase("true")) {
			System.out.println(itc.getCurrentXmlTest().getParameter("fileName"));
			List<String> tests = null;
			try {
				tests = fetchRunnerFileData(
						System.getProperty("user.dir") + "\\Excel\\" + itc.getCurrentXmlTest().getParameter("fileName"),
						2);
			} catch (Exception e) {
				e.printStackTrace();
			}
//			System.out.println(tests);

			if (!tests.isEmpty()) {
				for (String test : tests) {
//					if(test.toString().equals("Test01")) {
//						list.add(new Test01());
//					}
//					else if(test.toString().equals("Test02")) {
//						list.add(new Test02());
//					}
					try {
//				        list.add(Class.forName("com.prithvi.tech.tests."+test).newInstance());
						list.add(Class.forName(this.getClass().getPackage().getName() + "." + test)
								.getDeclaredConstructor(new Class[] { String.class, String.class, String.class })
								.newInstance("DataFile", "chrome", "environment"));
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("No Tests to Run!! Exiting..");
			}
		}else {
			System.out.println("Skipped --> "+itc.getCurrentXmlTest().getParameter("fileName"));
		}

		Object[] data = list.toArray();
		list.clear();
		return data;
	}

	public List<String> fetchRunnerFileData(String runnerFile, int columnFlag)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		List<String> runTests = new ArrayList<String>();
		Workbook wb = WorkbookFactory.create(new FileInputStream(new File(runnerFile)));
		// Get sheet with the given name "Sheet1"
		Sheet s = wb.getSheet("Sheet1");
		// Returns the number of physically defined rows (NOT the number of rows in the
		// sheet)
		int rowCount = s.getPhysicalNumberOfRows();
//		System.out.println("total rows in sheet is : " + rowCount);
		// Iterating rows
		for (int i = 0; i < rowCount; i++) {
			// Returns the logical row (not physical) 0-based. If you ask for a row that is
			// not defined you get a null.
			Row r = s.getRow(i);
			// Gets the number of defined cells (NOT number of cells in the actual
			// row!).That is to say if only columns 0,4,5 have values then there would be 3
			int columnCount = r.getPhysicalNumberOfCells();
			// Iterating columns
			for (int j = 0; j < columnCount; j++) {
				Cell c = r.getCell(j);
				/*
				 * Get the value of the cell as a string For numeric cells we throw an
				 * exception. For blank cells we return an empty string.For formulaCells that
				 * are not string Formulas, we throw an exception.
				 */
				c.setCellType(CellType.STRING);
				String data = c.getStringCellValue();
//				System.out.print(data + "\t");
				if (columnFlag >= 0 && columnFlag <= columnCount && j == columnFlag && data.equalsIgnoreCase("Y")) {
					runTests.add(r.getCell(1).getStringCellValue());
				}
			}
//			System.out.println();			
		}
//		for(String st: runTests) {
//			System.out.println(st);
//		}
		return runTests;
	}

}
