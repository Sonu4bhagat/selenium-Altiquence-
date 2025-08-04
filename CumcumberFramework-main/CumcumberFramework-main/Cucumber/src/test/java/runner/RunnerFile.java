package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/java/feature"},
        glue = {"stepDefination", "wrapper"},
        monochrome = true,
        tags = "@login"
)
public class RunnerFile extends AbstractTestNGCucumberTests {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass
    public void setUp() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

    }

    @DataProvider
    @Override
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass
    public void tearDown() {
        testNGCucumberRunner.finish();
    }

}
