package ie.atu.jobseeker.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjectDTO {

  private String name;

  private String location;

  private String description;

  private List<String> skills;

  private Integer managedHeadCount;

  private Integer projectValue;

}
