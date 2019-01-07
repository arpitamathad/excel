package pack1;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Excel {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		try {
			Workbook w = WorkbookFactory.create(new FileInputStream("./data/links.xlsx"));
			String link;
			for(int i=0 ; i<=2 ;i++) {
			link = w.getSheet("sheet1").getRow(i).getCell(0).toString();
			driver.get(link);
			String actTitle = driver.getTitle();
			System.out.println(actTitle);
			String expTitle = w.getSheet("sheet1").getRow(i).getCell(1).toString();
			Assert.assertEquals(actTitle, expTitle);
			System.out.println(actTitle.equals(expTitle));
			Thread.sleep(2000);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
