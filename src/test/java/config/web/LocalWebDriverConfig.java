package config.web;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/web/local.properties"
})
public interface LocalWebDriverConfig extends Config {

    @Key("webdriver.baseUrl")
    String getBaseUrl();

    @Key("service.apiUrl")
    String getApiUrl();

    @Key("browser.name")
    @DefaultValue("chrome")
    String getBrowserName();

    @Key("browser.size")
    @DefaultValue("1920x1200")
    String getBrowserSize();
}