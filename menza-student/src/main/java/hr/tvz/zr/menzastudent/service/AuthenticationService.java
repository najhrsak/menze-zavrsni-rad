package hr.tvz.zr.menzastudent.service;

import hr.tvz.zr.menzastudent.model.*;

import java.util.Optional;

public interface AuthenticationService {
    UserAuthResponse authenticate(UserAuthRequest request);
    User signUp(SignUpRequest signUpRequest);
    UserAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    Optional<UniqueUsernameResponse> validateUsername(ValidateUsernameBody body);
}
