package pl.rekeep.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Matter")
@Table(name = "HD_TEMATY")
public class Matter {

    @Id
    @Column(name = "ID_TE")
    private Long id;

    @Column(name = "NAZWA_TE")
    private String name;

    @Column(name = "PUBLICZNY")
    private char isPublic;

    @Column(name = "TRESC_DEF")
    private String definition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ID_DZ")
    private Department department;
}
