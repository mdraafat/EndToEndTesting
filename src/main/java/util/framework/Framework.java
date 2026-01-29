package util.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Framework {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(10);
    private static final String GOOGLE_VIGNETTE = "#google_vignette";
    private static final String AD_SELECTOR = ".adsbygoogle.adsbygoogle-noablate";

    private Framework(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_WAIT);
        this.actions = new Actions(driver);
    }

    public static Framework start() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return new Framework(driver);
    }

    public void goToUrl(String url) {
        driver.get(url);
    }

    public void closeBrowser() {
        driver.quit();
    }

    public boolean isPresent(String cssSelector) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)))
                    .isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String verify(String cssSelector) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector))
        );

        return element.getText();
    }

    public void clickOn(String cssSelector) {
        removeAds();
        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector))
        );

        actions.moveToElement(element).click().perform();
    }

    public void sendText(String cssSelector, String text) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector))
        );

        element.clear();
        element.sendKeys(text);
    }

    public void selectOption(String cssSelector, String option) {

        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector))
        );

        new Select(element).selectByContainsVisibleText(option);
    }

    public void waitForAllRequiredFields(String cssSelector) {
        wait.until(_ -> {
            List<WebElement> requiredFields = driver.findElements(By.cssSelector(cssSelector));

            for (WebElement field : requiredFields) {
                String value = field.getAttribute("value");
                if (value == null || value.isEmpty()) {
                    return false;
                }
            }
            return true;
        });
    }

    private void removeAds() {
        ((JavascriptExecutor) driver).executeScript(
                """
                        var ads = document.querySelectorAll(arguments[0]);
                        ads && ads.length && ads.forEach(el => el.outerHTML = "");
                        """,
                AD_SELECTOR
        );

        if (driver.getCurrentUrl().endsWith("#google_vignette")) driver.navigate().back();
    }
}
