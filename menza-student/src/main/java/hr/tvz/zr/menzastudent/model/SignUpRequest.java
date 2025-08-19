package hr.tvz.zr.menzastudent.model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignUpRequest {
    /*@Size(min = 1, max = 100,
            message = "must be at least 1 character long and max 200 characters")
    @Pattern(
            regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "invalid email"
    )
    private String email;*/
    @Size(min = 1, max = 100,
            message = "must be at least 1 character long and max 200 characters")
    @Pattern(
            regexp = "^[A-Za-z.]*$",
            message = "only letters and dot allowed"
    )
    private String username;

    @NotBlank(message = "must not be null")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
            message = " minimum of 8 and maximum 20 of char,at least 1 lowercase, uppercase and special char "
    )
    private String password;

    /*@NotBlank(message = "must not be null")
    @Pattern(
            regexp = "^[\\w\\-\\s]+$",
            message = "only letters, numbers and hyphen allowed"
    )
    private String name;*/



}
