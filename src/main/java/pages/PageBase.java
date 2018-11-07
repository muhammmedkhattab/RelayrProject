package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PageBase {

	protected WebDriver driver;
	// protected

	public Select select;
	public Actions action;
	public static String currentWindowID = null;

	// create constructor
	public PageBase(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	// Method to Click Buttons
	protected static void clickButton(WebElement button) {

		button.click();
	}

	// Method to send Keys
	protected static void setText(WebElement textElement, String value) {

		textElement.sendKeys(value);
	}

	public void KeyPressEnter(WebElement webElement) {

		webElement.sendKeys(Keys.RETURN);
	}

}
