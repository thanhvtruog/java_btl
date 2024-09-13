package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Center;
import com.mycompany.myapp.domain.Fresher;
import com.mycompany.myapp.domain.Project;
import com.mycompany.myapp.service.dto.CenterDTO;
import com.mycompany.myapp.service.dto.FresherDTO;
import com.mycompany.myapp.service.dto.ProjectDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Project} and its DTO {@link ProjectDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProjectMapper extends EntityMapper<ProjectDTO, Project> {
    @Mapping(target = "freshers", source = "freshers", qualifiedByName = "fresherIdSet")
    @Mapping(target = "center", source = "center", qualifiedByName = "centerName")
    ProjectDTO toDto(Project s);

    @Mapping(target = "removeFreshers", ignore = true)
    Project toEntity(ProjectDTO projectDTO);

    @Named("fresherId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    FresherDTO toDtoFresherId(Fresher fresher);

    @Named("fresherIdSet")
    default Set<FresherDTO> toDtoFresherIdSet(Set<Fresher> fresher) {
        return fresher.stream().map(this::toDtoFresherId).collect(Collectors.toSet());
    }

    @Named("centerName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CenterDTO toDtoCenterName(Center center);
}
