package pl.rekeep.app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatusDto {
    private Long id;
    private String name;
    private String color;
    private String score;
}
