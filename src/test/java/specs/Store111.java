package specs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store111 {

    private String id;
    private String name;
    private String address;
    private String cityKey;
    private String cityName;
    private String type;
    private double lat;
    @JsonProperty("long") // Указываем, что это поле соответствует JSON-полю "long"
    private double longitude;
    private String opensAt;
    private String closesAt;
    private boolean isDefaultStore;
    private boolean isEcomAvailable;
    private boolean isPickupAvailable;
    private boolean isDeliveryAvailable;
    private boolean isLentaScanAvailable;
    private boolean is24hStore;
    private boolean hasPetShop;
    private boolean hasTobaccoPoint;
    private String division;
    private boolean isFavorite;
    private int minOrderSumm;
    private Integer maxOrderSumm;
    private Integer minDeliveryOrderSumm;
    private Integer maxDeliveryOrderSumm;
    private int maxWeight;
    private Integer maxDeliveryWeight;
    private int maxQuantityPerItem;
    private int maxDeliveryQuantityPerItem;
    private int orderLimitOverall;
    private int deliveryOrderLimitOverall;
    private String storeTimeZoneOffset;
    private String url;
    private boolean showOnTheMap;
}