package stepDefination;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.*;

import static wrapper.CommanMethods.driver;

public class OCMPservices {
    @And("User click on the service account")
    public void userClickOnTheServiceAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement serviceAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains (text(),'Service Accounts')]")));
        serviceAccount.click();
    }

    @And("User redirects to the service account board")
    public void userRedirectsToTheServiceAccountBoard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement serviceAccountList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains (text(),' Service Accounts ')]")));
        Assert.assertTrue(serviceAccountList.getText().contains("Service Accounts"));
    }

    @And("User store the list of service accounts")
    public void userStoreTheListOfServiceAccounts() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Wait for the table to load
            WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.table-wrapper.table-responsive > table")));

            // Extract headers
            List<WebElement> headers = table.findElements(By.cssSelector("thead.table-head th"));
            List<String> headerTexts = new ArrayList<>();

            for (WebElement header : headers) {
                String headerText = header.getText().trim();
                headerTexts.add(headerText.isEmpty() ? "Unnamed Column" : headerText);
            }

            System.out.println("Header Columns: " + headerTexts);

            // Extract data rows
            List<WebElement> rows = table.findElements(By.cssSelector("tbody.table-body tr"));
            List<Map<String, String>> serviceAccounts = new ArrayList<>();

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                Map<String, String> rowData = new LinkedHashMap<>(); // Maintain column order

                for (int i = 0; i < cells.size(); i++) {
                    String key = (i < headerTexts.size()) ? headerTexts.get(i) : "Column" + (i + 1);
                    String value = cells.get(i).getText().trim();
                    rowData.put(key, value);
                }

                serviceAccounts.add(rowData);
            }

            // Log the collected data
            System.out.println("Stored Service Account Data:");
            for (Map<String, String> account : serviceAccounts) {
                System.out.println(account);
            }

        } catch (TimeoutException te) {
            System.out.println("Service accounts table did not load in time: " + te.getMessage());
        } catch (Exception e) {
            System.out.println("Error while storing service account list: " + e.getMessage());
        }
    }


    @And("User click on the next for Meta title active user")
    public void userClickOnTheNextForMetaTitleActiveUser(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean found = false;

        while (!found) {
            try {
                // Wait for the table to be present
                WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div.table-wrapper.table-responsive > table")));
                List<WebElement> rows = table.findElements(By.cssSelector("tbody.table-body tr"));

                // Iterate each row to check for Meta Status
                for (WebElement row : rows) {
                    List<WebElement> cells = row.findElements(By.tagName("td"));
                    if (cells.size() >= 7) {
                        String metaStatus = cells.get(6).getText().trim();
                        if (metaStatus.equalsIgnoreCase("Active")) {
                            System.out.println("Meta Status 'Active' found!");
                            found = true;
                            break;
                        }
                    }
                }

                if (found) break;

                // Scroll to bottom to make pagination controls visible
                js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(500); // Give time for scroll

                // Find the Next button
                WebElement nextBtn = driver.findElement(By.xpath("//li[contains(@class,'pagination-next')]//button"));

                // Check if Next button is enabled
                String disabledAttr = nextBtn.getAttribute("disabled");
                boolean isDisabled = disabledAttr != null && (disabledAttr.equals("true") || disabledAttr.equals("disabled"));
                String classAttr = nextBtn.getAttribute("class");
                if (classAttr != null && classAttr.contains("disabled")) {
                    isDisabled = true;
                }

                if (isDisabled) {
                    System.out.println("Reached the last page. No 'Active' meta status found.");
                    break;
                }

                // Scroll to Next button and click it
                js.executeScript("arguments[0].scrollIntoView(true);", nextBtn);
                wait.until(ExpectedConditions.elementToBeClickable(nextBtn)).click();

                // Wait for table rows to refresh
                wait.until(ExpectedConditions.stalenessOf(rows.get(0)));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody.table-body tr")));
                Thread.sleep(1000); // stabilize

            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
                break;
            }
        }

    }



    @When("User find the Meta title active user then click on right")
    public void userFindTheMetaTitleActiveUserThenClickOnNext() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Wait for the table to be visible
            WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.table-wrapper.table-responsive > table")));

            // Get all table rows
            List<WebElement> rows = table.findElements(By.cssSelector("tbody.table-body tr"));
            System.out.println("Total rows found: " + rows.size());

            for (WebElement row : rows) {
                try {
                    List<WebElement> cells = row.findElements(By.tagName("td"));
                    System.out.println("Row Text: " + row.getText());
                    System.out.println("Cell Count: " + cells.size());

                    // Check if there are enough columns to avoid IndexOutOfBounds
                    if (cells.size() >= 9) {
                        String metaStatus = cells.get(6).getText().trim();
                        String serviceStatus = cells.get(7).getText().trim();

                        System.out.println("Meta Status: " + metaStatus + ", Service Status: " + serviceStatus);

                        // Check if both statuses are "Active"
                        if (metaStatus.equalsIgnoreCase("Active") && serviceStatus.equalsIgnoreCase("Active")) {
                            WebElement nextButton = cells.get(cells.size() - 1).findElement(By.tagName("button"));

                            // Scroll into view and highlight
                            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
                            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", nextButton);

                            // Wait for clickability
                            wait.until(ExpectedConditions.elementToBeClickable(nextButton));

                            // Try JS click first (for hidden or JS-handled buttons)
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);
                            System.out.println("Clicked on 'Next' button for active user");

                            // Wait for dashboard or redirection
                            wait.until(ExpectedConditions.or(
                                    ExpectedConditions.urlContains("dashboard"),
                                    ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Dashboard')]"))
                            ));

                            System.out.println("Redirection successful. URL: " + driver.getCurrentUrl());

                            // Handle new window/tab if any
                            String currentHandle = driver.getWindowHandle();
                            for (String handle : driver.getWindowHandles()) {
                                if (!handle.equals(currentHandle)) {
                                    driver.switchTo().window(handle);
                                    System.out.println("Switched to new tab/window.");
                                }
                            }

                            break; // Exit loop after successful click
                        }
                    } else {
                        System.out.println("Skipping row - not enough columns.");
                    }
                } catch (Exception e) {
                    System.out.println("Error while processing a row: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Error during Meta Active user search: " + e.getMessage());
        }
    }


    @Then("User should landed to the SSO Dashboard")
    public void userShouldLandedToTheSSODashboard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement ssoDashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Dashboard')]")));
        Assert.assertTrue(ssoDashboard.getText().contains("Dashboard"));
    }
}
