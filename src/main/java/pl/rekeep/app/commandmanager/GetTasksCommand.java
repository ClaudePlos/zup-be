package pl.rekeep.app.commandmanager;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.rekeep.core.cqrs.annotations.Command;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Command
public class GetTasksCommand {
    private String userLogin;
    private Long matterId;
}
