package hu.nye.project.datingapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class MessageDTO {

    private Long id;

    @NotNull
    private Long senderId;

    @NotNull
    private Long receivedId;
    @NotBlank
    private String message;

    private Date sendDate = new Date();

    public MessageDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceivedId() {
        return receivedId;
    }

    public void setReceivedId(Long receiveUserId) {
        this.receivedId = receiveUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
}
