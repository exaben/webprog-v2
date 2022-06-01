package hu.nye.project.datingapp.service.impl;

import hu.nye.project.datingapp.dto.ProfileDTO;
import hu.nye.project.datingapp.entity.Profile;
import hu.nye.project.datingapp.exceptions.NotFoundException;
import hu.nye.project.datingapp.repository.ProfileRepository;
import hu.nye.project.datingapp.service.ProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;

    public ProfileServiceImpl(ProfileRepository profileRepository, ModelMapper modelMapper) {
        this.profileRepository = profileRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProfileDTO> findAll() {
        List<Profile> profileList = this.profileRepository.findAll();

        return profileList.stream()
                .map(profile -> modelMapper.map(profile, ProfileDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProfileDTO> findById(Long id) {
        Optional<Profile> optionalProfile = this.profileRepository.findByUserId(id);
        return optionalProfile.map(profile -> modelMapper.map(profile, ProfileDTO.class));
    }

    @Override
    public ProfileDTO update(ProfileDTO profileDTO) {
        Long profileId = profileDTO.getId();
        Optional<Profile> optionalProfile = this.profileRepository.findById(profileId);

        if (optionalProfile.isEmpty()) {
            throw new NotFoundException("Profile data not found with id=" + profileId);
        }

        Profile profileToUpdate = modelMapper.map(profileDTO, Profile.class);
        Profile savedProfile = this.profileRepository.save(profileToUpdate);

        return modelMapper.map(savedProfile, ProfileDTO.class);
    }

}
