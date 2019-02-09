package http;

import org.junit.Test;
import service.StringExtension;
import service.model.HttpPostObject;

import static org.junit.Assert.assertEquals;

public class TestHttpPut {
    @Test
    public void onlyName() {
        String name = StringExtension.generateString();
        HttpPostObject requestBody = HttpPostObject.builder()
                .custname(name)
                .build();
        PutHttp putHttp = new PutHttp();
        HttpPostObject responseForm = putHttp.perform(requestBody);

        assertEquals(requestBody, responseForm);
    }
}
