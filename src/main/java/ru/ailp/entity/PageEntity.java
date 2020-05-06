package ru.ailp.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.ailp.entity.abstr.AbstractEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "pages", schema = "ailp")
public class PageEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pages_page_id_seq")
    @SequenceGenerator(name = "pages_page_id_seq", sequenceName = "ailp.pages_page_id_seq", allocationSize = 1)
    @Column(name = "page_id")
    Long id;
    Long lessonId;
    String pageName;
    String pageType;
    String comment;
}