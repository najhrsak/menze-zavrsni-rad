package hr.tvz.zr.menzawa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "BLAGAJNE")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Blagajna{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "korisnicko_ime")
    private String username;
    @Column(name = "lozinka")
    private String password;
    private String naziv;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name= "menza_id")
    private Menza menza;

    public UUID getId() {
        return id;
    }

}
