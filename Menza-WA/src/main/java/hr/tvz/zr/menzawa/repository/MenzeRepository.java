package hr.tvz.zr.menzawa.repository;

import hr.tvz.zr.menzawa.model.Menza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenzeRepository extends JpaRepository<Menza, UUID> {
    Optional<Menza> findByNaziv(String naziv);

}
