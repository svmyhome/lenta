package helpers;

import config.AuthConfig;
import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;

public class ProjectSettings {

    public static final String platform = System.getProperty("platform", "mobile");
    public static final boolean isWeb = platform.equals("web");
    public static final boolean isMobile = platform.equals("mobile");

    public static final String environmentType = System.getProperty("environmentType", "local");
    public static final boolean isRemoteStartWeb = environmentType.equals("remote");

    public static final String mobileOS = System.getProperty("mobileOS", "android"); // todo переделать 11 строкой
    public static final String deviceHost = System.getProperty("deviceHost", "local");
    public static final boolean isBrowserStackDevice = "browserstack".equals(deviceHost);
    public static final boolean isAndroid = mobileOS.equals("android");

    public static class Credentials {
        static AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());
        public static final String USER_NAME = System.getProperty("userBrowserStack", config.getUserName());
        public static final String PASSWORD = System.getProperty("passwordBrowserStack", config.getPassword());
    }

    public static class ProjectConfiguration {
        public static ProjectConfig projectConfig = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    }
}
