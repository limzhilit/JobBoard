package ie.atu.jobboard.classes;
//comment to commit
public class Employer {

    private int employerId;
    private String companyName;
    private String industry;
    private String location;
    private String contactEmail;
    private String contactPhone;

    public int getEmployerId() { return employerId; }
    public void setEmployerId(int employerId){ this.employerId = employerId; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
}
