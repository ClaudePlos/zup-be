package pl.rekeep.app.domain.mapper;

import org.mapstruct.Mapper;
import pl.rekeep.app.domain.Status;
import pl.rekeep.app.domain.dto.StatusDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusMapper {
    StatusDto dto(Status entity);

    Status entity(StatusDto dto);

    List<StatusDto> dtos(List<Status> entities);

    List<Status> entities(List<StatusDto> dtos);
}
