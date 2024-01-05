package com.gpwsofts.ffcalculator.ranking;

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

public class FFCalculatorRanking {

	public FFCalculatorRanking() {
		
	}
	
	public void generate() {
		InputStream is = null;
		Scanner sc = null;
		double[] pts = new double[7016];
		List<Double> listPts = null;
		DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();
		decimalFormatSymbols.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("#####.###",decimalFormatSymbols);
		df.setRoundingMode(RoundingMode.HALF_UP);
		
		try {
			listPts = new ArrayList<Double>();
			is = getClass().getResourceAsStream("nuage-u17.csv");
			sc = new Scanner(is);
			int oldPos = -1;
			double oldPts = 50000;
			int currentPos = 1;
			double currentPts = -1;
			while (sc.hasNextLine()){
				String line = sc.nextLine();
				System.out.println("line is " + line);
				StringTokenizer st = new StringTokenizer(line, ":");
				currentPos = Integer.parseInt(st.nextToken());
				currentPts =  Double.parseDouble(st.nextToken());
				int diffPos = currentPos - oldPos;
				double diffPts = oldPts - currentPts;
				double diffPtsParPos = diffPts/diffPos;
				System.out.println("Entre la position " + oldPos + " (" + oldPts + " pts) et " + currentPos   + " (" + currentPts + " pts) il y a " + diffPts + " points soit " + diffPtsParPos + " points par place" );
				double indexPts = oldPts;
				for (int i=oldPos; i<currentPos; i++){
					indexPts = indexPts - diffPtsParPos;
					if (oldPos < 1500)
					System.out.println(" alors <" + i + "> => <" + indexPts + ">");
					if (i>=0){
						pts[i]=indexPts;
						listPts.add(Double.valueOf(indexPts));
					}
				}
				oldPos = currentPos;
				oldPts = currentPts;
			}			
						
			String arrPrint = listPts.stream()	
					.sorted(Collections.reverseOrder())
					.map(number -> df.format(number).toString())
					//.sorted(Collections.reverseOrder())
					.collect(Collectors.joining(","));
			System.out.println("arrPrint " + arrPrint);
			
			
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					
				}
			if (sc != null)
				sc.close();
			System.out.println("8 il a " + pts[8-1]);
			System.out.println("1386 il a " + pts[1386-1]);
		}
	}
}
