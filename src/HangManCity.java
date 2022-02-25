import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangManCity {
    public static void main(String[] args) throws FileNotFoundException {
        // Reads the txt file with all the cities & Player's input
        Scanner scanner = new Scanner(new File("/Users/alejandroguerravillegas/Desktop/MiniProject1/cities.txt"));
        Scanner entrance = new Scanner(System.in);

        // Creates an array list of all the cities on the txt file
        List<String> cities = new ArrayList<>();
        while (scanner.hasNext()) {
            cities.add(scanner.nextLine());
        }
        // pics the random city
        Random generator = new Random();
        String city = cities.get(generator.nextInt(cities.size()));
        System.out.println(city);

        // generates the dashes covering the word the player must guess
        List<Character> playerGuesses = new ArrayList<>();

        int mistakes = 0;
        // Loop to keep the player guessing all the letters
        while (true) {
            if (mistakes >= 6){
                System.out.println("You lose!!!");
                String.format("The city was: %s", city);
                break;
            }

            System.out.println("Here's the question.");
            printWordStatus(city, playerGuesses);
            System.out.println("You had guessed ("+ mistakes +")");

            // Conditional to increase the wrong guessing count
            if (!getPlayerGuess(entrance, city, playerGuesses)){
                mistakes++;
            }
            // conditional to determine if the player has won
            if (printWordStatus(city, playerGuesses)){
                System.out.println("You Win!!!");
               break;
            }
        }
    }

    // method to ask the player to enter each letter guess
    private static boolean getPlayerGuess(Scanner entrance, String city, List<Character> playerGuesses) {
        System.out.println("Guess a letter:");
        String guessLetter = entrance.nextLine();
        playerGuesses.add(guessLetter.charAt(0));
        // ** ArrayList<Scanner> guessesArrayList = new ArrayList<>();
        // ** guessesArrayList.add(entrance);
        // ** System.out.println("You had guessed this letters: %s" + guessesArrayList);
        return city.contains(guessLetter);
    }

    // method to print the dashed city & counts the guesses in order to
    // determine if the player won or lose
    private static boolean printWordStatus(String city, List<Character> playerGuesses) {
        int counter = 0;
        for (int i = 0; i < city.length(); i++) {
            if (playerGuesses.contains(city.charAt(i))) {
                System.out.print(city.charAt(i));
                counter++;
                //String.format("You have guessed ( %2d ) wrong letters",
                //        counter);
            } else {
                System.out.print("-");
            }
        }
        System.out.println();
        return (city.length() == counter);
    }

}

