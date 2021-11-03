package pl.rekeep.app.domain.mapper;

import org.mapstruct.Mapper;
import pl.rekeep.app.domain.Department;
import pl.rekeep.app.domain.dto.DepartmentDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentDto dto(Department entity);

    Department entity(DepartmentDto dto);

    List<DepartmentDto> dtos(List<Department> entities);

    List<Department> entities(List<DepartmentDto> dtos);
}
