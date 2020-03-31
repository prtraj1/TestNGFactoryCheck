package com.prithvi.tech.tests;

import org.testng.annotations.Test;

public class Test01 extends BaseClass {

	private String paramData = "default";

	public Test01() {
		System.out.println("Object of Test01");
	}

	public Test01(String param, String browser, String env) {
		System.out.println("Object of Test01 with param as " + param);
		this.paramData = param;
		browserName = browser;
		environment = env;
	}

	@Test
	public void testMt1() {
		System.out.println("Test01 Method 1 with param as " + paramData);
	}

	@Test
	public void testMt2() {
		System.out.println("Test01 Method 2 with param as " + paramData);
	}
}
