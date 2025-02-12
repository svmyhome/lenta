package helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiHelper {
    @Step("Полученное значение совпадает c {expected}")
    public ApiHelper compareValues(String expected, String actual) {
        assertThat(expected).isEqualTo(actual);
    return this;
    }

    @Step("Количество равно {expected}")
    public ApiHelper compareListSize(Response response, String jsonPath, int expected) {
        assertThat(response.jsonPath().getList(jsonPath).size()).isGreaterThanOrEqualTo(expected);
        return this;
    }
}
