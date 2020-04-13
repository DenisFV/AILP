package ru.ailp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.ailp.entity.abstr.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Data
@Entity
@Table(name = "events", schema = "ailp")
public class EventEntity extends AbstractEntity {

    @Id
    @Column(name = "event_id")
    private Long id;
    private String eventType;
    private String eventName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public EventEntity(Long id) {
        this.id = id;
    }
}
