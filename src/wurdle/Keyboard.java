package wurdle;

public class Keyboard {
	
	final private char[] keyBoardChars = {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A',
			 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'e' ,'Z', 'X', 'C', 
			 'V', 'B', 'N', 'M', 'd'};

	public char[] keyBoardFirstRow = new char[10];
	public char[] keyBoardSecondRow = new char[9];
	public char[] keyBoardThirdRow = new char[9];
	
	public void populateKeyboardRows() {
		for(int i=0; i<10; i++) {
			keyBoardFirstRow[i] = keyBoardChars[i];		
		}
		for(int i=0; i<9; i++) {
			keyBoardSecondRow[i] = keyBoardChars[i+10];		
		}
		for(int i=0; i<9; i++) {
			keyBoardThirdRow[i] = keyBoardChars[i+19];		
		}	
	}	
}
