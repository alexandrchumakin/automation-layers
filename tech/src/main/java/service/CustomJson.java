package service;

import com.google.gson.Gson;

import lombok.Data;
import service.model.PostForm;
import service.model.PostResponse;

@Data
public class CustomJson {
    private PostForm postForm;

    public CustomJson(String stringObject) {
        Gson g = new Gson();
        PostResponse parsedObject = g.fromJson(stringObject, PostResponse.class);
        try {
            postForm = parsedObject.getForm();
            System.out.println("Parsed post form object: " + postForm);
        } catch (NullPointerException ex) {
            postForm = null;
            System.out.println(String.format("Failed to unmarshal `%s`: %s", stringObject, StringExtension.formatMessage(ex)));
        }
    }
}