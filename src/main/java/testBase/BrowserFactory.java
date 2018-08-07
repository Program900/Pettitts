package testBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Properties;


public class BrowserFactory {
    public static WebDriver driver;
    public static Properties prop;
    public  static EventFiringWebDriver e_driver;

    static String location =System.getProperty("user.dir");

    public static WebDriver getDriver(String browser){


        if (browser.equals("firefox") || browser.equals("FIREFOX")) {
            System.out.println("firefox browser");
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\geckodriver");
            driver = new FirefoxDriver();


            return  driver;
        } else if (browser.equals("chrome") || browser.equals("CHROME")) {
            System.out.println("chrome browser");
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\rache\\Downloads\\One\\onefamily\\src\\main\\resources\\chromedriver.exe");
            driver = new ChromeDriver();

            return  driver;
        } else if (browser.equals("ie") || browser.equals("IE")) {
            System.out.println("ie browser");
            driver = new InternetExplorerDriver();

            return driver;
        }
        return null;

    }
}
