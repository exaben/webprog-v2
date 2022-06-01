package hu.nye.project.datingapp.service.impl;

import hu.nye.project.datingapp.dto.MessageDTO;
import hu.nye.project.datingapp.entity.Message;
import hu.nye.project.datingapp.entity.User;
import hu.nye.project.datingapp.exceptions.NotFoundException;
import hu.nye.project.datingapp.repository.MessageRepository;
import hu.nye.project.datingapp.repository.UserRepository;
import hu.nye.project.datingapp.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public MessageServiceImpl(MessageRepository messageRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public MessageDTO create(MessageDTO messageDTO) {
        Message messageToSave = modelMapper.map(messageDTO, Message.class);
        Message savedMessage = this.messageRepository.save(messageToSave);

        return modelMapper.map(savedMessage, MessageDTO.class);
    }

    @Override
    public List<MessageDTO> findAllBySenderIdAndReceivedId(Long senderId, Long receivedId) {
        Optional<User> optionalUser = this.userRepository.findById(receivedId);

        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found with id=" + receivedId);
        }

        //oda vissza kezeli az özeneteket
        List<Message> messagesBySender = this.messageRepository.findUserMessagesBySenderIdAndReceivedId(senderId, receivedId);
        List<Message> messagesByReceived = this.messageRepository.findUserMessagesBySenderIdAndReceivedId(receivedId, senderId);

        List<Message> messageList = Stream.concat(messagesBySender.stream(), messagesByReceived.stream())
                .collect(Collectors.toList());

        //dátum alapján rendezi az üzeneteket
        Comparator<Message> comparatorId = (Message m1, Message m2) -> m1.getSendDate().compareTo(m2.getSendDate());

        messageList.sort(comparatorId);

        return messageList.stream()
                .map(messages -> modelMapper.map(messages, MessageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);

        if (optionalMessage.isPresent()) {
            Message messageToDelete = optionalMessage.get();
            messageRepository.delete(messageToDelete);
        } else {
            throw new NotFoundException("Message not found with id=" + id);
        }
    }
}
