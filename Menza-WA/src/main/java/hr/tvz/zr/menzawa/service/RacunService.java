package hr.tvz.zr.menzawa.service;

import hr.tvz.zr.menzawa.model.Blagajna;
import hr.tvz.zr.menzawa.model.Jelo;
import hr.tvz.zr.menzawa.model.Racun;
import hr.tvz.zr.menzawa.model.RacunDTO;
import org.hibernate.validator.constraints.ru.INN;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RacunService {
    Optional<Racun> getRacunByID(UUID uuid);
    Optional<Racun> newRacun(Blagajna menza);

    Optional<RacunDTO> addToRacun(UUID uuidRacuna, UUID uuidJela, Integer kolicina);

    List<Jelo> getJel();

    Optional<Racun> removeFromRacun(UUID uuidRacuna, UUID uuidJela);
}
