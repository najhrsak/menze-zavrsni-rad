package hr.tvz.zr.menzawa.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "JELA")
public class Jelo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String naziv;
    private Double cijena;
    @JsonManagedReference
    @ManyToMany(targetEntity = Racun.class, mappedBy = "jela")
    private List<Racun> racuni;
}
