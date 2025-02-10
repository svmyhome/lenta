package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class StoresSpecs {
    static Map<String, String> headers = Map.of(
            "Content-Type", "application/json",
            "DeviceID", "ff86f8dd-7154-f591-199c-19fecb138aac",
            "SessionToken", "3849FACA09F05B077ADF56894288E40A1",
            "X-Domain", "moscow",
            "X-Organization-ID", "", // Пустое значение
            "X-Platform", "omniweb",
            "X-Retail-Brand", "lo"
    );

    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .log().body()
            .headers(headers);

    public static ResponseSpecification successResponse = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();
}
