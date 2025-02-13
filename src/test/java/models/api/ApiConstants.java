package models.api;

public class ApiConstants {

    public static final String SKU_VODKA_ARKHANGELSKAYA = "354331";
    public static final String NAME_VODKA_ARKHANGELSKAYA = "Водка АРХАНГЕЛЬСКАЯ Северная выдержка 40%, 0.5л";
    public static final String SKU_BREAD = "хлеб";
    public static final String SKU_IDS = "0177";
    public static final String SKU_MILK = "молоко";
    public static final String SKU_MILK_ART = "Арт: 656620";
    public static final String SKU_MILK_LONG_NAME = "Молоко пастеризованное СЕВЕРНОЕ МОЛОКО Вологодское 2,5%, без змж, 930г";

    public static final String STORE_CODE = "0067";
    public static final String STORE_ADDRESS_LENINA = "ул. Ленина, д. 132";
    public static final String STORE_CITY = "Альметьевск";
    public static final int STORE_COUNT = 662;
    public static final String STORE_ADDRESS_SPB = "Санкт-Петербург, Заневский пр., 71";

    public static final String USER_IS_NOT_APPROVED_MESSAGE = "Нет аккаунта с таким номером телефона. Проверьте цифры, пожалуйста, или зарегистрируйтесь.";
    public static final String USER_IS_NOT_APPROVED_ERROR = "DbUserIsNotApproved";
    public static final String UNSUPPORTED_MEDIA_TYPE_MESSAGE = "Запрашиваемый media-type не поддерживается.";
    public static final String UNSUPPORTED_MEDIA_TYPE_ERROR = "UnsupportedMediaType";


    public static final String LOGINOTP = "/api/v1/authentication/loginotp";
    public static final String SKUS_NAME = "/api/v1/skus/%s/name";
    public static final String CATALOG_SEARCH = "/api/v1/stores/0012/catalog/search/?value=";
    public static final String STORES = "/api/v1/stores/";
    public static final String DELIVERY_MODE_GET = "/jrpc/deliveryModeGet";



}
