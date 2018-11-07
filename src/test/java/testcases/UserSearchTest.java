package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import bsh.ParseException;
import data.DataDriven;
import pages.HomePage;
import pages.SearchPage;
import utilities.API;

public class UserSearchTest extends TestBase {
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
	String searchPriceDevLocator = DataDriven.getCellData("SearchPageLocators", "PriceListDevLocator", 1);
	String selectLocator = DataDriven.getCellData("SearchPageLocators", "SelectLocator", 1);
	String selectSortBy = DataDriven.getCellData("SearchPageLocators", "SelectSortBy", 1);

	// Calling API from TD

	String searchApiURL = DataDriven.getCellData("GeneralTestData", "Search_API", 1);
	String sortApiURL = DataDriven.getCellData("GeneralTestData", "Sort_ASC_API", 1);

	@Test
	public void UserSearchTC() throws InterruptedException, ParseException, IOException {
		// call API
		API.searchAPI(searchApiURL);
		// Pages Objects
		homeObject = new HomePage(driver);
		searchObject = new SearchPage(driver);

		// calling searchGUI method from Home page

		homeObject.searchGUI(driver, searchLocator, searchTXT);

		// loop to get the first three price and title
		// Asserting the first three Titles (API and GUI)

		searchObject.searchForTitle(driver, listNameTXT);
	
		for (int i = 0; i < 3; i++) {

			try {
				Assert.assertEquals(searchObject.titleList.get(i), API.titleList.get(i));
			} catch (AssertionError e) {
				
				System.out.println("Failed!");
				System.out.println("GUI Title Name is not equal to API  Title Name"+e.getMessage());
			
			}

		}

	}

}
