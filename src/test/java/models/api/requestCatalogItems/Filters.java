package models.api.requestCatalogItems;

import java.util.List;

public record Filters(List<Object> range, List<Object> checkbox, List<Object> multicheckbox) {
}