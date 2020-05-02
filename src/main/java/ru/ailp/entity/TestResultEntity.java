package ru.ailp.entity;

import lombok.*;
import ru.ailp.entity.abstr.AbstractEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "test_results", schema = "ailp")
public class TestResultEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_results_test_result_id_seq")
    @SequenceGenerator(name = "test_results_test_result_id_seq", sequenceName = "ailp.test_results_test_result_id_seq", allocationSize = 1)
    @Column(name = "test_result_id")
    private Long id;
    private Long testId;
    private Long questionId;
    private Long userId;
    private String result;
    private String comment;
}
