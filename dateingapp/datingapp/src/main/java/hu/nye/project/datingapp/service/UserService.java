package hu.nye.project.datingapp.service;

import hu.nye.project.datingapp.dto.PasswordDTO;
import hu.nye.project.datingapp.dto.UserCreateDTO;
import hu.nye.project.datingapp.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> findAll();

    UserCreateDTO create(UserCreateDTO userCreateDTO);

    Optional<UserDTO> findById(Long id);

    PasswordDTO updatePassword(PasswordDTO passwordDTO, Long id) throws Exception;

    UserCreateDTO update(UserCreateDTO userCreateDTO, Long id);

    void delete(Long id);

    Optional<UserDTO> findByUsernameAndPassword(String username, String password);

    Optional<UserDTO> findUserByUsername(String username);
}
