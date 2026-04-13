package ie.atu.jobseeker.services;

import ie.atu.jobseeker.model.*;
import ie.atu.jobseeker.repository.ExperienceRepository;
import ie.atu.jobseeker.repository.JobseekerRepository;
import ie.atu.jobseeker.util.TextUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobseekerRankingService {
  private final JobseekerRepository jobseekerRepo;
  private final ExperienceRepository experienceRepository;

  @Transactional
  public void rebuildSearchableText(Long userId) {

    Jobseeker jobseeker = jobseekerRepo.findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("Jobseeker not found"));

    List<Experience> experiences = experienceRepository.findByUserId(userId);

    String text = buildSearchableText(jobseeker, experiences);

    jobseeker.setSearchableText(text);

    System.out.println(jobseeker.getSearchableText());

    jobseekerRepo.save(jobseeker);
  }

  public String buildSearchableText(Jobseeker js, List<Experience> experiences) {
    StringBuilder sb = new StringBuilder();

    for (Experience exp : experiences) {
      for (Position pos : exp.getPositions()) {
        sb.append(pos.getTitle()).append(" ");
        sb.append(pos.getDescription()).append(" ");

        for (Project project : pos.getProjects()) {
          sb.append(project.getName()).append(" ");
          sb.append(project.getDescription()).append(" ");

          for (String skill : project.getSkills()) {
            sb.append(skill).append(" ");
          }
        }
      }
    }

    return TextUtils.normalize(sb.toString());
  }

  public List<JobseekerScore> rank(List<Jobseeker> jobseekers, List<String> keywords) {
    List<JobseekerScore> results = new ArrayList<>();

    for (Jobseeker js : jobseekers) {
      int score = calculateScore(js, keywords);
      results.add(new JobseekerScore(js, score));
    }

    // Sort descending by score
    results.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));

    return results;
  }

  private int calculateScore(Jobseeker js, List<String> keywords) {
    int score = 0;

    String text = js.getSearchableText();

    for (String keyword : keywords) {
      if (text.contains(keyword.toLowerCase())) {
        score++;
      }
    }

    return score;
  }


}

