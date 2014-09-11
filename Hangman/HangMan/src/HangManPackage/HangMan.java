// HangMan v 1.0
// Written by James D. Nell
// August 28, 2014
// All code is original 
// No outside credit necessary
// Does not include a game restart option

package HangManPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class HangMan { // Adding doc comments to apply to GitHub Assignment . 
	
	static int lives = 6; // number of lives

	static String[] listOfGuesses = new String[0]; // keeps track of all guesses

	static int count = 0; 
							 
	static int winner = 0; 

	public static void main(String[] args) {

		char[] word = Word(); // Get a random word from list

		Boolean[] alphabet = Alphabet(); // Creates a boolean array to store the
											// 26 values to keep track of
											// letters used / currently not used

		Graphics(lives); // Display hang man graphics

		char[] display = LengthOfWord(word); // determine the length of the
		// random word

		while (lives >= 1) {

			char[] alphaLetter = AlphaLetter(); 	// char list of all valid characters
			
			String guess = Guess(alphaLetter); 	// gets guesses from user and stores it in
												// String 'guess'

						
			int livesUpdated = Results(lives, guess, alphabet, alphaLetter, word, display); // the backbone of the app
			
			lives = livesUpdated; // updates lives variable for current lives
						
			Lives(livesUpdated); // updates the number of lives used
			
			Graphics(livesUpdated); // Draws the Hangman Graphics     
	
			String compare = Display(display); // compares the guesses to the correct word

			int totalGuesses = ListOfGuesses(listOfGuesses, guess, count); // organizes all guesses into one string
			
			winner = Winner(word, compare, totalGuesses); // determines if the correct word has been guessed
			
			if (winner == 1) lives = 0; // you win method

		}

		if (winner == 0){ // prints the loss and word to the terminal
		System.out.println("Your looking a little blue. . .");
		String theWord = new String(word);
		System.out.println("Your word was : " + theWord);}
	}
	


	private static int Winner(char[] word, String compare, int totalGuesses) {
		
		String finalWord = new String(word);
		
		if (compare.equals(finalWord)){
			System.out.println("You have guessed the correct word!!!");
			System.out.println("It took you " + totalGuesses + " guesses ");
			 int winner = 1;
			return winner;
			
		}
		
		return 0;
	}

	private static int ListOfGuesses(String[] listOfGuesses2, String guess,
			int count2) {

		ArrayList<String> stringList = new ArrayList<String>();

		Collections.addAll(stringList, listOfGuesses);

		stringList.add(guess);

		listOfGuesses = (String[]) stringList.toArray(new String[0]);

		System.out.println(" ");
		System.out.println("-------------------------- "); // just a divider
															// line to
															// organize the
															// output to the
															// terminal

		System.out.println("You have guessed the following letters already:");

		for (int z = 0; z < listOfGuesses.length; z++) {

			System.out.print(listOfGuesses[z] + " ");

		}
		System.out.println("");

		listOfGuesses[count] = guess;
		count++;
		// guesses++;
		return count;
	}

	private static String Display(char[] display) {

		// prints the display array with correct guesses in the proper order
		// - If there are any.
		for (int b = 0; b < display.length; b++) {

			System.out.print(display[b] + " ");

		}
		String compare = new String(display);
		return compare;
	}

	private static int Results(int lives, String guess, Boolean[] alphabet,
			char[] alphaLetter, char[] word, char[] display) {
		
		int multipleLetters = 0;	// counter for multiple letters to
								// prevent 'lives' from adding more
								// than 1 to offset the lives-- with
								// each guess

		// turns 'guess' string in to a char
		char result = guess.charAt(0);
		
		// iterates the alphabet char array looking for result char
		for (int i = 0; i < alphabet.length; i++) {
			
			if (result == alphaLetter[i]) {
				
				alphabet[i] = true;
				
				// iterates the char word array and places the char result
				// into char word array
				for (int y = 0; y < word.length; y++) {
					
					if (result == word[y]) {
						multipleLetters++;
						// places correct letter into char display array
						display[y] = result;
						
						if (multipleLetters >= 1) { 	// only adds a life once
													// for any one letter

							lives++; // simply offsets the lives--; that
										// reduced lives of every guess
							
						}
	
						//return lives;
					}
				
				}
				
			}
			
		} 
	
	lives--;
		return lives;
		
	} 

	private static void Lives(int lives2) { // displays # of lives for testing

		System.out.println("You have " + lives2 + " lives remaining");
		System.out.println(" ");
		System.out.println("-------------------------- "); // just a divider
															// line to
															// organize the
															// output to the
															// terminal
	}

	private static Boolean[] Alphabet() {

		// creating an array to remember alphabet

		Boolean[] alphabet = new Boolean[25];

		for (int i = 0; i < alphabet.length; i++) {

			alphabet[i] = false;
		}

		return alphabet;
	}

	private static char[] AlphaLetter() {
		// char array created for alphabet array
		char[] alphaLetter = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
				'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z' }; // alphabet in char array

		return alphaLetter;
	}

	private static String Guess(char[] alphaLetter) {
		
		Boolean test = false;
		String guess = null;
		
		while (test == false) {
			
		System.out.print("Please enter your guess (Lower Case Letters Only): ");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		guess = scanner.nextLine();
		
		int length = guess.length();

		// turns 'guess' string in to a char
				char result = guess.charAt(0);
				
				// iterates the alphabet char array looking for result char
				for (int i = 0; i < alphaLetter.length; i++) {
					
					if (result == alphaLetter[i] && length == 1) {	
						test = true;
						}	
				}
		}
		return guess;
	}

	private static char[] LengthOfWord(char[] word) {
		// Determine length of word and create 'display' variable with word
		// length

		System.out.println("length of word = " + word.length);

		char[] display = new char[word.length];

		// populates char array display with '_' underscore
		for (int c = 0; c < display.length; c++) {

			display[c] = '_';
		}
		return display;

	}

	private static void Graphics(int lives) {

		// Hangman graphic header - used in every output
		System.out.println(" H A N G M A N");
		System.out.println("_______________");
		System.out.println("| |        \\ /");
		System.out.println("| |         |");

		if (lives <= 5) {
			System.out.println("| |         O");
		} else {
			System.out.println("| |      ");
		}
		if (lives == 4) {
			System.out.println("| |         |\\");
		}

		if (lives <= 3) {
			System.out.println("| |        /|\\");
		} else {
			System.out.println("| |       ");
		}

		if (lives <= 2) {
			System.out.println("| |         |");
		} else {
			System.out.println("| |      ");
		}
		if (lives == 1) {
			System.out.println("| |          \\");
		}

		if (lives == 0) {
			System.out.println("| |        / \\");
		} else {
			System.out.println("| |");
		}

		System.out.println("| |");
		System.out.println("----------------");

		// complete graphic for reference
		// System.out.println("_______________");
		// System.out.println("| |        \\ /");
		// System.out.println("| |         O");
		// System.out.println("| |        /|\\");
		// System.out.println("| |         |");
		// System.out.println("| |        / \\");
		// System.out.println("| |");
		// System.out.println("| |");
		// System.out.println("----------------");

	}

	public static char[] Word() {
		
		Random rand = new Random();

		int  n = rand.nextInt(20); // random number between 0 & 19

		// list of 20 words - can be any lower case words using 26 standard alphabetic characters
		String[] list = { "battle", "computer", "clock", "running", // utilizes double and triple letter combinations
				"telephone", "whiskers", "mountain", "peaches", "talking",
				"nickle", "pickle", "stalking", "wife", "remind", "roses",
				"flowers", "traffic", "down", "knowing", "disfunction" };
		char[] select;

		select = list[n].toCharArray();
	
		System.out.println("Your word is :" + list[n]); // displays chosen word

		return select; //returns chosen word
	}

}