package cucumberOptions;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(features = "src/test/resources/features",
        glue = {"stepDefinitions", "cucumberOptions"},
        plugin = { "pretty",
                "html:target/cucumber-reports/cucumber-pretty.html", "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt" }
        )
public class RunCukeTest {
    private TestNGCucumberRunner testNGCucumberRunner;

    // Phương thức này được chạy trước bất kỳ bài kiểm thử nào trong lớp này
    @BeforeClass(alwaysRun=true)
    public void setUpClass() {
        // Khởi tạo TestNGCucumberRunner với lớp kiểm thử hiện tại
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
    // Phương thức này chạy các scenarios
    @Test(description = "Run Cucumber Features", dataProvider = "scenarios")
    public void feature(PickleWrapper pickleWrapper, FeatureWrapper cucumberFeature) {
        // Chạy một kịch bản riêng lẻ bằng cách lấy pickle từ PickleWrapper
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }
    // DataProvider cung cấp các scenarios cho phương thức kiểm thử
    @DataProvider(name = "scenarios")
    public Object[][] scenarios() {
        if (testNGCucumberRunner == null) {
            testNGCucumberRunner = new TestNGCucumberRunner(getClass());
        }
        // Cung cấp các scenarios sẽ được chạy trong các bài kiểm thử
        return testNGCucumberRunner.provideScenarios();
    }
    // Phương thức này được chạy sau khi tất cả các bài kiểm thử trong lớp này đã hoàn tất
    @AfterClass(alwaysRun=true)
    public void tearDownClass() {
        // Kết thúc runner và thực hiện bất kỳ dọn dẹp nào cần thiết
        testNGCucumberRunner.finish();
    }

}
