package drivers;

import com.codeborne.selenide.Configuration;
import config.WebDriverConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static common.ProjectSettings.Credentials.SELENOID_PASSWORD;
import static common.ProjectSettings.Credentials.SELENOID_USER;
import static common.ProjectSettings.isRemoteStartWeb;

public class CreateWebDriver {

    public static void webDriverConfig() {
        WebDriverConfig webDriverConfig = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
        RestAssured.baseURI = webDriverConfig.getApiUrl();
        Configuration.baseUrl = webDriverConfig.getBaseUrl();
        Configuration.browser = webDriverConfig.getBrowserName();
        Configuration.browserSize = webDriverConfig.getBrowserSize();
        if (isRemoteStartWeb) {
            Configuration.remote = "https://" + SELENOID_USER + ":" + SELENOID_PASSWORD + webDriverConfig.getRemoteUrl();
            Configuration.browserVersion = webDriverConfig.getBrowserVersion();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }

}
