package com.demo.domain.entity;

import com.demo.shared.base.BaseEvent;
import com.demo.shared.constant.JPA_TYPE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@TypeDef(name = JPA_TYPE.JSON_BINARY, typeClass = com.vladmihalcea.hibernate.type.json.JsonBinaryType.class)
@Table(name = "event_store")
public class EventStoreEntity {

    @Id
    private String id;
    private Date timeStamp;
    private String aggregateIdentifier;
    private String aggregateType;
    private int version;
    private String eventType;

    @Type(type = JPA_TYPE.JSON_BINARY)
    @Column(columnDefinition = JPA_TYPE.JSON_BINARY)
    private BaseEvent eventData;

}
