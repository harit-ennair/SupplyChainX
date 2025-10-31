package org.example.supplychainx.service.common.impl;

import org.example.supplychainx.dto.common.UserDTO;
import org.example.supplychainx.mapper.common.UserMapper;
import org.example.supplychainx.model.common.RoleEnum;
import org.example.supplychainx.model.common.User;
import org.example.supplychainx.repository.common.UserRepository;
import org.example.supplychainx.service.common.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
//    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO create(UserDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Un utilisateur avec cet email existe déjà.");
        }

        User user = userMapper.toEntity(dto);
        return userMapper.toDto(userRepository.save(user));
    }


    @Override
    public UserDTO update(Long id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));


        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());


        if (dto.getRole() != null && dto.getRole() != user.getRole()) {
            user.setRole(dto.getRole());
        }
        if( dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(dto.getPassword());
        }

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));


        if (user.getRole() == RoleEnum.ADMIN) {
            throw new RuntimeException("Impossible de supprimer un administrateur.");
        }

        userRepository.delete(user);
    }

    @Override
    public UserDTO getById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }
}
