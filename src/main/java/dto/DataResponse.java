package dto;

import lombok.*;

@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class DataResponse {

    private String id;
    private String type;
    private AttributesResponse attributes;
}
