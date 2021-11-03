package pl.rekeep.app.commandmanager.handler;

import pl.rekeep.app.commandmanager.GetZupTaskCommand;
import pl.rekeep.app.domain.ZupTask;
import pl.rekeep.app.domain.dto.ZupTaskDto;
import pl.rekeep.app.domain.mapper.ZupTaskMapper;
import pl.rekeep.app.domain.repository.ZupTaskManager;
import pl.rekeep.app.web.model.response.GenericMonoResponse;
import pl.rekeep.core.cqrs.annotations.CommandHandlerAnnotation;
import pl.rekeep.core.cqrs.command.handler.CommandHandler;

import java.util.Optional;

@CommandHandlerAnnotation
public class GetZupTaskCommandHandler implements CommandHandler<GetZupTaskCommand, GenericMonoResponse<ZupTaskDto>> {

    private final ZupTaskManager zupTaskManager;
    private final ZupTaskMapper zupTaskMapper;

    public GetZupTaskCommandHandler(ZupTaskManager zupTaskManager, ZupTaskMapper zupTaskMapper) {
        this.zupTaskManager = zupTaskManager;
        this.zupTaskMapper = zupTaskMapper;
    }

    @Override
    public GenericMonoResponse<ZupTaskDto> handle(GetZupTaskCommand command) {

        String uuid = command.getUuid();

        Optional<ZupTask> zupTask = zupTaskManager.findById(uuid);

        if (zupTask.isPresent()) {
            ZupTaskDto zupTaskDto = zupTaskMapper.dto(zupTask.get());
            return GenericMonoResponse.<ZupTaskDto>builder().payload(zupTaskDto).build();
        } else {
            return GenericMonoResponse.<ZupTaskDto>builder().payload(null).build();
        }
    }
}
