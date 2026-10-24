package ir.maktabsharif.dao;

import ir.maktabsharif.dao.BaseModel.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "projects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProjectDao extends BaseModel<Integer> {

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false)
    private String description;
}
