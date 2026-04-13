package ie.atu.jobseeker.controller;

import ie.atu.jobseeker.dto.ExperienceUpdateDTO;
import ie.atu.jobseeker.model.Experience;
import ie.atu.jobseeker.services.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/jobseeker/experience")
@RequiredArgsConstructor
public class ExperienceController {

  private final ExperienceService experienceService;

  @GetMapping
  public ResponseEntity<?> getAll(@RequestHeader("Authorization") String token) {
    return ResponseEntity.ok(experienceService.getAll(token));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getAllById(@PathVariable Long id) {
    return ResponseEntity.ok(experienceService.getAllById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> upsert(
      @PathVariable Long id,
      @RequestBody ExperienceUpdateDTO experience
  ) {
    return ResponseEntity.ok(experienceService.upsert(id, experience));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete( @PathVariable Long id) {
    experienceService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/bulk")
  public ResponseEntity<?> bulkSave(@RequestBody List<Experience> experiences) {
    return ResponseEntity.ok(experienceService.saveAll( experiences));
  }
}