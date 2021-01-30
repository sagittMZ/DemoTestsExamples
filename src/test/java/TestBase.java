import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentsHelper.*;


public class TestBase {
    @BeforeAll
    static void setup() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);  // add ui visibility fot user during the test - running
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
          //command to run: gradle test -Dremote.browser.url=selenoid.autotests.cloud + requared add to build.gradle in test part this:
          //test {
          //    systemProperties = System.properties
          //    useJUnitPlatform()
          //}
        //Configuration.remote = "https://user1:1234@"+System.getProperty("remote.browser.url") +":4444/wd/hub/";

       // Configuration.remote = "wait url from devops";
        Configuration.browserVersion = "87.0";
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