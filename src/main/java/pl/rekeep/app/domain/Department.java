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
@Entity(name = "Department")
@Table(name = "HD_DZIALY")
public class Department {

    @Id
    @Column(name = "ID_DZ")
    private Long id;

    @Column(name = "NAZWA_DZ")
    private String name;
}
