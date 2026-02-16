package ie.atu.jobboard.classes;

public class Candidate {
  private String name;
  private String email;
  private String phone;
  private String experience;
  private String skills;
  private String location;
  private String age;

  public Candidate(String name, String email, String phone, String experience, String skills, String location, String age) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.experience = experience;
    this.skills = skills;
    this.location = location;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getExperience() {
    return experience;
  }

  public void setExperience(String experience) {
    this.experience = experience;
  }

  public String getSkills() {
    return skills;
  }

  public void setSkills(String skills) {
    this.skills = skills;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Candidate{" +
        "name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        ", experience='" + experience + '\'' +
        ", skills='" + skills + '\'' +
        ", location='" + location + '\'' +
        ", age='" + age + '\'' +
        '}';
  }

}
