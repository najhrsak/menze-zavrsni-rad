package hr.tvz.zr.menzastudent.repository;

import hr.tvz.zr.menzastudent.model.Blagajna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BlagajnaRepository extends JpaRepository<Blagajna, UUID> {
    Optional<Blagajna> findByUsernameAndPassword(String username, String password);

    Optional<Blagajna> findBlagajnaByUsername(String username);
}
