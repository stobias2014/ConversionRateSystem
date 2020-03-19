package com.fdmgroup.conversionratesystem;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import org.junit.Before;
import org.junit.Test;

public class TestConversionRateSystem {
	
	CurrencyController currencyController;
	CurrencyRateParser currencyRateParser;
	MathContext mathContext;
	CurrencyConversion cc;
	CurrencyRateParser mockCurrencyRateParser;
	CurrencyConversion currencyConversion;
	

	@Before
	public void setUp() throws Exception {
		currencyRateParser = new CurrencyRateParser();
		currencyController = new CurrencyController(currencyRateParser);
		mathContext = new MathContext(5, RoundingMode.HALF_UP);
		currencyConversion = new CurrencyConversion(currencyRateParser); 
	}
	
	@Test
	public void test_CurrencyController_WhenGetUSerInfoCalled_UserEntersOption1_ReturnOption1() {
		
		currencyController.getUserInfo();
		
		assertEquals(1, currencyController.getUserOption());
	}
	
	@Test
	public void test_CurrencyController_WhenGetUSerInfoCalled_UserEntersOption2_ReturnOption2() {
		
		currencyController.getUserInfo();
		
		assertEquals(2, currencyController.getUserOption());
	}
	
	@Test
	public void test_CurrencyController_WhenGetUSerInfoCalled_UserEntersCurrencyIdUSD_ReturnsUSD() {
		
		currencyController.getUserInfo();
		
		assertEquals("USD", currencyController.getCurrencyId());
	}
	
	@Test
	public void test_CurrencyController_WhenGetUSerInfoCalled_UserEntersBigDecimalAmount5dot5_ReturnsBigDecimalAmount5dot5() {
		
		currencyController.getUserInfo();
		
		assertEquals(new BigDecimal(5.5), currencyController.getInitCurrencyAmount());
	}
	
	
	
	@Test
	public void test_CurrencyRateParser_ParseCurrencyInfo_WhenGetCurrencyIdUSDReturns1dot1223() {
		currencyRateParser.parseCurrencyInfo();
		
		assertEquals(new BigDecimal(1.1223).round(mathContext), currencyRateParser.getCurrencyRates().get("USD"));
	}
	
	@Test
	public void test_CurrencyRateParser_ParseCurrencyInfo_WhenGetCurrencyIdJPYReturns121dot13() {
		currencyRateParser.parseCurrencyInfo();
		
		assertEquals(new BigDecimal(121.13).round(mathContext), currencyRateParser.getCurrencyRates().get("JPY"));
	}
	
	@Test
	public void test_CurrencyRateParser_ParseCurrencyInfo_WhenGetCurrencyIdBGNReturns1dot9558() {
		currencyRateParser.parseCurrencyInfo();
		
		assertEquals(new BigDecimal(1.9558).round(mathContext), currencyRateParser.getCurrencyRates().get("BGN"));
	}
	
	@Test
	public void test_CurrencyRateParser_ParseCurrencyInfo_WhenGetCurrencyIdCZKReturns25dot575() {
		currencyRateParser.parseCurrencyInfo();
		
		assertEquals(new BigDecimal(25.575).round(mathContext), currencyRateParser.getCurrencyRates().get("CZK"));
	}
	
	@Test
	public void test_CurrencyRateParser_ParseCurrencyInfo_WhenGetCurrencyIdCZKReturns7dot4678() {
		currencyRateParser.parseCurrencyInfo();
		
		assertEquals(new BigDecimal(7.4678).round(mathContext), currencyRateParser.getCurrencyRates().get("DKK"));
	}
	
	@Test
	public void test_CurrencyRateParser_ParseCurrencyInfo_WhenGetCurrencyIdGBPReturns7dot4678() {
		currencyRateParser.parseCurrencyInfo();
		
		assertEquals(new BigDecimal(0.90260).round(mathContext), currencyRateParser.getCurrencyRates().get("GBP"));
	}
	
	@Test
	public void test_CurrencyConversion_WhenConvertFrom1EuroToUSD_GetConvertedValue1dot1223() {
		currencyRateParser.parseCurrencyInfo();
		currencyConversion = new CurrencyConversion(currencyRateParser);
		
		assertEquals(new BigDecimal(1.1223).round(mathContext), currencyConversion.convertFromEuroToCurrency(new BigDecimal(1), "USD"));
	}
	
	@Test
	public void test_CurrencyConversion_WhenConvertFrom1USDToEuro_GetConvertedValue1dot1223() {
		currencyRateParser.parseCurrencyInfo();
		currencyConversion = new CurrencyConversion(currencyRateParser);
		
		assertEquals(new BigDecimal(1).setScale(5), currencyConversion.convertCurrencyToEuro(new BigDecimal(1.1223), "USD"));
	}
	
}
