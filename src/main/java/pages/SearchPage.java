package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SearchPage extends PageBase {

	// Lists
	public List<String> titleList = new ArrayList<String>();

	public SearchPage(WebDriver driver) {
		super(driver);

	}

	public void searchForTitle(WebDriver driver, String searchTitleLocator) throws InterruptedException {

		List<WebElement> titleListLocator = driver.findElements(By.xpath((searchTitleLocator)));

		for (int i = 0; i < 3; i++) {
			titleList.add(titleListLocator.get(i).getText());

		}
	}

	public void sortPriceASC(WebDriver driver, String selectlocator, String sortPrice) throws InterruptedException {

		WebElement sortDropdownList = driver.findElement(By.xpath((selectlocator)));
		select = new Select(sortDropdownList);
		select.selectByValue(sortPrice);
	}
}
