package hr.tvz.zr.menzastudent.service;

import hr.tvz.zr.menzastudent.model.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {
    UserDetailsService userDetailsService();
    Optional<UserDTO> getUser(Long id);

   /* UserAuthResponse authenticate(UserAuthRequest userAuthRequest);

    User signUp(SignUpRequest signUpRequest);

    UserAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);*/
}
