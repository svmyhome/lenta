package config;

import org.aeonbits.owner.ConfigFactory;

public class WebDriverConfiguration {
    final public static WebDriverConfig webDriverConfig = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
}