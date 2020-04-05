package ru.ailp.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@ApiModel(value = "UserEventDto", description = "UserEvent")
public class UserEventDto implements Serializable {

    @ApiModelProperty(value = "Индентификатор объекта", position = 1, example = "1")
    private Long userEventId;
    private Long eventId;
    private Long userId;
    private LocalDateTime eventDate;
    private Long pageId;

    public UserEventDto() {
    }

    public Long getUserEventId() {
        return userEventId;
    }

    public void setUserEventId(Long userEventId) {
        this.userEventId = userEventId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEventDto)) return false;
        UserEventDto that = (UserEventDto) o;
        return Objects.equals(userEventId, that.userEventId) &&
                Objects.equals(eventId, that.eventId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(eventDate, that.eventDate) &&
                Objects.equals(pageId, that.pageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEventId, eventId, userId, eventDate, pageId);
    }

    @Override
    public String toString() {
        return "UserEventDto{" +
                "userEventId=" + userEventId +
                ", eventId=" + eventId +
                ", userId=" + userId +
                ", eventDate=" + eventDate +
                ", pageId=" + pageId +
                '}';
    }
}
