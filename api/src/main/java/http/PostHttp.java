package http;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import service.CustomJson;
import service.model.HttpPostObject;
import service.model.PostForm;

class PostHttp extends BaseHttp {
    PostHttp() {
        this.httpUrl = HOST + "/post";
    }

    HttpPostObject perform(PostForm requestBody) {
        JsonNode response = requestWithBody(Unirest.post(httpUrl), requestBody.toHttpRequest()).getBody();
        String responseObject = response.getObject().toString();
        return new CustomJson(responseObject).getHttpObject();
    }
}
