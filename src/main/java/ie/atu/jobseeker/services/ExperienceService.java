package ie.atu.jobseeker.services;

import ie.atu.jobseeker.dto.ExperienceResponseDTO;
import ie.atu.jobseeker.dto.ExperienceUpdateDTO;
import ie.atu.jobseeker.dto.PositionDTO;
import ie.atu.jobseeker.dto.ProjectDTO;
import ie.atu.jobseeker.mapper.ExperienceMapper;
import ie.atu.jobseeker.model.Position;
import ie.atu.jobseeker.model.Project;
import ie.atu.jobseeker.repository.ExperienceRepository;
import ie.atu.jobseeker.model.Experience;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceService extends BaseService{
  
  private final ExperienceRepository experienceRepository;
  private final JobseekerRankingService jobseekerRankingService;

  @Transactional
  public ExperienceResponseDTO upsert(Long experienceId, ExperienceUpdateDTO dto) {

    System.out.println("Upserting Experience with ID: " + experienceId);
    System.out.println("Upserting Experience DTO: " + dto);

    Long userId = getCurrentUserId();

    Experience existing = experienceRepository
        .findByIdAndUserId(experienceId, userId)
        .orElse(null);

    if (existing == null) {
      existing = new Experience();
      existing.setUserId(userId);
    }

    existing.setOrganisation(dto.getOrganisation());

    // IMPORTANT: clear old graph safely
    existing.getPositions().clear();

    for (PositionDTO posDto : dto.getPositions()) {

      Position position = new Position();
      position.setTitle(posDto.getTitle());
      position.setDescription(posDto.getDescription());

      for (ProjectDTO projDto : posDto.getProjects()) {

        Project project = new Project();
        project.setName(projDto.getName());
        project.setDescription(projDto.getDescription());
        project.setSkills(projDto.getSkills());

        project.setPosition(position);
        position.getProjects().add(project);
      }

      position.setExperience(existing);
      existing.getPositions().add(position);
    }

    Experience saved = experienceRepository.save(existing);

    jobseekerRankingService.rebuildSearchableText(userId);

    return ExperienceMapper.toDTO(saved);
  }


  @Transactional
  public void delete(Long id) {
    Long userId = getCurrentUserId();

    // Verify ownership before deleting
    Experience existing = experienceRepository.findByIdAndUserId(id, userId)
        .orElseThrow(() -> new RuntimeException("Experience not found or access denied"));
    jobseekerRankingService.rebuildSearchableText(userId);

    experienceRepository.delete(existing);
  }

  public List<ExperienceResponseDTO> getAll(String token) {
    Long userId = getCurrentUserId();
    return experienceRepository.findByUserId(userId)
        .stream()
        .map(ExperienceMapper::toDTO)
        .toList();
  }

  public List<ExperienceResponseDTO> getAllById(Long id) {
    return experienceRepository.findByUserId(id)
        .stream()
        .map(ExperienceMapper::toDTO)
        .toList();
  }

  @Transactional
  public List<ExperienceResponseDTO> saveAll(List<Experience> experiences) {
    Long userId = getCurrentUserId();
    experienceRepository.deleteByUserId(userId);
    experiences.forEach(exp -> {
      exp.setId(null);
      exp.setUserId(userId);
    });
    return experienceRepository.saveAll(experiences)
        .stream()
        .map(ExperienceMapper::toDTO)
        .toList();
  }
}
