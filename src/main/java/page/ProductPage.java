package page;

import util.framework.Framework;

public class ProductPage {

    private final Framework framework;

    private static final String PRODUCTS_LINK = ".nav > li:nth-child(2) > a";
    private static final String PRODUCT_LIST = "div.features_items";
    private static final String PRODUCT_CARD_LINK = "div.choose > ul > li > a";
    private static final String PRODUCT_NAME = "div.product-information > h2";
    private static final String PRODUCT_NAME_ON_LIST = "div.productinfo > p";
    private static final String PRODUCT_CATEGORY = "div.product-information > p:nth-child(3)";
    private static final String PRODUCT_PRICE = "div.product-information > span > span";
    private static final String PRODUCT_AVAILABILITY = "div.product-information > p:nth-child(6)";
    private static final String PRODUCT_CONDITION = "div.product-information > p:nth-child(7)";
    private static final String PRODUCT_BRAND = "div.product-information > p:nth-child(8)";
    private static final String SEARCH_BAR = "input#search_product";
    private static final String SEARCH_BUTTON = "button#submit_search";
    private static final String SEARCHED_PRODUCTS = "div.features_items > h2";


    public ProductPage(Framework framework) {
        this.framework = framework;
    }


    public void clickProductsLink() {
        framework.clickOn(PRODUCTS_LINK);
    }

    public boolean isProductsPageDisplayed() {
        return framework.isCurrentEndpoint("products");
    }

    public boolean isProductListDisplayed() {
        return framework.isPresent(PRODUCT_LIST);
    }

    public void clickProduct() {
        framework.clickOn(PRODUCT_CARD_LINK);
    }

    public boolean isFirstProductDetailsDisplayed() {
        return framework.isCurrentEndpoint("product_details/1");
    }

    public boolean areFirstProductDetailsInformationDisplayed() {
        return framework.isPresent(PRODUCT_NAME) && framework.isPresent(PRODUCT_CATEGORY) &&
                framework.isPresent(PRODUCT_PRICE) && framework.isPresent(PRODUCT_AVAILABILITY)
                && framework.isPresent(PRODUCT_CONDITION) && framework.isPresent(PRODUCT_BRAND);
    }

    public void searchForProductName(String searchKeyword) {
        framework.sendTo(SEARCH_BAR, searchKeyword);
    }
    public void clickSearchButton() {
        framework.clickOn(SEARCH_BUTTON);
    }

    public boolean isSearchedProductsDisplayed() {
        return framework.isPresent(SEARCHED_PRODUCTS);
    }

    public boolean checkSearchedProducts(String searchKeyword) {
        return framework.clickOnEachAndCheck(PRODUCT_CARD_LINK, PRODUCT_CATEGORY, searchKeyword, PRODUCT_NAME_ON_LIST);
    }

}
