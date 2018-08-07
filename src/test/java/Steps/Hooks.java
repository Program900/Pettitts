package Steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import testBase.TestBase;

import java.io.File;
import java.io.IOException;

public class Hooks extends TestBase {


    
    @Before
    public void openBrowser() throws IOException {
        initialization();

    }


    @After
    public void embedScreenshot(Scenario scenario) throws IOException {
       
       if(scenario.isFailed()) try {
           File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);


           FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\src\\main\\java\\Screenshots\\Screenshots.png"));

       } catch (WebDriverException somePlatformsDontSupportScreenshots) {
           System.err.println(somePlatformsDontSupportScreenshots.getMessage());
       }
        driver.quit();
        
    }
    
}