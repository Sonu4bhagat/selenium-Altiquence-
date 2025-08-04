package stepDefination;

import com.beust.ah.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static wrapper.CommanMethods.driver;
import static wrapper.CommanMethods.wait;

public class SADashboard {
    Login login = new Login();

    @Given("User should login using the valid credential")
    public void userShouldLoginUsingTheValidCredential() {
        login.userLandedOnTheLoginPage();
        login.userEnterTheUsernameInTheField("sonu.bhagat+3@altiquence.com");
        login.userEnterThePasswordInTheField("Sonu@altiquence2");
        login.userClickOnTheLoginButton();
        login.userEnterTheOTP();
        login.userClickOnTheVerifyButton();
        login.userRedirectsToTheDashboard();
    }

    @And("User should landed to the dashboard")
    public void userShouldLandedToTheDashboard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dashBoardPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Welcome')]")));
        dashBoardPage.isDisplayed();
    }

    @When("User click on the IVR")
    public void userClickOnTheIVR() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement IVRpage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains (text(),' IVR ')]")));
        IVRpage.click();
    }

    @Then("User redirects to the IVR page")
    public void userRedirectsToTheIVRPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement IVRredirection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Calls Received')]")));
        Assert.assertEquals(IVRredirection.getText(), "Calls Received");
    }

    @When("User click on the OBD")
    public void userClickOnTheOBD() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement OBDpage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains (text(),' OBD ')]")));
        OBDpage.click();
    }

    @Then("User redirects to the OBD page")
    public void userRedirectsToTheOBDPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement OBDpage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains (text(),'Numbers Uploaded')]")));
        Assert.assertEquals(OBDpage.getText(), "Numbers Uploaded");
    }

    @When("User click on the CCS")
    public void userClickOnTheCCS() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement CCSpage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains (text(),' CCS ')]")));
        CCSpage.click();
    }

    @Then("User redirects to the CCS page")
    public void userRedirectsToTheCCSPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement CCSpage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains (text(),'Total Calls')]")));
        Assert.assertEquals(CCSpage.getText(), "Total Calls");
    }

    @When("User click on the WABA")
    public void userClickOnTheWABA() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement WABApage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains (text(),' WABA ')]")));
        WABApage.click();
    }

    @Then("User redirects to the WABA page")
    public void userRedirectsToTheWABAPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement WABApage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains (text(),'Pending')]")));
       Assert.assertEquals(WABApage.getText(), "Pending");
    }
}
