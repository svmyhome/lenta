package common.helpers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static common.MobileConfiguration.projectConfig;


public class LocalHelper {
    public static URL getLocalUrl() {
        try {
            return new URL(projectConfig.getLocalUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    public static String getAppPath() {
        String appVersion = "LentaApp.apk";
        String appPath = "src/test/resources/apps/" + appVersion;

        File app = new File(appPath);
        return app.getAbsolutePath();
    }

    public static void setLocation(double latitude, double longitude) {
        try {
            String command = String.format("adb -s emulator-5554 emu geo fix %f %f", longitude, latitude);
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            System.out.println("Location set to: Latitude=" + latitude + ", Longitude=" + longitude);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
