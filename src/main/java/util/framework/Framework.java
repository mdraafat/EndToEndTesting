package util.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Framework {

    private final WebDriver driver;

    private Framework(WebDriver driver) {
        this.driver = driver;
    }

    public static Framework start(String browser) {
        WebDriver driver = switch (browser.toLowerCase()) {
            case "firefox" -> new FirefoxDriver();
            case "safari" -> new SafariDriver();
            case "edge" -> new EdgeDriver();
            default -> new ChromeDriver();
        };

        driver.manage().window().maximize();

        return new Framework(driver);
    }

    public static Framework startChrome() {
        return start("chrome");
    }

    public static Framework startFirefox() {
        return start("firefox");
    }

    public static Framework startEdge() {
        return start("edge");
    }

    public static Framework startSafari() {
        return start("safari");
    }

    public void goToUrl(String url) {
        driver.get(url);
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public boolean isPresent(String cssSelector) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
        return driver.findElement(By.cssSelector(cssSelector)).isDisplayed();
    }

    public String getText(String cssSelector) {
        isPresent(cssSelector);
        removeAd();
        return driver.findElement(By.cssSelector(cssSelector)).getText();
    }

    public void clickOn(String cssSelector) {
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    public void sendText(String cssSelector, String text) {
        driver.findElement(By.cssSelector(cssSelector)).sendKeys(text);
    }

    public void selectOption(String cssSelector, String option) {
        new Select(driver.findElement(By.cssSelector(cssSelector))).selectByContainsVisibleText(option);
    }

    public void waitForAllRequiredFields(String cssSelector) {
        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(_ -> {

                List<WebElement> requiredFields =
                        driver.findElements(By.cssSelector(cssSelector));

                for (WebElement webElement : requiredFields) {
                    String webElementValue = webElement.getAttribute("value");
                    if (webElementValue == null || webElementValue.isEmpty()) {
                        return false;
                    }
                }
                return true;
            });
    }

    public void removeAd(){
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll('.adsbygoogle.adsbygoogle-noablate').forEach(el => el.remove());"
        );
    }
}