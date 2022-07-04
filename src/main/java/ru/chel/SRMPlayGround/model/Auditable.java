package ru.chel.SRMPlayGround.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class Auditable {
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Yekaterinburg")
    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;
}
