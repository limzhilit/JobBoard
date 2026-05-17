package ie.atu.jobseeker.dto;

import lombok.Data;

import java.util.List;

@Data
public class PositionDTO {

  private String title;

  private Integer yearStart;

  private Integer yearEnd;

  private String description;

  private List<ProjectDTO> projects;
}
