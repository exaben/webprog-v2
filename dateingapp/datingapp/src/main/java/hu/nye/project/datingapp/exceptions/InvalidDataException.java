package hu.nye.project.datingapp.exceptions;

import java.util.List;

public class InvalidDataException extends  RuntimeException{

    private List<String> messages;

    public InvalidDataException(String message, List<String> messages) {
        super(message);
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}
