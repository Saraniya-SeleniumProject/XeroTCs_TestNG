package Test_ID01;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import com.relevantcodes.extentreports.*;

@SuppressWarnings("unused")
public class Forgot_Password{
	public static ExtentReports reports ;
	public static	ExtentTest logger;
	private static ChromeDriver driver;	

@BeforeTest()

public static void reporting()
{
//String fileName = new SimpleDateFormat("'SampleTestExtentDemo_'yyyyMMddHHmm'.html'").format(new Date());
String fileName = new SimpleDateFormat("'SampleTestExtentDemo_'yyyyMMddHHmm'.html'").format(new Date());
String reportpath="C:\\Users\\senid\\Desktop\\XERO APP DOCS\\Report"+fileName;
{
reports = new ExtentReports(reportpath);

}
}

/*public static String[][] getDataInput(String filepath,String filename, String Sheetname) throws IOException
{

//Get the Xl path
File xlfile = new File(filepath+"\\"+filename);

//access to the Xl path
FileInputStream xlaccess = new FileInputStream(xlfile);

//access to workbook
HSSFWorkbook Wb = new HSSFWorkbook(xlaccess);

//Access the sheet
HSSFSheet sheet = Wb.getSheet(Sheetname);

int rowCount = sheet.getLastRowNum();
int columnCount = sheet.getRow(0).getLastCellNum();

System.out.println(rowCount);
System.out.println(columnCount);

String [][] readData = new String [rowCount][columnCount];
for(int i=0;i<=rowCount;i++)
{
for(int j=0;j <sheet.getRow(i).getLastCellNum();j++)
{
//System.out.println(sheet.getRow(i).getCell(j).getStringCellValue()+ "||");
readData[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
}
//System.out.println();
}
return readData;

}

@BeforeMethod()
public static void setForChromeDriverLaunchBrowser() throws InterruptedException
{

//set up chrome drive
System.setProperty("webdriver.chrome.driver", "C:\\Users\\senid\\Downloads\\chromedriver_win32\\chromedriver.exe");
driver = new ChromeDriver();
//maximize the window
driver.manage().window().maximize();

driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
//Launch website
driver.get("https://login.xero.com/");
Thread.sleep(5000);

} */

@AfterTest
public static void Report_close()
{
reports.endTest(logger);
reports.flush();
}

// @AfterMethod
// public static void TearDown() throws InterruptedException
// {
// Thread.sleep(3000);
// driver.quit();
//
// }

@Test
public  Forgot_Password() throws Exception

{

logger = reports.startTest("Forgot_Password");

//String data[][] = getDataInput("C:\\Users\\senid\\Desktop\\XERO APP DOCS\\TestData XL Sheets","Forgot_Password.xls","Forgot_Password");


WebElement ForgotPasswordLink = driver.findElement(By.xpath("//a[@class='forgot-password-advert']"));
ForgotPasswordLink.click();

WebElement ForgotPasswordPage = driver.findElement(By.xpath("//p[contains(text(),'To reset your password, enter the email address yo')]"));
ForgotPasswordPage.click();

WebElement EnterEmail_ForgottenPwd= driver.findElement(By.xpath("//input[@id='UserName']"));
EnterEmail_ForgottenPwd.clear();
EnterEmail_ForgottenPwd.sendKeys("charu.vasu@gmail.com");
Thread.sleep(3000);

WebElement SendLinkButton= driver.findElement(By.id("submitButton"));
SendLinkButton.click();

WebElement PleaseCheckUrMailPage = driver.findElement(By.xpath("//p[contains(text(),'A link to reset your password has been sent to:')]"));
System.out.println("Text displayed in PleaseCheckUrMailPage::: " +PleaseCheckUrMailPage.getTagName());
Thread.sleep(3000);

if (PleaseCheckUrMailPage.getTagName()=="A link to reset your password has been sent to:")
{

	logger.log(LogStatus.PASS,"test passed");
             System.out.println ("test passed");
       }
else {
logger.log(LogStatus.FAIL,"test failed");
System.out.println ("test failed"); 
}
}
}



