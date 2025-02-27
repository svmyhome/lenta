package common;

import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;

public class ProjectConfiguration {
    final public static ProjectConfig projectConfig = ConfigFactory.create(ProjectConfig.class, System.getProperties());
}