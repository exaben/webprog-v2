package hu.nye.project.datingapp.service;

import hu.nye.project.datingapp.dto.MatchedDTO;

import java.util.List;

public interface MatchedService {

    List<MatchedDTO> findAll();

    List<MatchedDTO> findAllUserMatchedById(Long userId);

    MatchedDTO create(MatchedDTO matchedDTO);

    void delete(Long id);
}
