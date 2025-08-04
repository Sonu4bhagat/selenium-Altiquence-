package wrapper;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CucumberWrapper extends CommanMethods {

    @Before
    public static void setUpBrowser(){
        setUp();
    }

    @After
    public void quitBrowser(){
        closeDriver();
    }

}
