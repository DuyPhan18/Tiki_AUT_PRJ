package runCucumberTest;
import cucumberOptions.RunCukeTest;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",
        glue = {"stepDefinitions", "cucumberOptions"},
        plugin = { "pretty",
                "html:target/cucumber-reports/cucumber-pretty.html", "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt" 
        },
        tags="@MainMenu")
public class RunMainMenuTest extends RunCukeTest{
}
