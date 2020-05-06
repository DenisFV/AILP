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
@Table(name = "lesson_tests", schema = "ailp")
public class LessonTestEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_tests_lesson_test_id_seq")
    @SequenceGenerator(name = "lesson_tests_lesson_test_id_seq", sequenceName = "ailp.lesson_tests_lesson_test_id_seq", allocationSize = 1)
    @Column(name = "lesson_test_id")
    Long id;
    Long lessonId;
    Long pageId;
    Long testId;
    Long testTypeId;
    String testName;
    LocalDateTime startDate;
    LocalDateTime endDate;
}
