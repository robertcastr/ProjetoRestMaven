package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ContactsResponse {

    private DataResponse data;

}
