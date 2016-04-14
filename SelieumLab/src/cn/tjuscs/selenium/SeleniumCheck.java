package cn.tjuscs.selenium;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.csvreader.CsvReader;

public class SeleniumCheck {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors  = new StringBuffer();
	
	
	
	@Test
	public void test() throws InterruptedException, IOException {
		String chDriver = new File(new File(".").getCanonicalPath() + "\\" +"driver/chromedriver.exe").getCanonicalPath();
		System.out.println(chDriver);
		System.setProperty("webdriver.chrome.driver", chDriver);
		System.setProperty("webdriver.chrome.bin", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		baseUrl = "http://www.ncfxy.com/index.html";
		String emailtemp = null;
		String sidtemp = null;
		String pwd = null;
		CsvReader r = new CsvReader("G:\\学习\\大三\\web开发\\SelieumLab\\info.csv",',',Charset.forName("GBK"));
		r.readHeaders();
		while (r.readRecord()) {
			     //读取一条记录
			     //System.out.println(r.getRawRecord());
			     //按列名读取这条记录的值
			     sidtemp = r.get("id");
			     if(sidtemp.equals(""))
			    	 break;
			     pwd = sidtemp.substring(sidtemp.length() - 6);
			     emailtemp = r.get("email");
			     driver.get(baseUrl);
				 Thread.sleep(500);
				 driver.findElement(By.id("name")).sendKeys(sidtemp);
				 driver.findElement(By.id("pwd")).sendKeys(pwd);
				 driver.findElement(By.id("submit")).click();
				 String email = driver.findElement(By.xpath(".//*[@id='table-main']/tr[1]/td[2]")).getText();
			     System.out.println(sidtemp);
			     
			     assertEquals(email,emailtemp);
		}
		r.close();
		
	}
	
	@After
	public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString); 
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	}




