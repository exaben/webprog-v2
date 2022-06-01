package hu.nye.project.datingapp.controller;

import hu.nye.project.datingapp.dto.UserDTO;
import hu.nye.project.datingapp.request.AuthenticationRequest;
import hu.nye.project.datingapp.service.UserService;
import hu.nye.project.datingapp.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

//bejelentkezes
@RestController
@RequestMapping(path = "/authentication")
public class AuthenticationController {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthenticationController(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDTO> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        Optional<UserDTO> optionalUserDTO = authenticateUser(authenticationRequest);
        optionalUserDTO.get().setToken(jwtUtil.createAndSignToken(authenticationRequest.getUsername()));

        return ResponseEntity.ok(optionalUserDTO.get());
    }

    private Optional<UserDTO> authenticateUser(AuthenticationRequest authenticationRequest) {
        Optional<UserDTO> userDTO = userService.findByUsernameAndPassword(authenticationRequest.getUsername(),
                authenticationRequest.getPassword());

        if (userDTO.isEmpty()) {
            throw new RuntimeException("Authentication failed");
        }

        return userDTO;
    }
}
