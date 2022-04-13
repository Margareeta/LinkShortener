package org.margareeta.linkshortener.baseservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "links")
public class LinkEntity {
    @Id
    @Column(name = "short_link")
    private String shortLink;
    @Column(name = "full_link")
    private String fullLink;
    @Convert(converter = AtomicLongConverter.class)
    @Column(name = "click_counter")
    private AtomicLong counter;
    @Column(name = "created_at")
    private Date createdAt;


}
