package com.fdmgroup.conversionratesystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

import java.math.MathContext;
import java.util.HashMap;
import java.io.*;
import java.util.*;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
/**
 * Parse an XML file of exchange rates and populate a HashMap with these values
 * @author ST,JQ,TJG,TS
 * 
 */
public class CurrencyRateParser {
	
	private static Logger log = LogManager.getLogger("com.fdmgroup.conversionratesystem.currencyrateparser");
	
	private HashMap<String, BigDecimal> currencyRates;
	
	public HashMap<String, BigDecimal> getCurrencyRates() {
		return currencyRates;
	}

	public void setCurrencyRates(HashMap<String, BigDecimal> currencyRates) {
		this.currencyRates = currencyRates;
	}

	/**
	 * Parses XML file containing currency exchange rates
	 * 
	 */
	public void parseCurrencyInfo() {
		currencyRates = new HashMap<>();
		
		log.trace("parseCurrencyInfo method ");
		
		try {
			String inputFileName = "currencyInfo.txt";
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFileName);
			Element rootElement = document.getRootElement();
			Element cubeNode = rootElement.getChildren().get(2);
			Element cubeTimeNode = cubeNode.getChildren().get(0);
			List<Element> cubeTimeNodeChildren = cubeTimeNode.getChildren();

			for (int temp = 0; temp < cubeTimeNodeChildren.size(); temp++) {
				MathContext mathContext = new MathContext(6);
				Element currencyNode = cubeTimeNodeChildren.get(temp);
				String nodeCurrencySymbol = currencyNode.getAttributeValue("currency");
				String nodeCurrencyValue = currencyNode.getAttributeValue("rate");
				BigDecimal currencyValueBigDecimal = new BigDecimal(nodeCurrencyValue).round(mathContext);

				currencyRates.put(nodeCurrencySymbol, currencyValueBigDecimal);
			}
			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} 
	}
	
}


