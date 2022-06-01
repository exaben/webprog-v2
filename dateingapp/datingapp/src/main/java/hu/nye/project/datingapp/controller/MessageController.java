package hu.nye.project.datingapp.controller;

import hu.nye.project.datingapp.dto.MessageDTO;
import hu.nye.project.datingapp.service.MessageService;
import hu.nye.project.datingapp.util.CheckError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/messages")
public class MessageController {

    private final MessageService messageService;
    private final CheckError checkError = new CheckError();

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(path = "/{senderId}/{receivedId}", method = RequestMethod.GET)
    public ResponseEntity<List<MessageDTO>> findAllBySenderAndReceived(@PathVariable Long senderId,
            @PathVariable Long receivedId) {
        return ResponseEntity.ok(this.messageService.findAllBySenderIdAndReceivedId(senderId, receivedId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<MessageDTO> create(@RequestBody @Valid MessageDTO messageDTO, BindingResult bindingResult) {
        this.checkError.checkForRequestErrors(bindingResult, "message");

        MessageDTO savedMessage = this.messageService.create(messageDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessage);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.messageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
