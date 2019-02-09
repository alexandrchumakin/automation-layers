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
public class HttpPostObject {
    private String comments;
    private String custemail;
    private String custname;
    private String custtel;
    private String delivery;
    private String size;
    private String[] topping;

    public String toSimpleData() {
        ObjectMapper oMapper = new ObjectMapper();
        Map map = oMapper.convertValue(this, Map.class);
        map.values().removeIf(Objects::isNull);
        return Joiner.on("&").withKeyValueSeparator("=").join(map);
    }
}
