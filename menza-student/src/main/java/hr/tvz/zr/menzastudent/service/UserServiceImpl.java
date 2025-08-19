package hr.tvz.zr.menzastudent.service;

import hr.tvz.zr.menzastudent.exception.ClientException;
import hr.tvz.zr.menzastudent.model.*;
import hr.tvz.zr.menzastudent.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    //private final TokenRepository tokenRepository;

    @Autowired
    private final UserRepository userRepository;


    @Override
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findUserByUsername(username).orElseThrow(() -> new ClientException("User not found"));
    }
    @Override
    public Optional<UserDTO> getUser(Long id) {
        User user;
        if(userRepository.findById(id).isPresent())
        {
            user = userRepository.findById(id).get();
            return Optional.of(mapUserToDTO(user));
        }
        return Optional.empty();
    }

    private UserDTO mapUserToDTO(User user){
        return new UserDTO(user.getUsername(), user.getAuthorities().stream().findAny().get().toString());
    }



    /*private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }*/
}
