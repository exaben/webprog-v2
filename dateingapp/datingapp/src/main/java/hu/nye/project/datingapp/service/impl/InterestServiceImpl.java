package hu.nye.project.datingapp.service.impl;

import hu.nye.project.datingapp.dto.InterestDTO;
import hu.nye.project.datingapp.entity.Interest;
import hu.nye.project.datingapp.exceptions.NotFoundException;
import hu.nye.project.datingapp.repository.InterestRepository;
import hu.nye.project.datingapp.service.InterestService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InterestServiceImpl implements InterestService {

    private final InterestRepository interestRepository;
    private final ModelMapper modelMapper;

    public InterestServiceImpl(InterestRepository interestRepository, ModelMapper modelMapper) {
        this.interestRepository = interestRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<InterestDTO> findAll() {
        // Összes érdeklődési kör lekérdezése
        List<Interest> interestList = this.interestRepository.findAll();

        // A lekérdezett érdeklődési körök DTO classá alakítása és visszaádása
        return interestList.stream()
                .map(interest -> modelMapper.map(interest, InterestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public InterestDTO create(InterestDTO interestDTO) {
        Interest interestToSave = modelMapper.map(interestDTO, Interest.class);
        Interest saveInterest = this.interestRepository.save(interestToSave);

        return modelMapper.map(saveInterest, InterestDTO.class);
    }

    @Override
    public Optional<InterestDTO> findById(Long id) {
        Optional<Interest> optionalInterest = this.interestRepository.findById(id);
        return optionalInterest.map(interest -> modelMapper.map(interest, InterestDTO.class));
    }

    @Override
    public InterestDTO update(InterestDTO interestDTO) {
        Long id = interestDTO.getId();
        Optional<Interest> optionalInterest = this.interestRepository.findById(id);

        if (optionalInterest.isEmpty()) {
            throw new NotFoundException("Interest not found with id=" + id);
        }

        Interest interestToUpdate = modelMapper.map(interestDTO, Interest.class);
        Interest saveUser = this.interestRepository.save(interestToUpdate);

        return modelMapper.map(saveUser, InterestDTO.class);
    }

    @Override
    public void delete(Long id) {
        Optional<Interest> optionalInterest = this.interestRepository.findById(id);

        if (optionalInterest.isPresent()) {
            Interest interestToDelete = optionalInterest.get();
            this.interestRepository.delete(interestToDelete);
        } else {
            throw new NotFoundException("Interest not found with id=" + id);
        }
    }
}
