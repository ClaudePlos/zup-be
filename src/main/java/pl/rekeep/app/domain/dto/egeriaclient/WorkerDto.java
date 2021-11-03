package pl.rekeep.app.domain.dto.egeriaclient;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkerDto {
    private Long id;
    private String personalNumber;
    private String surname;
    private String name;
    private String startWorkDate;
}
