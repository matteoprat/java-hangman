# java-hangman
A very basic Hangman game written in java.
I made this project after "learning" Java in 5 days. It tooks like 2 hours to implement and write documentation.
The game itself have just one word "HANGMAN" to guess, you can change the word by editing the main.java file variable theWord.
It perform basic operations:
- display a number of _ equals to the word length;
- ask the user an input, it the user write a word (different from "quit" or "exit") the first character is checked;
- the user have a total of 5 attempt;
- if the letter is inside the word a message will be displayed and the _ in position of letter are replaced with actual letter;
- if the letter is not inside the word a message will be displayed informing the player that the letter is not in the word and display the number of attempt left;
- if the letter is not (or it is) inside the word and the user already tried this letter a message will inform that he will have no penalties and display number of attempt left;
- if the user write "quit" / "exit" or just press enter without any input the game will quit, with a message informing that the game is over;
- if the user have no more attempt the game will quit, with a message informing that the game is over;
- if the user guess all the letter the game will quit, informing the user that he found the word.

# logic
- I used a char[] to represent the word. All letters and the user input are converted to their capitalized form .
- I used a Set< Character > to store the guessed letters and a Set< String > containing QUIT and EXIT to control if the player want to quit.
- I used a Map< Character, List< Integer > > that is populated with letters[... list of index] to speed up the control avoiding array cycle that are slower than looking inside a HashMap, when a letter is inside the map it just change the char[index] retrieved from the HashMap List, to replace the _ with actual letter.
- When a letter is founded it is removed from map. When the map.size() is zero all letters have been found.
