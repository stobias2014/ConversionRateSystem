package com.fdmgroup.conversionratesystem;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Performs currency conversions.
 * @author ST,JQ,TJG,TS
 * 
 */
public class CurrencyConversion {
	
	private static Logger log = LogManager.getLogger("com.fdmgroup.conversionratesystem.currencyconversion");
	
	CurrencyRateParser currencyRateParser;
	HashMap<String, BigDecimal> currencyRates;
	BigDecimal currencyExchangeRate;
	MathContext mc = new MathContext(6); // 8 precision
	
	public CurrencyConversion(CurrencyRateParser currencyRateParser) {
		this.currencyRateParser = currencyRateParser;
	}
	/**
	 * Converts a currency to Euros.
	 * @param initAmount: the amount of money to be converted into Euros
	 * @param currencyID: the 3 letter currency ID of the initAmount.
	 * @return the equivalent amount of Euros based on initAmount and currencyID
	 */
	public BigDecimal convertCurrencyToEuro(BigDecimal initAmount, String currencyID) {
		log.trace("convertCurrencyToEuro method with arguments " + initAmount + " and " + currencyID);
		currencyRates = currencyRateParser.getCurrencyRates();
		currencyExchangeRate = currencyRates.get(currencyID);
		BigDecimal euroAmount = initAmount.divide(currencyExchangeRate,mc);
		
		return euroAmount;
	}
	/**
	 * Converts Euros to another currency.
	 * @param initAmount: amount of Euros to change
	 * @param currencyID: what to change the Euros into
	 * @return equivalent amount of target currency given the initAmount in Euros
	 */
	public BigDecimal convertFromEuroToCurrency(BigDecimal initAmount, String currencyID) {
		log.trace("convertFromEuroToCurrency method with arguments " + initAmount + " and " + currencyID);
		currencyRates = currencyRateParser.getCurrencyRates();
		currencyExchangeRate = currencyRates.get(currencyID);
		BigDecimal currencyAmount = initAmount.multiply(currencyExchangeRate, mc);
		
		return currencyAmount;
	}
}