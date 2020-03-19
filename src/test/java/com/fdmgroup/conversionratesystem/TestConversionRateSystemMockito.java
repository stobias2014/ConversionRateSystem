package com.fdmgroup.conversionratesystem;

import static org.junit.Assert.*;

import org.mockito.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class TestConversionRateSystemMockito {
	CurrencyMenu currencyMenu;
	CurrencyController mockCurrencyController;
	CurrencyController currencyController;
	CurrencyRateParser mockCurrencyRateParser;
	CurrencyConversion mockCurrencyConversion;
	View mockView;

	@Before
	public void setUp() throws Exception {
		mockCurrencyController = mock(CurrencyController.class);
		currencyMenu = new CurrencyMenu(mockCurrencyController);
	}

	@Test
	public void test_CurrencyMenu_WhenDisplayMenuCalled_GetUserInfoCalledOnce() {
		currencyMenu.displayMenu();
		
		verify(mockCurrencyController).getUserInfo();
	}

}
