package hu.nye.project.datingapp.controller;

import hu.nye.project.datingapp.dto.PasswordDTO;
import hu.nye.project.datingapp.dto.UserCreateDTO;
import hu.nye.project.datingapp.dto.UserDTO;
import hu.nye.project.datingapp.service.UserService;
import hu.nye.project.datingapp.util.CheckError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;
    private final CheckError checkError = new CheckError();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserCreateDTO> create(@RequestBody @Valid UserCreateDTO userCreateDTO, BindingResult bindingResult) {
        this.checkError.checkForRequestErrors(bindingResult, "user");

        UserCreateDTO savedUser = this.userService.create(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        Optional<UserDTO> optionalUserDTO = userService.findById(id);

        ResponseEntity<UserDTO> response;

        if (optionalUserDTO.isPresent()) {
            response = ResponseEntity.ok(optionalUserDTO.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return response;
    }

    //A felhazsnalo csak a jelszavát tudja módosítani
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserCreateDTO> update(@RequestBody @Valid UserCreateDTO userCreateDTO,@PathVariable Long id, BindingResult bindingResult) {
        this.checkError.checkForRequestErrors(bindingResult, "user");

        UserCreateDTO updatedUser = userService.update(userCreateDTO, id);
        return ResponseEntity.ok(updatedUser);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
