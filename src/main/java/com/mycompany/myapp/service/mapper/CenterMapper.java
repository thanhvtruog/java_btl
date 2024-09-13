package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Center;
import com.mycompany.myapp.service.dto.CenterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Center} and its DTO {@link CenterDTO}.
 */
@Mapper(componentModel = "spring")
public interface CenterMapper extends EntityMapper<CenterDTO, Center> {}
