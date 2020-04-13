package ru.ailp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.ailp.entity.abstr.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "plans", schema = "ailp")
public class PlanEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plans_plan_id_seq")
    @SequenceGenerator(name = "plans_plan_id_seq", sequenceName = "ailp.plans_plan_id_seq", allocationSize = 1)
    @Column(name = "plan_id")
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long lesson1;
    private Long lesson2;
    private Long lesson3;
}
