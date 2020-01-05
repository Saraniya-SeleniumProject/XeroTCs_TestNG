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

import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.*;




public class Incorrect_Email  {
	

	public static ExtentReports reports ;
	public static	ExtentTest logger;
	private static ChromeDriver driver;	


@BeforeTest
public static void reporting()
{
String fileName = new SimpleDateFormat("'SampleTestExtentDemo_'yyyyMMddHHmm'.html'").format(new Date());
String reportpath="C:\\Users\\senid\\Desktop\\XERO APP DOCS\\Report"+fileName;
{
reports = new ExtentReports(reportpath);

}
}

public static String[][] getDataInput(String filepath,String filename, String Sheetname) throws IOException
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
ChromeDriver driver = new ChromeDriver();
//maximize the window
driver.manage().window().maximize();

driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
//Launch website
driver.get("https://login.xero.com/");
Thread.sleep(5000);

}

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
public Incorrect_Email()throws Exception

{

logger = reports.startTest("Incorrect_Email");

String data[][] = getDataInput("C:\\Users\\senid\\Desktop\\XERO APP DOCS\\TestData XL Sheets","Incorrect_Email.xls","Incorrect_Email");

//Username textbox WebElement
WebElement username = driver.findElement(By.xpath("//input[@id='email']"));
String username_data = data[1][2];
enter_data_textbox(username,username_data,"User Name");

//password textbox WebElement
WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
password.clear();
logger.log(LogStatus.PASS,"The password field was cleared");

//Log In button WebElement
WebElement LoginButton = driver.findElement(By.xpath("//button[@id='submitButton']"));
//Calling method to click a button
LoginButton.click();
//logger.log(LogStatus.PASS,"The LogIn button was clicked");

WebElement ActualErrormessage = driver.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect')]"));
String ExpectedErrormessage ="Your email or password is incorrect";
if(ActualErrormessage.getText() == ExpectedErrormessage )
{
	logger.log(LogStatus.PASS," Correct error message was displayed");
}
else
{
	logger.log(LogStatus.FAIL," Correct error message was not displayed");
	driver.quit();
}
}
public static void enter_data_textbox(WebElement textbox,String inputData, String textbox_name)
{
if (textbox.isDisplayed()== true)
{
if (textbox.isEnabled() == true)
{
textbox.sendKeys(inputData);

if(textbox.getAttribute("value").equals(inputData))
{
logger.log(LogStatus.PASS,"'"+inputData+ "' was entered in '"+textbox_name+ "' textbox ");
System.out.println("'"+inputData+ "' was entered in '"+textbox_name+ "' textbox ");
}
else
{
logger.log(LogStatus.FAIL,"'"+inputData+ "' was not entered in '"+textbox_name+ "' textbox ") ;
System.out.println("'"+inputData+ "' was not entered in '"+textbox_name+ "' textbox ");
}
}
}
}
}

