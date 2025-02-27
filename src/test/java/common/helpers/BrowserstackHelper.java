package common.helpers;

import java.net.MalformedURLException;
import java.net.URL;

import static common.Credentials.BROWSERSTACK_PASSWORD;
import static common.Credentials.BROWSERSTACK_USER;
import static common.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.given;

public class BrowserstackHelper {

    public static URL getBrowserstackUrl() {
        try {
            return new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", BROWSERSTACK_USER, BROWSERSTACK_PASSWORD));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String videoUrl(String sessionId) {
        String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);
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
