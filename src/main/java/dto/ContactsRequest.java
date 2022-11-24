package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @Getter @Setter @AllArgsConstructor
public class ContactsRequest {

    private String name;
    @JsonProperty("last_name")
    private String lastname;
    private String email;
    private String age;
    private String phone;
    private String address;
    private String state;
    private String city;
}
