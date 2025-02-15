package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.DeviceAndroidConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import io.appium.java_client.Setting;

import static helpers.BrowserstackHelper.getBrowserstackUrl;
import static helpers.LocalHelper.*;
import static helpers.ProjectSettings.ProjectConfiguration.projectConfig;
import static helpers.ProjectSettings.isAndroid;
import static helpers.ProjectSettings.isBrowserStackDevice;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class CreateMobileDriver implements WebDriverProvider {
    DeviceAndroidConfig androidConfig;
    UiAutomator2Options androidOptions;

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        if (isAndroid) {
            return createAndroidDriver();
        } else {
            throw new UnsupportedOperationException("Unsupported platform: neither Android");
        }
    }

    public AndroidDriver createAndroidDriver() {
        androidConfig = ConfigFactory.create(DeviceAndroidConfig.class, System.getProperties());
        androidOptions = new UiAutomator2Options();
        androidOptions.setCapability("disableIdLocatorAutocompletion", true);
        androidOptions.setDeviceName(androidConfig.getDeviceName());
        androidOptions.setPlatformVersion(androidConfig.getPlatformVersion());
        if (isBrowserStackDevice) {
            androidOptions.setApp(androidConfig.getApp());
            androidOptions.setCapability("project", projectConfig.getProjectName());
            androidOptions.setCapability("build", projectConfig.getBuildName() + " " + LocalDateTime.now());
            androidOptions.setCapability("name", projectConfig.getTestName() + " " + androidConfig.getDeviceName());
            androidOptions.setCapability("interactiveDebugging", true);
            androidOptions.setCapability("browserstack.debug", true);
            androidOptions.setCapability("geoLocation","RU");
            androidOptions.setCapability("location", "59.9343,30.3351");
            return new AndroidDriver(getBrowserstackUrl(), androidOptions);
        } else {
            androidOptions.setAutomationName(ANDROID_UIAUTOMATOR2);
            androidOptions.setPlatformName(ANDROID);
            androidOptions.setUdid(androidConfig.getUdid());
            androidOptions.setApp(getAppPath());
            androidOptions.setAppPackage(androidConfig.getAppPackage());
            androidOptions.setAppActivity(androidConfig.getAppActivity());
            setLocation(59.939476, 30.436496);
            return new AndroidDriver(getLocalUrl(), androidOptions);
        }

    }

}