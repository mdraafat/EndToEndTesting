package page;

import util.framework.Framework;
import util.data.User;

public class SignupPage {

    private final Framework framework;

    // Locators
    private static final String NEW_USER_SIGNUP_HEADING = "div.signup-form > h2";
    private static final String SIGNUP_NAME_INPUT = "[data-qa='signup-name']";
    private static final String SIGNUP_EMAIL_INPUT = "[data-qa='signup-email']";
    private static final String SIGNUP_BUTTON = "[data-qa='signup-button']";
    private static final String ACCOUNT_INFO_HEADING = "div.login-form > h2";
    private static final String GENDER_MALE_RADIO = "input#id_gender1";
    private static final String GENDER_FEMALE_RADIO = "input#id_gender2";
    private static final String PASSWORD_INPUT = "input#password.form-control";
    private static final String DAY_DROPDOWN = "select#days";
    private static final String MONTH_DROPDOWN = "select#months";
    private static final String YEAR_DROPDOWN = "select#years";
    private static final String NEWSLETTER_CHECKBOX = "input#newsletter";
    private static final String SPECIAL_OFFERS_CHECKBOX = "input#optin";
    private static final String FIRST_NAME_INPUT = "input#first_name";
    private static final String LAST_NAME_INPUT = "input#last_name";
    private static final String COMPANY_INPUT = "input#company";
    private static final String ADDRESS1_INPUT = "input#address1";
    private static final String ADDRESS2_INPUT = "input#address2";
    private static final String COUNTRY_DROPDOWN = "select#country";
    private static final String STATE_INPUT = "input#state";
    private static final String CITY_INPUT = "input#city";
    private static final String ZIPCODE_INPUT = "input#zipcode";
    private static final String MOBILE_NUMBER_INPUT = "input#mobile_number";
    private static final String CREATE_ACCOUNT_BUTTON = "[data-qa='create-account']";
    private static final String REQUIRED_FIELDS = ".required input, .required select";
    private static final String ACCOUNT_CREATED_MESSAGE = "[data-qa='account-created']";
    private static final String CONTINUE_BUTTON = "[data-qa='continue-button']";
    private static final String ACCOUNT_DELETED_MESSAGE = "[data-qa='account-deleted']";
    private static final String EMAIL_EXISTS_MESSAGE = "div.signup-form > form > p";
    private static final String LOGGED_IN_TEXT = ".nav > li:nth-child(10) > a";


    public SignupPage(Framework framework) {
        this.framework = framework;
    }

    public String getNewUserSignupDisplayedText() {
        return framework.verify(NEW_USER_SIGNUP_HEADING);
    }

    public void enterNameAndEmail(User user) {
        framework.sendText(SIGNUP_NAME_INPUT, user.getName());
        framework.sendText(SIGNUP_EMAIL_INPUT, user.getEmail());
        framework.clickOn(SIGNUP_BUTTON);
    }

    public String getAccountInformationDisplayedText() {
        return framework.verify(ACCOUNT_INFO_HEADING);
    }

    public void fillNewAccountAndAddressInformation(User user) {

        // Gender selection
        if ("Male".equalsIgnoreCase(user.getGender())) {
            framework.clickOn(GENDER_MALE_RADIO);
        } else {
            framework.clickOn(GENDER_FEMALE_RADIO);
        }

        // Account information
        framework.sendText(PASSWORD_INPUT, user.getPassword());
        framework.selectOption(DAY_DROPDOWN, String.valueOf(user.getDay()));
        framework.selectOption(MONTH_DROPDOWN, user.getMonth());
        framework.selectOption(YEAR_DROPDOWN, String.valueOf(user.getYear()));
        framework.clickOn(NEWSLETTER_CHECKBOX);
        framework.clickOn(SPECIAL_OFFERS_CHECKBOX);

        // Address information
        framework.sendText(FIRST_NAME_INPUT, user.getFirstName());
        framework.sendText(LAST_NAME_INPUT, user.getLastName());
        framework.sendText(COMPANY_INPUT, user.getCompany());
        framework.sendText(ADDRESS1_INPUT, user.getAddress1());
        framework.sendText(ADDRESS2_INPUT, user.getAddress2());
        framework.sendText(STATE_INPUT, user.getState());
        framework.sendText(CITY_INPUT, user.getCity());
        framework.sendText(ZIPCODE_INPUT, String.valueOf(user.getZipcode()));
        framework.sendText(MOBILE_NUMBER_INPUT, String.valueOf(user.getMobileNumber()));
        framework.selectOption(COUNTRY_DROPDOWN, user.getCountry());


    }

    public void createAccount() {

        framework.waitForAllRequiredFields(REQUIRED_FIELDS);
        framework.clickOn(CREATE_ACCOUNT_BUTTON);
    }

    public String getAccountCreatedText() {
        return framework.verify(ACCOUNT_CREATED_MESSAGE);
    }

    public void clickContinue() {
        framework.clickOn(CONTINUE_BUTTON);
    }

    public String getAccountDeletedText() {
        return framework.verify(ACCOUNT_DELETED_MESSAGE);
    }

    public boolean checkLoggedInAsText() {
        String actualText = framework.verify(LOGGED_IN_TEXT);
        return actualText.contains("Logged in as");
    }

    public String getAlreadyExistMessage() {
        return framework.verify(EMAIL_EXISTS_MESSAGE);
    }
}