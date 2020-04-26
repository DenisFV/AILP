package ru.ailp.entity.abstr;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Transient
    private Long Id;
}
