package test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestProductDisplay {

	public static void main(String[] args) {

		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://demo.virtuemart.net/");	
		
		driver.findElement(By.xpath("//*[@id='header']/ul[2]/li[1]/a")).click();
		
		List<WebElement>itemsList = driver.findElements(By.xpath("//div[starts-with(@class,'product vm-col vm-col-3')]/div[@class='spacer']"));
		System.out.println(itemsList.size());
	}

}
