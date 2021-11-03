package pl.rekeep.app.commandmanager.handler;

import pl.rekeep.app.commandmanager.SaveZupTaskCommand;
import pl.rekeep.app.domain.Task;
import pl.rekeep.app.domain.ZupTask;
import pl.rekeep.app.domain.dto.ZupTaskDto;
import pl.rekeep.app.domain.mapper.ZupTaskMapper;
import pl.rekeep.app.domain.repository.TaskManager;
import pl.rekeep.app.domain.repository.ZupTaskManager;
import pl.rekeep.app.web.model.response.GenericMonoResponse;
import pl.rekeep.core.cqrs.annotations.CommandHandlerAnnotation;
import pl.rekeep.core.cqrs.command.handler.CommandHandler;

import java.util.Optional;

@CommandHandlerAnnotation
public class SaveZupTaskCommandHandler implements CommandHandler<SaveZupTaskCommand, GenericMonoResponse<ZupTaskDto>> {

    private final TaskManager taskManager;
    private final ZupTaskManager zupTaskManager;
    private final ZupTaskMapper zupTaskMapper;

    public SaveZupTaskCommandHandler(ZupTaskManager zupTaskManager, ZupTaskMapper zupTaskMapper, TaskManager taskManager) {
        this.zupTaskManager = zupTaskManager;
        this.zupTaskMapper = zupTaskMapper;
        this.taskManager = taskManager;
    }

    @Override
    public GenericMonoResponse<ZupTaskDto> handle(SaveZupTaskCommand command) {

        String userName = command.getUserName();
        String uuid = command.getUuid();

        Optional<Long> taskId = zupTaskManager.findTaskIdByParams(userName, uuid);

        if (taskId.isPresent()) {

            Optional<Task> task = taskManager.findById(taskId.get());

            if (task.isPresent()) {
                ZupTask zupTask = ZupTask.builder()
                        .properties(command.getNewZupTaskRequest().getProperties())
                        .id(uuid)
                        .task(task.get())
                        .createdBy(userName).build();

                zupTaskManager.save(zupTask);

                return GenericMonoResponse.<ZupTaskDto>builder().payload(zupTaskMapper.dto(zupTask)).build();
            } else {
                return GenericMonoResponse.<ZupTaskDto>builder().payload(null).build();
            }

        } else {
            return GenericMonoResponse.<ZupTaskDto>builder().payload(null).build();
        }

    }


}
