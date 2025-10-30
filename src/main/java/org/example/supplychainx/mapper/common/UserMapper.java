package org.example.supplychainx.mapper.common;

import org.example.supplychainx.dto.common.UserDTO;
import org.example.supplychainx.model.common.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO dto);
}
