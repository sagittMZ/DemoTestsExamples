import com.codeborne.selenide.Configuration;
import configs.ExampleConfig;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentsHelper.*;


public class TestBase {
    @BeforeAll
    static void setup() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        ExampleConfig config = ConfigFactory.newInstance().create(ExampleConfig.class);
        String selenoid_password = config.selenoid_password();
        String selenoid_user=config.selenoid_user();
        String remote_browser_url = config.remote_browser_url();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);  // add ui visibility fot user during the test - running
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
          //command to run: gradle test -Dremote.browser.url=selenoid.autotests.cloud + requared add to build.gradle in test part this:
          //test {
          //    systemProperties = System.properties
          //    useJUnitPlatform()
          //}
        Configuration.remote = "https://"+ selenoid_user+":"+ selenoid_password+"@"+remote_browser_url+"/wd/hub/";  //так же можно это указывать в параметрах джобы дженкинс

        Configuration.browserVersion = "100.0";
        Configuration.startMaximized = true;
    }

    @AfterEach
    @Step("Attachments")
    public void afterEach(){
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        attachVideo();

        closeWebDriver();
    }


}