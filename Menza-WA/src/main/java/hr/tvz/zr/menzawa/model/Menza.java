package hr.tvz.zr.menzawa.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MENZE")
public class Menza {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column()
    private String naziv;
    private String adresa;
    private String info;
    @Column(name = "radno_vrijeme")
    private String radnoVrijeme;
    private String meni;
    @JsonManagedReference
    @OneToMany(mappedBy = "menza", fetch = FetchType.EAGER)
    private List<Blagajna> blagajne;
    private Integer guzva;
}
