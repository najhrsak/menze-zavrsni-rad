package hr.tvz.zr.menzastudent.service;

import hr.tvz.zr.menzastudent.config.JwtService;
import hr.tvz.zr.menzastudent.exception.ClientException;
import hr.tvz.zr.menzastudent.model.*;
import hr.tvz.zr.menzastudent.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
    //private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserAuthResponse authenticate(UserAuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow(() -> new ClientException("Invalid username or password"));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        /*UserAuthResponse userAuthResponse = new UserAuthResponse();
        userAuthResponse.setAccessToken(jwtToken);
        userAuthResponse.setRefreshToken(refreshToken);*/
        return UserAuthResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public User signUp(SignUpRequest signUpRequest) {
        Optional<User> userExist = userRepository.findUserByUsername(signUpRequest.getUsername());

        if(userExist.isPresent()){
            throw new ClientException("User  Already exist");
        }
        User user = new User();

        user.setUsername(signUpRequest.getUsername());
        user.setRole(Role.ROLE_STUDENT);
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String username = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findUserByUsername(username).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwt = jwtService.generateToken(user);

            UserAuthResponse userAuthResponse = new UserAuthResponse();

            userAuthResponse.setAccessToken(jwt);
            userAuthResponse.setRefreshToken(refreshTokenRequest.getToken());

            return  userAuthResponse;

        }
        System.out.println("UGABUGA");
        return null;
    }

    @Override
    public Optional<UniqueUsernameResponse> validateUsername(ValidateUsernameBody body) {
        Optional<User> existingUser = userRepository.findUserByUsername(body.getUsername());
        return existingUser.map(user -> new UniqueUsernameResponse(false,
                new UserDTO(user.getUsername(), user.getRole().toString())));
    }
}
