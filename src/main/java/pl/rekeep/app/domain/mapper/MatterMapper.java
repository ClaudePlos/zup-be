package pl.rekeep.app.domain.mapper;

import org.mapstruct.Mapper;
import pl.rekeep.app.domain.Matter;
import pl.rekeep.app.domain.dto.MatterDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MatterMapper {
    MatterDto dto(Matter entity);

    Matter entity(MatterDto dto);

    List<MatterDto> dtos(List<Matter> entities);

    List<Matter> entities(List<MatterDto> dtos);
}
