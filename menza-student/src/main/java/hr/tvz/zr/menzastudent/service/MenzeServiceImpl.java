package hr.tvz.zr.menzastudent.service;

import hr.tvz.zr.menzastudent.database.Database;
import hr.tvz.zr.menzastudent.model.Menza;
import hr.tvz.zr.menzastudent.repository.MenzeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
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
    public Optional<Double> getPosjecenostMenze(String naziv) {
        Optional<Menza> menza = getMenzaByNaziv(naziv);
        if(menza.isPresent())
        {
            try{
                int brojBlagajni = menza.get().getBlagajne().size();
                int minuta = Database.getRacuniWithinLast(1, naziv);
                int pet = Database.getRacuniWithinLast(5, naziv);
                int deset = Database.getRacuniWithinLast(10, naziv);
                int dvadeset = Database.getRacuniWithinLast(20, naziv);
                int polaSata = Database.getRacuniWithinLast(30, naziv);
                double bs = brojBlagajni*6.0;
                double pUMinuti = minuta/bs;
                double pUPet = pet/(bs*5);
                double pUDeset = deset/(bs*10);
                double pUDvadeset = dvadeset/(bs*20);
                double pUPolaSata = polaSata/(bs*30);
                return Optional.of((pUPet + pUDeset + pUDvadeset + pUMinuti + pUPolaSata)/5.0*100);
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Integer> getGuzva(String naziv) {
        Optional<Menza> menza = menzeRepository.findByNaziv(naziv);
        return menza.map(Menza::getGuzva);
    }

}
