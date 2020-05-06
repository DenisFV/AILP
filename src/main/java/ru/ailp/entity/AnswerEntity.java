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
@Table(name = "question_answers", schema = "ailp")
public class AnswerEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_answers_answer_id_seq")
    @SequenceGenerator(name = "question_answers_answer_id_seq", sequenceName = "ailp.question_answers_answer_id_seq", allocationSize = 1)
    @Column(name = "answer_id")
    Long id;
    Long questionId;
    String answer;
    String comment;
}
