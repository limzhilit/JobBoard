package ie.atu.jobseeker.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ExperienceUpdateDTO {
  private Long id;
  private String organisation;
  private List<PositionDTO> positions;
}