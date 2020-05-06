package ru.ailp.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.ailp.entity.abstr.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "plans", schema = "ailp")
public class PlanEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plans_plan_id_seq")
    @SequenceGenerator(name = "plans_plan_id_seq", sequenceName = "ailp.plans_plan_id_seq", allocationSize = 1)
    @Column(name = "plan_id")
    Long id;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Long lesson1;
    Long lesson2;
    Long lesson3;
}
