package pl.rekeep.app.domain.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ZupTaskDto {
    private String id;
    private JsonNode properties;
    private String createBy;
    private String createdAt;
    private Long taskId;
}
