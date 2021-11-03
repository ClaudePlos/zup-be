package pl.rekeep.app.web.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewTaskRequest {
    private String description;
    private String header;
    private LocalDate expectedDate;
    private String userSurname;
    private String userLogin;
    private Long status;
    private Long priority;
    private Long matter;
    private Long department;

    private String forUser;
    private NewZupTaskRequest newZupTaskRequest;
}
