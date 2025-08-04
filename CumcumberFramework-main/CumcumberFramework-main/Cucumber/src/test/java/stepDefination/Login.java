package stepDefination;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static wrapper.CommanMethods.driver;
import static wrapper.CommanMethods.wait;

public class Login {
    @Given("User landed on the login page")
    public void userLandedOnTheLoginPage() {
        WebElement userNameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='loginEmail']")));
        userNameField.isDisplayed();
    }

    @And("User Enter the username in the field {string}")
    public void userEnterTheUsernameInTheField(String email) {
        WebElement userNameFields = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='loginEmail']")));
        userNameFields.sendKeys(email);
    }

    @And("User enter the password in the field {string}")
    public void userEnterThePasswordInTheField(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='cst_login_pwd']")));
        passwordField.sendKeys(password);
    }

    @And("User click on the login button")
    public void userClickOnTheLoginButton() {
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@value='Login']")));
        loginButton.click();
    }

    @And("User enter the OTP")
    public void userEnterTheOTP() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> otpFields = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='verify_otp_sec']//input[@type='text']"))
        );
        String otp = "112233";
        for (int i = 0; i < otp.length(); i++) {
            WebElement otpField = otpFields.get(i);
            otpField.clear();
            otpField.sendKeys(String.valueOf(otp.charAt(i)));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @When("User click on the verify button")
    public void userClickOnTheVerifyButton() {
        WebElement clickVerify = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains (text(), 'Verify')]")));
        clickVerify.click();
    }


    @Then("User redirects to the Dashboard")
    public void userRedirectsToTheDashboard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Check for Super Admin Dashboard
            WebElement superAdminHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5[contains(text(),'Welcome')]")));
            if (superAdminHeader.isDisplayed()) {
                Assert.assertTrue(superAdminHeader.isDisplayed(), "Super Admin Dashboard not loaded");
                System.out.println("Super Admin Dashboard loaded successfully.");
                return;
            }

        } catch (TimeoutException ignored) {
            // Ignored to continue with the next check
        }

        try {
            // Check for Enterprise Dashboard
            WebElement enterpriseHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@class='cst_welcome_text']")));
            if (enterpriseHeader.isDisplayed()) {
                Assert.assertTrue(enterpriseHeader.isDisplayed(), "Enterprise Dashboard not loaded");
                System.out.println("Enterprise Dashboard loaded successfully.");
                return;
            }

        } catch (TimeoutException ignored) {
            // Ignored to continue with the next check
        }

        try {
            // Check for Retailer Dashboard
            WebElement retailerHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[@class='welcome-title']")));
            if (retailerHeader.isDisplayed()) {
                Assert.assertTrue(retailerHeader.isDisplayed(), "Retailer Dashboard not loaded");
                System.out.println("Retailer Dashboard loaded successfully.");
                return;
            }
        } catch (TimeoutException ignored) {
            // Ignored to continue with the final failure
        }
        // If none of the dashboards were detected
        Assert.fail("Unknown dashboard type detected. No matching headers found.");
    }

    @Then("User get the error message")
    public void userGetTheErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'alert alert-error')]")));
        Assert.assertTrue(errorPopup.isDisplayed(), "Invalid credentials popup is not displayed");

    }

    @And("User click on the forgot password link")
    public void userClickOnTheForgotPasswordLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement forgotPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),' Forgot Password?')]")));
        forgotPassword.click();
    }

    @And("User enter the email {string}")
    public void userEnterTheEmail(String email) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement enterEmail  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter business email ID']")));
        enterEmail.sendKeys(email);
    }

    @When("User Click on the Get Reset password link")
    public void userClickOnTheGetResetPasswordLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement resetPasswordLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains (text(), ' Get Reset Password Link')]")));
        resetPasswordLink.click();
    }

    @Then("User should landed to the Check your mail page")
    public void userShouldLandedToTheCheckYourMailPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkMailPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='text-dark']")));
        boolean isMailPage = checkMailPage.isDisplayed();
        Assert.assertTrue(isMailPage, "Mail page is not displayed");
    }

    @And("User click on the Back to login button")
    public void userClickOnTheBackToLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement backToLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains (text(),' Back to Login ')]")));
        backToLogin.click();
    }
}
