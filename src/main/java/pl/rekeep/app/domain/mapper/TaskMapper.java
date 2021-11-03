package pl.rekeep.app.domain.mapper;

import org.mapstruct.Mapper;
import pl.rekeep.app.domain.Task;
import pl.rekeep.app.domain.dto.TaskDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto dto(Task entity);

    Task entity(TaskDto dto);

    List<TaskDto> dtos(List<Task> entities);

    List<Task> entities(List<TaskDto> dtos);
}
