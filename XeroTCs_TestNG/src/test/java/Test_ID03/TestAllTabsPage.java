package Test_ID03;


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



public class TestAllTabsPage{
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
public TestAllTabsPage() throws Exception

{

logger = reports.startTest("TestAllTabsPage");

String data[][] = getDataInput("C:\\Users\\senid\\Desktop\\XERO APP DOCS\\TestData XL Sheets","TestAllTabsPage.xls","TestAllTabsPage");

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
	WebElement AccountingDropdownMenu = driver.findElement(By.xpath("//button[@name='navigation-menu/accounting']"));
	 WebElement rootAccount = AccountingDropdownMenu.findElement(By.linkText("Accounting"));
	 
	 Actions A=new Actions(driver);
	 A.moveToElement(rootAccount).build().perform();
	 
	 //Validating ReportsSubLink
	 WebElement ReportsSublink = AccountingDropdownMenu.findElement(By.linkText("Reports"));
	 Thread.sleep(1000);
	 ReportsSublink.click();
	 System.out.println("You are in Reports page");
	 
	//Validating ContactsMenuDropdown
		WebElement ContactsDropdownMenu = driver.findElement(By.xpath("//button[@name='navigation-menu/contacts']"));
		ContactsDropdownMenu.click();
		System.out.println("you are now in contact drop down page");
		
		//Validating usermenuDropdown and 
		WebElement UserDropdownMenu = driver.findElement(By.xpath("//span[@class='xrh-appbutton--text']"));
		 WebElement UsernameRoot = UserDropdownMenu.findElement(By.linkText("abc"));
		 
		 Actions A1=new Actions(driver);
		 A1.moveToElement(UsernameRoot).build().perform();
		 
		 //click on Settings Sub Link from UserMenuDropdown
		 WebElement SettingSublink = UserDropdownMenu.findElement(By.linkText("Settings"));
		 Thread.sleep(1000);
		 SettingSublink.click();
		 System.out.println("You are in Settings page");
		 Thread.sleep(1000);
		 UserDropdownMenu.click();//collapse the menu
		 driver.navigate().back(); //Back to dashboard page
		 
		 //click on Files Sub Link from UserMenuDropdown
		 WebElement FilesSublink = UserDropdownMenu.findElement(By.linkText("Files"));
		 Thread.sleep(1000);
		 FilesSublink.click();
		 
		 System.out.println("you are now in file page of xero");
		 Thread.sleep(1000);
		 UserDropdownMenu.click();//collapse the menu
		 driver.navigate().back(); //Back to dashboard page
		 
		//Validating + Menu Dropdown
			WebElement PlusDropdownMenu = driver.findElement(By.xpath("//button[@class='xrh-button xrh-addon--iconbutton xrh-header--iconbutton xrh-focusable--parent xrh-focusable--parent-is-active']//div[@class='xrh-focusable--child xrh-iconwrapper']"));
			PlusDropdownMenu.click();
			System.out.println("you are now in contact drop down page");
			PlusDropdownMenu.click();//collapse the menu
			
			//Validating the Notifications Icon
			WebElement NotificationIcon = driver.findElement(By.xpath("//li[3]//button[1]//div[1]"));
			NotificationIcon.click();
			WebElement CloseTheNotificationFrame= driver.findElement(By.className("o-closeIcon"));
			CloseTheNotificationFrame.click();
			
			//Validating the SearchIcon
			WebElement SearchIcon = driver.findElement(By.xpath("//li[2]//button[1]//div[1]"));
			SearchIcon.click();
			WebElement SearchTextBox = driver.findElement(By.xpath("//input[@id='queryInput']"));
			Thread.sleep(1000);
			if (SearchTextBox.isDisplayed())
			{
				System.out.println("Search Text Box displayed");
				WebElement SearchTextBoxCollapse = driver.findElement(By.xpath("//h1[@class='xui-pageheading--title']"));
				SearchTextBoxCollapse.click(); //collapse search text box
			}
			
			//Help ICON Validation
			WebElement HelpIcon = driver.findElement(By.xpath("//li[4]//button[1]//div[1]"));
			HelpIcon.click();
			Thread.sleep(2000);
			System.out.println("Help Menu Dropdown displayed");
			HelpIcon.click(); //collapse the help menu
} else {driver.quit();}
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
	