package hu.nye.project.datingapp.service;

import hu.nye.project.datingapp.dto.InterestDTO;

import java.util.List;
import java.util.Optional;

public interface InterestService {

    List<InterestDTO> findAll();

    InterestDTO create(InterestDTO interestDTO);

    Optional<InterestDTO> findById(Long id);

    InterestDTO update(InterestDTO interestDTO);

    void delete (Long id);
}
