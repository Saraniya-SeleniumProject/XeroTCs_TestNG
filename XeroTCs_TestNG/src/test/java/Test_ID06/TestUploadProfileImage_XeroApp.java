package Test_ID06;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import com.relevantcodes.extentreports.*;



public class TestUploadProfileImage_XeroApp{
	public static ExtentReports reports ;
	public static	ExtentTest logger;
	private static ChromeDriver driver;	

	@BeforeTest

public static void reporting()
{
//String fileName = new SimpleDateFormat("'SampleTestExtentDemo_'yyyyMMddHHmm'.html'").format(new Date());
String fileName = new SimpleDateFormat("'SampleTestExtentDemo_'yyyyMMddHHmm'.html'").format(new Date());
String reportpath="C:\\Users\\senid\\Desktop\\XERO APP DOCS\\Report"+fileName;
reports = new ExtentReports(reportpath);
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
driver = new ChromeDriver();
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
public TestUploadProfileImage_XeroApp() throws Exception

{

logger = reports.startTest("TestUploadProfileImage_XeroApp");

String data[][] = getDataInput("C:\\Users\\senid\\Desktop\\XERO APP DOCS\\TestData XL Sheets","TestUploadProfileImage_XeroApp.xls","TestUploadProfileImage_XeroApp");

//Username textbox WebElement
WebElement username = driver.findElement(By.xpath("//input[@id='email']"));
String username_data = data[1][2];
enter_data_textbox1(username,username_data,"Username");

//password textbox WebElement
WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
String password_data = data[2][2];
enter_data_textbox2(password,password_data,"Password");

//Log In button WebElement
WebElement LoginButton = driver.findElement(By.xpath("//button[@id='submitButton']"));
//Click on Login Button
LoginButton.click();
//logger.log(LogStatus.PASS,"The LogIn button was clicked");

WebElement DashboardPage = driver.findElement(By.xpath("//div[@class='xdash-Dashboard__table___vFe0J']"));
if (DashboardPage.isDisplayed())
{
	System.out.println("You are in Dashboard page");
	//Validating AccountsMenuDropdown
		WebElement UserDropdownMenu = driver.findElement(By.xpath("//abbr[@class='xrh-avatar xrh-avatar-color-4']"));
		 WebElement rootUser = UserDropdownMenu.findElement(By.linkText("SS"));
		 
		 Actions A=new Actions(driver);
		 A.moveToElement(rootUser).build().perform();
		 
		 //Validating ReportsSubLink
		 WebElement EditProfileSublink = UserDropdownMenu.findElement(By.linkText("Edit Profile"));
		 Thread.sleep(1000);
		 EditProfileSublink.click();
		 
		 WebElement ProfileSettingsPage = driver.findElement(By.xpath("//div[@class='x-partner-menu']"));
		 if (ProfileSettingsPage.isDisplayed())
		 {
			 WebElement UploadImageButton = driver.findElement(By.xpath("//span[@id='button-1041-btnInnerEl']"));
			 UploadImageButton.click();
			 //upload file from system
			WebElement BrowseFileFromComputer =driver.findElement(By.cssSelector("#filefield-1174-button-fileInputEl"));
			  BrowseFileFromComputer.sendKeys("C:\\Users\\senid\\Desktop\\Framework - Overview Diagram & Types.png");
			  //click on upload button
			  driver.findElement(By.xpath("//div[@id='button-1178-btnWrap']")).click();
			     Thread.sleep(5000);
			    WebElement UploadedImage = driver.findElement(By.xpath("//img[@class='your-logo']"));
			    if (UploadedImage.isDisplayed())
			    {
			     logger.log(LogStatus.PASS,"Test passed");
			     Thread.sleep(1000);
			     WebElement SaveChangesButton = driver.findElement(By.xpath("//div[@id='button-1164']"));
			     SaveChangesButton.click(); //Changes saved
			     driver.navigate().back();
			     driver.quit();
			}
			}
			 
		 }else {driver.quit();}
	
}

public static void enter_data_textbox1(WebElement textbox,String inputData, String textbox_name)
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

public static void enter_data_textbox2(WebElement textbox,String inputData, String textbox_name)
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
	
	