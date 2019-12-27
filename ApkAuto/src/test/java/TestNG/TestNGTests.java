package TestNG;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestNGTests 
{
	public AndroidDriver<?> dr;
	public URL url;
	public DesiredCapabilities dc;
	public ExtentReports er;
	public ExtentTest et;
	public WebDriverWait w;
	public Methods m;
	
  @Test (enabled = true)
  public void test1() 
  {
	  w = new WebDriverWait(dr, 50);

	  //Scenarion-1: Test to confirm an employee can't be added without any part of the form incomplete and confirm the error messages
	  MobileElement add_emp_btn = (MobileElement) dr.findElement(By.id("com.aaks.qaautomation:id/fab"));
	  w.until(ExpectedConditions.visibilityOf(add_emp_btn));
	  
	  add_emp_btn.click();
	  
	  MobileElement create_btn = (MobileElement) dr.findElement(By.id("com.aaks.qaautomation:id/createButton"));
	  w.until(ExpectedConditions.visibilityOf(create_btn));
	  
	  MobileElement first_name_txt = (MobileElement) dr.findElement(By.id("com.aaks.qaautomation:id/firstNameEditText"));
	  first_name_txt.sendKeys("John");
	  
	  create_btn.click();
	  try
	  {
		  if(dr.findElement(By.id("com.aaks.qaautomation:id/validationLastNameTextView")).isDisplayed() 
				  || dr.findElement(By.id("com.aaks.qaautomation:id/validationTitleTextView")).isDisplayed()
				  || dr.findElement(By.id("com.aaks.qaautomation:id/validationProjectTextView")).isDisplayed()
			  || dr.findElement(By.id("com.aaks.qaautomation:id/validationFirstNameTextView")).isDisplayed())
				  {
					  et.log(LogStatus.PASS, "Error Messsage Checking test is Passed");
				  }
		  else
		  {
			  et.log(LogStatus.FAIL, "Error Messsage Checking test is Failed");
		  }
	  }
	  catch(Exception e)
	  {
		  et.log(LogStatus.ERROR, "Error @ Error Message validation test" + e.getMessage());
	  }
	  
  }
  
  @Test (enabled = false)
  public void test2() 
  {
	  w = new WebDriverWait(dr, 50);
	  //Scenarion-2: Test to delete an employee and confirm it's been removed
	  w.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.aaks.qaautomation:id/fab")));
	  String emp_name = m.addEmployee(dr);
	  
	  try
	  {
		  MobileElement added_emp = (MobileElement) dr.findElement(By.id("com.aaks.qaautomation:id/fullNameTextView"));
		  if(added_emp.isDisplayed())
		  {
			  et.log(LogStatus.PASS, "Employee added successfully");
		  }
		  else
		  {
			  et.log(LogStatus.FAIL, "Employee failed to add");
		  }
		  
		  added_emp.click();
		  
		  m.delEmployee(dr);
		  
		  if(added_emp.isDisplayed())
		  {
			  et.log(LogStatus.PASS, "Employee Deleted successfully");
		  }
		  else
		  {
			  et.log(LogStatus.FAIL, "Employee deletion failed");
		  }
	  }
	  catch(Exception e)
	  {
		  et.log(LogStatus.ERROR, "Error @ Deleting employee" + e.getMessage());
	  }
  }
  
  @Test (enabled = false)
  public void test3() 
  {
	  w = new WebDriverWait(dr, 50);
	  //Scenarion-3: Test to confirm an advertisement appears after every two employees are added
	  try 
	  {
		  w.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.aaks.qaautomation:id/fab")));
		  String emp_name = m.addEmployee(dr);
		  m.addEmployee(dr);
		  
		  MobileElement ad_view = (MobileElement) dr.findElement(By.id("com.aaks.qaautomation:id/adView"));
		  
		  if(ad_view.isDisplayed())
		  {
			  et.log(LogStatus.PASS, "Advertisement is showing after 2 employees");
		  }
		  else
		  {
			  et.log(LogStatus.FAIL, "Advertisement is not showing");
		  }
		  
	  }
	  catch(Exception e)
	  {
		  et.log(LogStatus.ERROR, "Error @ Advertisement Test");
	  }
	  
  }
  
  @Test (enabled = false)
  public void test4()
  {
	  w = new WebDriverWait(dr, 50);
	  //Scenarion-4: Test with a loop that adds 9 users. Then delete users 1, 2, "10" (in this order), and then dismiss all of the ads
	  try
	  {
		  w.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.aaks.qaautomation:id/fab")));
		  
		  for(int i =0 ; i<9;i++)
		  {
			  m.addEmployee(dr);
		  }
		  
		  ArrayList<MobileElement> added_emp_list = (ArrayList<MobileElement>) dr.findElements(By.id("com.aaks.qaautomation:id/fullNameTextView"));
		  //Deleting first & second employee
		  try 
		  {
			  added_emp_list.get(0).click();
			  m.delEmployee(dr); 
			  et.log(LogStatus.PASS, "1st Contact deletion Successful");
		  }
		  catch(Exception ex)
		  {
			  et.log(LogStatus.FAIL, "Contact deletion failed");
		  } 
		  try 
		  {
			  added_emp_list.get(1).click();
			  m.delEmployee(dr); 
			  et.log(LogStatus.PASS, "2nd Contact deletion Successful");
		  }
		  catch(Exception ex)
		  {
			  et.log(LogStatus.FAIL, "Contact deletion failed");
		  }
		  
		  try
		  {
			  //dismiss all of the ads
			  ArrayList<MobileElement> adds_list = (ArrayList<MobileElement>) dr.findElements(By.id("com.aaks.qaautomation:id/adView"));
			  for(int i=0; i<adds_list.size();i++)
			  {
				  adds_list.get(i).click();
				  m.dismissAdd(dr);
			  }
			  System.out.println("All Adds dismissed");
			  if(dr.findElement(By.id("com.aaks.qaautomation:id/adView")).isDisplayed())
			  {
				  et.log(LogStatus.PASS, "Adds were dismissed");
			  }
			  else
			  {
				  et.log(LogStatus.FAIL, "Adds were not dismissed");
			  }
		  }
		  catch(Exception e)
		  {
			  et.log(LogStatus.PASS, "Adds were dismissed");
		  }
		  
	  }
	  catch(Exception e)
	  {
		  et.log(LogStatus.ERROR, "Error @ Advertisement dismiss Test" + e.getMessage());
	  }
	  
  }
  
  @BeforeSuite
  public void startAppium() throws Exception
  {
	  //Capabilities
	  url = new URL("http://0.0.0.0:4723/wd/hub/");
	  dc = new DesiredCapabilities();
	  dc.setCapability("deviceName","1215fc0d09101e03");
      dc.setCapability("platformVersion", "7.0");
      dc.setCapability("appPackage", "com.aaks.qaautomation");
      dc.setCapability("appActivity", "com.aaks.qaautomation.MainActivity");

      m = new Methods();
      
      //Driver Creation
      while(true)
      {
    	  try
    	  {
    		  dr = new AndroidDriver(url, dc);
    		  break;
    	  }
    	  catch(Exception e)
    	  {}
      }
      
      //Reports Creation
      er = new ExtentReports("ReportFile.html");
      et = er.startTest("Test Application Tests");
      dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);      
  }

  @AfterSuite
  public void closeAppium() throws Exception
  {
	  //Report Closure
	  er.endTest(et);
	  er.flush();
	  //Application Close
	  dr.resetApp();
	  dr.closeApp();
  }


}
