package pl.rekeep.app.commandmanager.handler;

import pl.rekeep.app.commandmanager.SaveTaskCommand;
import pl.rekeep.app.domain.*;
import pl.rekeep.app.domain.dto.TaskDto;
import pl.rekeep.app.domain.mapper.TaskMapper;
import pl.rekeep.app.domain.repository.*;
import pl.rekeep.app.web.model.request.NewTaskRequest;
import pl.rekeep.app.web.model.response.GenericMonoResponse;
import pl.rekeep.core.cqrs.annotations.CommandHandlerAnnotation;
import pl.rekeep.core.cqrs.command.handler.CommandHandler;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@CommandHandlerAnnotation
public class SaveTaskCommandHandler implements CommandHandler<SaveTaskCommand, GenericMonoResponse<TaskDto>> {

    private static final Long NEW_TASK_ID = 0L;

    private final TaskManager taskManager;
    private final DepartmentManager departmentManager;
    private final MatterManager matterManager;
    private final PriorityManager priorityManager;
    private final StatusManager statusManager;
    private final TaskMapper taskMapper;


    public SaveTaskCommandHandler(TaskManager taskManager, DepartmentManager departmentManager,
                                  MatterManager matterManager, PriorityManager priorityManager,
                                  StatusManager statusManager, TaskMapper taskMapper) {
        this.taskManager = taskManager;
        this.departmentManager = departmentManager;
        this.matterManager = matterManager;
        this.priorityManager = priorityManager;
        this.statusManager = statusManager;
        this.taskMapper = taskMapper;
    }

    @Override
    public GenericMonoResponse<TaskDto> handle(SaveTaskCommand command) {

        NewTaskRequest request = command.getNewTaskRequest();
        String forUser = command.getNewTaskRequest().getForUser();

        Optional<Department> department = departmentManager.findById(request.getDepartment());
        Optional<Matter> matter = matterManager.findById(request.getMatter());
        Optional<Priority> priority = priorityManager.findById(request.getPriority());
        Optional<Status> status = statusManager.findById(request.getStatus());

        //TODO: sprawdzić, czy department, matter, priority, status isPresent

        UUID uuid = UUID.randomUUID();

        Task task = Task.builder()
                .id(NEW_TASK_ID)
                .header(HEADER(forUser, uuid.toString()))
                .description(DESCRIPTION(uuid.toString()))
                .expectedDate(LocalDate.now().plusDays(3))
                .userSurname(request.getUserSurname())
                .userLogin(request.getUserLogin())
                .department(department.get())
                .matter(matter.get())
                .priority(priority.get())
                .status(status.get())
                .build();

        taskManager.save(task);

        TaskDto taskDto = taskMapper.dto(task);
        taskDto.setUuid(uuid.toString());

        return GenericMonoResponse.<TaskDto>builder().payload(taskDto).build();
    }

    private String HEADER(String userName, String uuid) {
        return String.format("Wniosek o uprawnienia dla %s, wniosek %s", userName, uuid);
    }

    private String DESCRIPTION(String uuid) {
//        return String.format("Przejdź do <a href='http://127.0.0.1:4200/zupui/%s' target='_blank'>wniosku</a>", uuid);
        return String.format("Przejdź do <a href='http://192.168.0.93:8084/zupui/%s' target='_blank'>wniosku</a>", uuid);
    }


}
