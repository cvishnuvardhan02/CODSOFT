import java.util.Scanner;

public class StudentGrade {
    public static void main(String[] args) {
        // Create a Scanner object to take input from the user
        Scanner scanner = new Scanner(System.in);

        // Declare variables for marks
        int numSubjects;
        double totalMarks = 0;

        // Input number of subjects
        System.out.print("Enter the number of subjects: ");
        numSubjects = scanner.nextInt();

        // Array to store marks of each subject
        double[] marks = new double[numSubjects];

        // Take input for marks of each subject
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + (i + 1) + " out of 100: ");
            marks[i] = scanner.nextDouble();
            totalMarks += marks[i];
        }

        // Calculate total marks, percentage, and grade
        double percentage = totalMarks / numSubjects;
        char grade = calculateGrade(percentage);

        // Display the results
        System.out.println("\nTotal Marks Obtained: " + totalMarks);
        System.out.println("Overall Percentage: " + percentage + "%");
        System.out.println("Grade: " + grade);

        // Close the scanner object
        scanner.close();
    }

    // Method to calculate grade based on percentage
    public static char calculateGrade(double percentage) {
        if (percentage >= 90) {
            return 'A';
        } else if (percentage >= 80) {
            return 'B';
        } else if (percentage >= 70) {
            return 'C';
        } else if (percentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}
