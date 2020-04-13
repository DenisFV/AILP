package ru.ailp.entity;

import lombok.*;
import ru.ailp.entity.abstr.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event_log", schema = "ailp")
public class EventLogEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_log_event_log_id_seq")
    @SequenceGenerator(name = "event_log_event_log_id_seq", sequenceName = "ailp.event_log_event_log_id_seq", allocationSize = 1)
    @Column(name = "event_log_id")
    private Long id;
    private Long userId;
    private Long eventId;
    private LocalDateTime eventDate;
    private Long pageId;
    private Long lessonId;
}
