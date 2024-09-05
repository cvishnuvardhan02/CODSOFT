import java.util.ArrayList;
import java.util.Scanner;

// Class representing a Course
class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;
    int registeredStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = 0;
    }

    // Display course details
    public void displayCourse() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Schedule: " + schedule);
        System.out.println("Capacity: " + capacity);
        System.out.println("Available Slots: " + (capacity - registeredStudents));
        System.out.println();
    }

    // Check if course has available slots
    public boolean hasAvailableSlots() {
        return registeredStudents < capacity;
    }

    // Register a student
    public void registerStudent() {
        if (hasAvailableSlots()) {
            registeredStudents++;
        } else {
            System.out.println("No available slots in " + title);
        }
    }

    // Drop a student from course
    public void dropStudent() {
        if (registeredStudents > 0) {
            registeredStudents--;
        } else {
            System.out.println("No students to drop from " + title);
        }
    }
}

// Class representing a Student
class Student {
    String studentId;
    String name;
    ArrayList<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    // Register a course
    public void registerCourse(Course course) {
        if (course.hasAvailableSlots()) {
            registeredCourses.add(course);
            course.registerStudent();
            System.out.println(name + " registered for " + course.title);
        } else {
            System.out.println("No available slots in " + course.title);
        }
    }

    // Drop a course
    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.dropStudent();
            System.out.println(name + " dropped " + course.title);
        } else {
            System.out.println(name + " is not registered for " + course.title);
        }
    }

    // Display registered courses
    public void displayRegisteredCourses() {
        System.out.println(name + "'s Registered Courses:");
        for (Course course : registeredCourses) {
            course.displayCourse();
        }
    }
}

// Main class for handling course registration system
public class CourseRegistrationSystem {
    ArrayList<Course> courseList;
    ArrayList<Student> studentList;
    Scanner scanner;

    public CourseRegistrationSystem() {
        courseList = new ArrayList<>();
        studentList = new ArrayList<>();
        scanner = new Scanner(System.in);
        initCourses();
    }

    // Initialize with some sample courses
    public void initCourses() {
        courseList.add(
                new Course("CS101", "Introduction to Programming", "Basics of programming", 30, "Mon-Wed 10:00-11:30"));
        courseList.add(
                new Course("CS102", "Data Structures", "Introduction to data structures", 25, "Tue-Thu 12:00-13:30"));
        courseList.add(new Course("CS103", "Database Systems", "Introduction to databases", 20, "Wed-Fri 14:00-15:30"));
    }

    // Display available courses
    public void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courseList) {
            course.displayCourse();
        }
    }

    // Register a student
    public void registerStudent() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        Student student = new Student(studentId, name);
        studentList.add(student);

        boolean registering = true;
        while (registering) {
            System.out.println(
                    "1. View Available Courses\n2. Register for a Course\n3. Drop a Course\n4. View Registered Courses\n5. Exit");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    displayCourses();
                    break;
                case 2:
                    System.out.print("Enter Course Code to Register: ");
                    String courseCode = scanner.nextLine();
                    Course courseToRegister = findCourseByCode(courseCode);
                    if (courseToRegister != null) {
                        student.registerCourse(courseToRegister);
                    } else {
                        System.out.println("Course not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter Course Code to Drop: ");
                    String courseCodeToDrop = scanner.nextLine();
                    Course courseToDrop = findCourseByCode(courseCodeToDrop);
                    if (courseToDrop != null) {
                        student.dropCourse(courseToDrop);
                    } else {
                        System.out.println("Course not found!");
                    }
                    break;
                case 4:
                    student.displayRegisteredCourses();
                    break;
                case 5:
                    registering = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // Find a course by its code
    public Course findCourseByCode(String courseCode) {
        for (Course course : courseList) {
            if (course.courseCode.equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }

    // Main method
    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        system.registerStudent();
    }
}
