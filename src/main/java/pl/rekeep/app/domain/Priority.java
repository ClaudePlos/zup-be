package pl.rekeep.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Priority")
@Table(name = "HD_PRIORYTETY")
public class Priority {

    @Id
    @Column(name = "ID_PR")
    private Long id;

    @Column(name = "NAZWA_PR")
    private String name;

    @Column(name = "KOLOR")
    private String color;

    @Column(name = "PKT_PR")
    private Long score;

}
