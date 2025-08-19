package hr.tvz.zr.menzawa.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenzaDTO {
    @NotBlank(message = "must not be null")
    @Pattern(
            regexp = "^[\\p{L}0-9-, ]+$",
            message = "only letters, numbers and hyphen allowed"
    )
    private String naziv;
    @NotBlank(message = "must not be null")
    @Pattern(
            regexp = "^[\\p{L}0-9-, ]+$",
            message = "only letters, numbers and hyphen allowed"
    )
    private String adresa;
    @NotBlank(message = "must not be null")
    @Pattern(
            regexp = "^[\\p{L}0-9-,.:;?!()%#/ ]+$",
            message = "only letters, numbers and hyphen allowed"
    )
    private String info;
    @NotBlank(message = "must not be null")
    @Pattern(
            regexp = "^[\\p{L}0-9-: ]+$",
            message = "only letters, numbers and hyphen allowed"
    )
    private String radnoVrijeme;
    @NotBlank(message = "must not be null")
    @Pattern(
            regexp = "^[\\p{L}0-9€ ,-]+$",
            message = "only letters, numbers and hyphen allowed"
    )
    private String meni;

}
