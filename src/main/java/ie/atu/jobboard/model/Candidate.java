package ie.atu.jobboard.model;

import java.util.List;

public class Candidate {
  private long id;
  private String name;
  private String age;
  private String email;
  private String phone;
  private String password;
  private List<Experience> experiences;

  public Candidate(long id, String name, String age, String email, String phone, String password, List<Experience> experiences) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.email = email;
    this.phone = phone;
    this.password = password;
    this.experiences = experiences;
  }

}