package com.fdmgroup.conversionratesystem;

import org.apache.commons.lang3.*;
import org.apache.commons.lang3.math.*;
import java.math.BigDecimal;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Control the interactions between the various classes
 * @author ST,JQ,TJG,TS
 * 
 */
public class CurrencyController {
	
	private static Logger log = LogManager.getLogger("com.fdmgroup.conversionratesystem.currencycontroller");

	private int userOption;
	private String currencyId;
	private BigDecimal initCurrencyAmount;
	private BigDecimal convertedCurrencyAmount;
	private CurrencyConversion currencyConversion;
	private View viewOfConversion = new View();
	private CurrencyRateParser currencyRateParser;
	
	public CurrencyController(CurrencyRateParser currencyRateParser) {
		this.currencyRateParser = currencyRateParser;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public int getUserOption() {
		return userOption;
	}

	public void setUserOption(int userOption) {
		this.userOption = userOption;
	}
	
	public BigDecimal getInitCurrencyAmount() {
		return initCurrencyAmount;
	}

	public void setInitCurrencyAmount(BigDecimal initCurrencyAmount) {
		this.initCurrencyAmount = initCurrencyAmount;
	}
/**
 * Get user inputs and call the corresponding conversion method.
 */
	public int getUserInfo() {
		int userOption = 0;
		log.trace("getUserInfo method in process ");
		
		Scanner myScanner = new Scanner(System.in);
		currencyRateParser = new CurrencyRateParser();
		currencyRateParser.parseCurrencyInfo();
		log.trace("parseCurrencyInfo method executed ");
		currencyConversion = new CurrencyConversion(currencyRateParser);		
		

		System.out.println("Enter 1 or 2 to choose your option");

//		userOption = myScanner.nextInt();
//		while (!myScanner.hasNext("[123]")) {

		while (!myScanner.hasNext("[123]")) {

			System.out.println("Invalid input, please enter either 1 or 2");
			log.info("Invalid option input");
			log.info("Please enter either 1 or 2");
			
			myScanner.next();
		}

		userOption = myScanner.nextInt();
		
		System.out.println("Enter a valid currency ID for conversion");
		currencyId = myScanner.next().toUpperCase();
		while(!currencyRateParser.getCurrencyRates().containsKey(currencyId)) {
			System.out.println("Invalid input, please enter a valid currency ID");
			log.info("Invalid currency ID input");
			log.info("Please enter a valid currency ID");
			currencyId = myScanner.next().toUpperCase();
		}
		
		System.out.println("Enter currency amount.");
//		initCurrencyAmount = myScanner.nextBigDecimal();
		while (!myScanner.hasNext("[0-9]")) {
			System.out.println("Invalid input, please enter numeric value");
			log.info("Invalid option input");
			log.info("Please enter numeric value");
			
			myScanner.next();
		}
		
		initCurrencyAmount = myScanner.nextBigDecimal();

		if (userOption == 1) {
			convertedCurrencyAmount = currencyConversion.convertFromEuroToCurrency(initCurrencyAmount, currencyId);
			log.trace("convertFromEuroToCurrency method returned value " + convertedCurrencyAmount);
			viewOfConversion.printResultOfEuroToCurrency(initCurrencyAmount, convertedCurrencyAmount, currencyId);
			log.trace("printResultOfEuroToCurrency method executed");
		} else if (userOption == 2) {
			convertedCurrencyAmount = currencyConversion.convertCurrencyToEuro(initCurrencyAmount, currencyId);
			log.trace("convertCurrencyToEuro method returned value " + convertedCurrencyAmount);
			viewOfConversion.printResultOfCurrencyToEuro(initCurrencyAmount, convertedCurrencyAmount, currencyId);
			log.trace("printResultOfCurrencyToEuro method executed");
		} else if (userOption == 3) {
			System.out.println("Exiting menu");
		}
		else {
			System.out.println("Invalid option.");
		}
		
		return userOption;
	}
}
