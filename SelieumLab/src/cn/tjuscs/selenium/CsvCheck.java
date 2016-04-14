package cn.tjuscs.selenium;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CsvCheck {
	public static void main(String[] args) throws IOException, InterruptedException{
		
		String chDriver = new File(new File(".").getCanonicalPath() + "\\" +"driver/chromedriver.exe").getCanonicalPath();
		System.out.println(chDriver);
		System.setProperty("webdriver.chrome.driver", chDriver);
		System.setProperty("webdriver.chrome.bin", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.ncfxy.com/index.html");
		driver.findElement(By.id("name")).sendKeys("3013218108");
		driver.findElement(By.id("pwd")).sendKeys("218108");
		driver.findElement(By.id("submit")).click();
		System.out.println(driver.getCurrentUrl());
		Thread.sleep(500);
	}
}
