package service.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class PostForm {
    @Builder.Default
    private String comments = "";
    @Builder.Default
    private String custemail = "";
    @Builder.Default
    private String custname = "";
    @Builder.Default
    private String custtel = "";
    @Builder.Default
    private String delivery = "";
    private String size;
    private String[] topping;
}
