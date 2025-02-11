package models.api.stores.store;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StoreResponse(
        String id,
        String name,
        String address,
        String cityKey,
        String cityName,
        String type,
        double lat,

        @JsonProperty("long")
        double longitude,

        String opensAt,
        String closesAt,
        boolean isDefaultStore,
        boolean isEcomAvailable,
        boolean isPickupAvailable,
        boolean isDeliveryAvailable,
        boolean isLentaScanAvailable,
        boolean is24hStore,
        boolean hasPetShop,
        boolean hasTobaccoPoint,
        String division,
        boolean isFavorite,
        int minOrderSumm,
        Integer maxOrderSumm,
        Integer minDeliveryOrderSumm,
        Integer maxDeliveryOrderSumm,
        int maxWeight,
        Integer maxDeliveryWeight,
        int maxQuantityPerItem,
        int maxDeliveryQuantityPerItem,
        int orderLimitOverall,
        int deliveryOrderLimitOverall,
        String storeTimeZoneOffset,
        String url,
        boolean showOnTheMap
) {
}