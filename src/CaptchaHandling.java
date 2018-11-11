

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CaptchaHandling {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\c-deepak.jindal\\Desktop\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://9gag.com/");
		driver.findElement(By.id("jsid-signup-button")).click();
		driver.findElement(By.linkText("Email Address")).click();
		int framenum = framenumber(driver, By.className("recaptcha-checkbox-checkmark"));
		driver.switchTo().frame(framenum);
		driver.findElement(By.className("recaptcha-checkbox-checkmark")).click();
		driver.switchTo().defaultContent();
		int framenum2 = framenumber(driver, By.xpath("//*[id='recaptcha-verify-button']"));
		driver.switchTo().frame(framenum2);
		driver.findElement(By.xpath("//*[id='recaptcha-verify-button']")).click();
		
		
	}
	
	public static int framenumber(WebDriver driver, By by)
	{
		int i;
		int framescount = driver.findElements(By.tagName("iframe")).size();
		System.out.println(framescount);
		for( i=0 ; i<framescount ; i++)
		{
			driver.switchTo().frame(i);
			
			int count = driver.findElements(by).size();
			if(count>0)
			{
				break;
			}
			else
			{
				System.out.println("next loop");
			}
		    driver.switchTo().defaultContent();

		}	
		driver.switchTo().defaultContent();
		return i;
	}
		
}
