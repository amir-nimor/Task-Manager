package ir.maktabsharif.dto.task;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDto {

    private Long id;
    private String title;


    private Integer userId;

    private Integer projectId;
    private Integer estimatedMinutes;

}
