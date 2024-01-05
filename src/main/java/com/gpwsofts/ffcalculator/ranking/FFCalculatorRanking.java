package com.gpwsofts.ffcalculator.ranking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

public class FFCalculatorRanking {
	private static final Logger logger = Logger.getLogger(FFCalculatorRanking.class.getName());

	public FFCalculatorRanking() {
		
	}
	
	public void generate(EFFCalculatorRanking effc) throws IOException {
		InputStream is = null;
		Scanner sc = null;
		String resourceToRead = null;
		String resourceToWrite= null;
		FileWriter fw = null;
		double[] pts = new double[10000];
		List<Double> listPts = null;
		DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();
		decimalFormatSymbols.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("#####.###",decimalFormatSymbols);
		df.setRoundingMode(RoundingMode.HALF_UP);
		
		try {
			listPts = new ArrayList<Double>();
			resourceToRead = effc.getResource();
			logger.info("chargement de la resource et ouverture d'un scanner = <" + resourceToRead + ">");
			is = getClass().getResourceAsStream(resourceToRead);
			sc = new Scanner(is);
			int oldPos = -1;
			double oldPts = 50000;
			int currentPos = 1;
			double currentPts = -1;
			logger.info("<" + resourceToRead + "> - debut du parcours des lignes de la ressource");
			while (sc.hasNextLine()){
				String line = sc.nextLine();
				logger.trace(  "traitement de la ligne <" + line + ">");
				StringTokenizer st = new StringTokenizer(line, ":");
				currentPos = Integer.parseInt(st.nextToken());
				currentPts =  Double.parseDouble(st.nextToken());
				int diffPos = currentPos - oldPos;
				double diffPts = oldPts - currentPts;
				
				double diffPtsParPos = diffPts/diffPos;
				logger.debug("  entre la position " + oldPos + " (" + oldPts + " pts) et " + currentPos   + " (" + currentPts + " pts) il y a " + diffPts + " points soit " + diffPtsParPos + " points par place" );
				double indexPts = oldPts;
				for (int i=oldPos; i<currentPos; i++){
					indexPts = indexPts - diffPtsParPos;
					if (oldPos < 1500)
					logger.trace("   alors <" + i + "> => <" + indexPts + ">");
					if (i>=0){
						pts[i]=indexPts;
						listPts.add(Double.valueOf(indexPts));
					}
				}
				oldPos = currentPos;
				oldPts = currentPts;
			}			
			logger.info("<" + resourceToRead + "> - fin du parcours des lignes de la ressource");
			logger.info("passage en reverse de la liste");	
			String arrPrint = listPts.stream()	
					.sorted(Collections.reverseOrder())
					.map(number -> df.format(number).toString())					
					.collect(Collectors.joining(","));
			logger.info("fin de passage en reverse de la liste");	
			logger.info("ecriture du fichier resultat dans dist");
			resourceToWrite = "./dist/"+effc.name();
			fw = new FileWriter(resourceToWrite);
			fw.append(arrPrint);
			fw.flush();
			fw.close();
			logger.info("fin ecriture du fichier resultat dans dist");
		} catch (IOException e) {
			logger.error("probleme lors de la generation du fichier pour <" + effc + ">" ,e);
			throw e;
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					
				}
			if (sc != null)
				sc.close();
			logger.info("8 il a " + pts[8-1]);
			logger.info("1386 il a " + pts[1386-1]);
		}
	}
}
