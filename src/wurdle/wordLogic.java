package wurdle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class wordLogic {
	
	private List<String> possibleWordList;
	private List<String> validWordList;
	
	public boolean wordIsValid(String guess) {
		
		createValidWordList();
		
		return guess.length() == 5 && validWordList.contains(guess);		
	}
	
	public String generateWordleWord() {	// generates a random 5 letter word		
		
		createWordleWordList();		
		int wordIndex = (int) (Math.random() * possibleWordList.size());
		
		return possibleWordList.get(wordIndex);
	}
	
	private void createValidWordList() {
		
		validWordList = new ArrayList<>();
		
		try {
            Scanner scanner = new Scanner(new File("src/textFiles/valid-wordle-words.txt"));

            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim();
                validWordList.add(word);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	private void createWordleWordList() {
		
		possibleWordList = new ArrayList<>();
		
		try {
            // Create a Scanner object to read the file
            Scanner scanner = new Scanner(new File("src/textFiles/wordleWords.txt"));

            scanner.useDelimiter(",");

            while (scanner.hasNext()) {
                String word = scanner.next().trim();
                possibleWordList.add(word);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	public Letter[][] guessedLetters = new Letter[6][5];
	
	public void modifyLetterList(String word, String wordleWord, int round) {
		
		for(int i=0; i<5; i++) {
			
			guessedLetters[round][i] = new Letter();
			
			if(checkLetterGreen(word.charAt(i), wordleWord, i)) {			
				guessedLetters[round][i].letter = word.charAt(i);
				guessedLetters[round][i].green = true;
			}
			else if(checkLetterOrange(word.charAt(i), wordleWord)) {				
				guessedLetters[round][i].letter = word.charAt(i);
				guessedLetters[round][i].orange = true;
			}
			else {		
				guessedLetters[round][i].letter = word.charAt(i);
				guessedLetters[round][i].grey = true;
			}
		}	
	}
	
	public boolean checkLetterGreen(char letter, String wordleWord, int index) {
		
		return letter == wordleWord.charAt(index);
	}
	
	public boolean checkLetterOrange(char letter, String wordleWord) {	
		
		return wordleWord.indexOf(letter) != -1;
	}
}
