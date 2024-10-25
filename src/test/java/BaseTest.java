import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1200";
        Configuration.baseUrl = "https://lenta.com/";
        Configuration.pageLoadStrategy = "eager";
    }
}
