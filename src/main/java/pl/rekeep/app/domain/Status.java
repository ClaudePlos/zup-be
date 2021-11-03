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
@Entity(name = "Status")
@Table(name = "HD_STATUSY")
public class Status {

    @Id
    @Column(name = "ID_ST")
    private Long id;

    @Column(name = "NAZWA_ST")
    private String name;

    @Column(name = "KOLOR")
    private String color;

    @Column(name = "PKT_ST")
    private String score;

}
