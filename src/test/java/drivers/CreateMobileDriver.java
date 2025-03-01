package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.DeviceAndroidConfig;
import config.MobileConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;

import static common.helpers.BrowserstackHelper.getBrowserstackUrl;
import static common.helpers.LocalHelper.getAppPath;
import static common.helpers.LocalHelper.getLocalUrl;
import static common.helpers.LocalHelper.setLocation;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class CreateMobileDriver implements WebDriverProvider {
    DeviceAndroidConfig androidConfig;
    MobileConfig mobileConfig;
    UiAutomator2Options androidOptions;
    private static boolean isBrowserStackDevice;

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        mobileConfig = ConfigFactory.create(MobileConfig.class, System.getProperties());
        androidConfig = ConfigFactory.create(DeviceAndroidConfig.class, System.getProperties());
        if (androidConfig.isAndroid()) {
            return createAndroidDriver();
        } else {
            throw new UnsupportedOperationException("Unsupported platform: neither Android");
        }
    }

    public AndroidDriver createAndroidDriver() {
        androidOptions = new UiAutomator2Options();
        isBrowserStackDevice = mobileConfig.isBrowserStackDevice();
        androidOptions.setCapability("disableIdLocatorAutocompletion", true);
        androidOptions.setDeviceName(androidConfig.getDeviceName());
        androidOptions.setPlatformVersion(androidConfig.getPlatformVersion());
        if (isBrowserStackDevice) {
            androidOptions.setApp(androidConfig.getApp());
            androidOptions.setCapability("project", mobileConfig.getProjectName());
            androidOptions.setCapability("build", mobileConfig.getBuildName() + " " + LocalDateTime.now());
            androidOptions.setCapability("name", mobileConfig.getTestName() + " " + androidConfig.getDeviceName());
            androidOptions.setCapability("interactiveDebugging", true);
            androidOptions.setCapability("browserstack.debug", true);
            androidOptions.setCapability("geoLocation", "RU");
            androidOptions.setCapability("autoGrantPermissions", true);
            androidOptions.setCapability("location", mobileConfig.getBrowserStackLocation());
            return new AndroidDriver(getBrowserstackUrl(), androidOptions);
        } else {
            androidOptions.setAutomationName(ANDROID_UIAUTOMATOR2);
            androidOptions.setPlatformName(ANDROID);
            androidOptions.setUdid(androidConfig.getUdid());
            androidOptions.setApp(getAppPath());
            androidOptions.setAppPackage(androidConfig.getAppPackage());
            androidOptions.setAppActivity(androidConfig.getAppActivity());
            androidOptions.setCapability("autoGrantPermissions", true);
            setLocation(mobileConfig.getAndroidLatitude(), mobileConfig.getAndroidLongitude());
            return new AndroidDriver(getLocalUrl(), androidOptions);
        }

    }

    public static boolean isBrowserStackDevice() {
        return isBrowserStackDevice;
    }


}