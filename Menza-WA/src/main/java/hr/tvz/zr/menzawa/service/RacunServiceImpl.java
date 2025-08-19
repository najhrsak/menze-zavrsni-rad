package hr.tvz.zr.menzawa.service;

import hr.tvz.zr.menzawa.model.*;
import hr.tvz.zr.menzawa.repository.JelaRepository;
import hr.tvz.zr.menzawa.repository.RacunRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RacunServiceImpl implements RacunService{
    private final RacunRepository racunRepository;
    private final JelaRepository jelaRepository;
    @Override
    public Optional<Racun> getRacunByID(UUID uuid) {
        if(racunRepository.findByUuid(uuid).isPresent())
        {
            Racun racun = racunRepository.findByUuid(uuid).get();
            racun.setSuma(0.0);
            racun.getJela().stream().forEach(jelo -> {
                racun.setSuma(racun.getSuma() + jelo.getCijena());
            });
            racun.setVrijemeIzdavanja(LocalDateTime.now());
            return Optional.of(racunRepository.saveAndFlush(racun));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Racun> newRacun(Blagajna blagajna) {
        Racun racun = new Racun(UUID.randomUUID(), blagajna.getMenza().getNaziv(), null
                ,0.0, blagajna.getNaziv(), LocalDateTime.now());
        racunRepository.save(racun);
        return Optional.of(racun);
    }

    @Override
    public Optional<RacunDTO> addToRacun(UUID uuidRacuna, UUID uuidJela, Integer kolicina) {
        if(racunRepository.findByUuid(uuidRacuna).isPresent() && jelaRepository.findById(uuidJela).isPresent()){
            Racun racun = racunRepository.findByUuid(uuidRacuna).get();
            List<Jelo> jela = racun.getJela();
            for(int i = 0; i<kolicina; i++)
                jela.add(jelaRepository.findById(uuidJela).get());
            racun.setJela(jela);
            racunRepository.saveAndFlush(racun);
            List<JeloDTO> jelaDTO = new ArrayList<>();
            for(Jelo j: racun.getJela())
                jelaDTO.add(new JeloDTO(j.getNaziv(), j.getCijena().toString()));
            RacunDTO racunDTO = new RacunDTO(racun.getUuid(), racun.getMenza(), jelaDTO, racun.getSuma().toString(),
                    racun.getBlagajna(), racun.getVrijemeIzdavanja());
            return Optional.of(racunDTO);
        }
        return Optional.empty();
    }

    @Override
    public List<Jelo> getJel() {
        return jelaRepository.findAll();
    }

    @Override
    public Optional<Racun> removeFromRacun(UUID uuidRacuna, UUID uuidJela) {
        if(racunRepository.findByUuid(uuidRacuna).isPresent() && jelaRepository.findById(uuidJela).isPresent()){
            Racun racun = racunRepository.findByUuid(uuidRacuna).get();
            List<Jelo> jela = racun.getJela();
            jela.remove(jelaRepository.findById(uuidJela).get());
            racun.setJela(jela);
            return Optional.of(racunRepository.saveAndFlush(racun));
        }
        return Optional.empty();
    }
}
