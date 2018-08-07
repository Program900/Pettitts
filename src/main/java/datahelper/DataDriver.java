package datahelper;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;

public class DataDriver {
    public WebDriver driver;
    public List<HashMap<String,String>> datamap;
    public List<HashMap<String,String>> datamap1;


    public DataDriver(WebDriver driver){
        this.driver=driver;
        datamap = DataHelper.data(System.getProperty("user.dir")+"/src/test/resources/testData/default.xlsx","Sheet1");
        datamap1 = DataHelper.data(System.getProperty("user.dir")+"/src/test/resources/testData/TestData-seleniumframework.xlsx","SignInContactUs");

    }
    public void he() throws Throwable {
        int index = 1;
        System.out.println("Printing he  data set...");
        for(HashMap h:datamap)
        {
            // System.out.println(h.keySet());
            // System.out.println(h.values());
        }

        System.out.println(datamap.get(index).get("heading"));
        System.out.println(datamap.get(index).get("email"));
        System.out.println(datamap.get(index).get("order_reference"));
        System.out.println(datamap.get(index).get("message"));

    }
    public void she() throws Throwable {
        int index = 1;
        System.out.println("Printing current data set...");
        for(HashMap h:datamap1)
        {
            // System.out.println(h.keySet());
            // System.out.println(h.values());
        }

        System.out.println(datamap1.get(index).get("Test Case Name"));
        System.out.println(datamap1.get(index).get("username"));
        System.out.println(datamap1.get(index).get("password"));
        System.out.println(datamap1.get(index).get("Browser"));
        System.out.println(datamap1.get(index).get("subject"));
        System.out.println(datamap1.get(index).get("email"));
        System.out.println(datamap1.get(index).get("order_reference"));
        System.out.println(datamap1.get(index).get("message"));

    }

}
