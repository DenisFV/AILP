package ru.ailp.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.ailp.entity.abstr.AbstractEntity;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "questions", schema = "ailp")
public class QuestionEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "questions_questions_id_seq")
    @SequenceGenerator(name = "questions_questions_id_seq", sequenceName = "ailp.questions_questions_id_seq", allocationSize = 1)
    @Column(name = "question_id")
    Long id;
    Long testId;
    String answerOption;
    String question;
    String comment;
}
