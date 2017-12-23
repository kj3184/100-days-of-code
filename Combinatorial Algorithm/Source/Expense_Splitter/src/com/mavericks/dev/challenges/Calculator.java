/**
 * 
 */
package com.mavericks.dev.challenges;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

/**
 * @author kunal jadhav
 *
 */
public class Calculator {

	final static Logger logger = Logger.getLogger(Calculator.class);

	static double totalexpense = 0.0;

	public static void main(String[] args) {
		
		logger.info(" ClassName:Calculator " + "###################" + " Begin execution ");
		String fileName1 = "inputs\\friends.txt";
		String fileName2 = "inputs\\expenses.txt";
		HashMap<String, Double> inputMap = new HashMap<String, Double>();
		readFriendsFile(inputMap, fileName1);
		readExpenseFile(inputMap, fileName2);
		calculateExpense(inputMap);
		logger.info(" ClassName:Calculator " + "###################" + " End here ");
	}

	/*
	 * Method name :readFriendsFile() Author: Kunal 
	 * This reads first input file ie group details
	 */

	private static void readFriendsFile(HashMap<String, Double> inputMap, String fileName) {
		logger.info(" ClassName:Calculator " + "###################" + " Method:readFriendsFile ");
		for (Object str : readFile(fileName)) {
			logger.debug("Detail" + str);
			inputMap.put((String) str, 0.0);

		}
	}
	
	/*
	 * Method name :readFriendsFile() Author: Kunal 
	 * This method reads second input file ie expense details
	 */

	private static void readExpenseFile(HashMap<String, Double> inputMap, String fileName) {

		logger.info(" ClassName:Calculator " + "###################" + " Method:readExpenseFile ");

		for (Object str : readFile(fileName)) {
			logger.debug("Detail" + str);
			int firstSpace = ((String) str).indexOf(" ");
			int specialChar = ((String) str).indexOf("$");
			if (specialChar > -1) {
				String strTmp = ((String) str).substring(specialChar);
				double d1 = inputMap.get(((String) str).substring(0, firstSpace))
						+ Double.parseDouble(strTmp.substring(1, strTmp.indexOf(" ")));
				DecimalFormat df = new DecimalFormat("#.##");
				totalexpense = totalexpense + Double.parseDouble(strTmp.substring(1, strTmp.indexOf(" ")));
				inputMap.put(((String) str).substring(0, firstSpace), Double.parseDouble(df.format(d1)));
			} else {
				logger.info("Invalid Input line");
			}

		}

	}

	
	/*
	 * Method name :readFile() Author: Kunal 
	 * It is common utility function reo read txt file
	 */
	
	private static Object[] readFile(String fileName) {
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			return stream.toArray();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/*
	 * Method name :calculateExpense() Author: Kunal 
	 * This method calculates expense using avg sum and substraction formula 
	 */
	
	private static void calculateExpense(HashMap<String, Double> inputMap) {

		logger.info(" ClassName:Calculator " + "###################" + " Method:calculateExpense ");
		double expensePerHead = totalexpense / inputMap.size();
		DecimalFormat df = new DecimalFormat("#.##");
		Iterator<Entry<String, Double>> iterator = inputMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Double> entry = iterator.next();

			inputMap.put(entry.getKey(), Double.parseDouble(df.format(entry.getValue() - expensePerHead)));
		}

		Object[] tmpvalArr = inputMap.values().toArray();
		Arrays.sort(tmpvalArr);
		print(inputMap, tmpvalArr, 0, 0, "");
	}

	private static void print(HashMap<String, Double> inputmap, Object[] tmpvalArr, int i, int j, String payer) {
		int arrlength = tmpvalArr.length;

		if (i < tmpvalArr.length - 1 && j < tmpvalArr.length - 1) {

			if (payer.trim().length() == 0) {
				payer = getPayerName(inputmap, (double) tmpvalArr[i]);
			}

			if ((double) tmpvalArr[i] < 0.0) {
				if ((double) tmpvalArr[i] * -1 > (double) tmpvalArr[arrlength - 1 - j]) {
					DecimalFormat df = new DecimalFormat("#.##");
					tmpvalArr[i] = Double.parseDouble(
							df.format(((double) tmpvalArr[i] * -1) - (double) tmpvalArr[tmpvalArr.length - 1 - j]));
					System.out.println(payer + " pays $" + tmpvalArr[arrlength - 1 - j] + " to "
							+ getPayerName(inputmap, (double) tmpvalArr[arrlength - 1 - j]));
					print(inputmap, tmpvalArr, i, j + 1, payer);
				} else {
					DecimalFormat df = new DecimalFormat("#.##");
					tmpvalArr[i] = Double.parseDouble(
							df.format((double) tmpvalArr[tmpvalArr.length - 1 - j] - (double) tmpvalArr[i]));
					System.out.println(payer + " pays $" + (double) tmpvalArr[i] + " to "
							+ getPayerName(inputmap, (double) tmpvalArr[arrlength - 1 - j]));
					print(inputmap, tmpvalArr, i + 1, j, payer);
				}
			} else {
				if ((double) tmpvalArr[i] > (double) tmpvalArr[arrlength - 1 - j]) {
					DecimalFormat df = new DecimalFormat("#.##");
					tmpvalArr[i] = Double.parseDouble(
							df.format(((double) tmpvalArr[i]) - (double) tmpvalArr[tmpvalArr.length - 1 - j]));
					System.out.println(payer + " pays $" + tmpvalArr[arrlength - 1 - j] + " to "
							+ getPayerName(inputmap, (double) tmpvalArr[arrlength - 1 - j]));
					print(inputmap, tmpvalArr, i, j + 1, payer);
				} else {
					DecimalFormat df = new DecimalFormat("#.##");
					tmpvalArr[i] = Double.parseDouble(
							df.format((double) tmpvalArr[tmpvalArr.length - 1 - j] - (double) tmpvalArr[i]));
					System.out.println(payer + " pays $" + (double) tmpvalArr[i] + " to "
							+ getPayerName(inputmap, (double) tmpvalArr[arrlength - 1 - j]));
					print(inputmap, tmpvalArr, i + 1, j, payer);
				}
			}

		}

	}

	private static String getPayerName(HashMap<String, Double> inputmap, double tmpval) {

		Iterator<Entry<String, Double>> iterator2 = inputmap.entrySet().iterator();
		while (iterator2.hasNext()) {
			Entry<String, Double> entry = iterator2.next();
			if (entry.getValue() == tmpval) {
				return entry.getKey();
			}
		}

		return "";
	}

}
