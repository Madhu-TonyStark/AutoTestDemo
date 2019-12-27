package TestNG;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Methods 
{
	public AndroidDriver<?> dr;
	WebDriverWait w;
	
	public String addEmployee(AndroidDriver<?> dr)
	{
		w = new WebDriverWait(dr, 30);
		String emp_name = "John";
		try 
		{
			MobileElement add_emp_btn = (MobileElement) dr.findElement(By.id("com.aaks.qaautomation:id/fab"));
			add_emp_btn.click();
		  
			//Adding Emp
			MobileElement first_name_txt = (MobileElement) dr.findElement(By.id("com.aaks.qaautomation:id/firstNameEditText"));
			w.until(ExpectedConditions.visibilityOf(first_name_txt));
			first_name_txt.sendKeys(emp_name);
			  
			MobileElement last_name_txt = (MobileElement) dr.findElement(By.id("com.aaks.qaautomation:id/lastNameEditText"));
			last_name_txt.sendKeys("Paul");
			  
			dr.findElement(By.id("com.aaks.qaautomation:id/titleSpinner")).click();    
			dr.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("+ "new UiSelector().text(\"Developer\"));")).click();
			  
			dr.findElement(By.id("com.aaks.qaautomation:id/projectSpinner")).click();    
			dr.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("+ "new UiSelector().text(\"Professional\"));")).click();
			  
			MobileElement create_emp_btn = (MobileElement) dr.findElement(By.id("com.aaks.qaautomation:id/createButton"));
			create_emp_btn.click();
			  
			w.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.aaks.qaautomation:id/adView")));
		}
		catch(Exception e)
		{}
		return emp_name;
	}
	
	public void delEmployee(AndroidDriver<?> dr)
	{
		w = new WebDriverWait(dr, 30);
		MobileElement del_em_btn = (MobileElement) dr.findElement(By.id("com.aaks.qaautomation:id/deleteEmployeeButton"));
		del_em_btn.click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.aaks.qaautomation:id/adView")));
	}
	
	public void dismissAdd(AndroidDriver<?> dr)
	{
		w = new WebDriverWait(dr, 30);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.aaks.qaautomation:id/adView")));
		dr.findElement(By.id("com.aaks.qaautomation:id/adView")).click();
	}
	
	public void getScreen()
	{
		File scrFile = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		scrFile.toPath();
	}
}
