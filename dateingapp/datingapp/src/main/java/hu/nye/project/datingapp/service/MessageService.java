package hu.nye.project.datingapp.service;

import hu.nye.project.datingapp.dto.MessageDTO;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    MessageDTO create(MessageDTO messageDTO);

    List<MessageDTO> findAllBySenderIdAndReceivedId(Long senderId, Long receivedId);

    void delete(Long id);

}
