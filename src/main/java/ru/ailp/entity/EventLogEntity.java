package ru.ailp.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "event_log", schema = "ailp")
public class EventLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_log_event_log_id_seq")
    @SequenceGenerator(name = "event_log_event_log_id_seq", sequenceName = "ailp.event_log_event_log_id_seq", allocationSize = 1)
    private Long eventLogId;
    private Long userId;
    private Long eventId;
    private LocalDateTime eventDate;
    private Long pageId;
    private Long lessonId;
}
