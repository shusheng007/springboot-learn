package top.ss007.jpademo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditable<T> {

    @CreatedBy
    private T createdBy;

    @CreatedDate
    private Date createAt;

    @LastModifiedBy
    private T updatedBy;

    @LastModifiedDate
    private Date updateAt;

}
