package com.prithvi.tech.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test02 extends BaseClass {

	private String paramData = "default";

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
		System.out.println("Test02 Method 1 with param as " + txt);
	}

	@Test
	public void testMt2() {
		log.info("Test 02 Method 2");
		System.out.println("Test02 Method 2 with param as " + this.paramData);
	}

	@DataProvider(name = "data")
	public String[] dataPro() {
		return new String[] { this.paramData };
	}
}
