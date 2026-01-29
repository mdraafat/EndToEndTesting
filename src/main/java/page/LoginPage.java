package page;

import util.data.User;
import util.framework.Framework;

public class LoginPage {

    private final Framework framework;

    // Locators
    private static final String USER_LOGIN_HEADING = "div.login-form > h2";
    private static final String LOGIN_EMAIL_INPUT = "[data-qa='login-email']";
    private static final String LOGIN_PASSWORD_INPUT = "[data-qa='login-password']";
    private static final String LOGIN_BUTTON = "[data-qa='login-button']";
    private static final String ACCOUNT_DELETED_MESSAGE = "[data-qa='account-deleted']";
    private static final String LOGGED_IN_TEXT = ".nav > li:nth-child(10) > a";
    private static final String LOGOUT_LINK = ".nav > li:nth-child(4) > a";

    private static final String WRONG_CREDENTIALS_MESSAGE = "div.login-form > form > p";

    public LoginPage(Framework framework) {
        this.framework = framework;
    }

    public String getLoginIntoAccountDisplayedText() {
        return framework.verify(USER_LOGIN_HEADING);
    }

    public void enterEmailAndPassword(User user) {

        framework.sendText(LOGIN_EMAIL_INPUT, user.getEmail());
        framework.sendText(LOGIN_PASSWORD_INPUT, user.getPassword());
        framework.clickOn(LOGIN_BUTTON);
    }

    public void enterInCorrectEmailAndPassword(User user) {
        framework.sendText(LOGIN_EMAIL_INPUT, user.getEmail() + "invalid");
        framework.sendText(LOGIN_PASSWORD_INPUT, user.getPassword() + "wrong");
        framework.clickOn(LOGIN_BUTTON);
    }

    public String getAccountDeletedText() {
        return framework.verify(ACCOUNT_DELETED_MESSAGE);
    }

    public boolean checkLoggedInAsText() {
        String actualText = framework.verify(LOGGED_IN_TEXT);
        return actualText.contains("Logged in as");
    }

    public String getWrongCredentialsMessage() {
        return framework.verify(WRONG_CREDENTIALS_MESSAGE);
    }

    public void clickLogout() { framework.clickOn(LOGOUT_LINK); }

    public boolean isLoginPageDisplayed() {
        return framework.isPresent(USER_LOGIN_HEADING);
    }
}
