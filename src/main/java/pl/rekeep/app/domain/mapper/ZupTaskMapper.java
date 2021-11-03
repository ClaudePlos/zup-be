package pl.rekeep.app.domain.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.rekeep.app.domain.Task;
import pl.rekeep.app.domain.ZupTask;
import pl.rekeep.app.domain.dto.ZupTaskDto;

@Mapper(componentModel = "spring")
public interface ZupTaskMapper {

    //@Mapping(source = "properties", target = "properties", qualifiedByName = "jsonNodeToString")
    @Mapping(source = "task", target = "taskId", qualifiedByName = "taskEntityToId")
    ZupTaskDto dto(ZupTask entity);

    @Named("taskEntityToId")
    static Long taskEntityToId(Task task) {
        return task.getId();
    }

    @Named("jsonNodeToString")
    static String jsonNodeToString(JsonNode jsonNode) {
        return jsonNode.toString();
    }
}
