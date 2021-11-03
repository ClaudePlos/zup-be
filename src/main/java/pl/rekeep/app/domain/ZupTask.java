package pl.rekeep.app.domain;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import pl.rekeep.app.domain.converter.JsonNodeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "ZupTask")
@Table(name = "NAP_ZUP_ZGLOSZENIA")
public class ZupTask {

    @Id
/*    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")*/
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    private String createdBy;

    @Lob
    @Convert(converter = JsonNodeConverter.class)
    private JsonNode properties;

    @Column(name = "CREATED_AT", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HD_ZGLOSZENIE_ID")
    private Task task;

    @Override
    public String toString() {
        return "ZupTask{" +
                "id=" + id +
                ", createdBy='" + createdBy + '\'' +
                ", properties=" + properties +
                ", createdAt=" + createdAt +
                '}';
    }
}