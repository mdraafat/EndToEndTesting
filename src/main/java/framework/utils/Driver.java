package framework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public enum Driver {
    chrome {
        @Override
        public WebDriver create() {
            return new ChromeDriver();
        }
    },
    firefox {
        @Override
        public WebDriver create() {
            return new FirefoxDriver();
        }
    },
    edge {
        @Override
        public WebDriver create() {
            return new EdgeDriver();
        }
    },
    safari {
        @Override
        public WebDriver create() {
            return new SafariDriver();
        }
    };

    public abstract WebDriver create();
}