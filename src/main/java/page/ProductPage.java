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
    private static final String IMAGE_WRAPPER = ".product-image-wrapper";
    private static final String ADD_TO_CART = ".product-overlay > .overlay-content >  a";
    private static final String ADD_TO_CART_BUTTON = "a.btn.btn-default.add-to-cart";
    private static final String CONTINUE_SHOPPING = "button.close-modal";
    private static final String VIEW_CART = ".modal-body > p > a";
    private static final String WRITE_REVIEW = "ul.nav-tabs > li.active > a";
    private static final String INPUT_NAME = "input#name";
    private static final String INPUT_EMAIL = "input#email";
    private static final String INPUT_REVIEW = "textarea#review";
    private static final String SUBMIT_BUTTON = "button#button-review";
    private static final String SUCCESS_ALERT = "div.alert-success > span";
    private static final String QUANTITY_INPUT = "input#quantity";
    private static final String CART_BUTTON = "button.cart";
    private static final String BRANDS_SECTION = "div.brands_products";
    private static final String FIRST_BRAND = "div.brands-name > ul > li:nth-of-type(1) > a";
    private static final String FIRST_BRAND_TEXT = ".features_items > h2";
    private static final String SECOND_BRAND = "div.brands-name > ul > li:nth-of-type(2) > a";
    private static final String SECOND_BRAND_TEXT = ".features_items > h2";



    public ProductPage(Framework framework) {
        this.framework = framework;
    }


    public void clickProductsLink() {
        framework.clickOn(PRODUCTS_LINK);
    }

    public void scrollToFirstProduct() {
        framework.scrollTo(IMAGE_WRAPPER);
    }

    public boolean isProductsPageDisplayed() {
        return framework.isCurrentEndpoint("products");
    }

    public boolean isProductListDisplayed() {
        return framework.isPresent(PRODUCT_LIST);
    }

    public void clickProduct() {
        framework.scrollTo(PRODUCT_CARD_LINK);
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

    public void hoverAndClickOnFirstProduct() {
        framework.hoverAndClickOverProduct(IMAGE_WRAPPER, ADD_TO_CART,0);
    }

    public void hoverAndClickOnSecondProduct() {
        framework.hoverAndClickOverProduct(IMAGE_WRAPPER, ADD_TO_CART,1);
    }

    public void clickContinueShopping() {
        framework.clickOn(CONTINUE_SHOPPING);
    }

    public void clickViewCart() {
        framework.clickOn(VIEW_CART);
    }

    public boolean isWriteYourReviewDisplayed() {
        framework.scrollTo(WRITE_REVIEW);
        return framework.isPresent(WRITE_REVIEW);
    }

    public void fillInReview(){
        framework.sendTo(INPUT_NAME, "raafat");
        framework.sendTo(INPUT_EMAIL, "raafat@gmail.com");
        framework.sendTo(INPUT_REVIEW, "awesome");
    }

    public String clickSubmit(){
        framework.clickOn(SUBMIT_BUTTON);
        return framework.getTextOf(SUCCESS_ALERT);
    }

    public void increaseQuantityTo4() {
        framework.sendTo(QUANTITY_INPUT, "4");
    }

    public void clickAddToCart() {
        framework.clickOn(CART_BUTTON);
    }


    public void scrollToBrands() {
        framework.scrollTo(BRANDS_SECTION);
    }

    public boolean isBrandsSectionVisible() {
        return framework.isPresent(BRANDS_SECTION);
    }

    public void clickOnFirstBrandName() {
        framework.clickOn(FIRST_BRAND);
    }

    public String getFirstBrandNameText() {
        return framework.getTextOf(FIRST_BRAND_TEXT).toUpperCase();
    }

    public String getSecondBrandNameText() {
        return framework.getTextOf(SECOND_BRAND_TEXT).toUpperCase();
    }

    public void clickOnSecondBrandName() {
        framework.clickOn(SECOND_BRAND);
    }

    public void clickAddToCartForAllResults() {
        framework.clickAllProductsAndDismissModal(IMAGE_WRAPPER, ADD_TO_CART_BUTTON, CONTINUE_SHOPPING);
    }

    public int numberOfProductsInSearchResult() {
        return framework.countAllElementsLike(ADD_TO_CART);
    }

    public void scrollToHeader() {
        framework.scrolltoTop();
    }
}