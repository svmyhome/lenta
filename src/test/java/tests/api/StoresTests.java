package tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import models.api.deliveryModeGet.DeliveryRequest;
import models.api.deliveryModeGet.response.Delivery;
import models.api.requestCatalogItems.CatalogItemsResponse;
import models.api.requestCatalogItems.Filters;
import models.api.requestCatalogItems.Sort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.List;
import java.util.Map;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static specs.StoresSpers.requestSpec;
import static specs.StoresSpers.successResponse;


@Slf4j
@Feature("Магазины")
@Story("Пользователь может получить информацию о магазине")
@Owner("Sarychev")
@Tag("api")
@DisplayName("Получить информацию о магазине")
public class StoresTests extends TestBase {

//    @Test
//    public void getListStoresTest() {
//        given().queryParam("page", 2)
//                .log().uri().log().params()
//                .when().get(LIST_USERS)
//                .then().body(matchesJsonSchemaInClasspath("JsonSchemas/ListUsersSchema.json"))
//                .log().body().statusCode(200);
//    }

    @Test
    public void getListStoresTest() {
        given().log().all()
                .get("/api/v1/stores")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getStoreTest() {
        given().log().all()
                .get("/api/v1/stores/0067")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getSkuTest() {
        given().log().all()
                .get("https://lenta.com/api/v1/skus/354331/name")
                .then()
                .log().all()
                .statusCode(200)
                .body("code", is("354331"));
    }


    @Test
    public void catalogItemsTest() {
        Map<String, String> headers = Map.of(
                "Content-Type", "application/json",
                "DeviceID", "ff86f8dd-7154-f591-199c-19fecb138aac",
                "SessionToken", "3849FACA09F05B077ADF56894288E40A1",
                "X-Domain", "moscow",
                "X-Organization-ID", "", // Пустое значение
                "X-Platform", "omniweb",
                "X-Retail-Brand", "lo"
        );


        Sort sort = new Sort("popular", "desc");
        Filters filters = new Filters(List.of(), List.of(),List.of());
        CatalogItemsResponse searchRequest = new CatalogItemsResponse(null, "665629", 3, 0 , sort, filters);

        // {"categoryId":null,"query":"665629","limit":40,"offset":0,"sort":{"type":"popular","order":"desc"},"filters":{"range":[],"checkbox":[],"multicheckbox":[]}}
        // {"categoryId":null,"query":"хлеб","limit":3,"offset":0,"sort":{"type":"popular","order":"desc"},"filters":{"range":[],"checkbox":[],"multicheckbox":[]}}

        given().log().all()
                .headers(headers)
                .when().body(searchRequest)
                .post("https://lenta.com/api-gateway/v1/catalog/items")
                .then()
                .log().all()
                .statusCode(200);

    }


    @Test
    public void deliveryModeGet() {
        // {"method":"deliveryModeGet","params":{},"jsonrpc":"2.0","id":"deliveryModeGet_1daa977f820b6"}

        DeliveryRequest deliveryRequest = new DeliveryRequest("deliveryModeGet", null, "2.0", "deliveryModeGet_1daa977f820b6");

        Delivery response = step("Make request", () -> given(requestSpec)
                .when().body(deliveryRequest)
                .post("/jrpc/deliveryModeGet")
                .then()
                .spec(successResponse).extract().as(Delivery.class));

        step("Verify", () -> {
            assertThat(response.jsonrpc()).isEqualTo("2.0");
            assertThat(response.result().sessionToken()).isEqualTo("3849FACA09F05B077ADF56894288E40A1");
        });

    }


    @Test
    public void catalogSearchTest() {
        given().log().all().header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36")
                .when().body("{    \"StoreIds\": [\"0177\"]\n" +
                        "}")
                .post("https://lenta.com/api/v1/stores/0012/catalog/search/?value=хлеб")
                .then()
                .log().all()
                .statusCode(200)
                .body("code", is("354331"));
    }

    @Test
    public void registrationTest() {
        given().log().all().header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36")
                .when().body(" {  \"phoneNumber\": \"7953")
                .post("https://lenta.com/api/v1/Registration/RequestUserStatus")
                .then()
                .log().all()
                .statusCode(200)
                .body("code", is("354331"));
    }

    @Test
    public void loginotpTest() {
        given().log().all().header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36")
                .when().body(" {  \"phoneNumber\": \"7953500675\"}")
                .post("https://lenta.com/api/v1/authentication/loginotp")
                .then()
                .log().all()
                .statusCode(200)
                .body("code", is("354331"));
    }


}
