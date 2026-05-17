package ie.atu.jobseeker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "job-search")
@ToString
public class JobSearch {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long userId;

  private String location;

  private List<String> keywords;

  @Enumerated(EnumType.STRING)
  private JobType jobType;

  private Integer minSalary;

  private LocalDateTime searchTime;

  public enum JobType {
    FULL_TIME,
    PART_TIME,
    INTERNSHIP,
    CONTRACT,
    TEMPORARY,
    FREELANCE
  }

  private Gender gender;

  private Integer minAge;
  private Integer maxAge;

  private String ethnicity;





}
