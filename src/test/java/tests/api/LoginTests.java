package tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import models.api.login.LoginOtpRequest;
import models.api.login.UnsupportedMediaTypeResponse;
import models.api.login.UserIsNotApprovedResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static models.api.ApiConstants.UNSUPPORTED_MEDIA_TYPE_ERROR;
import static models.api.ApiConstants.UNSUPPORTED_MEDIA_TYPE_MESSAGE;
import static models.api.ApiConstants.USER_IS_NOT_APPROVED_ERROR;
import static models.api.ApiConstants.USER_IS_NOT_APPROVED_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static specifications.ApiSpecifications.requestSpecification;
import static specifications.ApiSpecifications.statusCode403ResponseSpecification;
import static specifications.ApiSpecifications.statusCode415RequestSpecification;
import static specifications.ApiSpecifications.statusCode415ResponseSpecification;


@Slf4j
@Feature("Авторизация")
@Story("Пользователь не может авторизоваться если в запросе некорректные данные")
@Owner("Sarychev")
@Tag("api")
@DisplayName("Ошибки авторизации")
public class LoginTests extends TestBase {

    public final LoginOtpRequest loginOtpRequest = new LoginOtpRequest("79535006575");

    @Test
    @DisplayName("Запрашиваемый media-type не поддерживается")
    @Severity(SeverityLevel.MINOR)
    public void unsupportedMediaTypeloginOtpTest() {
        UnsupportedMediaTypeResponse unsupportedMediaTypeResponse = step("Получен код 415", () ->
                given(statusCode415RequestSpecification)
                        .when().body(loginOtpRequest)
                        .post("/api/v1/authentication/loginotp")
                        .then()
                        .spec(statusCode415ResponseSpecification)
                        .extract().as(UnsupportedMediaTypeResponse.class));

        step("Нет аккаунта с таким номером телефона", () ->
        {
            assertThat(unsupportedMediaTypeResponse.message()).isEqualTo(UNSUPPORTED_MEDIA_TYPE_MESSAGE);
            assertThat(unsupportedMediaTypeResponse.errorCode()).isEqualTo(UNSUPPORTED_MEDIA_TYPE_ERROR);
        });
    }

    @Test
    @DisplayName("Ошибка Нет аккаунта с таким номером телефона")
    @Severity(SeverityLevel.TRIVIAL)
    public void forbiddenLoginOtpTest() {
        UserIsNotApprovedResponse userIsNotApprovedResponse = step("Получен код 403", () ->
                given(requestSpecification)
                        .when().body(loginOtpRequest)
                        .post("/api/v1/authentication/loginotp")
                        .then()
                        .spec(statusCode403ResponseSpecification).extract().as(UserIsNotApprovedResponse.class));

        step("Нет аккаунта с таким номером телефона", () ->
        {
            assertThat(userIsNotApprovedResponse.message()).isEqualTo(USER_IS_NOT_APPROVED_MESSAGE);
            assertThat(userIsNotApprovedResponse.errorCode()).isEqualTo(USER_IS_NOT_APPROVED_ERROR);
        });
    }

}
