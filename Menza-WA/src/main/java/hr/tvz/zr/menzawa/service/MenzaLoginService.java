package hr.tvz.zr.menzawa.service;

import hr.tvz.zr.menzawa.model.*;

import java.util.Optional;

public interface MenzaLoginService {
    Optional<Blagajna> login(MenzaLoginRequest menzaLoginRequest);
    Optional<Blagajna> currentlyLoggedIn();
}
