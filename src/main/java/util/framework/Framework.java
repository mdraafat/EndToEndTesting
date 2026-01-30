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
    private static final String AD_SELECTOR = ".adsbygoogle.adsbygoogle-noablate";

    private static final String HOME_URL = "https://automationexercise.com/";


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

    public void goToHome() {
        driver.get(HOME_URL);
    }

    public Boolean isCurrentEndpoint(String endpoint) {
        removeAds();
        try {
            return wait.until(ExpectedConditions.urlToBe(HOME_URL + endpoint));
        } catch (TimeoutException e) {
            return false;
        }
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

    public String getTextOf(String cssSelector) {

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

    public void sendTo(String cssSelector, String text) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector))
        );

        element.clear();
        element.sendKeys(text);
    }

    public void acceptPrompt(){
        driver.switchTo().alert().accept();
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

    public boolean clickOnEachAndCheck(String linkSelector, String categorySelector, String searchKeyword, String productNameOnListSelector) {
        removeAds();

        // Get all product links
        List<WebElement> productLinks = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(linkSelector))
        );

        int totalProducts = productLinks.size();

        // Loop through each product
        for (int i = 0; i < totalProducts; i++) {
            // Re-fetch the list to avoid stale element reference
            productLinks = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(linkSelector))
            );

            // Get all product names on the listing page
            List<WebElement> productNames = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(productNameOnListSelector))
            );

            // Get the product name from the listing
            String productNameOnList = productNames.get(i).getText().toLowerCase();
            String keyword = searchKeyword.toLowerCase();

            // Check if the product name already contains the keyword
            if (productNameOnList.contains(keyword)) {
                // Skip clicking, product already matches by name
                continue;
            }
            removeAds();
            // Click on the current product to check category
            actions.moveToElement(productLinks.get(i)).click().perform();

            // Wait for the category to be visible and get its text
            WebElement categoryElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(categorySelector))
            );

            String categoryText = categoryElement.getText().toLowerCase();

            // Check if category contains the search keyword
            if (!categoryText.contains(keyword)) {
                return false; // Return false if product doesn't match
            }

            // Navigate back to the product list
            driver.navigate().back();
            removeAds();

        }

        return true; // All products matched
    }

    public void removeAds() {
        ((JavascriptExecutor) driver).executeScript(
                """
                        var ads = document.querySelectorAll(arguments[0]);
                        ads && ads.length && ads.forEach(el => el.outerHTML = "");
                        """,
                AD_SELECTOR
        );

        if (driver.getCurrentUrl().endsWith("#google_vignette")) driver.navigate().back();
    }

    public void scrollToBottom(){
        removeAds();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void ScrolltoTop() {
        removeAds();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    public boolean OnTop() {
        return (Long) ((JavascriptExecutor) driver).executeScript("return window.pageYOffset;") == 0;
    }
}
