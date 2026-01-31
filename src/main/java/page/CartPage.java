package page;

import util.framework.Framework;

public class CartPage {

    private final Framework framework;


    private static final String SUBSCRIPTION_TEXT = "div.single-widget > h2";
    private static final String EMAIL_INPUT = "input#susbscribe_email";
    private static final String BUTTON_SUBSCRIBE = "button#subscribe";
    private static final String SUBSCRIPTION_SUCCESS_ALERT = ".alert-success";
    private static final String CART_LINK = ".nav > li:nth-child(3) > a";
    private static final String CART_ITEM = "#cart_info_table > tbody > tr";
    private static final String ITEM_PRICE = "#product-%d > td.cart_price > p";
    private static final String ITEM_QTY = "#product-%d > td.cart_quantity > button";
    private static final String TOTAL_PRICE = "#product-%d > td.cart_total > p.cart_total_price";
    private static final String DELETE_BUTTON = "#product-%d > td.cart_delete > a";
    private static final String EMPTY_CART = "p.text-center b";
    private static final String PROCEED_TO_CHECKOUT = "a.check_out";


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

    public int noOfItemsInCart() {
        return framework.numberOfElements(CART_ITEM);
    }

    public String qtyofTheItemInCartRow() {
        return framework.getTextOf(String.format(ITEM_QTY, 1));
    }

    public boolean verifyFirstItemTotal() {
        return framework.verifyPriceTimesQuantityEqualsTotal(
                String.format(ITEM_PRICE, 1),
                String.format(ITEM_QTY, 1),
                String.format(TOTAL_PRICE, 1)
        );
    }

    public boolean verifySecondItemTotal() {
        return framework.verifyPriceTimesQuantityEqualsTotal(
                String.format(ITEM_PRICE, 2),
                String.format(ITEM_QTY, 2),
                String.format(TOTAL_PRICE, 2)
        );
    }

    public void removeProduct(int productId) {
        framework.clickOn(String.format(DELETE_BUTTON, productId));
    }

    public boolean isCartPageDisplayed() {
        return framework.isCurrentEndpoint("view_cart");
    }

    public String getCartEmptyDisplayedText() {
        return framework.getTextOf(EMPTY_CART);
    }

    public void clickProceedToCheckout() {
        framework.clickOn(PROCEED_TO_CHECKOUT);
    }
}
