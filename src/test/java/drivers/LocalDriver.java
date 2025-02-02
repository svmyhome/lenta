package drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class LocalDriver implements WebDriverProvider {
    UiAutomator2Options androidOptions;


    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
            return createAndroidDriver();
    }

    public AndroidDriver createAndroidDriver() {
//        String device = System.getProperty("device");
//        if (device == null) {
//            device = "pixel6Pro";
//            System.setProperty("device", device);
//        }
//        androidConfig = ConfigFactory.create(BrowserStackAndroidConfig.class, System.getProperties());

        androidOptions = new UiAutomator2Options();
        androidOptions.setAutomationName(ANDROID_UIAUTOMATOR2);
        androidOptions.setPlatformName(ANDROID);
        androidOptions.setCapability("disableIdLocatorAutocompletion", true);
//        androidOptions.setDeviceName("Pixel_3a_API_34_extension_level_7_arm64");
//        androidOptions.setPlatformVersion("14.0"); // puro
//        androidOptions.setUdid("2FK0224429007348");
        androidOptions.setPlatformVersion("11.0");
//        androidOptions.setUdid("V4CEBEEANB498D6D"); //local
        androidOptions.setUdid("emulator-5554"); // Studio
        androidOptions.setApp(getAppPath());
//        androidOptions.setAppPackage("org.wikipedia.alpha");
//        androidOptions.setAppActivity("org.wikipedia.main.MainActivity");
        androidOptions.setAppPackage("com.icemobile.lenta.stage.qa");
        androidOptions.setAppActivity("ru.app.main.MainActivity");
        setLocation(59.939476, 30.436496);

        return new AndroidDriver(getLocalUrl(), androidOptions);
    }

    private void setLocation(double latitude, double longitude) {
        try {
            // Формируем команду adb для установки местоположения
            String command = String.format("adb -s emulator-5554 emu geo fix %f %f", longitude, latitude);
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor(); // Ожидаем завершения команды
            System.out.println("Location set to: Latitude=" + latitude + ", Longitude=" + longitude);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static URL getLocalUrl() {
        try {
            return new URL("http://192.168.31.143:4723");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    private String getAppPath() {
//        String appVersion = "app-alpha-universal-release.apk";
        String appVersion = "LentaApp.apk";
        String appUrl = "https://github.com/wikimedia/apps-android-wikipedia" +
                "/releases/download/latest/" + appVersion;
        String appPath = "src/test/resources/apps/" + appVersion;

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app.getAbsolutePath();
    }

}
