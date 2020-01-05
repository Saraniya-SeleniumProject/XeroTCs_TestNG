package Test_ID02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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


@SuppressWarnings("unused")
public class SignUpToXDC_AccountantOrBookKeeperLink {
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

/*public static String[][] getDataInput(String filepath,String filename, String Sheetname) 
		throws IOException
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

}*/

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

//@AfterMethod
//public static void TearDown() throws InterruptedException
//{
//Thread.sleep(3000);
//driver.quit();
//
//}

@Test
public  SignUpToXDC_AccountantOrBookKeeperLink() throws Exception

{

logger = reports.startTest("SignUpToXDC_FullOfferDetailsLink");

//String data[][] = getDataInput("C:\\Users\\senid\\Desktop\\XERO APP DOCS\\TestData XL Sheets","SignUpToXDC_FullOfferDetailsLink.xls","SignUpToXDC_FullOfferDetailsLink");

//Free Trial button WebElement
WebElement FreeTrialButton = driver.findElement(By.xpath("//a[@class='btn btn-primary global-ceiling-bar-btn']"));
//Click on Free Trial Button
FreeTrialButton.click();
//logger.log(LogStatus.PASS,"The Free Trial button got clicked");

WebElement SignUpForm = driver.findElement(By.name("signupForm"));
if(SignUpForm.isDisplayed())
{
Thread.sleep(3000);
driver.findElement(By.linkText("accountant or bookkeeper")).click();					
System.out.println("title of page is: " + driver.getTitle());
driver.close();
}else { driver.quit();}
}
}