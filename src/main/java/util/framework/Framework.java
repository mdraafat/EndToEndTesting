package util.framework;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Framework {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(60);
    private static final String AD_SELECTOR = ".adsbygoogle.adsbygoogle-noablate";

    private static final String HOME_URL = "https://automationexercise.com/";


    private Framework(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_WAIT);
    }

    public static Framework start() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER); // for faster run
        WebDriver driver = new ChromeDriver(options);
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
        removeAds();
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector))
        );

        return element.getText();
    }

    public int getValueOf(String cssSelector) {
        return Integer.parseInt(getTextOf(cssSelector).replaceAll("[^0-9]", ""));
    }

    public void clickOn(String cssSelector) {
        removeAds();
        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector))
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});",
                element
        );

        // Re-wait for clickability after scroll
        wait.until(ExpectedConditions.elementToBeClickable(element));

        new Actions(driver).moveToElement(element).click().perform();
    }

    public String getFastDisappearingText(String cssSelector) {
        driver.navigate().back();
        String text = getTextOf(cssSelector);
        driver.navigate().forward();
        return text;
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
            new Actions(driver).moveToElement(productLinks.get(i)).click().perform();

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
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Wait until at least one ad element exists
            wait.until(driver -> {
                Long count = (Long) ((JavascriptExecutor) driver).executeScript(
                        "return document.querySelectorAll(arguments[0]).length;",
                        AD_SELECTOR
                );
                return count > 0;
            });

            ((JavascriptExecutor) driver).executeScript(
                    """
                    var ads = document.querySelectorAll(arguments[0]);
                    ads && ads.length && ads.forEach(el => el.outerHTML = "");
                    """,
                    AD_SELECTOR
            );
        } catch (TimeoutException e) {
            // No ads present - continue
        }

        if (driver.getCurrentUrl().endsWith("#google_vignette")) {
            driver.navigate().back();
        }
    }

    public void scrollToBottom(){
        removeAds();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrolltoTop() {
        removeAds();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    public boolean OnTop() {
        return (Long) ((JavascriptExecutor) driver).executeScript("return window.pageYOffset;") == 0;
    }

    public void hoverAndClickOverProduct(String cssSelector, String childSelector, int index) {
        removeAds();
        List<WebElement> products = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(cssSelector))
        );

        new Actions(driver).moveToElement(products.get(index)).perform();

        WebElement childElement = products.get(index).findElement(By.cssSelector(childSelector));
        wait.until(ExpectedConditions.elementToBeClickable(childElement));
        childElement.click();
    }

    public void clickOnIndex(String cssSelector, int index) {
        removeAds();
        List<WebElement> products = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(cssSelector))
        );

        new Actions(driver).moveToElement(products.get(index)).click().perform();
    }

    public int numberOfElements(String cssSelector) {
        try {
            List<WebElement> elements = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(cssSelector))
            );
            return elements.size();
        } catch (TimeoutException e) {
            return 0;
        }
    }

    public boolean verifyPriceTimesQuantityEqualsTotal(String priceSelector, String qtySelector, String totalSelector) {
        int itemPrice = getValueOf(priceSelector);
        int itemQty = getValueOf(qtySelector);
        int totalPrice = getValueOf(totalSelector);
        return totalPrice == itemPrice * itemQty;
    }

    public void scrollTo(String cssSelector) {
        removeAds();
        WebElement element = driver.findElement(By.cssSelector(cssSelector));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                element
        );
    }

    public int countAllElementsLike(String selector) {
        removeAds();

        // Get all elements matching the selector
        List<WebElement> elements = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(selector))
        );

        return elements.size();
    }

    public void clickAllProductsAndDismissModal(String productWrapperSelector, String addToCartSelector, String continueShoppingSelector) {
        removeAds();

        // Get all product wrapper elements
        List<WebElement> products = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(productWrapperSelector))
        );

        // Iterate through each product
        for (WebElement product : products) {
            removeAds();

            // Find the "Add to Cart" button within this product
            WebElement addToCartButton = product.findElement(By.cssSelector(addToCartSelector));

            // Only scroll if element is not displayed
            if (!(addToCartButton).isDisplayed()) {
                scrollTo(addToCartSelector);
            }

            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
            addToCartButton.click();

            // Wait for the modal and click "Continue Shopping"
            WebElement continueButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector(continueShoppingSelector))
            );
            continueButton.click();

            // Wait for modal to close
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(continueShoppingSelector)));
        }
    }

    public void waitFor(int seconds){
        new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public String clickOnAndGetMessage(String buttonSelector, String messageSelector) {
        removeAds();
        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(buttonSelector))
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});",
                element
        );

        wait.until(ExpectedConditions.elementToBeClickable(element));

        // Block navigation temporarily
        ((JavascriptExecutor) driver).executeScript(
                "window.addEventListener('beforeunload', function(e) {" +
                        "    e.preventDefault();" +
                        "    e.returnValue = '';" +
                        "}, {once: true});" +
                        "arguments[0].click();",
                element
        );

        // Wait for and read success message
        WebElement successMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(messageSelector))
        );

        String message = successMsg.getText();

        // Allow navigation to proceed
        ((JavascriptExecutor) driver).executeScript(
                "window.onbeforeunload = null;"
        );

        return message;
    }

    public ExpectedCondition<Boolean> fileDownloaded(String fileName) {
        return _ -> {
            String downloads = System.getProperty("user.home") + "/Downloads";
            File file = new File(downloads, fileName);
            return file.exists();
        };
    }

    public boolean waitForFileDownload(String fileName, int timeoutSeconds) {
        WebDriverWait fileWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return fileWait.until(fileDownloaded(fileName));
    }

}
