package ie.atu.jobboard.model;

import java.util.ArrayList;
import java.util.Scanner;

public class JobPostApp {

    static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        ArrayList<JobPost> jobs = new ArrayList<>();

        JobPost job = new JobPost();

        System.out.print("Enter Job ID: ");
        job.setJobId(scan.nextInt());

        System.out.print("Enter Employer ID: ");
        job.setEmployerId(scan.nextInt());
        scan.nextLine(); // clear buffer

        System.out.print("Enter Job Title: ");
        job.setTitle(scan.nextLine());

        System.out.print("Enter Description: ");
        job.setDescription(scan.nextLine());

        System.out.print("Enter Required Skills: ");
        job.setRequiredSkills(scan.nextLine());

        System.out.print("Enter Location: ");
        job.setLocation(scan.nextLine());

        System.out.print("Enter Salary Range: ");
        job.setSalaryRange(scan.nextLine());

        jobs.add(job);

        // display saved jobs
        System.out.println("\nAll Job Postings:");
        for (JobPost j : jobs) {
            System.out.println(j.getJobId() + " | " +
                    j.getTitle() + " | " +
                    j.getRequiredSkills() + " | " +
                    j.getLocation());
        }

        scan.close();
    }
}
