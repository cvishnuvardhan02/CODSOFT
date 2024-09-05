import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    static String[] questions = {
            "1. What is the capital of France?",
            "2. What is the largest planet in our solar system?",
            "3. Which language is used to develop Android apps?",
            "4. Who invented Java programming language?",
            "5. What is the result of 9 + 10?"
    };

    static String[][] options = {
            { "a) Berlin", "b) Madrid", "c) Paris", "d) Rome" },
            { "a) Earth", "b) Jupiter", "c) Mars", "d) Saturn" },
            { "a) Swift", "b) Kotlin", "c) Python", "d) JavaScript" },
            { "a) James Gosling", "b) Dennis Ritchie", "c) Bjarne Stroustrup", "d) Guido van Rossum" },
            { "a) 19", "b) 21", "c) 20", "d) 18" }
    };

    static char[] correctAnswers = { 'c', 'b', 'b', 'a', 'c' };
    static char[] userAnswers = new char[questions.length];
    static int score = 0;
    static int timeLimit = 10; // seconds per question

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Quiz!");
        for (int i = 0; i < questions.length; i++) {
            displayQuestion(i);
            boolean answeredInTime = getUserAnswerWithinTime(sc, i);

            if (!answeredInTime) {
                System.out.println("\nTime's up for this question!");
                userAnswers[i] = 'X'; // Mark as unanswered
            } else {
                if (userAnswers[i] == correctAnswers[i]) {
                    score++;
                }
            }
            System.out.println(); // Line break between questions
        }

        // Display result
        displayResult();
        sc.close();
    }

    public static void displayQuestion(int questionIndex) {
        System.out.println(questions[questionIndex]);
        for (String option : options[questionIndex]) {
            System.out.println(option);
        }
    }

    public static boolean getUserAnswerWithinTime(Scanner sc, int questionIndex) {
        final boolean[] answered = { false };
        Timer timer = new Timer();

        // Schedule a task to be executed after timeLimit seconds
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!answered[0]) {
                    System.out.println("\nTime's up!");
                }
                timer.cancel();
            }
        };
        timer.schedule(task, timeLimit * 1000);

        // Get user input within time
        while (!answered[0]) {
            System.out.print("Your answer (a/b/c/d): ");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.length() == 1 && "abcd".contains(input)) {
                userAnswers[questionIndex] = input.charAt(0);
                answered[0] = true;
                timer.cancel();
                return true;
            } else {
                System.out.println("Invalid input! Please enter a valid option (a, b, c, d).");
            }
        }

        return false;
    }

    public static void displayResult() {
        System.out.println("\nQuiz Over!");
        System.out.println("Final Score: " + score + "/" + questions.length);

        System.out.println("\nSummary:");
        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.println("Your answer: " + (userAnswers[i] == 'X' ? "Unanswered" : userAnswers[i]));
            System.out.println("Correct answer: " + correctAnswers[i] + "\n");
        }
    }
}
