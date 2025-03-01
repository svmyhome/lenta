package config;

import org.aeonbits.owner.ConfigFactory;

public class AuthConfiguration {
    final static AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());
    public static final String BROWSERSTACK_USER = System.getProperty("userBrowserStack", config.getUserName());
    public static final String BROWSERSTACK_PASSWORD = System.getProperty("passwordBrowserStack", config.getPassword());
    public static final String SELENOID_USER = System.getProperty("selenoidUser", config.selenoidUser());
    public static final String SELENOID_PASSWORD = System.getProperty("selenoidPassword", config.selenoindPassword());
}