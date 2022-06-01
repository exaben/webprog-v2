package hu.nye.project.datingapp.controller;

import hu.nye.project.datingapp.dto.MatchedDTO;
import hu.nye.project.datingapp.service.MatchedService;
import hu.nye.project.datingapp.util.CheckError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/matched")
public class MatchedController {

    private final MatchedService matchedService;
    private final CheckError checkError = new CheckError();

    public MatchedController(MatchedService matchedService) {
        this.matchedService = matchedService;
    }

    //leker minden matchet
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<MatchedDTO>> findAll() {
        return ResponseEntity.ok(this.matchedService.findAll());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<MatchedDTO>> findAllUserMatched(@PathVariable Long id) {
        return ResponseEntity.ok(this.matchedService.findAllUserMatchedById(id));
    }

    //letrehoz egy uj matchet
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<MatchedDTO> create(@RequestBody @Valid MatchedDTO matchedDTO, BindingResult bindingResult) {
        this.checkError.checkForRequestErrors(bindingResult, "matched");

        MatchedDTO saveToMatched = this.matchedService.create(matchedDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveToMatched);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.matchedService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
