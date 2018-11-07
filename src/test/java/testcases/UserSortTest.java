package testcases;

import java.io.IOException;
import org.testng.annotations.Test;
import bsh.ParseException;
import data.DataDriven;
import pages.HomePage;
import pages.SearchPage;


public class UserSortTest extends TestBase {
	// Objects from classes{searchPage and HomePage}
	SearchPage searchObject;
	HomePage homeObject;

	// Calling Testdata URL
	String homePageURL = DataDriven.getCellData("GeneralTestData", "HomePageProdURL", 1);

	// Calling Testdata from Home page Locators
	String searchTXT = DataDriven.getCellData("HomePageLocators", "SearchText", 1);
	String searchLocator = DataDriven.getCellData("HomePageLocators", "HomeSearchLocator", 1);

	// Calling Testdata from Search page Locators
	String listNameTXT = DataDriven.getCellData("SearchPageLocators", "TextNameListLocator", 1);
	String listPriceTXT = DataDriven.getCellData("SearchPageLocators", "PriceListLocator", 1);
	String selectLocator = DataDriven.getCellData("SearchPageLocators", "SelectLocator", 1);
	String selectSortBy = DataDriven.getCellData("SearchPageLocators", "SelectSortBy", 1);
	String searchPriceDevLocator = DataDriven.getCellData("SearchPageLocators", "PriceListDevLocator", 1);

	@Test
	public void UserSearchSortbyTC() throws InterruptedException, ParseException, IOException {
	
		
		// Pages Objects
		homeObject = new HomePage(driver);
		searchObject = new SearchPage(driver);
		
		// calling searchGUI method from Home,Search pages
		// search in the GUI
		homeObject.searchGUI(driver, searchLocator, searchTXT);

		// Sort in the GUI
		searchObject.sortPriceASC(driver, selectLocator, selectSortBy);

	}
}
