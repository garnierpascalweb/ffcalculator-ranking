package com.gpwsofts.ffcalculator.ranking;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.testng.annotations.Test;

public class FFCalculatorRankingTest {
	private static final Logger logger = Logger.getLogger(FFCalculatorRankingTest.class.getName());
	@BeforeClass
	public void setup() {
		InputStream is = null;
		Properties props = null;
		try {
			is = this.getClass().getResourceAsStream("log4j.properties");
			props = new Properties();
			props.load(is);
			PropertyConfigurator.configure(props);
		} catch (IOException e) {
			
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					
				}
		}
	}
	
	@Test
	public void test() {
		logger.info("ceci est un test");
	}
}
