package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/web/${environmentType}.properties",
        "classpath:config/web/webLocal.properties"
})
public interface WebDriverConfig extends Config {

    @Key("isRemoteStartWeb")
    boolean isRemoteStartWeb();

    @Key("remote.url")
    String getRemoteUrl();

    @Key("remote.videoUrl")
    String getRemoteVideoUrl();

    @Key("webdriver.baseUrl")
    String getBaseUrl();

    @Key("service.apiUrl")
    String getApiUrl();

    @Key("browser.name")
    String getBrowserName();

    @Key("browser.version")
    String getBrowserVersion();

    @Key("browser.size")
    String getBrowserSize();

}