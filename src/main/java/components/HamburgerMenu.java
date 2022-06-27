package components;


import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HamburgerMenu {
    //TODO think about more user friendly locator :))
    String MENU_ITEM = "//div[@class='hmenu-item hmenu-title '][contains(text(), '",
           SUB_MAIN_MENU_ITEM = "')]/following::div[contains(text(), '",
           SUB_SUB_MENU_ITEM = "')]/following::a[contains(text(), '",
           LEFT_MAIN_FILTERS_BLOCK_NAME = "//span[@class='a-size-base a-color-base a-text-bold'][contains(text(), '",
           LEFT_MAIN_FILTER_NAME= "//span[@class='a-size-base a-color-base'][contains(text(), '";

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
}

