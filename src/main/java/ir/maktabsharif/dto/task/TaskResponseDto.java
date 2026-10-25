package ir.maktabsharif.dto.task;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class TaskResponseDto {

    private Long id;

    private String title;

    private Integer estimatedMinutes;

    private Integer userId;

    private Integer projectId;
}
