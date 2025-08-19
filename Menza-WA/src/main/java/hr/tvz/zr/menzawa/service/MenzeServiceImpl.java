package hr.tvz.zr.menzawa.service;

import hr.tvz.zr.menzawa.model.Menza;
import hr.tvz.zr.menzawa.model.MenzaDTO;
import hr.tvz.zr.menzawa.model.MenzaLoginRequest;
import hr.tvz.zr.menzawa.repository.MenzeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MenzeServiceImpl implements MenzeService{

    MenzeRepository menzeRepository;
    @Override
    public List<Menza> getNaziviMenza() {
        return menzeRepository.findAll();
    }

    @Override
    public Optional<Menza> getMenzaByNaziv(String naziv) {
        return menzeRepository.findByNaziv(naziv);
    }

    @Override
    public Optional<Menza> getMenzaByUUID(UUID uuid) {
        return menzeRepository.findById(uuid);
    }


    @Override
    public Optional<Menza> update(UUID uuid, MenzaDTO menza) {
        Menza newMenza = new Menza();
        newMenza.setId(uuid);
        newMenza.setNaziv(menza.getNaziv());
        newMenza.setAdresa(menza.getAdresa());
        newMenza.setInfo(menza.getInfo());
        newMenza.setRadnoVrijeme(menza.getRadnoVrijeme());
        newMenza.setMeni(menza.getMeni());
        newMenza.setGuzva(0);
        return Optional.of(menzeRepository.saveAndFlush(newMenza));
    }

    @Override
    public Optional<Menza> prijaviGuzvu(UUID uuid) {
        Optional<Menza> menza = menzeRepository.findById(uuid);
        if(menza.isPresent()) {
            menza.get().setGuzva(1);
            return Optional.of(menzeRepository.saveAndFlush(menza.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Menza> odjaviGuzvu(UUID uuid) {
        Optional<Menza> menza = menzeRepository.findById(uuid);
        if(menza.isPresent()) {
            menza.get().setGuzva(0);
            return Optional.of(menzeRepository.saveAndFlush(menza.get()));
        }
        return Optional.empty();
    }


}
