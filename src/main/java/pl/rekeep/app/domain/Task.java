package pl.rekeep.app.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Task")
@Table(name = "HD_ZGLOSZENIA")
public class Task {

    @Id
    @Column(name = "ID_ZG")
    private Long id;

    @Column(name = "DATA_ZG", insertable = false, updatable = false)
    private LocalDate createdAt;

    @Column(name = "OPIS")
    private String description;

    @Column(name = "NAGLOWEK")
    private String header;

    @Column(name = "DATA_OCZ")
    private LocalDate expectedDate;

    @Column(name = "NAZWISKO_ZGLOSZ")
    private String userSurname;

    @Column(name = "UID_ZGLOSZ")
    private String userLogin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ID_ST")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ID_PR")
    private Priority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ID_TE")
    private Matter matter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ID_DZ")
    private Department department;


}
