package ir.maktabsharif.dao;

import ir.maktabsharif.dao.BaseModel.BaseModel;
import ir.maktabsharif.dao.enums.Rols;
import jakarta.persistence.Column;
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
public class UserDao extends BaseModel<Integer> {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private Rols rols;

}
