package http;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import service.StringExtension;

class BaseHttp {
    final String HOST = "http://0.0.0.0";
    String httpUrl;

    HttpResponse<JsonNode> requestWithBody(HttpRequestWithBody req, String data) {
        HttpResponse<JsonNode> jsonResponse = null;
        System.out.println("Request: " + data);
        try {
            jsonResponse = req
                    .header("accept", "text/html")
                    .header("content-type", "application/x-www-form-urlencoded")
                    .body(data)
                    .asJson();
        } catch (UnirestException e) {
            System.out.println(String.format("Cannot get response with url '%s': %s", req.getUrl(),
                    StringExtension.formatMessage(e)));
        }
        return jsonResponse;
    }
}
