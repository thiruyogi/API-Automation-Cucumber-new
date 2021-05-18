package test.java.Runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features= {"src/test/resources/Features"},
glue= "test.java.Steps",plugin = { "pretty", "html:target/cucumber-reports.html", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},monochrome = true)
public class RunnerCuke extends AbstractTestNGCucumberTests {

}
