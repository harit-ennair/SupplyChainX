package org.example.supplychainx.mapper.common;

import org.example.supplychainx.dto.common.UserDTO;
import org.example.supplychainx.model.common.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    UserDTO toDto(User user);

    @Mapping(target = "idUser", ignore = true)
    User toEntity(UserDTO dto);
}
