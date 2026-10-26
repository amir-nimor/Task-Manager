package ir.maktabsharif.dto.project;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ProjectResponseDto {

    private Integer id;
    private String name;
    private String description;
}
