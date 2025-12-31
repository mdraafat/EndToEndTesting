package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Framework {

    private final WebDriver driver;

    private Framework(WebDriverFactory driverType) {
        this.driver = driverType.create();
    }

    private static Framework start(WebDriverFactory driverType) {
        return new Framework(driverType);
    }

    public static Framework startChrome() {
        return start(WebDriverFactory.chrome);
    }

    public static Framework startFirefox() {
        return start(WebDriverFactory.firefox);
    }

    public static Framework startEdge() {
        return start(WebDriverFactory.edge);
    }

    public static Framework startSafari() {
        return start(WebDriverFactory.safari);
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

    public String text(String cssSelector) {
        isPresent(cssSelector);
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

    public void waitForAllRequiredFieldsFilled() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(_ -> {

                List<WebElement> requiredFields =
                        driver.findElements(By.cssSelector(".required input, .required select"));

                for (WebElement webElement : requiredFields) {
                    String webElementValue = webElement.getAttribute("value");

                    if (webElementValue == null || webElementValue.isEmpty()) {
                        return false;
                    }
                }
                return true;
            });
    }


}