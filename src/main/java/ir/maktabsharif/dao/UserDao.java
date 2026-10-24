package ir.maktabsharif.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDao extends BaseModel<Integer>{
    private String name;
    private String email;
    private Rols rols;

}
