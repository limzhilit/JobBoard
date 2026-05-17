package ie.atu.jobseeker.dto;

import lombok.Data;

import java.util.List;

@Data
public class PositionResponseDTO {
  private Long id;
  private String title;
  private Integer yearStart;
  private Integer yearEnd;
  private String description;
  private List<ProjectResponseDTO> projects;
}