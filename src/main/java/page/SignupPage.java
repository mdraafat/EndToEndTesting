package page;

import framework.Framework;
import util.User;

public class SignupPage {

    private final Framework framework;

    public SignupPage(Framework framework) {
        this.framework = framework;
    }

    public void navigateToHome() {
        framework.goToUrl("https://automationexercise.com/");
    }

    public boolean isHomePageDisplayed() {
        return framework.isPresent("#slider-carousel");
    }

    public void clickSignupLoginLink() {
        framework.clickOn(".nav > li:nth-child(4) > a");
    }

    public String getNewUserSignupDisplayedText() {
        return framework.text("div.signup-form > h2");
    }

    public void enterEmailAndAddress(User user) {
        framework.sendText("[data-qa='signup-name']", user.name);
        framework.sendText("[data-qa='signup-email']", user.email);
        framework.clickOn("[data-qa='signup-button']");
    }

    public String getAccountInformationDisplayedText() {
        return framework.text("div.login-form > h2");
    }

    public void fillNewAccountAndAddressInformation(User user) {

        if ("Male".equalsIgnoreCase(user.gender)) {
            framework.clickOn("input#id_gender1");
        } else {
            framework.clickOn("input#id_gender2");
        }

        framework.sendText("input#password.form-control", user.password);

        framework.selectOption("select#days", String.valueOf(user.day));
        framework.selectOption("select#months", user.month);
        framework.selectOption("select#years", String.valueOf(user.year));

        framework.clickOn("input#newsletter");
        framework.clickOn("input#optin");

        framework.sendText("input#first_name", user.firstName);
        framework.sendText("input#last_name", user.lastName);
        framework.sendText("input#company", user.company);
        framework.sendText("input#address1", user.address1);
        framework.sendText("input#address2", user.address2);
        framework.sendText("input#state", user.state);
        framework.sendText("input#city", user.city);
        framework.sendText("input#zipcode", String.valueOf(user.zipcode));
        framework.sendText("input#mobile_number", String.valueOf(user.mobileNumber));
        framework.selectOption("select#country", user.country);
    }

    public void createAccount() {
        framework.waitForAllRequiredFieldsFilled();
        framework.clickOn("[data-qa='create-account']");
    }

    public String getAccountCreatedText() {
        return framework.text("[data-qa='account-created']");
    }

    public void clickContinue() {
        framework.clickOn("[data-qa='continue-button']");
    }

    public Boolean checkLoggedInAsText() {
        return framework.text(".navbar-nav > li:last-child > a").contains("Logged in as");
    }

    public void clickDelete() {
        framework.clickOn(".navbar-nav > li:nth-child(5) > a");
    }

    public String getAccountDeletedText() {
        return framework.text("[data-qa='account-deleted']");
    }
}