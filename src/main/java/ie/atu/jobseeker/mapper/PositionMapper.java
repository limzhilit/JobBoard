package ie.atu.jobseeker.mapper;

import ie.atu.jobseeker.dto.PositionResponseDTO;
import ie.atu.jobseeker.model.Position;

public class PositionMapper {
  public static PositionResponseDTO toDTO(Position pos) {
    PositionResponseDTO dto = new PositionResponseDTO();
    dto.setId(pos.getId());
    dto.setTitle(pos.getTitle());
    dto.setYearStart(pos.getYearStart());
    dto.setYearEnd(pos.getYearEnd());
    dto.setDescription(pos.getDescription());

    dto.setProjects(
        pos.getProjects().stream()
            .map(ProjectMapper::toDTO)
            .toList()
    );

    return dto;
  }
}
