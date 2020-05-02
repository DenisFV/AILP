package ru.ailp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.ailp.entity.abstr.AbstractEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "pages", schema = "ailp")
public class PageEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pages_page_id_seq")
    @SequenceGenerator(name = "pages_page_id_seq", sequenceName = "ailp.pages_page_id_seq", allocationSize = 1)
    @Column(name = "page_id")
    private Long id;
    private Long lessonId;
    private String pageName;
    private String pageType;
    private String comment;
}