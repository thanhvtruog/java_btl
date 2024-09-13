package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Center;
import com.mycompany.myapp.domain.Fresher;
import com.mycompany.myapp.service.dto.CenterDTO;
import com.mycompany.myapp.service.dto.FresherDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Fresher} and its DTO {@link FresherDTO}.
 */
@Mapper(componentModel = "spring")
public interface FresherMapper extends EntityMapper<FresherDTO, Fresher> {
    @Mapping(target = "center", source = "center", qualifiedByName = "centerName")
    FresherDTO toDto(Fresher s);

    @Named("centerName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CenterDTO toDtoCenterName(Center center);
}
