package ir.maktabsharif.dto.project;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProjectRequestDto {

    private String name;
    private String description;
}
