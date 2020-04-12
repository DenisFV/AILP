package ru.ailp.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "plans", schema = "ailp")
public class PlansEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plans_plan_id_seq")
    @SequenceGenerator(name = "plans_plan_id_seq", sequenceName = "ailp.plans_plan_id_seq", allocationSize = 1)
    private Long planId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long lesson1;
    private Long lesson2;
    private Long lesson3;
}
