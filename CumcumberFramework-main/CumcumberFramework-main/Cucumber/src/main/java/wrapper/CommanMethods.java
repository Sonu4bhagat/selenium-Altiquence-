package wrapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommanMethods {

    public static WebDriver driver;
    public static WebDriverWait wait;

    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://stagingvault.smartping.io/");
        driver.manage().window().maximize();
    }
    public static void closeDriver(){
        if (driver!= null){
            driver.quit();
        }
    }
}
