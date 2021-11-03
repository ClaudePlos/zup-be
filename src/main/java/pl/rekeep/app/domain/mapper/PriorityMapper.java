package pl.rekeep.app.domain.mapper;

import org.mapstruct.Mapper;
import pl.rekeep.app.domain.Priority;
import pl.rekeep.app.domain.dto.PriorityDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriorityMapper {
    PriorityDto dto(Priority entity);

    Priority entity(PriorityDto dto);

    List<PriorityDto> dtos(List<Priority> entities);

    List<Priority> entities(List<PriorityDto> dtos);
}
