package components;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.selector.ByText;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HamburgerMenu {
    @Step("Scroll to main menu element")
    public void scrollToMainMenuElement(String menuItem){
        $("#hmenu-content").hover();
        $(byText(menuItem)).scrollIntoView("{block: \"center\"}");

    }
    //TODO use more user friendly locator :))
    @Step("Click to main menu element in certain block")
    public void clickToMainMenuElement(String menuItem, String menuSubItem) {
        $("#hmenu-content").hover();
        $x("//div[@class='hmenu-item hmenu-title '][contains(text(), '"+menuItem+"')]/following::div[contains(text(), '"+menuSubItem+"')]").click();
    }

    @Step("Click to main menu element in certain block")
    public void clickToSubMainMenuElement(String subMenuItem, String subMenuSubItem) {
        $("#hmenu-content").hover();
        $x("//div[@class='hmenu-item hmenu-title '][contains(text(), '"+subMenuItem+"')]/following::a[contains(text(), '"+subMenuSubItem+"')]").click();
    }
}

