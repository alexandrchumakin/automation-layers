package service.model;

import lombok.Data;

@Data
public class PostResponse {
    private Object args;
    private String data;
    private Object files;
    private PostForm form;
    private Object headers;
    private Object json;
    private String origin;
    private String url;
}
