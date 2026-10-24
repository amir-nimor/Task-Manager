package ir.maktabsharif.dao;

import ir.maktabsharif.dao.BaseModel.BaseModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TaskDao extends BaseModel<Long> {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false,name = "Minutes")
    private Integer estimatedMinutes;

    @ManyToOne
    private UserDao userDao;

    @ManyToOne
    private ProjectDao projectDao;

}
