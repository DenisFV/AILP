package ru.ailp.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.ailp.entity.abstr.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "event_logs", schema = "ailp")
public class EventLogEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_logs_event_log_id_seq")
    @SequenceGenerator(name = "event_logs_event_log_id_seq", sequenceName = "ailp.event_logs_event_log_id_seq", allocationSize = 1)
    @Column(name = "event_log_id")
    Long id;
    Long userId;
    Long eventId;
    LocalDateTime eventDate;
    Long pageId;
    Long lessonId;
}
