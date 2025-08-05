import java.util.ArrayList;
import java.util.Scanner;

public class Result {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("===== Student Grade Card Manager =====");
        System.out.print("Enter number of students: ");
        int studentCount = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 0; i < studentCount; i++) {
            System.out.print("\nEnter name of student " + (i + 1) + ": ");
            String name = sc.nextLine();
            Student student = new Student(name);

            System.out.print("Enter number of subjects for " + name + ": ");
            int subjectCount = sc.nextInt();

            for (int j = 0; j < subjectCount; j++) {
                System.out.print("Enter grade for subject " + (j + 1) + ": ");
                int grade = sc.nextInt();
                student.addGrade(grade);
            }

            sc.nextLine(); // consume newline
            students.add(student);
        }

        // Display all student reports
        System.out.println("\n===== Student Summary Report =====");
        for (Student s : students) {
            s.displayReport();
        }

        sc.close();
    }
}
