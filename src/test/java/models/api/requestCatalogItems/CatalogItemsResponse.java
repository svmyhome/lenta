package models.api.requestCatalogItems;

public record CatalogItemsResponse(Long categoryId, String query, int limit, int offset, Sort sort, Filters filters) {
    //"{\"categoryId\":null,\"query\":\"хлеб\",\"limit\":40,\"offset\":0,\"sort\":{\"type\":\"popular\",\"order\":\"desc\"},\"filters\":{\"range\":[],\"checkbox\":[],\"multicheckbox\":[]}}"
}
