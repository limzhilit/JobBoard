package ie.atu.jobseeker.mapper;

import ie.atu.jobseeker.dto.ProjectResponseDTO;
import ie.atu.jobseeker.model.Project;

public class ProjectMapper {

  public static ProjectResponseDTO toDTO(Project proj) {
    ProjectResponseDTO dto = new ProjectResponseDTO();
    dto.setId(proj.getId());
    dto.setName(proj.getName());
    dto.setLocation(proj.getLocation());
    dto.setDescription(proj.getDescription());
    dto.setSkills(proj.getSkills());
    dto.setManagedHeadCount(proj.getManagedHeadCount());
    dto.setProjectValue(proj.getProjectValue());

    return dto;
  }
}
