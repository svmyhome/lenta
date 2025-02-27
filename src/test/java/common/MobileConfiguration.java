package common;

import config.MobileConfig;
import org.aeonbits.owner.ConfigFactory;

public class MobileConfiguration {
    final public static MobileConfig projectConfig = ConfigFactory.create(MobileConfig.class, System.getProperties());
}