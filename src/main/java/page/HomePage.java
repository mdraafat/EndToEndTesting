package page;

import util.framework.Framework;

public class HomePage {

    private final Framework framework;

    // Locators
    private static final String HOME_URL = "https://automationexercise.com/";
    private static final String SLIDER_CAROUSEL = "#slider-carousel";
    private static final String SIGNUP_LOGIN_LINK = ".nav > li:nth-child(4) > a";
    private static final String LOGGED_IN_TEXT = ".nav > li:nth-child(10) > a";
    private static final String DELETE_ACCOUNT_LINK = ".nav > li:nth-child(5) > a";
    private static final String ACCOUNT_DELETED_MESSAGE = "[data-qa='account-deleted']";

    public HomePage(Framework framework) {
        this.framework = framework;
    }

    public void navigateToHome() {
        framework.goToUrl(HOME_URL);
    }

    public boolean isHomePageDisplayed() {
        return framework.isPresent(SLIDER_CAROUSEL);
    }

    public void clickSignupLoginLink() {
        framework.clickOn(SIGNUP_LOGIN_LINK);
    }

    public boolean checkLoggedInAsText() {
        String actualText = framework.verify(LOGGED_IN_TEXT);
        return actualText.contains("Logged in as");
    }

    public void clickDelete() {
        framework.clickOn(DELETE_ACCOUNT_LINK);
    }

    public String getAccountDeletedText() {
        return framework.verify(ACCOUNT_DELETED_MESSAGE);
    }
}