package hr.tvz.zr.menzastudent.controller;

import hr.tvz.zr.menzastudent.config.JwtService;
import hr.tvz.zr.menzastudent.model.*;
import hr.tvz.zr.menzastudent.service.AuthenticationService;
import hr.tvz.zr.menzastudent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("menza-app")
public class UserController {
    private UserService userService;
    private AuthenticationService authenticationService;
    private JwtService jwtService;

    @PostMapping("login")
    public ResponseEntity<UserAuthResponse> userLogin(@RequestBody UserAuthRequest userAuthRequest){
        return new ResponseEntity<>(authenticationService.authenticate(userAuthRequest), HttpStatus.OK);
    }

    @PostMapping("signup")
    public ResponseEntity<ResponseRequest> userSignUp(@RequestBody SignUpRequest signUpRequest){
        ResponseRequest responseRequest = new ResponseRequest();
        responseRequest.setResponse(authenticationService.signUp(signUpRequest));
        return new ResponseEntity<>(responseRequest, HttpStatus.CREATED);
    }

    @PostMapping("refresh")
    public ResponseEntity<UserAuthResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return new ResponseEntity<>(authenticationService.refreshToken(refreshTokenRequest), HttpStatus.OK);
    }
    @PostMapping("validate-username")
    public ResponseEntity<UniqueUsernameResponse> validateUniqueUsername(@RequestBody ValidateUsernameBody body){
        return authenticationService.validateUsername(body)
                .map(uur -> ResponseEntity.status(HttpStatus.OK).body(uur))
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("is-token-expired")
    public Integer isTokenExpired(@RequestBody RefreshTokenRequest refreshTokenRequest){
        Boolean isIt = jwtService.isTokenExpired(refreshTokenRequest.getToken());
        if(isIt)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build().getStatusCode().value();
        else
            return ResponseEntity.status(HttpStatus.OK).build().getStatusCode().value();
    }





}
