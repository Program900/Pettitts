package testrunner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features={"classpath:features"},
        format={"json:target\\cucumber.json","html:target\\cucumber-pretty"},glue="Steps")

public class TestRunner extends AbstractTestNGCucumberTests{
}
