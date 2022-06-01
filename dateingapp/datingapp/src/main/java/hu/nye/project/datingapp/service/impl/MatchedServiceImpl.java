package hu.nye.project.datingapp.service.impl;

import hu.nye.project.datingapp.dto.MatchedDTO;
import hu.nye.project.datingapp.entity.Matched;
import hu.nye.project.datingapp.entity.User;
import hu.nye.project.datingapp.exceptions.NotFoundException;
import hu.nye.project.datingapp.repository.MatchedRepository;
import hu.nye.project.datingapp.repository.UserRepository;
import hu.nye.project.datingapp.service.MatchedService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchedServiceImpl implements MatchedService {

    private final MatchedRepository matchedRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public MatchedServiceImpl(MatchedRepository matchedRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.matchedRepository = matchedRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public List<MatchedDTO> findAll() {
        List<Matched> matchedList = this.matchedRepository.findAll();
        return matchedList.stream()
                .map(matched -> this.modelMapper.map(matched, MatchedDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MatchedDTO> findAllUserMatchedById(Long userId) {
        List<Matched> matchedList = this.matchedRepository.findAllUserMatchedByUserId(userId);

        return matchedList.stream()
                .map(matched -> this.modelMapper.map(matched, MatchedDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MatchedDTO create(MatchedDTO matchedDTO) {
        Matched saveToMatched = this.modelMapper.map(matchedDTO, Matched.class);
        Optional<User> optionalUser = this.userRepository.findById(matchedDTO.getLikedUserId());

        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found with id=" + matchedDTO.getLikedUserId());
        }

        Matched savedMatched = this.matchedRepository.save(saveToMatched);
        return this.modelMapper.map(savedMatched, MatchedDTO.class);
    }

    @Override
    public void delete(Long id) {
        Optional<Matched> optionalMatched = this.matchedRepository.findById(id);

        if (optionalMatched.isPresent()) {
            Matched matchedToDelete = optionalMatched.get();
            this.matchedRepository.delete(matchedToDelete);

        } else {
            throw new NotFoundException("Matched not found with id=" + id);
        }
    }
}
