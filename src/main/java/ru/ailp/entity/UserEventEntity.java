package ru.ailp.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_event", schema = "ailp", catalog = "ailp")
public class UserEventEntity {
    private Long userEventId;
    private Long eventId;
    private Long userId;
    private LocalDateTime eventDate;
    private Long pageId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ailp.user_event_user_event_id_seq")
    @SequenceGenerator(name = "ailp.user_event_user_event_id_seq", sequenceName = "ailp.user_event_user_event_id_seq", allocationSize = 1)
    @Column(name = "user_event_id", nullable = false)
    public Long getUserEventId() {
        return userEventId;
    }

    public void setUserEventId(Long userEventId) {
        this.userEventId = userEventId;
    }

    @Basic
    @Column(name = "event_id", nullable = true)
    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "event_date", nullable = true)
    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    @Basic
    @Column(name = "page_id", nullable = true)
    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEventEntity that = (UserEventEntity) o;
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
        return "UserEventEntity{" +
                "userEventId=" + userEventId +
                ", eventId=" + eventId +
                ", userId=" + userId +
                ", eventDate=" + eventDate +
                ", pageId=" + pageId +
                '}';
    }
}
