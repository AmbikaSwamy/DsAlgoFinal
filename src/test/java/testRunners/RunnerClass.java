package testRunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions
	(
			features = "src/test/resources/Features",
			glue = "StepDefinitions",
			dryRun = false,
			monochrome = true,
			plugin= {"pretty", "html:test-output/ds-algo.html"				
					, "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
					, "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
					, "html:target/cucumber.html"
					, "json:target/cucumber.json"
					}		
	)
public class RunnerClass {

}
