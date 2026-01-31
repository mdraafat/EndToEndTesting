package page;

import util.framework.Framework;

public class HomePage {

    private final Framework framework;

    // Locators
    private static final String SLIDER_CAROUSEL = "#slider-carousel";
    private static final String SIGNUP_LOGIN_LINK = ".nav > li:nth-child(4) > a";
    private static final String LOGGED_IN_TEXT = ".nav > li:nth-child(10) > a";
    private static final String DELETE_ACCOUNT_LINK = ".nav > li:nth-child(5) > a";
    private static final String TEST_CASES_LINK = ".nav > li:nth-child(5) > a";
    private static final String CONTACT_US_LINK = ".nav > li:nth-child(8) > a";
    private static final String ACCOUNT_DELETED_MESSAGE = "[data-qa='account-deleted']";
    private static final String SUBSCRIPTION_TEXT = "div.single-widget > h2";
    private static final String EMAIL_INPUT = "input#susbscribe_email";
    private static final String BUTTON_SUBSCRIBE = "button#subscribe";
    private static final String SUBSCRIPTION_SUCCESS_ALERT = ".alert-success";
    private static final String ARROW_BUTTON = "a#scrollUp";
    private static final String MAIN_TEXT = "#slider-carousel  h2";
    private static final String ADD_TO_CART = ".add-to-cart";
    private static final String PRODUCT_CARD_LINK = "div.choose > ul > li > a";
    private static final String VIEW_CART = ".modal-body > p > a";
    private static final String CATEGORY_PRODUCTS = "div.category-products";
    private static final String IMAGE_WRAPPER = ".product-image-wrapper";
    private static final String WOMEN_LINK_CAT = "#accordian > div:nth-child(1) h4 > a";
    private static final String MEN_LINK_CAT = "#accordian > div:nth-child(2) h4 > a";
    private static final String DRESS_LINK_CAT = "div#Women > div > ul > li:nth-of-type(1) > a";
    private static final String TSHIRTS_LINK_CAT = "div#Men > div > ul > li:nth-of-type(1) > a";
    private static final String WOMEN_DRESS_TEXT = ".features_items > h2";
    private static final String MEN_TSHIRT_TEXT = ".features_items > h2";
    private static final String RECOMMENDED_ITEMS = ".recommended_items > h2";
    private static final String RECOMMENDED_ITEM = "#recommended-item-carousel div.productinfo > a";
    private static final String REGISTER_LINK = "div.modal-body > p:nth-child(2) > a";



    public HomePage(Framework framework) {
        this.framework = framework;
    }

    public void goToHome() {
        framework.goToHome();
    }

    public boolean isHomePageDisplayed() {
        return framework.isPresent(SLIDER_CAROUSEL);
    }

    public void clickSignupLoginLink() {
        framework.clickOn(SIGNUP_LOGIN_LINK);
    }

    public boolean checkLoggedInAsText() {
        String actualText = framework.getTextOf(LOGGED_IN_TEXT);
        return actualText.contains("Logged in as");
    }

    public void clickDelete() {
        framework.clickOn(DELETE_ACCOUNT_LINK);
    }

    public String getAccountDeletedText() {
        return framework.getTextOf(ACCOUNT_DELETED_MESSAGE);
    }

    public void clickContactUsLink() {
        framework.clickOn(CONTACT_US_LINK);
    }

    public void clickTestCasesLink() {
        framework.clickOn(TEST_CASES_LINK);
    }

    public boolean isTestCasesDisplayed() {
        return framework.isCurrentEndpoint("test_cases");
    }

    public void scrollToFooter() {
        framework.scrollToBottom();
    }

    public boolean isSubscriptionDisplayed() {
        return framework.isPresent(SUBSCRIPTION_TEXT);
    }

    public void enterEmailForSubscribe(String email) {
        framework.sendTo(EMAIL_INPUT, email);
        framework.clickOn(BUTTON_SUBSCRIBE);
    }

    public boolean isSubscriptionSuccess() {
        return framework.isPresent(SUBSCRIPTION_SUCCESS_ALERT);
    }

    public void scrollToHeaderViaButton() {
        framework.clickOn(ARROW_BUTTON);
    }

    public boolean isMainTextFullyDisplayed() {
        return framework.isPresent(MAIN_TEXT) && framework.OnTop();
    }

    public void scrollToTop() {
        framework.scrolltoTop();
    }

    public void clickProduct() {
        framework.clickOn(PRODUCT_CARD_LINK);
    }

    public void clickOnFirstProduct() {
        framework.clickOnIndex(ADD_TO_CART,0);
    }

    public void clickViewCart() {
        framework.clickOn(VIEW_CART);
    }

    public void scrollToFirstProduct() {
        framework.scrollTo(IMAGE_WRAPPER);
    }

    public boolean isCategoriesSectionVisible() {
        return framework.isPresent(CATEGORY_PRODUCTS);
    }

    public void clickOnWomenCategory() {
        framework.clickOn(WOMEN_LINK_CAT);
    }

    public void clickOnDressCategory() {
        framework.clickOn(DRESS_LINK_CAT);
    }

    public String getWomenDressCategoryText() {
        return framework.getTextOf(WOMEN_DRESS_TEXT).toUpperCase();
    }
    public void clickOnMenCategory() {
        framework.clickOn(MEN_LINK_CAT);
    }

    public void clickOnTshirtCategory() {
        framework.clickOn(TSHIRTS_LINK_CAT);
    }

    public String getMenTshirtCategoryText() {
        return framework.getTextOf(MEN_TSHIRT_TEXT).toUpperCase();
    }


    public void scrollToCategories() {
        framework.scrollTo(CATEGORY_PRODUCTS);
    }

    public void ScrollToRecommended() {
        framework.scrollTo(RECOMMENDED_ITEMS);
    }

    public boolean isRecommendedDisplayed() {
        return framework.isPresent(RECOMMENDED_ITEMS);
    }

    public void clickOnRecommendedProduct() {
        framework.clickOn(RECOMMENDED_ITEM);
    }

    public void clickRegisterLoginLink() {
        framework.clickOn(REGISTER_LINK);
    }
}