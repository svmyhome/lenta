package models.api.deliveryModeGet;

import net.bytebuddy.description.method.ParameterDescription;

public record DeliveryRequest(String method, Object params, String jsonrpc, String id) {

    //    {"method":"deliveryModeGet","params":{},"jsonrpc":"2.0","id":"deliveryModeGet_1daa977f820b6"}


}
