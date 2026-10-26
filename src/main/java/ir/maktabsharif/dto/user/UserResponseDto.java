package ir.maktabsharif.dto.user;

import ir.maktabsharif.dao.enums.Rols;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class UserResponseDto {

    private Integer id;
    private String name;
    private String email;
    private Rols rols;
}
