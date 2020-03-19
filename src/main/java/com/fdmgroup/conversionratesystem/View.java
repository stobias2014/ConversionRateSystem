package com.fdmgroup.conversionratesystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
/**
 * Displays the results of either the conversion of Euro to a currency or a currency to Euro.
 * @author ST,JQ,TJG,TS
 * 
 */
public class View {
	
	private static Logger log = LogManager.getLogger("com.fdmgroup.conversionratesystem.view");
/**
 * Display results of conversion INTO Euros
 * @param initialAmount: amount of starting currency
 * @param convertedCurrencyAmount: equivalent amount of Euros
 * @param currencyId: ID of currency that was converted to Euro
 */
	public void printResultOfCurrencyToEuro(BigDecimal initialAmount, BigDecimal convertedCurrencyAmount, String currencyId) {
		log.trace("printResultOfCurrencyToEuro method with arguments " + initialAmount + ", " + convertedCurrencyAmount 
				+ ", " + currencyId);
		System.out.println("\n" + initialAmount + " " + currencyId + " = " + convertedCurrencyAmount + " euros");		
	}
/**
 * Display results of conversion FROM Euro
 * @param initialAmount: amount of Euros to convert
 * @param convertedCurrencyAmount: equivalent amount of target currency
 * @param currencyId: ID of currency that Euro was converted to
 */
	public void printResultOfEuroToCurrency(BigDecimal initialAmount, BigDecimal convertedCurrencyAmount, String currencyId) {
		log.trace("printResultOfEuroToCurrency method with arguments " + initialAmount + ", " + convertedCurrencyAmount 
				+ ", " + currencyId);
		System.out.println("\n" + initialAmount + " euro " + " = " + convertedCurrencyAmount + " " + currencyId);
	}
}
