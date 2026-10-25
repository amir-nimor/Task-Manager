package ir.maktabsharif.dto.task;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class TaskRequestDto {

    private Long id;
    private String title;


    private Integer userId;

    private Integer projectId;
    private Integer estimatedMinutes;

}
