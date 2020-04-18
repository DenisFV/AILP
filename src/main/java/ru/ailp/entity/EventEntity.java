package ru.ailp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.ailp.entity.abstr.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Data
@Entity
@Table(name = "events", schema = "ailp")
public class EventEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "events_event_id_seq")
    @SequenceGenerator(name = "events_event_id_seq", sequenceName = "ailp.events_event_id_seq", allocationSize = 1)
    @Column(name = "event_id")
    private Long id;
    private String eventType;
    private String eventName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
