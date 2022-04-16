package com.company;

import com.company.services.Game;

/**
 * A simple HangMan game playable from console.
 */
public class Main {

    public static Game game;
    public static final String theWord = "HANGMAN"; // Yes, I know... bird is the word, not this time :)

    /**
     * Constructor create a reference to Game Object providing the solution word as argument
     * @see com.company.services.Game
     */
    public static void main(String[] args) {
	// write your code here
        game = new Game(theWord);
        newGame();
    }

    /**
     * Invoke the Game beginGame method to start the game.
     * @see com.company.services.Game
     */
    public static void newGame() {
        game.beginGame();
    }
}