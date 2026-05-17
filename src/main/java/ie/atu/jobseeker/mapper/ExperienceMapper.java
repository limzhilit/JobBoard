package ie.atu.jobseeker.mapper;

import ie.atu.jobseeker.dto.ExperienceResponseDTO;
import ie.atu.jobseeker.model.Experience;

public class ExperienceMapper {

  public static ExperienceResponseDTO toDTO(Experience exp) {
    ExperienceResponseDTO dto = new ExperienceResponseDTO();
    dto.setId(exp.getId());
    dto.setOrganisation(exp.getOrganisation());

    dto.setPositions(
        exp.getPositions().stream()
            .map(PositionMapper::toDTO)
            .toList()
    );

    return dto;
  }


}
