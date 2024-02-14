package wurdle;

public class GameLogic {
	
	public wordLogic wordLogic;
	public String wordleWord;
	
	public int round;
    public String guessString;
    
    public boolean gameWon;
    public boolean gameOver;
	
	public void startGame() {
		
		wordLogic = new wordLogic();		
		wordleWord = wordLogic.generateWordleWord();
		
    	round = 0;
    	guessString = "";
    	
    	gameWon = false;
    	gameOver = false;   	
    }
	
	public void guess(String word, String wordleWord, int round) {	// checks user guess	
		
		word = word.toUpperCase();	
		wordLogic.modifyLetterList(word, wordleWord, round);
		
		if(word.equals(wordleWord)) {
			gameWon = true;
		}
	}
	
	public void winGame() {		
		gameOver = true;		
		System.out.println("WELL DONE");
	}
	
	public void removeGuessLetter() {		
		if(guessString.length() > 0) {
			guessString = guessString.substring(0, guessString.length() - 1);
		}	
	}
	
	public void addGuessLetter(char letter) {		
		if(guessString.length() < 5) {
			guessString += letter;
		}		
	}
	
	public boolean letterIsValid(char letter) {		
		return true;		
	}
	
	public boolean isGameOver(int r) {
    	return r > 5;
    }	
}
