package org.example.supplychainx.service.common;

import org.example.supplychainx.dto.common.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO create(UserDTO dto);
    UserDTO update(Long id, UserDTO dto);
    void delete(Long id);
    UserDTO getById(Long id);
    List<UserDTO> getAll();
}
