package ru.ailp.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.ailp.entity.abstr.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "events", schema = "ailp")
public class EventEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "events_event_id_seq")
    @SequenceGenerator(name = "events_event_id_seq", sequenceName = "ailp.events_event_id_seq", allocationSize = 1)
    @Column(name = "event_id")
    Long id;
    String eventType;
    String eventName;
    LocalDateTime startDate;
    LocalDateTime endDate;
}
