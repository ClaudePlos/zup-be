package pl.rekeep.app.commandmanager;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.rekeep.app.web.model.request.NewZupTaskRequest;
import pl.rekeep.core.cqrs.annotations.Command;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Command
public class SaveZupTaskCommand {
    private String userName;
    private String uuid;
    private NewZupTaskRequest newZupTaskRequest;
}
