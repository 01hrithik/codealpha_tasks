import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    ArrayList<Integer> grades;

    Student(String name) {
        this.name = name;
        grades = new ArrayList<>();
    }

    void addGrade(int grade) {
        grades.add(grade);
    }

    double getAverage() {
        int total = 0;
        for (int grade : grades) {
            total += grade;
        }
        return grades.size() > 0 ? (double) total / grades.size() : 0.0;
    }

    int getHighest() {
        int max = Integer.MIN_VALUE;
        for (int grade : grades) {
            if (grade > max) max = grade;
        }
        return max;
    }

    int getLowest() {
        int min = Integer.MAX_VALUE;
        for (int grade : grades) {
            if (grade < min) min = grade;
        }
        return min;
    }

    void displayReport() {
        System.out.println("Name: " + name);
        System.out.println("Grades: " + grades);
        System.out.println("Average: " + getAverage());
        System.out.println("Highest: " + getHighest());
        System.out.println("Lowest: " + getLowest());
        System.out.println("------------------------------");
    }
}

