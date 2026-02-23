package ie.atu.jobboard.model;
//comment to commit
import java.util.ArrayList;
import java.util.Scanner;

public class EmployerApp {

    static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        ArrayList<Employer> employers = new ArrayList<>();

        Employer e = new Employer();

        System.out.print("Enter Employer ID: ");
        e.setEmployerId(scan.nextInt());
        scan.nextLine(); // clear buffer

        System.out.print("Enter Company Name: ");
        e.setCompanyName(scan.nextLine());

        System.out.print("Enter Industry: ");
        e.setIndustry(scan.nextLine());

        System.out.print("Enter Location: ");
        e.setLocation(scan.nextLine());

        System.out.print("Enter Email: ");
        e.setContactEmail(scan.nextLine());

        System.out.print("Enter Phone: ");
        e.setContactPhone(scan.nextLine());

        employers.add(e);

        System.out.println("\nAll Employers:");
        for (Employer emp : employers) {
            System.out.println(emp.getEmployerId() + " | " +
                    emp.getCompanyName() + " | " +
                    emp.getIndustry());
        }

        scan.close();
    }
}
