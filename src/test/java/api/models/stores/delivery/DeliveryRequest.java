package api.models.stores.delivery;

public record DeliveryRequest(String method, Object params, String jsonrpc, String id) {

}
