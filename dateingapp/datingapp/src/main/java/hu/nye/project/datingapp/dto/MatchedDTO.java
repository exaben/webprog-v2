package hu.nye.project.datingapp.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class MatchedDTO {

    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Long likedUserId;

    private Date likedDate = new Date();

    public MatchedDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLikedUserId() {
        return likedUserId;
    }

    public void setLikedUserId(Long likedUserId) {
        this.likedUserId = likedUserId;
    }

    public Date getLikedDate() {
        return likedDate;
    }

    public void setLikedDate(Date likedDate) {
        this.likedDate = likedDate;
    }
}
