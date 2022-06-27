package components;


import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HamburgerMenu {
    //TODO think about more user friendly locator :))
    String MENU_ITEM = "//div[@class='hmenu-item hmenu-title '][contains(text(), '",
           SUB_MAIN_MENU_ITEM = "')]/following::div[contains(text(), '",
           SUB_SUB_MENU_ITEM = "')]/following::a[contains(text(), '",
           LEFT_MAIN_FILTERS_BLOCK_NAME = "//span[@class='a-size-base a-color-base a-text-bold'][contains(text(), '",
           LEFT_MAIN_FILTER_NAME= "//span[@class='a-size-base a-color-base'][contains(text(), '",
           ABOUT_PRODUCT_FEATURES = "#feature-bullets";

    @Step("Scroll to main menu element")
    public void scrollToMainMenuElement(String menuItem){
        $("#hmenu-content").shouldBe(visible).hover();
        $(byText(menuItem)).scrollIntoView("{block: \"center\"}");
    }

    @Step("Click to main menu element in certain block")
    public void clickToMainMenuElement(String menuItem, String menuSubItem) {
        $("#hmenu-content").hover();
        $x(MENU_ITEM + menuItem+ SUB_MAIN_MENU_ITEM + menuSubItem+"')]").click();
    }

    @Step("Click to submain menu element in certain block")
    public void clickToSubMainMenuElement(String subMenuItem, String subMenuSubItem) {
        $("#hmenu-content").hover();
        $x(MENU_ITEM + subMenuItem+ SUB_SUB_MENU_ITEM +subMenuSubItem+"')]").click();
    }

    @Step("Scroll to certain filters block and select filter")
    public void scrollToCertainFiltersBlockAndSelectFilter(String filtersBlockName,String filterName) {
        $x(LEFT_MAIN_FILTERS_BLOCK_NAME + filtersBlockName+"')]").scrollTo();
        $x(LEFT_MAIN_FILTER_NAME + filterName+"')]").click();
    }

    @Step("Sort the Samsung results with price High to Low")
    public void sortResultsFromHighToLow(String sortType) {
        $(".a-dropdown-label").shouldHave(text("Sort by:")).click();
        $(byAttribute("data-value","{\"stringVal\":\"price-desc-rank\"}")).click();
        $(".a-dropdown-prompt").shouldHave(text(sortType));
    }

    @Step("Click on the certain num of priced item in search result")
    public void clickToCertainSearchResultItem(Integer searchResultItemNumber) {
        ElementsCollection searchResults = $$(byAttribute("data-component-type","s-search-result"));
        System.out.println("searchResultAmount - " + $$(searchResults).size());
        $$(searchResults).get(1).click();
    }
    @Step("Assert block 'About this item' is exist")
    public void checkBlockHaveText(String requiredText) {
        $(ABOUT_PRODUCT_FEATURES).shouldHave(text(requiredText));
        String existingText = $(ABOUT_PRODUCT_FEATURES).$("h1").getText();
        System.out.println("existingText is:"+existingText);
    }
}
