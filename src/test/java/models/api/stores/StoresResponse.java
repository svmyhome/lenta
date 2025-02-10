package models.api.stores;

import java.time.ZoneOffset;
import java.util.List;

public record StoresResponse(List<StoreResponse> stores) {
}