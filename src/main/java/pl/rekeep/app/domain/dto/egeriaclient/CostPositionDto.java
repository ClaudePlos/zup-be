package pl.rekeep.app.domain.dto.egeriaclient;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostPositionDto {
    private String costPosition;
    private String description;
    private String officeLocalization;
}
