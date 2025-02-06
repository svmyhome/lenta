package models.api.deliveryModeGet.response;

public record DeliveryResult(String sessionToken, String type, String storeId, String addressId) {
}
