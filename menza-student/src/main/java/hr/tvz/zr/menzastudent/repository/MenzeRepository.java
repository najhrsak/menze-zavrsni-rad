package hr.tvz.zr.menzastudent.repository;

import hr.tvz.zr.menzastudent.model.Menza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MenzeRepository extends JpaRepository<Menza, UUID> {
    Optional<Menza> findByNaziv(String naziv);

}
