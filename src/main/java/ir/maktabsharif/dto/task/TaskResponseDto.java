package ir.maktabsharif.dto.task;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TaskResponseDto {

    private Long id;

    private String title;

    private Integer estimatedMinutes;

    private Integer userId;

    private Integer projectId;
}
