package wurdle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Frame extends JFrame implements KeyListener, ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel; // Main panel to contain the grid of smaller panels
	private JPanel[][] panelList;
	private JPanel keyboardPanel1;
	private JPanel keyboardPanel2;
	private JPanel keyboardPanel3;
	private JButton[] keyBoardFirstRowChars;
	private JButton[] keyBoardSecondRowChars;
	private JButton[] keyBoardThirdRowChars;
	private GameLogic gameLogic;
	private Keyboard keyboard;
    
    private final Color BG_COLOR = new Color(36,29,28);
    private final Font PANEL_FONT = new Font("Arial", Font.CENTER_BASELINE, 35);
    private final Font KEYBOARD_FONT = new Font("Arial", Font.CENTER_BASELINE, 20);
    private final Font ENTER_KEY_FONT = new Font("Serif", Font.BOLD, 30);
    private final Font DELETE_KEY_FONT = new Font("Serif", Font.BOLD, 30);
    //private final Color GREEN = new Color(108,169,101);
    //private final Color ORANGE = new Color(200,182,83);
    private final Color GREY = new Color(62,65,67);
    
    private final Color CAOIMHE_COLOR_1 = new Color(142, 67, 188);
    private final Color CAOIMHE_COLOR_2 = new Color(75, 180, 168);
    
    private final Color KEYBOARD_GREY = new Color(120,124,127);

    Frame() {

        this.setTitle("Wordle");
        this.setSize(new Dimension(800,800));
        this.setLayout(null); // Use null layout to manually position panels
        this.setResizable(false);
        this.getContentPane().setBackground(BG_COLOR);
        this.setFocusable(true);
        this.addKeyListener(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        // WURDLE LOGO
        
        BufferedImage iconImg = null;
		try {		
			iconImg = ImageIO.read(new File("src/wurdle/wurdleLogo.png"));			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int xCoordinate = (this.getWidth() - 400) / 2;
        
        JLabel wordleLabel = new JLabel(new ImageIcon(iconImg));
        wordleLabel.setBounds(xCoordinate, 1, 400, 100);
        this.add(wordleLabel);
        
        // Calculate x-coordinate to center mainPanel horizontally
        int panelWidth = 300; // Width of mainPanel
        int frameWidth = this.getWidth(); // Width of the frame
        xCoordinate = (frameWidth - panelWidth) / 2;

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 5, 10, 10)); // Adjust the gap values as needed
        mainPanel.setBounds(xCoordinate, 100, panelWidth, 360); // Adjust the bounds as needed
        mainPanel.setBackground(BG_COLOR);
        
        // 5x6 PANELS

        panelList = new JPanel[6][5];

        for (int i=0; i < 6; i++) {
            for (int z=0; z < 5; z++) {
                panelList[i][z] = new JPanel();
                panelList[i][z].setBackground(BG_COLOR); // Set background color to white
                panelList[i][z].setBorder(BorderFactory.createLineBorder(Color.gray, 2));
                mainPanel.add(panelList[i][z]);
            }
        }
        
        // KEYBOARD
        
        keyboard = new Keyboard();
        keyboard.populateKeyboardRows();
        
        panelWidth = 500;
        xCoordinate = (frameWidth - panelWidth) / 2;
        
        keyboardPanel1 = new JPanel();
        keyboardPanel1.setLayout(new GridLayout(1, 5, 5, 5));
        keyboardPanel1.setBounds(xCoordinate, 510, panelWidth, 60);
        keyboardPanel1.setBackground(BG_COLOR);
        
        keyboardPanel2 = new JPanel();
        keyboardPanel2.setLayout(new GridLayout(1, 5, 5, 5));
        keyboardPanel2.setBounds(xCoordinate, 585, panelWidth, 60);
        keyboardPanel2.setBackground(BG_COLOR);
        
        keyboardPanel3 = new JPanel();
        keyboardPanel3.setLayout(new GridLayout(1, 5, 5, 5));
        keyboardPanel3.setBounds(xCoordinate, 660, panelWidth, 60);
        keyboardPanel3.setBackground(BG_COLOR);
        
        keyBoardFirstRowChars = new JButton[10];
        
        for(int i=0; i<10; i++) {
        	
        	char currentChar = keyboard.keyBoardFirstRow[i];
        	
        	keyBoardFirstRowChars[i] = new JButton();
        	keyBoardFirstRowChars[i].setBackground(KEYBOARD_GREY);
        	keyBoardFirstRowChars[i].setForeground(Color.white);
        	keyBoardFirstRowChars[i].setFont(KEYBOARD_FONT); 
        	keyBoardFirstRowChars[i].setBorder(BorderFactory.createLineBorder(KEYBOARD_GREY, 2));
        	keyBoardFirstRowChars[i].setText(Character.toString(currentChar));
        	keyBoardFirstRowChars[i].setFocusable(false);
        	
        	keyBoardFirstRowChars[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	
                	if(currentChar == 'e') {
                		enterFunction();
                	}
                	else if(currentChar == 'd') {
                		gameLogic.removeGuessLetter();
                		updateCurrentPanels();
                	}
                	else {
                		gameLogic.addGuessLetter(currentChar);
                		updateCurrentPanels();
                	}
                 }          
              });          	
            keyboardPanel1.add(keyBoardFirstRowChars[i]);
		}
        
        keyBoardSecondRowChars = new JButton[9];
        
		for(int i=0; i<9; i++) {
			
			char currentChar = keyboard.keyBoardSecondRow[i];
			
			keyBoardSecondRowChars[i] = new JButton();
        	keyBoardSecondRowChars[i].setBackground(KEYBOARD_GREY); // Set background color to white
        	keyBoardSecondRowChars[i].setForeground(Color.white);
        	keyBoardSecondRowChars[i].setFont(KEYBOARD_FONT); 
        	keyBoardSecondRowChars[i].setBorder(BorderFactory.createLineBorder(KEYBOARD_GREY, 2));        	
        	keyBoardSecondRowChars[i].setText(Character.toString(currentChar));
        	keyBoardSecondRowChars[i].setFocusable(false);
        	
        	keyBoardSecondRowChars[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	
                	if(currentChar == 'e') {
                		enterFunction();
                	}
                	else if(currentChar == 'd') {
                		gameLogic.removeGuessLetter();
                		updateCurrentPanels();
                	}
                	else {
                		gameLogic.addGuessLetter(currentChar);
                		updateCurrentPanels();
                	}
                 }          
              });          	
            keyboardPanel2.add(keyBoardSecondRowChars[i]);	
		}
		keyBoardThirdRowChars = new JButton[9];
		
		for(int i=0; i<9; i++) {
			
			char currentChar = keyboard.keyBoardThirdRow[i];
			
			keyBoardThirdRowChars[i] = new JButton();
        	keyBoardThirdRowChars[i].setBackground(KEYBOARD_GREY); // Set background color to white
        	keyBoardThirdRowChars[i].setBorder(BorderFactory.createLineBorder(KEYBOARD_GREY, 2)); 
        	keyBoardThirdRowChars[i].setForeground(Color.white);
        	
        	if(currentChar == 'e') {
        		keyBoardThirdRowChars[i].setFont(DELETE_KEY_FONT); 
        		keyBoardThirdRowChars[i].setText(Character.toString('\u23CE'));        		
        	}
        	else if(currentChar == 'd') {
        		keyBoardThirdRowChars[i].setFont(ENTER_KEY_FONT); 
        		keyBoardThirdRowChars[i].setText(Character.toString('\u2190'));
        	}
        	else {
        		keyBoardThirdRowChars[i].setText(Character.toString(currentChar));
        		keyBoardThirdRowChars[i].setFont(KEYBOARD_FONT); 
        	}
        	keyBoardThirdRowChars[i].setFocusable(false);
        	
        	keyBoardThirdRowChars[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	
                	if(currentChar == 'e') {
                		enterFunction();
                	}
                	else if(currentChar == 'd') {
                		gameLogic.removeGuessLetter();
                		updateCurrentPanels();
                	}
                	else {
                		gameLogic.addGuessLetter(currentChar);
                		updateCurrentPanels();
                	}
                 }          
              });          	
        	
            keyboardPanel3.add(keyBoardThirdRowChars[i]);	
		}	
        
        this.add(mainPanel);
        this.add(keyboardPanel1);
        this.add(keyboardPanel2);
        this.add(keyboardPanel3);
        
        this.setVisible(true);
        
        gameLogic = new GameLogic();
        gameLogic.startGame();        
    }

	private Color checkLetterColor(Letter letter) {

        if (letter.green) {
            return CAOIMHE_COLOR_1;
        } else if (letter.orange) {
            return CAOIMHE_COLOR_2;
        }
        return GREY;
    }

    private void updateAllPanels() {
    	
        for (int i = 0; i < 6; i++) {
            for (int z = 0; z < 5; z++) {
            	
                panelList[i][z].removeAll();

                Letter currentLetter = gameLogic.wordLogic.guessedLetters[i][z];

                // Update existing panel instead of creating a new one
                JPanel panel = panelList[i][z];

                JLabel label = new JLabel();

                if (currentLetter != null) {
                    label.setText(Character.toString(currentLetter.letter));
                    panel.setBorder(BorderFactory.createLineBorder(checkLetterColor(currentLetter), 2));
                    panel.setBackground(checkLetterColor(currentLetter));
                } else {
                    label.setText("");
                }

                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);

                label.setFont(PANEL_FONT);
                label.setForeground(Color.white);

                panel.add(label);
                panel.revalidate();
                panel.repaint();
            }
        }
    }
    
    private void updateCurrentPanels() {
    	
    	for(int i=0; i<5; i++) {
    		
    		panelList[gameLogic.round][i].removeAll();
    		
    		JLabel label = new JLabel();
    		
    		// Check if guessString has enough characters before accessing
            if (i < gameLogic.guessString.length()) {
            	
            	char currentLetter = Character.toUpperCase(gameLogic.guessString.charAt(i));
            	
                label.setText(Character.toString(currentLetter));

                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);

                // Set the font and color
                label.setFont(PANEL_FONT); // Adjust font size as needed
                label.setForeground(Color.white); // Set the text color to green
            }
            
            panelList[gameLogic.round][i].add(label);
            panelList[gameLogic.round][i].revalidate();
            panelList[gameLogic.round][i].repaint();    		
    	}    	
    }
    
    private void updateKeyColor(Letter letter, JButton key) {
    	
    	if(letter.grey) {
    		if(key.getBackground() == KEYBOARD_GREY) {
    			key.setBackground(GREY);
    			key.setBorder(BorderFactory.createLineBorder(GREY, 2));
    		}
    	}
    	else if(letter.orange) {
    		if(key.getBackground() == KEYBOARD_GREY || key.getBackground() == GREY) {
    			key.setBackground(CAOIMHE_COLOR_2);
    			key.setBorder(BorderFactory.createLineBorder(CAOIMHE_COLOR_2, 2));
    		}
    	}
    	else {    	
    		if(key.getBackground() != CAOIMHE_COLOR_1) {
    			key.setBackground(CAOIMHE_COLOR_1);
    			key.setBorder(BorderFactory.createLineBorder(CAOIMHE_COLOR_1, 2));
    		}
    	}
    }
    
    private void updateKeyboardColors(int currentRound) {
    	
    	Letter[][] guessedLetters = gameLogic.wordLogic.guessedLetters;
    	
    	for(int i=0; i<guessedLetters.length-1; i++) {
    		
    		Letter currentLetter = guessedLetters[currentRound][i];
    		
    		for(int z=0; z<10; z++) {
    			if(currentLetter.letter == keyboard.keyBoardFirstRow[z]) {    				
    				updateKeyColor(currentLetter, keyBoardFirstRowChars[z]);
    			}
    		}
    		for(int z=0; z<9 ;z++) {
    			if(currentLetter.letter == keyboard.keyBoardSecondRow[z]) {    				
    				updateKeyColor(currentLetter, keyBoardSecondRowChars[z]);
    			}
    		}
    		for(int z=0; z<9; z++) {
    			if(currentLetter.letter == keyboard.keyBoardThirdRow[z]) {
    				updateKeyColor(currentLetter, keyBoardThirdRowChars[z]);
    			}
    		}	    		
    	}
    }
    
    private void enterFunction() {
    	
    	if(gameLogic.wordLogic.wordIsValid(gameLogic.guessString.toLowerCase())) {
			gameLogic.guess(gameLogic.guessString, gameLogic.wordleWord, gameLogic.round);
			updateKeyboardColors(gameLogic.round);
			updateAllPanels();
			gameLogic.round +=1;
			gameLogic.guessString = "";
			
			if(gameLogic.gameWon) {
				gameLogic.winGame();
			}
		} 	   	
    }


    @Override
    public void keyTyped(KeyEvent e) {
    	
    	gameLogic.gameOver = gameLogic.isGameOver(gameLogic.round) || gameLogic.gameWon;
    			
    			if(!gameLogic.gameOver) {		
    				
    				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
    					
    					enterFunction();
        		} 
    			else {
    				
        			char currentTyped = e.getKeyChar();
        			
        			if(currentTyped == KeyEvent.VK_BACK_SPACE) {
        				gameLogic.removeGuessLetter();	// boolean relates to backspace
        			}
        			else if (gameLogic.letterIsValid(currentTyped)) {
        				gameLogic.addGuessLetter(currentTyped);
        			}
        			
        			updateCurrentPanels();
        			
        		}
    		} else {
    			//gameOver();
    		}
    	}

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
