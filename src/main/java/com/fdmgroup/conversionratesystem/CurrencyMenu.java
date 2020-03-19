package com.fdmgroup.conversionratesystem;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Display a menu with the options to convert from Euro currency to a currency and convert a currency to Euro currency.
 * @author ST,JQ,TJG,TS
 * 
 */
public class CurrencyMenu {
	
	private static Logger log = LogManager.getLogger("com.fdmgroup.conversionratesystem.currencymenu");
	
	CurrencyController currencyController;
	
	public CurrencyMenu() {
		
	}
	
	public CurrencyMenu(CurrencyController currencyController) {
		this.currencyController = currencyController;
	}

	/**
	 * Displays menu and calls CurrencyController to handle to user input.
	 */
	public boolean displayMenu() {
		log.trace("displayMenu method in process");
		
		boolean menuFlag = true;
		
		System.out.println("---------- Currency Conversion System ----------\n");		
		System.out.println("Menu Options\n");
		System.out.println("1. Convert Euro to another currency");
		System.out.println("2. Convert a currency to Euro");
		System.out.println("3. Exit menu\n");
		System.out.println("------------------------------------------------\n");
		
		int userOption = currencyController.getUserInfo();
		if (userOption == 3) {
			menuFlag = false;
		}
		
		log.trace("getUserInfo method executed ");
		return menuFlag;
	}
}
