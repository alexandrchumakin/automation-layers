package http;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import service.CustomJson;
import service.model.HttpPostObject;

class PutHttp extends BaseHttp {
    PutHttp() {
        this.httpUrl = HOST + "/put";
    }

    HttpPostObject perform(HttpPostObject requestBody) {
        JsonNode response = requestWithBody(Unirest.put(httpUrl), requestBody.toSimpleData()).getBody();
        String responseObject = response.getObject().toString();
        return new CustomJson(responseObject).getHttpObject();
    }
}
