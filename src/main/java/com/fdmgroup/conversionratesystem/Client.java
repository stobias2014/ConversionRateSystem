package com.fdmgroup.conversionratesystem;


/**
 * @author ST,JQ,TJG,TS
 *
 */
public class Client {
	
	public static void main(String[] args) {
		
		boolean flag = true;
		
		CurrencyRateParser currencyRateParser = new CurrencyRateParser();
		CurrencyController currencyController = new CurrencyController(currencyRateParser);
		CurrencyMenu menu = new CurrencyMenu(currencyController);
		
		while (flag == true) {
		flag = menu.displayMenu();	
		}
	}
}
