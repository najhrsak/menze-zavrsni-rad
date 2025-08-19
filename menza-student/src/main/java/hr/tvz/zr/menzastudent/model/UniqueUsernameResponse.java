package hr.tvz.zr.menzastudent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniqueUsernameResponse {
    private Boolean isUnique;
    private UserDTO user;
}
