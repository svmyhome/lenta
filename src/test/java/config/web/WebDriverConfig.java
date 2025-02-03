package config.web;

import com.codeborne.selenide.Configuration;
import config.AuthConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class WebDriverConfig {


    public static void localWebDriverConfig() {
        LocalWebDriverConfig localWebDriverConfig = ConfigFactory
                .create(LocalWebDriverConfig.class, System.getProperties());
        RestAssured.baseURI = localWebDriverConfig.getApiUrl();
        Configuration.baseUrl = localWebDriverConfig.getBaseUrl();
        Configuration.browser = localWebDriverConfig.getBrowserName();
        Configuration.browserSize = localWebDriverConfig.getBrowserSize();
    }

    public static void remoteWebDriverConfig() {
        AuthConfig authConfig = ConfigFactory
                .create(AuthConfig.class, System.getProperties());
        RemoteWebDriverConfig remoteWebDriverConfig = ConfigFactory
                .create(RemoteWebDriverConfig.class, System.getProperties());
        Configuration.remote = "https://" + authConfig.selenoidUser() + ":" + authConfig.selenoindPassword() + remoteWebDriverConfig.getRemoteUrl();
        RestAssured.baseURI = remoteWebDriverConfig.getApiUrl();
        Configuration.baseUrl = remoteWebDriverConfig.getBaseUrl();
        Configuration.browser = remoteWebDriverConfig.getBrowserName();
        Configuration.browserVersion = remoteWebDriverConfig.getBrowserVersion();
        Configuration.browserSize = remoteWebDriverConfig.getBrowserSize();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

}
