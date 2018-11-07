package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	public static String value;

	public static String getCellData(String sheetName, String colName, int rowNum) {

		FileInputStream fis = null;
		XSSFWorkbook workbook = null;
		XSSFRow row = null;
		XSSFSheet sheet = null;
		XSSFCell cell = null;
		int col_num = -1;

		try {

			fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/data/Testdata.xlsx");

		} catch (FileNotFoundException e) {

			System.out
					.println("Error!! TestData file Not found!! Terminating...." + "Hint: Check file path of TestData");
			System.exit(0);
		}

		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			System.out.println("Error!! Terminating....");
			e.printStackTrace();
		}

		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);

		try {
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName))
					col_num = i;
			}

			row = sheet.getRow(1);
			cell = row.getCell(col_num);

			value = cell.getStringCellValue();
			workbook.close();

		} catch (Exception e) {

			System.out.println("Empty or Null field try to erase them and try agian " + e.getMessage());

		}
		return value;

	}

}
