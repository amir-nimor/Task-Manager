package ir.maktabsharif.dto.user;

import ir.maktabsharif.dao.enums.Rols;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequestDto {

    private Integer id;
    private String name;
    private String email;
    private Rols rols;

}
