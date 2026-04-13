package ie.atu.jobseeker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class JobseekerScore {
  private Jobseeker jobseeker;
  private int score;
}