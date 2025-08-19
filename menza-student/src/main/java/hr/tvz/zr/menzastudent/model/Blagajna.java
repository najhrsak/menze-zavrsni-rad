package hr.tvz.zr.menzastudent.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "BLAGAJNE")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Blagajna {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "korisnicko_ime")
    private String username;
    @Column(name = "lozinka")
    private String password;
    @ManyToOne
    @JoinColumn(name= "menza_id")
    @JsonBackReference
    private Menza menza;
}
