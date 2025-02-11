package models.api.stores.delivery.response;

public record DeliveryResult(String sessionToken, String type, String storeId, String addressId) {
}
