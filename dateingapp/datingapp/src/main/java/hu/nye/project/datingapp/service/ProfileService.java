package hu.nye.project.datingapp.service;


import hu.nye.project.datingapp.dto.ProfileDTO;

import java.util.List;
import java.util.Optional;

public interface ProfileService {

    List<ProfileDTO> findAll();

    Optional<ProfileDTO> findById(Long id);

    ProfileDTO update(ProfileDTO profileDTO);

}
