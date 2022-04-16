package com.company.services;

import java.util.*;

/**
 * A simple HangMan game, made to learn about the use of Set, HashMap with List inside, Scanner and to implement some logic.
 * Public methods:
 * <ul>
 *     <li>beginGame</li>
 * </ul>
 * Private methods:
 * <ul>
 *     <li>guessLetter</li>
 *     <li>playerWon</li>
 *     <li>playerLose</li>
 *     <li>showWord</li>
 *     <li>askLetter</li>
 *     <li>actions</li>
 * </ul>
 */
public class Game {

    private final char[] wordChars;
    private final char[] displayWord;
    private int attempt = 5;
    private final Scanner input = new Scanner(System.in);
    private final Map<Character,List<Integer>> lettersMap = new HashMap<>();
    private final Set<Character> lettersTried = new HashSet<>();
    private final Set<String> outOfGame = new HashSet<>(Arrays.asList("QUIT","EXIT"));

    /**
     * Initialize the Game Object, it creates the required variables used to check the state of the game.
     * @param wordToGuess - a String containing the game solution
     */
    public Game(String wordToGuess) {
        // the word to guess is saved inside a char array
        wordChars = wordToGuess.toCharArray();
        // populating the HashMap with key:letters / value:[positions] composing the word
        for (int i = 0; i < wordChars.length; i++) {
            lettersMap.computeIfAbsent(wordChars[i], k -> new ArrayList<>());
            lettersMap.get(wordChars[i]).add(i);
        }
        System.out.println(lettersMap);
        // creating a new array of char representing the status of guessed letter, by default shows _
        displayWord = new char[wordChars.length];
        Arrays.fill(displayWord, '_');
    }

    /**
     * This method check if the player type a command to terminate the program.
     * Otherwise, it performs checks on the submitted letter (Notice: if more than 1 character is provided
     * only the first one is checked) and send back the appropriate result code.
     * @param letter a String that contain the user input
     * @return a number representing a status code useful to determine what operation to call next.
     */
    private int guessLetter(String letter) {
        // if the player type quit or exit or enter no letter the game will end
        if (letter.length() == 0 || outOfGame.contains(letter)) {
            System.out.println("Oh, you are in a hurry, I see. Goodbye then!!");
            return 2;
        }
        // getting the first character from input
        char l = letter.toCharArray()[0];

        // check if player already guessed the letter
        if (lettersTried.contains(l)) {
            return 0;
        }

        // check if the letter is inside the word to find
        if (lettersMap.containsKey(l)) {
            // change the value of displayWord with the proper letter with
            // corresponding index of wordChars when contain the letter
            for (Integer i:lettersMap.get(l)) {
                displayWord[i] = l;
            }
            lettersMap.remove(l); // the letter will be no longer inside of letter to find
            lettersTried.add(l); // the letter is added to tried letters
            return 1;
        }

        // If we get there the letter wasn't inside the word, so we remove 1 attempt and add the guessed
        // letter to list of guessed letter
        lettersTried.add(l);
        attempt--;
        return -1;
    }

    /**
     * It checks if the player won. letterSet to 0 size means that each letter have been removed.
     * @return a boolean that is <b>true</b> if the player won, <b>false</b> if the game have to continue.
     */
    private boolean playerWon() {
        return (lettersMap.size() == 0);
    }

    /**
     * It checks if the number of attempts is 0, if so the player have no more moves, and it is game over.
     * @return a boolean that is <b>true</b> if the player have no more moves available, <b>false</b> if the game
     * can continue.
     */
    private boolean playerLose() {
        return (attempt == 0);
    }

    /**
     * It just displays the current status of the word to guess.
     */
    private void showWord() {
        System.out.println(new String(displayWord));
    }

    /**
     * It prints the welcome screen and instructions.
     */
    public void beginGame() {
        System.out.println("WELCOME PLAYER");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("You will start with 5 attempts. Each attempt consist on a letter.");
        System.out.println("If the letter is inside the word we are looking for we will display it.");
        System.out.println("Otherwise you will lose an attempt. Don't worry if you are distracted");
        System.out.println("and you try a letter already guessed we will not remove one attempt.");
        System.out.println("ENJOY!");
        System.out.println("---------------------------------------------------------------------------");
        askLetter();
    }

    /**
     * Ask which letter to try and perform checks calling guessLetter method. Depending on the result
     * it may notice to the player his/her win, his/her lose, ask for another letter or exit from the program.
     */
    private void askLetter() {
        if (playerWon()) {
            System.out.println("Congratulations, you win!");
            return;
        }

        if (playerLose()) {
            System.out.println("I'm sorry, you lose");
            System.out.println("The word was: "+new String(wordChars));
            return;
        }

        System.out.println();
        showWord();
        System.out.print("What letter do you think is missing? ");
        String letter = input.nextLine();

        actions(guessLetter(letter.toUpperCase()));
    }

    /**
     * This method read the answer from previous guess, print message to the player and pick next action to perform
     * @param ans the int resulting from checked letter chosen by the player
     */
    private void actions(int ans) {
        switch(ans) {
            case -1:
                System.out.println("The letter you guessed is not inside the word. "+attempt+" attempt(s) left.");
                askLetter();
                break;
            case 0:
                System.out.println("You already tried this letter, don't worry we will not punish you. You still have "+attempt+" attempt(s) left.");
                askLetter();
                break;
            case 1:
                System.out.println("Wow! The letter is inside the word. Keep going!!");
                askLetter();
                break;
            case 2: default:
                break;
        }
    }

}