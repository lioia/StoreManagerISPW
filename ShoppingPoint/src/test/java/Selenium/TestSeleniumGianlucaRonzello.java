package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSeleniumGianlucaRonzello {
    public float getKmPerHoursFromMetersPerSecond() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\gianr\\OneDrive\\Desktop\\StoreManagerISPW\\trunk\\ShoppingPoint\\src\\test\\java\\Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://convertlive.com/it/u/convertire/metri-al-secondo/a/chilometri-allora");
        driver.findElement(By.xpath("//*[@id=\"convert-value\"]")).sendKeys("5");
        driver.findElement(By.xpath("//*[@id=\"convert-submit\"]")).click();
        WebElement el = driver.findElement(By.xpath("//*[@id=\"converter\"]/div[4]/p/span[4]"));
        String value = el.getText();
        driver.close();
        return Float.parseFloat(value);
    }
}
