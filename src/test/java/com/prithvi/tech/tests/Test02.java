package com.prithvi.tech.tests;

import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.prithvi.tech.utils.ExcelUtil;

public class Test02 extends BaseClass {

	private String paramData = "default";
	ExcelUtil exc = new ExcelUtil();

	public Test02() {
		System.out.println("Object of Test02");
	}

	public Test02(String param, String browser, String env) {
		System.out.println("Object of Test02 with param as " + param);
		this.paramData = param;
		browserName = browser;
		environment = env;
	}

	@Test(dataProvider = "data")
	public void testMt1(String txt) {
		log.info("Test 02 Method 1");
//		System.out.println("Test02 Method 1 with param as " + txt);
	}

	@Test(dataProvider = "datafromexcel")
	public void testMt2(List<String> data) {
		log.info("Test 02 Method 2");
//		System.out.println("Test02 Method 2 with param as " + this.paramData);
		log.info("Name: "+data.get(1));
		log.info("Age: "+data.get(2));
		log.info("State: "+data.get(3));
	}

	@DataProvider(name = "data")
	public String[] dataPro() {
		return new String[] { this.paramData };
	}
	
	@DataProvider(name = "datafromexcel")
	public Object[][] dataXl() {
		int iteration = 3;
		String file = System.getProperty("user.dir")+"\\Excel\\Data\\"+"DemoData.xlsx";
		Object[][] obj = new Object[iteration][1];
		int itrCnt = iteration;
		for(int i=0; i<iteration; i++) {
			List<String> dt = exc.getDataByColNo(file, itrCnt);
			dt.add(0, "");
			obj[i][0] = dt;
			itrCnt--;
		}
		return obj;
	}
}
