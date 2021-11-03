package pl.rekeep.app.commandmanager.handler;

import pl.rekeep.app.commandmanager.GetTasksCommand;
import pl.rekeep.app.domain.Task;
import pl.rekeep.app.domain.dto.TaskDto;
import pl.rekeep.app.domain.mapper.TaskMapper;
import pl.rekeep.app.domain.repository.TaskManager;
import pl.rekeep.core.cqrs.annotations.CommandHandlerAnnotation;
import pl.rekeep.core.cqrs.command.handler.CommandHandler;

import java.util.List;

@CommandHandlerAnnotation
public class GetTasksCommandHandler implements CommandHandler<GetTasksCommand, List<TaskDto>> {

    private final TaskManager taskManager;
    private final TaskMapper taskMapper;

    public GetTasksCommandHandler(TaskManager taskManager, TaskMapper taskMapper) {
        this.taskManager = taskManager;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<TaskDto> handle(GetTasksCommand command) {

        String userLogin = command.getUserLogin();
        Long matterId = command.getMatterId();

        List<Task> tasks = taskManager.findByUserLoginAndMatterId(userLogin, matterId);
        return taskMapper.dtos(tasks);
    }
}
