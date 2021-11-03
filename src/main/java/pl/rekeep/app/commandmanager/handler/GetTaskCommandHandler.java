package pl.rekeep.app.commandmanager.handler;

import pl.rekeep.app.commandmanager.GetTaskCommand;
import pl.rekeep.app.domain.Task;
import pl.rekeep.app.domain.dto.TaskDto;
import pl.rekeep.app.domain.mapper.TaskMapper;
import pl.rekeep.app.domain.repository.TaskManager;
import pl.rekeep.core.cqrs.annotations.CommandHandlerAnnotation;
import pl.rekeep.core.cqrs.command.handler.CommandHandler;

@CommandHandlerAnnotation
public class GetTaskCommandHandler implements CommandHandler<GetTaskCommand, TaskDto> {

    private final TaskManager taskManager;
    private final TaskMapper taskMapper;

    public GetTaskCommandHandler(TaskManager taskManager, TaskMapper taskMapper) {
        this.taskManager = taskManager;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskDto handle(GetTaskCommand command) {
        Long id = command.getId();

        Task task = taskManager.findById(id).get();

        return taskMapper.dto(task);
    }
}
