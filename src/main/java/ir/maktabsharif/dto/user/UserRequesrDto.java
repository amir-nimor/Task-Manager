package ir.maktabsharif.dto.user;

import ir.maktabsharif.dao.enums.Rols;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequesrDto {

    private String name;
    private String email;
    private Rols rols;

}
