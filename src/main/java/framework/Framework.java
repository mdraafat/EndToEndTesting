package framework;

import framework.utils.Driver;
import org.openqa.selenium.WebDriver;

public class Framework {
    private final WebDriver driver;

    private Framework(Driver driverType) {
        this.driver = driverType.create();
    }

    private static Framework start(Driver driverType) {
        return new Framework(driverType);
    }

    public static Framework startChrome() {
        return start(Driver.chrome);
    }

    public static Framework startFirefox() {
        return start(Driver.firefox);
    }

    public static Framework startEdge() {
        return start(Driver.edge);
    }

    public static Framework startSafari() {
        return start(Driver.safari);
    }

    public Framework goToURL(String url) {
        driver.get(url);
        return this;
    }

    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}