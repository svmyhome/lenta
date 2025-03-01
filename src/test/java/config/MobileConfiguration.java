package config;

import org.aeonbits.owner.ConfigFactory;

public class MobileConfiguration {
    final public static MobileConfig mobileConfig = ConfigFactory.create(MobileConfig.class, System.getProperties());
}