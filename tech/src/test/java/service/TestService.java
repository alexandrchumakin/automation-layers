package service;

import org.junit.Test;
import service.model.PostForm;

import static org.junit.Assert.*;

public class TestService {
    @Test
    public void objectWithName() {
        String name = StringExtension.generateString();
        PostForm expectedObject = PostForm.builder()
                .custname(name)
                .build();
        PostForm actualObject = new CustomJson(String.format("{\n" +
                "  \"args\": {}, \n" +
                "  \"data\": \"\", \n" +
                "  \"files\": {}, \n" +
                "  \"form\": {\n" +
                "    \"comments\": \"\", \n" +
                "    \"custemail\": \"\", \n" +
                "    \"custname\": \"%s\", \n" +
                "    \"custtel\": \"\", \n" +
                "    \"delivery\": \"\"\n" +
                "  }, \n" +
                "  \"headers\": {}, \n" +
                "  \"json\": null, \n" +
                "  \"origin\": \"172.17.0.1\", \n" +
                "  \"url\": \"http://0.0.0.0/post\"\n" +
                "}", name)).getPostForm();
        assertEquals(expectedObject, actualObject);
    }

    @Test
    public void emptyObject() {
        PostForm actualObject = new CustomJson("").getPostForm();
        assertNull(actualObject);
    }
}
