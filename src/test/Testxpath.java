package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Testxpath {

	public static void main(String[] args) throws InterruptedException {

		FirefoxDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://demo.virtuemart.net/search?keyword=Poncho&limitstart=0&option=com_virtuemart&view=category&virtuemart_category_id=0");
		Thread.sleep(3000);
		
		WebElement elem= driver.findElement(By.xpath("//form[@class='product js-recalculate']/div/div[1]/div/div/a/div/b"));
		elem.click();
		Actions act = new Actions(driver);
		//act.moveToElement(elem).build().perform();
		Thread.sleep(2000);
		//act.clickAndHold(elem).build().perform();
		Thread.sleep(2000);
		//act.release();
		
		elem= driver.findElement(By.xpath("//form[@class='product js-recalculate']/div/div[1]/div/select"));
		Thread.sleep(2000);
		Select s = new Select(elem);
		s.selectByIndex(2);
		
		System.out.println("OK");
	}

}
