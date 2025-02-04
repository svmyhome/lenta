package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/local/android/${device}.properties",
        "classpath:config/emulation/android/${device}.properties",
        "classpath:config/browserstack/android/${device}.properties",
        "classpath:config/local/android/redmi9A.properties"
})
public interface DeviceAndroidConfig extends Config {
    @Key("android.app")
    String getApp();

    @Key("android.localUrl")
    String getLocalUrl();

    @Key("android.deviceName")
    String getDeviceName();

    @Key("android.udid")
    String getUdid();

    @Key("android.platformVersion")
    String getPlatformVersion();

    @Key("android.appPackage")
    String getAppPackage();

    @Key("android.appActivity")
    String getAppActivity();

}
