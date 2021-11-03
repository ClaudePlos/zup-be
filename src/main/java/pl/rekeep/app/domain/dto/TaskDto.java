package pl.rekeep.app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private LocalDate createdAt;
    private String description;
    private String header;
    private LocalDate expectedDate;
    private String userSurname;
    private String userLogin;
    private StatusDto status;
    private PriorityDto priority;
    private MatterDto matter;
    private DepartmentDto department;

    private String uuid;
}
