package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage extends PageBase {


	public HomePage(WebDriver driver) {

		super(driver);
		

	}
	// Search Method
	public void searchGUI(WebDriver driver,String searchLocator, String searchText) {
		WebElement searchTextElement = driver.findElement(By.id((searchLocator)));
		searchTextElement.clear();
		clickButton(searchTextElement);
		setText(searchTextElement, searchText);
		KeyPressEnter(searchTextElement);
	}



}
