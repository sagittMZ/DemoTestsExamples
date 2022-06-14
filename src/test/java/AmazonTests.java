import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import configs.ExampleConfig;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;


public class AmazonTests extends TestBase{
    //TODO Note: Clicking on above URL may redirect you to amazon.com. Change it to amazon.in

    ExampleConfig config = ConfigFactory.newInstance().create(ExampleConfig.class);
    URL siteUrl= config.site_url();


    @Test
    @DisplayName("AmazonTests")
    @Feature("clipboardhealth project")
    @Tags({@Tag("Amazon"),@Tag("Smoke")})
    @Owner("s_a_g_i_t_t")
    public void demoAmazon() {
        step("Open site url", ()-> open(siteUrl));

        step("Click on the hamburger menu in the top left corner", ()-> {
            //do smth
        });

        step("Scroll own and then Click on the TV, Appliances and Electronics link under Shop by Department section.", ()-> {
            //do smth
        });

        step("Then click on Televisions under Tv, Audio & Cameras sub section.", ()-> {
            //do smth
        });

        step("Scroll down and filter the results by Brand ‘Samsung’.", ()-> {
            //do smth
        });

        step("Sort the Samsung results with price High to Low.", ()-> {
            //do smth
        });

        step("Click on the second highest priced item (whatever that maybe at the time of automating).", ()-> {
            //do smth
        });

        step("Click on the second highest priced item (whatever that maybe at the time of automating).", ()-> {
            //do smth
        });

/*        step("Switch the Window", ()-> {
            Selenide.switchTo().window(1);
        });*/

        step("Assert that “About this item” section is present and log this section text to console/report.", ()-> {
            //do smth
        });
    }

    @Test
    @DisplayName("AmazonTests")
    @Feature("clipboardhealth project")
    @Tags({@Tag("Amazon"),@Tag("Skip")})
    @Owner("s_a_g_i_t_t")
    public void demoAmazonSkip() {
        step("Open site url", ()-> open(siteUrl));

        step("Just for tags using", ()-> {
            //do nothing, just for skip example
        });
    }

}