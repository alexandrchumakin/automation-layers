package http;

import com.mashape.unirest.http.Unirest;

class DeleteHttp extends BaseHttp {
    DeleteHttp() {
        this.httpUrl = HOST + "/delete";
    }

    int perform() {
        return requestWithBody(Unirest.delete(httpUrl), "").getStatus();
    }
}
