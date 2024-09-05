import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        boolean playAgain = true;
        int totalScore = 0;

        System.out.println("Welcome to Guess the Number Game!");

        while (playAgain) {
            int attempts = 0;
            int maxAttempts = 7; // You can modify this limit
            int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
            boolean guessedCorrectly = false;

            System.out.println("\nA random number between 1 and 100 has been generated.");
            System.out.println("You have " + maxAttempts + " attempts to guess the number.");

            // Guessing loop
            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the number correctly.");
                    guessedCorrectly = true;
                    totalScore += (maxAttempts - attempts + 1); // Score based on remaining attempts
                } else if (userGuess < numberToGuess) {
                    System.out.println("Your guess is too low.");
                } else {
                    System.out.println("Your guess is too high.");
                }

                if (!guessedCorrectly && attempts == maxAttempts) {
                    System.out.println("You've run out of attempts! The correct number was: " + numberToGuess);
                }
            }

            // Prompt for another round
            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = scanner.next();
            if (response.equalsIgnoreCase("no")) {
                playAgain = false;
            }
        }

        // Display final score
        System.out.println("\nThank you for playing! Your total score is: " + totalScore);
        scanner.close();
    }
}
