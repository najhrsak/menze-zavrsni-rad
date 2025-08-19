package hr.tvz.zr.menzawa.service;

import hr.tvz.zr.menzawa.model.*;
import hr.tvz.zr.menzawa.repository.BlagajnaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MenzaLoginServiceImpl implements MenzaLoginService{
    @Autowired
    private BlagajnaRepository blagajnaRepository;

    private static UUID uuid;

    @Override
    public Optional<Blagajna> login(MenzaLoginRequest menzaLoginRequest) {
        Optional<Blagajna> blagajna = blagajnaRepository.findByUsernameAndPassword(menzaLoginRequest.getUsername(),
                menzaLoginRequest.getPassword());
        blagajna.ifPresent(value -> uuid = value.getId());
        return blagajna;
    }

    //pri loginu se sprema ID prijavljene blagajne u 'uuid' pa se u ovoj metodi dohvaca blagajna s tim ID-jem
    @Override
    public Optional<Blagajna> currentlyLoggedIn() {
        return blagajnaRepository.findById(uuid);
    }

}
