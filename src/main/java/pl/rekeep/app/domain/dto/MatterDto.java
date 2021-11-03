package pl.rekeep.app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MatterDto {
    private Long id;
    private String name;
    private char isPublic;
    private String definition;
}
