package testBase;

import com.google.common.base.Function;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.TestUtil;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by geetham on 21/06/2017.
 */
public class TestBase {


    public static WebDriver driver;
    public static int indexSI = 1;
    public WebDriverWait wait;
    public String order;
    Properties prop;
    FileInputStream fis;
    private EventFiringWebDriver e_driver;
    public static String url;
    public int waitTime = 10;
    public Logger log;



    public void initialization() throws IOException {

        try {
            prop = new Properties();
            fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\config\\config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        driver =BrowserFactory.getDriver(prop.getProperty("browser"));
        log = Logger.getLogger(String.valueOf(TestBase.class));


        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.get(prop.getProperty("url"));
    }


    public void SendKeys(WebDriver driver, By locator, String test) {
        driver.findElement(locator).sendKeys(test);
    }


    public static void Clear(WebDriver driver, By locator) {
        driver.findElement(locator).clear();
    }

    public static void findElement(WebDriver driver, By locator) {
        driver.findElement(locator).click();
    }


    public void type(String text,By element){
        find(element).sendKeys(text);
    }

    public WebElement find(By element) {
        return driver.findElement(element);
    }

    public void click(By signInButton2) {
        find(signInButton2).click();
    }



    protected String getText(By element){
        return find(element).getText();


    }


    public void expliciteWait(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println("Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");


        }
    }
    public void clickWhenReady(By locator, int timeout) {
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();

    }

    public WebElement fluentWait(final WebElement webElement, int timeinsec) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeinsec, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });

        return element;
    };

    public WebElement getWhenVisible(By locator, int timeout) {
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;

    }
    public boolean isElementPresent(By by) {

        try {

            driver.findElement(by);

            return true;

        } catch (NoSuchElementException e) {

            return false;

        }

    }


    public void waitFor(int sec) throws InterruptedException {
        Thread.sleep(sec * 1000);
    }
    public void waitForVisiblility(By locator,Integer... timeOutInSeconds){
        int attempts =0;
        while(attempts<2){
            try{
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),(timeOutInSeconds.length >0 ? timeOutInSeconds[0] :null));
                break;
            }catch(Exception e){

            }
            attempts++;
        }

    }
    private void waitFor(ExpectedCondition<WebElement> condition,Integer timeOutInSeconds){
        timeOutInSeconds = timeOutInSeconds!= null ? timeOutInSeconds :30;
        WebDriverWait wait= new WebDriverWait(driver,30);wait.until(condition);

    }
    public void closeBrowser() {
        //driver = null;
        driver.close();
    }

    public  void getScreenShot(String fileName) throws IOException {
        File outputFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(outputFile, new File(System.getProperty("user.dir")+"/src/test/java/com/guru/magneto/screenshots/" + fileName + ".jpg"));
    }

    public static String now(String format) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat fm = new SimpleDateFormat();
        return fm.format(cal.getTime());
    }

    public static void updateResult(int indexSI, String testCaseName, String testCaseStatus, String scriptName) throws IOException {


        String startDateTime = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
        String resultFile = System.getProperty("user.dir")+"/src/test/java/com/guru/magneto/screenshots/report/TestHtmlReport.html";

        File file = new File(resultFile);
        System.out.println(file.exists());

        if (!file.exists()) {
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("<html>" + "\n");
            bw.write("<head><title>" + "Test execution report" + "</title>" + "\n");
            bw.write("</head>" + "\n");
            bw.write("<body>");
            bw.write("<font face='Tahoma'size='2'>" + "\n");
            bw.write("<u><h1 align='center'>" + "Test execution report" + "</h1></u>" + "\n");
            bw.flush();
            bw.close();
        }
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(file, true));
        if (indexSI == 1) {
            bw1.write("<table align='center' border='0' width='70%' height='10'>");
            bw1.write("<tr><td width='70%' </td></tr>");
            bw1.write("<table align='center' border='1' width='70%' height='47'>");
            bw1.write("<tr>");
            bw1.write("<td colspan='2' align='center'><b><font color='#000000' face='Tahoma' size='2'>ScriptName :&nbsp;&nbsp;&nbsp;</font><font color='#0000FF'' face='Tahoma' size='2'>" + scriptName + " </font></b></td>");
            bw1.write("<td colspan='1' align='left'><b><font color='#000000' face='Tahoma' size='1'>Start Time :&nbsp;</font></b><font color='#0000FF'' face='Tahoma' size='1'>" + startDateTime + " </font></td>");
            bw1.write("</tr>");
            bw1.write("</tr>");
            bw1.write("<td  bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>S.No</font></b></td>");
            bw1.write("<td  bgcolor='#CCCCFF' align='left'><b><font color='#000000' face='Tahoma' size='2'>Test case ID : Test case Description </font></b></td>");

            bw1.write("<td  bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>Result </font></b></td>");
            bw1.write("</tr>");
        }
        bw1.write("<tr>" + "\n");
        bw1.write("<td bgcolor='#FFFFDC'align='Center'><font color='#000000' face='Tahoma' size='2'>" + indexSI + "</font></td>" + "\n");
        bw1.write("<td  bgcolor='#FFFFDC' valign='middle' align='left'><b><font color='#000000' face='Tahoma' size='2'>" + testCaseName + "</font></b></td>" + "\n");
        if (testCaseStatus == "Pass") {
            bw1.write("<td  bgcolor='#FFFFDC' valign='middle' align='center'><b><font color='Green' face='Tahoma' size='2'>" + testCaseStatus + "</font></b></td>" + "\n");
        } else {
            bw1.write("<td  bgcolor='#FFFFDC' valign='middle' align='center'><b><font color='red' face='Tahoma' size='2'>" + testCaseStatus + "</font></b></td>" + "\n");
        }
        bw1.write("</tr>" + "\n");
        bw1.write("</body>" + "\n");
        bw1.write("</html>");
        bw1.flush();
        bw1.close();

    }

    public void loOut() {

    }
}







