package hr.tvz.zr.menzawa.repository;

import hr.tvz.zr.menzawa.model.Jelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JelaRepository extends JpaRepository<Jelo, UUID> {

}
