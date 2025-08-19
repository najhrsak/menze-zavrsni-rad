package hr.tvz.zr.menzawa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RacunDTO {
    private UUID uuid;
    private String menza;
    private List<JeloDTO> jela;
    private String suma;
    private String blagajna;
    private LocalDateTime vrijemeIzdavanja;
}