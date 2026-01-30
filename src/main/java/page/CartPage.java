package page;

import util.framework.Framework;

public class CartPage {

    private final Framework framework;


    private static final String SUBSCRIPTION_TEXT = "div.single-widget > h2";
    private static final String EMAIL_INPUT = "input#susbscribe_email";
    private static final String BUTTON_SUBSCRIBE = "button#subscribe";
    private static final String SUBSCRIPTION_SUCCESS_ALERT = ".alert-success";

    private static final String CART_LINK = ".nav > li:nth-child(3) > a";

    public CartPage(Framework framework) {
        this.framework = framework;
    }

    public void clickCartLink() {
        framework.clickOn(CART_LINK);
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
}
