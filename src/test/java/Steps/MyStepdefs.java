package Steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import pages.HomePage;


public class MyStepdefs {


    public HomePage homepage;
    public MyStepdefs(){
        homepage = new HomePage();

    }


    @Given("^I am on HomePage$")
    public void i_am_on_HomePage() throws Throwable {

        String actualTitle =homepage.verifyChildCarePageTitle();
        Assert.assertEquals(actualTitle,"Tailor Made Holidays, Luxury Travel and Tours with Pettitts");

    }
    @Given("^I have  ChooseyourDestination$")
    public void i_have_ChooseyourDestination() throws Throwable {
        System.out.println("i_have_ChooseyourDestination");



    }

    @When("^I click on ChooseyourDestination$")
    public void i_click_on_ChooseyourDestination() throws Throwable {
        System.out.println("i_click_on_ChooseyourDestination");

           }

    @Then("^I should see OptionsOfDestinationCollection$")
    public void i_should_see_OptionsOfDestinationCollection() throws Throwable {
        System.out.println("I should see OptionsOfDestinationCollection");

    }

    @Then("^I should see '(\\d+)' Destination$")
    public void iShouldSeeDestination(int arg0) throws Throwable {
        System.out.println("I should see Destination$");

    }

    @And("^I select any ChooseyourDestination$")
    public void iSelectAnyChooseyourDestination() throws Throwable {
        System.out.println("iSelectAnyChooseyourDestination$");

          }

    @Then("^I should see selected ChooseyourDestination$")
    public void iShouldSeeSelectedChooseyourDestination() throws Throwable {
        System.out.println("I should see selected ChooseyourDestination$");

    }


}
