package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter @AllArgsConstructor @Builder @NoArgsConstructor
public class AttributesResponse {

    private String name;
    @JsonProperty("last-name")
    private String lastname;
    private String email;
    private String age;
    private String phone;
    private String address;
    private String state;
    private String city;
}
