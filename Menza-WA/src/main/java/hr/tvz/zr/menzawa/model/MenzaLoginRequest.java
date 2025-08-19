package hr.tvz.zr.menzawa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class    MenzaLoginRequest {
    String username, password;
}
