package com.gpwsofts.ffcalculator.ranking;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Classe en attendant de faire fonctionner Test NG
 * @since 1.0.0
 */
public class FFCalculatorRankingMain {
	private static final Logger logger = Logger.getLogger(FFCalculatorRankingMain.class.getName());
	FFCalculatorRankingMain main = null;
	public static void main(String args[]) {
		FFCalculatorRankingMain main = new FFCalculatorRankingMain();
		main.setup();
		main.u17();
		main.all();
	}
	
	public void setup() {
		InputStream is = null;
		Properties props = null;
		try {
			is = this.getClass().getResourceAsStream("log4j.properties");
			props = new Properties();
			props.load(is);
			props.list(System.out);
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
	
	public void u17() {
		//logger.info("ceci est un test");
		FFCalculatorRanking ranking = null;
		try {
			ranking = new FFCalculatorRanking();
			ranking.generate(EFFCalculatorRanking.U17);			
		} catch (IOException e) {
			logger.error("probleme ", e);
		} finally {
			
		}
	}
	
	public void all() {
		//logger.info("ceci est un test");
		FFCalculatorRanking ranking = null;
		try {
			ranking = new FFCalculatorRanking();
			ranking.generate(EFFCalculatorRanking.ALL);			
		} catch (IOException e) {
			logger.error("probleme ", e);
		} finally {
			
		}
	}
}
