package pl.rekeep.app.commandmanager.egeriaclient;

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
public class GetWorkerCommand {
    private String personalNumber;
}
