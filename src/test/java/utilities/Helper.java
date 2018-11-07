package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helper {
	// Method to take screenshot
	public static void captureScreenShot(WebDriver driver, String screenshotname) {
		Path dest = Paths.get("./screenshots",
				screenshotname + new SimpleDateFormat(" yyyy-MM-dd-HH-mm").format(new Date()) + ".png");
		try {
			Files.createDirectories(dest.getParent());
			FileOutputStream out = new FileOutputStream(dest.toString());
			out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			out.close();
		} catch (IOException e) {

			System.out.println("Excpetion while taking screenshot" + e.getMessage());

		}
	}

	public static void openReport() throws IOException {

		String windowsPath = new String(System.getProperty("user.dir") +"\\test-output\\html\\index.html");
		File file = new File(windowsPath);
		try {
			Desktop.getDesktop().browse(file.toURI());
			System.out.println("Report is opening in the browser");
		} catch (IOException e) {

			System.out.println("Report is not found!"+e.getMessage());
			
		}

	}
}
