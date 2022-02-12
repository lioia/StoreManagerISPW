package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSeleniumLioiAlessandro {
    public float getPascalFromAtm() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lioia\\Documents\\Università\\3-1\\Ingegneria del Software e Progettazione Web\\StoreManagerISPW\\trunk\\ShoppingPoint\\src\\test\\java\\Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.convertunits.com/from/pascal/to/atm");
        Thread.sleep(2000); // Wait for cookie popup
        driver.findElement(By.className("fc-cta-consent")).click(); // click on popup button
        driver.findElement(By.xpath("//*[@id=\"EchoTopic\"]/form[1]/table/tbody/tr[2]/td[1]/input")).sendKeys("1");
        driver.findElement(By.xpath("//*[@id=\"EchoTopic\"]/form[1]/table/tbody/tr[3]/td/input")).click();
        WebElement el = driver.findElement(By.xpath("//*[@id=\"EchoTopic\"]/form[1]/table/tbody/tr[1]/td[1]/input"));
        String value = el.getAttribute("value");
        driver.close();
        return Float.parseFloat(value);
    }
}
