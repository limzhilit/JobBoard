package ie.atu.jobseeker.services;

import ie.atu.jobseeker.exception.JobseekerNotFoundException;
import ie.atu.jobseeker.model.JobSearch;
import ie.atu.jobseeker.model.Jobseeker;
import ie.atu.jobseeker.model.JobseekerScore;
import ie.atu.jobseeker.repository.JobseekerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class JobseekerService extends BaseService {

  private final JobseekerRepository jobseekerRepository;
  private final JobseekerRankingService jobseekerRankingService;

  public Jobseeker upsertJobseeker(String token, Jobseeker incoming) {
    Long userId = getCurrentUserId();
    incoming.setUserId(userId);

    return jobseekerRepository.findByUserId(userId)
        .map(existing -> {
          existing.setName(incoming.getName());
          existing.setGender(incoming.getGender());
          existing.setEthnicity(incoming.getEthnicity());
          existing.setLocation(incoming.getLocation());
          existing.setDob(incoming.getDob());
          existing.getPhone().setCountryCode(incoming.getPhone().getCountryCode());
          existing.getPhone().setNumber(incoming.getPhone().getNumber());
          existing.setLinks(incoming.getLinks());
          return jobseekerRepository.save(existing); // UPDATE
        })
        .orElseGet(() -> jobseekerRepository.save(incoming)); // INSERT
  }

  public Jobseeker getJobseeker(String token) {
    Long userId = getCurrentUserId();
    return jobseekerRepository.findByUserId(userId)
        .orElseThrow(() -> new JobseekerNotFoundException("Jobseeker profile not found for user: " + userId));
  }

  public Jobseeker getJobseekerById(Long id) {
    return jobseekerRepository.findByUserId(id)
        .orElseThrow(() -> new JobseekerNotFoundException("Jobseeker profile not found for user: " + id));
  }

  public ArrayList<Jobseeker> getAllJobseekers() {
    return (ArrayList<Jobseeker>) jobseekerRepository.findAll();
  }

  public Optional<List<JobseekerScore>> searchJobseekers(JobSearch criteria) {

    System.out.println("Searching for jobseekers with criteria: " + criteria);

    Specification<Jobseeker> specification = (root, query, cb) -> {

      var predicates = cb.conjunction();

      // location
      if (criteria.getLocation() != null && !criteria.getLocation().isEmpty()) {
        predicates = cb.and(predicates,
            cb.like(cb.lower(root.get("location")),
                "%" + criteria.getLocation().toLowerCase() + "%"));
      }

      // job type / availability (if applicable)
      if (criteria.getJobType() != null) {
        predicates = cb.and(predicates,
            cb.equal(root.get("jobType"), criteria.getJobType()));
      }

      // gender
      if (criteria.getGender() != null) {
        predicates = cb.and(predicates,
            cb.equal(root.get("gender"), criteria.getGender()));
      }

      // ethnicity
      if (criteria.getEthnicity() != null && !criteria.getEthnicity().isEmpty()) {
        predicates = cb.and(predicates,
            cb.equal(cb.lower(root.get("ethnicity")),
                criteria.getEthnicity().toLowerCase()));
      }

      // age filtering (if stored directly)
      if (criteria.getMinAge() != null) {
        predicates = cb.and(predicates,
            cb.greaterThanOrEqualTo(root.get("age"), criteria.getMinAge()));
      }

      if (criteria.getMaxAge() != null) {
        predicates = cb.and(predicates,
            cb.lessThanOrEqualTo(root.get("age"), criteria.getMaxAge()));
      }

      return predicates;
    };

    List<Jobseeker> results = jobseekerRepository.findAll(specification);

    List<String> keywords = Optional.ofNullable(criteria.getKeywords())
        .orElse(List.of());

    List<JobseekerScore> ranked = jobseekerRankingService.rank(results, keywords);

    System.out.println(ranked);
    return ranked.isEmpty() ? Optional.empty() : Optional.of(ranked);
  }

}
