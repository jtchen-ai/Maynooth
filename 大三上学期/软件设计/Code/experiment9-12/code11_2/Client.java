package experiment11_2;

public class Client {
    public static void main(String[] args) {
        OrganizationComponent university = new University("--Fuzhou University--", "211");

        OrganizationComponent college = new College("-School of computer and bigdata-", "Computer related major");
        OrganizationComponent college2 = new College("-College of Arts-", "Literature and history major");

        college.add(new Department("computer technology","Specialized master"));
        college.add(new Department("Computer science and technology","Academic master"));

        college2.add(new Department("Chinese literature","traditional culture"));
        college2.add(new Department("Foreign literature","Foreign culture"));

        university.add(college);
        university.add(college2);

        university.print();


    }
}
