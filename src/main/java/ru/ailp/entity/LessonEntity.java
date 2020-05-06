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
@Table(name = "lessons", schema = "ailp")
public class LessonEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lessons_lesson_id_seq")
    @SequenceGenerator(name = "lessons_lesson_id_seq", sequenceName = "ailp.lessons_lesson_id_seq", allocationSize = 1)
    @Column(name = "lesson_id")
    Long id;
    String lessonName;
    LocalDateTime startDate;
    LocalDateTime endDate;
}
