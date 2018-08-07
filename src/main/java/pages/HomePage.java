package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testBase.TestBase;

/**
 * Created by rache on 06/08/2018.
 */
public class HomePage extends TestBase{

    public WebDriver driver;

    public HomePage(){
        driver= TestBase.driver;

    }
    public String verifyChildCarePageTitle() {
        return driver.getTitle();
    }

    public boolean IsChooseyourDestination() {
        By x = By.xpath("");
        if (isElementPresent(x)) {
            return true;

        }

        else{
            return false;
        }
    }

}
