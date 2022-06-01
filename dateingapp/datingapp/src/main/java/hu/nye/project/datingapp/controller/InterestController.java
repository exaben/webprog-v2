package hu.nye.project.datingapp.controller;

import hu.nye.project.datingapp.dto.InterestDTO;
import hu.nye.project.datingapp.service.InterestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

//erdeklodesi korok kezelese
@RestController
@RequestMapping(path = "/interests")
public class InterestController {

    private final InterestService interestService;

    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<InterestDTO>> findAll() {
        return ResponseEntity.ok(this.interestService.findAll());
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ResponseEntity<InterestDTO> findById(@PathVariable Long id) {
        Optional<InterestDTO> optionalInterestDTO = this.interestService.findById(id);
        return optionalInterestDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
