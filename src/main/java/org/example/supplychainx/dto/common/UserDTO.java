package org.example.supplychainx.dto.common;

import org.example.supplychainx.model.common.RoleEnum;
import lombok.Data;

@Data
public class UserDTO {
    private Long idUser;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private RoleEnum role;
}
