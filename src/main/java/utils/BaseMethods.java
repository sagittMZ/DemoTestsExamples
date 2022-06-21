package utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import configs.ExampleConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;

import java.net.URL;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.SessionStorageConditions.itemWithValue;
import static com.codeborne.selenide.WebDriverRunner.url;
import static io.qameta.allure.Allure.step;
import static com.codeborne.selenide.WebDriverConditions.*;

public class BaseMethods {
    ExampleConfig config = ConfigFactory.newInstance().create(ExampleConfig.class);
    URL siteUrl= config.site_url();


    @Step("Check redirect to *.com and return back if required")
    public void checkMainPageRedirect(){
        String currentUrl = siteUrl.toString()+"/cggg";
        String sessionStorageItem = sessionStorage().getItem("CSM_previousURL");
        if(!currentUrl.equals(sessionStorageItem)){
            Selenide.back();
            System.out.println("redirect detected");
        }
    }
}
