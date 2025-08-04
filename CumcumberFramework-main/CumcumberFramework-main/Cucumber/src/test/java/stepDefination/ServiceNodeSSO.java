package stepDefination;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static wrapper.CommanMethods.driver;

public class ServiceNodeSSO {
    private WebElement findWABACard(WebDriverWait wait) {
        List<WebElement> serviceCards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'card') and contains(@class,'service-cards') and contains(@class,'sso_card')]")));

        for (WebElement card : serviceCards) {
            if (card.getText().toLowerCase().contains("waba")) {
                return card;
            }
        }

        throw new NoSuchElementException("WABA service not found in the list.");
    }

    @And("User click on the ServiceNodeSSO")
    public void userClickOnTheServiceNodeSSO() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement serviceNodeSSO = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Service Nodes SSO')]")));
        serviceNodeSSO.click();
    }

    @And("User find the WABA service from the list")
    public void userFindTheWABAServiceFromTheList() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement card = findWABACard(wait);
        System.out.println("Found WABA service card.");
    }

    @And("User find the Login button")
    public void userFindTheLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement card = findWABACard(wait);

        WebElement loginButton = card.findElement(By.xpath(".//button[contains(text(), 'Login')]"));
        loginButton.click();
    }

    @When("User click login as Superadmin")
    public void userClickLoginAsSuperadmin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement superAdminOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),' SUPER ADMIN ')]")));
        superAdminOption.click();
    }

    @Then("Redirects to the OCMP page")
    public void redirectsToTheOCMPPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        String originalWindow = driver.getWindowHandle();

        wait.until(driver -> driver.getWindowHandles().size() > 1);

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h5[contains(text(),' Control Centre ')]")));
        Assert.assertTrue(header.isDisplayed(), "WABA Dashboard heading is not displayed");
    }
}
