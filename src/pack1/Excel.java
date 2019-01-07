package pack1;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
			Date d = new Date();
			String dateTime = d.toString().replaceAll(":","_");
			String path = "./photos/"+dateTime+".png";
			TakesScreenshot t = (TakesScreenshot)driver;
			File srcFile = t.getScreenshotAs(OutputType.FILE);
			File destFile = new File(path);
			FileUtils.copyFile(srcFile, destFile);	
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		driver.close();
	}
}
