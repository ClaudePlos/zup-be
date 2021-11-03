package pl.rekeep.app.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rekeep.app.commandmanager.*;
import pl.rekeep.app.domain.ZupTask;
import pl.rekeep.app.domain.dto.TaskDto;
import pl.rekeep.app.domain.dto.ZupTaskDto;
import pl.rekeep.app.domain.repository.ZupTaskManager;
import pl.rekeep.app.web.model.request.NewTaskRequest;
import pl.rekeep.app.web.model.request.NewZupTaskRequest;
import pl.rekeep.app.web.model.response.GenericMonoResponse;
import pl.rekeep.core.cqrs.command.Gate;

import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping(path = "api/task")
public class TaskApi {

    private final Gate gate;
    private final ZupTaskManager zupTaskManager;

    public TaskApi(Gate gate, ZupTaskManager zupTaskManager) {
        this.gate = gate;
        this.zupTaskManager = zupTaskManager;
    }

    @GetMapping(path = "{id}")
    public TaskDto getTask(@PathVariable("id") Long id) {
        GetTaskCommand command = GetTaskCommand.builder()
                .id(id).build();

        return (TaskDto) gate.dispatch(command);
    }

    @PostMapping
    public ResponseEntity<GenericMonoResponse<ZupTaskDto>> saveNewTask(@RequestBody NewTaskRequest newTaskRequest) {

        SaveTaskCommand taskCommand = SaveTaskCommand.builder()
                .newTaskRequest(newTaskRequest).build();

        GenericMonoResponse<TaskDto> taskResponse = (GenericMonoResponse<TaskDto>) gate.dispatch(taskCommand);

        String forUser = taskResponse.getPayload().getUserLogin();
        String uuid = taskResponse.getPayload().getUuid();
        NewZupTaskRequest newZupTaskRequest = newTaskRequest.getNewZupTaskRequest();

        SaveZupTaskCommand zupTaskCommand = SaveZupTaskCommand.builder()
                .userName(forUser).uuid(uuid).newZupTaskRequest(newZupTaskRequest).build();

        GenericMonoResponse<ZupTaskDto> zupTaskResponse = (GenericMonoResponse<ZupTaskDto>) gate.dispatch(zupTaskCommand);

        return ResponseEntity.ok(zupTaskResponse);
    }

    @GetMapping(path = "user/{userLogin}/matter/{matterId}")
    public List<TaskDto> getTasks(@PathVariable("userLogin") String userLogin, @PathVariable("matterId") Long matterId) {
        GetTasksCommand command = GetTasksCommand.builder()
                .userLogin(userLogin)
                .matterId(matterId)
                .build();

        return (List<TaskDto>) gate.dispatch(command);
    }

    @GetMapping(path = "zup/task/{uuid}")
    public ResponseEntity<GenericMonoResponse<ZupTaskDto>> getZupTask(@PathVariable("uuid") String uuid) {
        GetZupTaskCommand command = GetZupTaskCommand.builder()
                .uuid(uuid).build();
        GenericMonoResponse<ZupTaskDto> response = (GenericMonoResponse<ZupTaskDto>) gate.dispatch(command);
        log.info("Invoked getZupTask");
        return ResponseEntity.ok(response);
    }

}
