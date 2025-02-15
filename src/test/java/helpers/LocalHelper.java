package helpers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class LocalHelper {
    public static URL getLocalUrl() {
        try {
            return new URL("http://192.168.0.36:4723");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    public static String getAppPath() {
//        String appVersion = "app-alpha-universal-release.apk";
//        String appUrl = "https://github.com/wikimedia/apps-android-wikipedia" +
//                "/releases/download/latest/" + appVersion;
        String appVersion = "LentaApp.apk";
        String appPath = "src/test/resources/apps/" + appVersion;

        File app = new File(appPath);
//        if (!app.exists()) {
//            try (InputStream in = new URL(appUrl).openStream()) {
//                copyInputStreamToFile(in, app);
//            } catch (IOException e) {
//                throw new AssertionError("Failed to download application", e);
//            }
//        }
        return app.getAbsolutePath();
    }

    public static void setLocation(double latitude, double longitude) {
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
}
