package org.example.supplychainx.service.common;

import org.example.supplychainx.dto.common.UserDTO;
import org.example.supplychainx.mapper.common.UserMapper;
import org.example.supplychainx.model.common.RoleEnum;
import org.example.supplychainx.model.common.User;
import org.example.supplychainx.repository.common.UserRepository;
import org.example.supplychainx.service.common.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Test 1: création d’un utilisateur
    @Test
    void shouldCreateUserSuccessfully() {
        // Arrange
        UserDTO dto = new UserDTO();
        dto.setFirstName("Harit");
        dto.setLastName("Ennair");
        dto.setEmail("harit@supplyx.com");
        dto.setPassword("1234");
        dto.setRole(RoleEnum.ADMIN);

        User entity = new User(null,"harit", "Harit", "Ennair", "harit@supplyx.com", "1234", RoleEnum.ADMIN);
        User savedEntity = new User(1L,"harit", "Harit", "Ennair", "harit@supplyx.com", "1234", RoleEnum.ADMIN);
        UserDTO savedDTO = new UserDTO(1L,"harit", "Harit", "Ennair", "harit@supplyx.com", "1234", RoleEnum.ADMIN);

        when(userMapper.toEntity(dto)).thenReturn(entity);
        when(userRepository.save(entity)).thenReturn(savedEntity);
        when(userMapper.toDto(savedEntity)).thenReturn(savedDTO);

        // Act
        UserDTO result = userService.create(dto);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getIdUser()).isEqualTo(1L);
        assertThat(result.getEmail()).isEqualTo("harit@supplyx.com");

        verify(userRepository, times(1)).save(entity);
    }

    // Test 2: récupération de tous les utilisateurs
    @Test
    void shouldReturnAllUsers() {
        // Arrange
        List<User> users = List.of(
                new User(1L,"ahmad", "Admin", "One", "admin@supplyx.com", "1234", RoleEnum.ADMIN),
                new User(2L,"john", "John", "Doe", "john@supplyx.com", "1234", RoleEnum.CHEF_PRODUCTION)
        );

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toDto(any(User.class))).thenAnswer(inv -> {
            User u = inv.getArgument(0);
            return new UserDTO(u.getIdUser(),u.getUsername(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getPassword(), u.getRole());
        });

        // Act
        List<UserDTO> result = userService.getAll();

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getEmail()).isEqualTo("admin@supplyx.com");
        verify(userRepository, times(1)).findAll();
    }

    // Test 3: mise à jour utilisateur existant
    @Test
    void shouldUpdateUser() {
        // Arrange
        Long id = 1L;
        User existingUser = new User(id,"harit", "Harit", "Old", "harit@supplyx.com", "1234", RoleEnum.ADMIN);
        UserDTO dto = new UserDTO(id,"harit", "Harit", "New", "harit@supplyx.com", "1234", RoleEnum.ADMIN);

        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);
        when(userMapper.toDto(existingUser)).thenReturn(dto);

        // Act
        UserDTO result = userService.update(id, dto);

        // Assert
        assertThat(result.getLastName()).isEqualTo("New");
        verify(userRepository, times(1)).save(existingUser);
    }

    // Test 4: suppression utilisateur
    @Test
    void shouldDeleteUser() {
        // Arrange
        Long id = 1L;
        User existingUser = new User(id,"john", "John", "Doe", "john@supplyx.com", "1234", RoleEnum.CHEF_PRODUCTION);
        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));

        // Act
        userService.delete(id);

        // Assert
        verify(userRepository, times(1)).delete(existingUser);
    }
}
