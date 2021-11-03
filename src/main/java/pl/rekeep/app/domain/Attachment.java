package pl.rekeep.app.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Attachment")
@Table(name = "HD_ZGLOSZENIA_PLIKI")
public class Attachment {

    @Id
    @Column(name = "ID_P")
    private Long id;

    @Column(name = "FILENAME")
    private String filename;

    @Lob
    @Column(name = "PLIK")
    private byte[] file;

    @Column(name = "FILETYPE")
    private final String fileType = "application/pdf; charset=binary";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ID_ZG")
    private Task task;

}
