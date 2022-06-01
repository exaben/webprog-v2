package hu.nye.project.datingapp.repository;

import hu.nye.project.datingapp.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findUserMessagesBySenderIdAndReceivedId(Long senderId, Long receivedId);
}
