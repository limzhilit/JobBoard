package ie.atu.jobboard.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class Project {
  @NotBlank(message = "User name is required")
  String name;
  String description;
  @Positive(message = "Year must be greater than 0")
  int yearStart;
  @Positive(message = "Year must be greater than 0")
  int yearEnd;
  String status;
  List<String> skills;
  String location;
  int managedHeadCount;
  int projectValue;
}
