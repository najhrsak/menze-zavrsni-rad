package hr.tvz.zr.menzawa.repository;

import hr.tvz.zr.menzawa.model.Racun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RacunRepository extends JpaRepository<Racun, UUID> {
    Optional<Racun> findByUuid(UUID uuid);
}
