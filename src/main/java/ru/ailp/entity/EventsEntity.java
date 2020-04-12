package ru.ailp.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "events", schema = "ailp")
public class EventsEntity {
    @Id
    private Long eventId;
    private String eventType;
    private String eventName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
