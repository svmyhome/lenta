package drivers;

import com.codeborne.selenide.Configuration;
import config.AuthConfig;
import config.WebDriverConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static helpers.ProjectSettings.isRemoteStartWeb;

public class CreateWebDriver {

    public static void webDriverConfig() {
        WebDriverConfig webDriverConfig = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
        RestAssured.baseURI = webDriverConfig.getApiUrl();
        Configuration.baseUrl = webDriverConfig.getBaseUrl();
        Configuration.browser = webDriverConfig.getBrowserName();
        Configuration.browserSize = webDriverConfig.getBrowserSize();
        if (isRemoteStartWeb) {
            AuthConfig authConfig = ConfigFactory
                    .create(AuthConfig.class, System.getProperties());
            Configuration.remote = "https://" + authConfig.selenoidUser() + ":" + authConfig.selenoindPassword() + webDriverConfig.getRemoteUrl();
            Configuration.browserVersion = webDriverConfig.getBrowserVersion();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }

//    public static void localWebDriverConfig() {
//        LocalWebDriverConfig localWebDriverConfig = ConfigFactory
//                .create(LocalWebDriverConfig.class, System.getProperties());
//        RestAssured.baseURI = localWebDriverConfig.getApiUrl();
//        Configuration.baseUrl = localWebDriverConfig.getBaseUrl();
//        Configuration.browser = localWebDriverConfig.getBrowserName();
//        Configuration.browserSize = localWebDriverConfig.getBrowserSize();
//    }

//    public static void remoteWebDriverConfig() {
//        AuthConfig authConfig = ConfigFactory
//                .create(AuthConfig.class, System.getProperties());
//        RemoteWebDriverConfig remoteWebDriverConfig = ConfigFactory
//                .create(RemoteWebDriverConfig.class, System.getProperties());
//        Configuration.remote = "https://" + authConfig.selenoidUser() + ":" + authConfig.selenoindPassword() + remoteWebDriverConfig.getRemoteUrl();
//        RestAssured.baseURI = remoteWebDriverConfig.getApiUrl();
//        Configuration.baseUrl = remoteWebDriverConfig.getBaseUrl();
//        Configuration.browser = remoteWebDriverConfig.getBrowserName();
//        Configuration.browserVersion = remoteWebDriverConfig.getBrowserVersion();
//        Configuration.browserSize = remoteWebDriverConfig.getBrowserSize();
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                "enableVNC", true,
//                "enableVideo", true
//        ));
//        Configuration.browserCapabilities = capabilities;
//    }

}
