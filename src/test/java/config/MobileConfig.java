package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/${environmentType}.properties",
        "classpath:config/local/mobileLocal.properties"
})
public interface MobileConfig extends Config {

    @Key("android.localUrl")
    String getLocalUrl();

    @Key("browserStack.project")
    String getProjectName();

    @Key("browserStack.build")
    String getBuildName();

    @Key("browserStack.testName")
    String getTestName();

    @Key("isBrowserStackDevice")
    boolean isBrowserStackDevice();

}