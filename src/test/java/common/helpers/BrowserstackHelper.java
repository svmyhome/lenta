package common.helpers;

import java.net.MalformedURLException;
import java.net.URL;

import static common.CustomAllureListener.withCustomTemplates;
import static config.AuthConfiguration.BROWSERSTACK_PASSWORD;
import static config.AuthConfiguration.BROWSERSTACK_USER;
import static config.MobileConfiguration.mobileConfig;
import static io.restassured.RestAssured.given;

public class BrowserstackHelper {

    public static URL getBrowserstackUrl() {
        try {
            return new URL(String.format("https://%s:%s%s", BROWSERSTACK_USER, BROWSERSTACK_PASSWORD, mobileConfig.getBrowserStackUrl()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String videoUrl(String sessionId) {
        String url = String.format("https://%s%s.json", mobileConfig.getBrowserStackApi(), sessionId);
        return given().filter(withCustomTemplates())
                .auth().basic(BROWSERSTACK_USER, BROWSERSTACK_PASSWORD)
                .get(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("automation_session.video_url");

    }

}
