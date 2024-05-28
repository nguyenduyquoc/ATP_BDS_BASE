package com.anthinhphatjsc.ezisolutions.mappers;

import com.anthinhphatjsc.ezisolutions.core.BaseDTO;
import com.anthinhphatjsc.ezisolutions.core.BaseMapper;
import com.anthinhphatjsc.ezisolutions.dtos.RoleDTO;
import com.anthinhphatjsc.ezisolutions.dtos.UserDTO;
import com.anthinhphatjsc.ezisolutions.entities.RoleEntity;
import com.anthinhphatjsc.ezisolutions.requests.RequestDtoRole;
import com.anthinhphatjsc.ezisolutions.requests.RequestDtoUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements BaseMapper<RoleEntity, RoleDTO, RequestDtoRole> {

    @Autowired
    ModelMapper modelMapper;

    @Override
    public RoleEntity toEntityFromRequest(RequestDtoRole dto) {
        return modelMapper.map(dto, RoleEntity.class);
    }

    @Override
    public RoleEntity merge(RoleEntity source, RoleEntity target) {
        target.setName(source.getName());
        target.setCode(source.getCode());
        target.setPriority(source.getPriority());
        return target;
    }

    @Override
    public RoleDTO toDTO(RoleEntity entity) {
        return modelMapper.map(entity, RoleDTO.class);
    }
}
