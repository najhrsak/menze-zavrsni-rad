package hr.tvz.zr.menzawa.service;

import hr.tvz.zr.menzawa.model.Menza;
import hr.tvz.zr.menzawa.model.MenzaDTO;
import hr.tvz.zr.menzawa.model.MenzaLoginRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenzeService {
    List<Menza> getNaziviMenza();
    Optional<Menza> getMenzaByNaziv(String naziv);
    Optional<Menza> getMenzaByUUID(UUID uuid);

    Optional<Menza> update(UUID uuid, MenzaDTO menza);
    Optional<Menza> prijaviGuzvu(UUID uuid);
    Optional<Menza> odjaviGuzvu(UUID uuid);


}
