package ie.atu.jobseeker.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExperienceResponseDTO {
  private Long id;
  private String organisation;
  private List<PositionResponseDTO> positions;
}
