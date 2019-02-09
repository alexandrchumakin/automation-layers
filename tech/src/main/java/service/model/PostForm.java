package service.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Map;
import java.util.Objects;

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

    public HttpPostObject toHttpObject() {
        return HttpPostObject.builder()
                .comments("".equals(comments) ? null : comments)
                .custemail("".equals(custemail) ? null : custemail)
                .custname("".equals(custname) ? null : custname)
                .custtel("".equals(custtel) ? null : custtel)
                .delivery("".equals(delivery) ? null : delivery)
                .size(size)
                .topping(topping)
                .build();
    }

    public String toHttpRequest() {
        ObjectMapper oMapper = new ObjectMapper();
        Map map = oMapper.convertValue(this, Map.class);
        map.values().removeIf(Objects::isNull);
        return Joiner.on("&").withKeyValueSeparator("=").join(map);
    }
}
