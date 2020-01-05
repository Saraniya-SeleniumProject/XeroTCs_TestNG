package Test_ID02;

import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.Select;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SignUp_To_XDC {
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
driver.get("https://www.xero.com/us/");
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
public void SignUp_to_XDC() throws Exception

{

logger = reports.startTest("SignUp_to_XDC");

String data[][] = getDataInput("C:\\Users\\senid\\Desktop\\XERO APP DOCS\\TestData XL Sheets","SignUp_to_XDC.xls","SignUp_to_XDC");

//Free Trial button WebElement
WebElement FreeTrialButton = driver.findElement(By.xpath("//a[@class='btn btn-primary global-ceiling-bar-btn']"));
//Click on Free Trial Button
FreeTrialButton.click();
//logger.log(LogStatus.PASS,"The Free Trial button got clicked");

WebElement SignUpForm = driver.findElement(By.name("signupForm"));
if(SignUpForm.isDisplayed())
{
	//FirstName textbox WebElement
	WebElement FirstName = driver.findElement(By.xpath("//input[@name='FirstName']"));
	String FirstName_data = data[1][2];
	enter_data_textbox1(FirstName,FirstName_data,"FirstName");

	//LastName textbox WebElement
		WebElement LastName = driver.findElement(By.xpath("//input[@name='LastName']"));
		String LastName_data = data[2][2];
		enter_data_textbox2(LastName,LastName_data,"LastName");
		
	//EmailAddress textbox WebElement
		WebElement EmailAddress = driver.findElement(By.xpath("//input[@name='EmailAddress']"));
		String EmailAddress_data = data[3][2];
		enter_data_textbox3(EmailAddress,EmailAddress_data,"EmailAddress");

	//PhoneNumber textbox WebElement
		WebElement PhoneNumber = driver.findElement(By.xpath("//input[@name='PhoneNumber']"));
		String PhoneNumber_data = data[4][2];
		enter_data_textbox4(PhoneNumber,PhoneNumber_data,"PhoneNumber");
		
	//Country Select Dropdown 
		Select drpCountry = new Select (driver.findElement(By.name("LocationCode")));
		drpCountry.selectByValue("US");
		
		//Check the "I am not a Robot Checkbox"
		// "switch" the driver to iFrame to locate exactly the checkbox of reCaptcha. 

			 WebElement iFrame_NotARobotCheckbox = driver.findElement(By.xpath("//textarea[@id='g-recaptcha-response']"));
			 driver.switchTo().frame(iFrame_NotARobotCheckbox);
			 // Now can click on checkbox of reCaptcha now.

			 @SuppressWarnings("unused")
			WebElement iFrameNotARobotCheckbox_checkbox = 
			 driver.findElement(By.xpath("//body[@class='xero is-live-mode']"));
			 iFrame_NotARobotCheckbox.click();
		
			 WebElement TermsNPolicy_ChkBox = driver.findElement(By.xpath("//input[@name='TermsAccepted']"));
			 if (!TermsNPolicy_ChkBox.isSelected())
			 {
				 TermsNPolicy_ChkBox.click();
			 }
			 
			 WebElement GetStartedButton = driver.findElement(By.xpath("//span[@class='g-recaptcha-submit']"));
              GetStartedButton.click();
              
}
else { 
	driver.quit();
	}
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
public static void enter_data_textbox3(WebElement textbox,String inputData, String textbox_name)
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
public static void enter_data_textbox4(WebElement textbox,String inputData, String textbox_name)
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