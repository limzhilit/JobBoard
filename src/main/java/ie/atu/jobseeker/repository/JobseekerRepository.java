package ie.atu.jobseeker.repository;

import ie.atu.jobseeker.model.Jobseeker;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JobseekerRepository extends JpaRepository<Jobseeker, Long>, JpaSpecificationExecutor<Jobseeker> {
  Optional<Jobseeker> findByUserId(Long userId);

  @EntityGraph(attributePaths = "experiences")
  Optional<Jobseeker> findWithExperiencesByUserId(Long userId);
}
