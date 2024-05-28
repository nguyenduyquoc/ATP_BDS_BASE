package com.anthinhphatjsc.ezisolutions.mappers;

import com.anthinhphatjsc.ezisolutions.core.BaseMapper;
import com.anthinhphatjsc.ezisolutions.dtos.RoleDTO;
import com.anthinhphatjsc.ezisolutions.dtos.UserDTO;
import com.anthinhphatjsc.ezisolutions.entities.UserEntity;
import com.anthinhphatjsc.ezisolutions.requests.RequestDtoUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements BaseMapper<UserEntity, UserDTO, RequestDtoUser> {

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserEntity toEntityFromRequest(RequestDtoUser dto) {
        return modelMapper.map(dto, UserEntity.class);
    }

    @Override
    public UserEntity merge(UserEntity source, UserEntity target) {
        target.setName(source.getName());
        return target;
    }

    @Override
    public UserDTO toDTO(UserEntity entity) {
        UserDTO dto = modelMapper.map(entity, UserDTO.class);
        dto.setRoles(entity.getRoles().stream().map(e -> modelMapper.map(e, RoleDTO.class)).toList());
        return dto;
    }
}
