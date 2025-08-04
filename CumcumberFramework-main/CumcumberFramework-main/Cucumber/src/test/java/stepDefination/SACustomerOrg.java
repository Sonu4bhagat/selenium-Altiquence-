package stepDefination;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.sql.Driver;
import java.time.Duration;

import static wrapper.CommanMethods.driver;

public class SACustomerOrg {
    @And("User should click on the CustomerORG")
    public void userShouldClickOnTheCustomerORG() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement customerORG = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains (text(),'Customer Org')]")));
        customerORG.click();
    }

    @And("User should click on the search field.")
    public void userShouldClickOnTheSearchField() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'popup-class')]")));
        } catch (TimeoutException ignored) {}

        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='search']")));
        searchField.click();
    }


    @When("User enter the text in search field {string}")
    public void userEnterTheTextInSearchField(String svCustomerID) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement enterSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='search']")));
        enterSearch.sendKeys(svCustomerID);
    }

    @Then("Searched result should appears")
    public void searchedResultShouldAppears() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement findSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[contains(@class,'table-striped')]//tbody//tr//td[1][contains(text(),'SV')]")));
        Assert.assertTrue(findSearch.isDisplayed());
    }

    @When("User click on the next button")
    public void userClickOnTheNextButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement clickNextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-sm btn-outline-secondary btn-icon']")));
        clickNextButton.click();
    }

    @Then("created wallet should appears")
    public void createdWalletShouldAppears() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement checkSVcustomerID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[contains (text(),'Wallet Name')]")));
        Assert.assertTrue(checkSVcustomerID.isDisplayed());
    }

    @When("User click on the organization detail")
    public void userClickOnTheOrganizationDetail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement clickOrganizationDetail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), ' Organizations details ')]")));
        clickOrganizationDetail.click();
    }

    @Then("User should get the information detail page")
    public void userShouldGetTheInformationDetailPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement informationDetail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[contains(text(),'Contact Information')]")));
        Assert.assertTrue(informationDetail.isDisplayed());
    }
}
