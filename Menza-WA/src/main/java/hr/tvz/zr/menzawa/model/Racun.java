package hr.tvz.zr.menzawa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RACUNI")
public class Racun {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String menza;
    //M:M veza izmedu racuna i jela
    @JsonBackReference
    @ManyToMany(targetEntity = Jelo.class)
    @JoinTable(
            name = "racun_jelo",
            joinColumns = {@JoinColumn(name = "racun_id")},
            inverseJoinColumns = {@JoinColumn(name = "jelo_id")}
    )
    private List<Jelo> jela;
    private Double
            suma;
    private String blagajna;
    @Column(name = "vrijeme_izdavanja")
    private LocalDateTime vrijemeIzdavanja;
}
