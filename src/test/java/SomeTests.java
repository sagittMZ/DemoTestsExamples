import com.codeborne.selenide.Condition;
import configs.ExampleConfig;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SomeTests extends TestBase{
    //TODO create api authorisation method

    ExampleConfig config = ConfigFactory.newInstance().create(ExampleConfig.class);
    String destination = config.destination();
    public String setDatePlusFromNow(int day){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDateTime.now().plusDays(day).format(formatter);
    }

    @Test
    @DisplayName("Check travelers number switcher")
    @Feature("Avionero project")
    @Link(url="https://Avionero.ru", name ="RUS")
    @Owner("s_a_g_i_t_t")
    public void travelersNumberSwitcher() {
        step("Open site url", ()-> open("https://avionero.ru/"));
        step("Go to travelers number switcher", ()-> $(byText("1 взрослый, Эконом")).parent().click());
        step("Set two travelers", ()-> $(byTitle("2 взрослых ")).click());
        step("Check of travelers counter value", ()-> assertEquals("2",$(".passengers-counter",0).$(".counter__value").getOwnText()));
        step("Check number of travellers at filters", ()-> {
            $(byText("2 взрослых, Эконом")).shouldBe(Condition.visible);
        });
    }

    @Test
    @DisplayName("Set destination options")
    @Feature("Avionero project")
    @Link(url="https://Avionero.ru", name ="RUS")
    @Owner("s_a_g_i_t_t")
    public void setDestinationOptions() {
        step("Open site url", ()-> {
            String destination = "Москва-"+ this.destination;
            open("https://avionero.ru/"+destination+".MOW?adults=2");
        });
        step("Set destination", ()-> $(by("data-cy","to-input")).parent().click());
        step("Go to 'Show more' options", ()-> {
            $(byText("Показать еще...")).click();
        });
        step("Set 'Asia' as an option", ()-> {
            $(byText("Азия")).click();
        });
        step("Set 'Island' as an option", ()-> {
            $(byText("Остров")).click();
        });
        step("Set 'Surf' as an option", ()-> {
            $(byText("Серфинг")).click();
        });
    }

    @Test
    @DisplayName("Set flex date")
    @Feature("Avionero project")
    @Link(url="https://Avionero.ru", name ="RUS")
    @Owner("s_a_g_i_t_t")
    public void setFlexDepartDate() {
        step("Open site url", ()-> {
            String destination = "Москва-"+ this.destination;
            open("https://avionero.ru/"+destination+".MOW-A?adults=2&destination-themes=island%2Csurfing");
        });
        step("Go to calendar", ()-> {
            $(by("data-cy","calendars-input")).click();
        });
        step("Set 'flex date' as an option", ()-> {
            $(byText("Гибкие даты")).click();
        });
        step("Set start date", ()-> {
            //yep, this is really requared )))
            $x("//div[@data-cy='first_calendar-panel']//div[@class='calendar']/div[8]").scrollIntoView(false);
            $(by("data-cy","month-8-name")).click();
        });
        step("Set 'one-way ticket' as an option", ()-> {
            $(byText("Обратный билет не нужен")).parent().click();
        });
        step("Check search is successful", ()-> {
            $(byText("Рекомендованные билеты")).parent().click();
        });
    }

    @Test
    @DisplayName("Set fixed date")
    @Feature("Avionero project")
    @Link(url="https://Avionero.ru", name ="RUS")
    @Owner("s_a_g_i_t_t")
    public void setFixedDepartDate() {
        int MonthNumber = LocalDateTime.now().plusDays(7).getMonthValue();
        int dayOfMonth = LocalDateTime.now().plusDays(7).getDayOfMonth();
        System.out.println(MonthNumber + ":"+dayOfMonth);
        step("Open site url", ()-> {
            String destination = "Москва-"+ this.destination;
            open("https://avionero.ru/"+destination+".MOW-A?adults=2&destination-themes=island%2Csurfing");
        });
        step("Go to calendar", ()-> {
            $(by("data-cy","calendars-input")).click();
        });
        step("Set 'flex date' as an option", ()-> {
            $(byText("Точные даты")).click();
        });
        step("Set start date", ()-> {
            $(by("data-cy","month-"+MonthNumber))
                    .$(".calendar__number",dayOfMonth)
                    .parent()
                    .click();
        });
        step("Set 'one-way ticket' as an option", ()-> {
            $(byText("Обратный билет не нужен")).parent().click();
        });
        step("Check search is successful", ()-> {
            $(byText("Рекомендованные билеты")).parent().click();
        });
    }

    @Test
    @DisplayName("Check tickets snippet")
    @Feature("Avionero project")
    @Link(url="https://Avionero.ru", name ="RUS")
    @Owner("s_a_g_i_t_t")
    public void checkFlightsPageHeaderPopup() {
        step("Open site url", ()-> {
            String flyDate = setDatePlusFromNow(7);
            String destination = "Москва-"+ this.destination;
            open("https://avionero.ru/"+destination+".MOW-A?departure="+flyDate+"&adults=2&destination-themes=island%2Csurfing");
        });
        step("Click for 'Recommended tickets' snippet", ()-> {
            $(by("data-cy","flightsPageHeader")).sibling(0).click();
        });
        step("Check popup is showed", ()-> {
            $(byText("Как мы рекомендуем билеты")).shouldBe(Condition.visible);
        });
        step("Check popup is closed", ()-> {
            $(by("data-cy","flightsPageHeaderPopupCloseButton")).click();
            $(byText("Как мы рекомендуем билеты")).shouldNotBe(Condition.visible);
        });

    }
}
