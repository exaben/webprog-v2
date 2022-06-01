package hu.nye.project.datingapp.controller;

import hu.nye.project.datingapp.dto.ProfileDTO;
import hu.nye.project.datingapp.service.ProfileService;
import hu.nye.project.datingapp.util.CheckError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final CheckError checkError = new CheckError();

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProfileDTO>> findAll() {
        return ResponseEntity.ok(this.profileService.findAll());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProfileDTO> findById(@PathVariable Long id) {
        Optional<ProfileDTO> optionalProfileDTO = this.profileService.findById(id);
        return optionalProfileDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ProfileDTO> update(@RequestBody @Valid ProfileDTO profileDTO, BindingResult bindingResult) {
        this.checkError.checkForRequestErrors(bindingResult, "profile");

        ProfileDTO updateProfile = this.profileService.update(profileDTO);
        return ResponseEntity.ok(updateProfile);
    }

}
