/*
Keisun Wu
12 December 2017

Assignment #4B - Connect Four

This program is a game of Connect Four.

Extra feature
- Score counting
- Allow the use of WASD for control
- Press W or ARROW UP to undo last move
- Press N to start a new game, X to exit
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class ConnectFour extends JPanel implements ActionListener, MouseListener, KeyListener {
	static JFrame frame;
	final int BANANA = -1;
	final int STRAWBERRY = 1;
	final int EMPTY = 0;
	final int SQUARE_SIZE = 60;
	final int TOP_OFFSET = 42;
	final int BORDER_SIZE = 4;
	final Font CALIBRI_16 = new Font("Calibri", Font.PLAIN, 16);

	int[][] board;
	int currentPlayer;
	int currentColumn;
	int[] lastPosition = {0, 0};
	boolean gameOver;

	int bananaScore = 0, strawberryScore = 0;

	Image firstImage, secondImage;

	Timer timer;

	// For drawing images offScreen (prevents Flicker)
	// These variables keep track of an off screen image object and
	// its corresponding graphics object
	Image offScreenImage;
	Graphics offScreenBuffer;

	String[] options = {"Yes", "No"};

	public ConnectFour() {
		board = new int[8][9];

		// Setting the defaults for the panel
		setPreferredSize(new Dimension((board[0].length - 2) * SQUARE_SIZE + 2 * BORDER_SIZE + 1, (board.length - 1) * SQUARE_SIZE + TOP_OFFSET + BORDER_SIZE + 1));
		setLocation(100, 10);
		setBackground(new Color(200, 200, 200));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// Set up the Menu
		// Set up the Game MenuItems
		JMenuItem newOption, exitOption, undoOption;
		undoOption = new JMenuItem("Undo");
		newOption = new JMenuItem("New");
		exitOption = new JMenuItem("Exit");

		// Set up the Game Menu
		JMenu gameMenu = new JMenu("Game");
		// Add each MenuItem to the Game Menu (with a separator)
		gameMenu.add(undoOption);
		gameMenu.addSeparator();
		gameMenu.add(newOption);
		gameMenu.addSeparator();
		gameMenu.add(exitOption);

		JMenuBar mainMenu = new JMenuBar();
		mainMenu.add(gameMenu);
		// Set the menu bar for this frame to mainMenu
		frame.setJMenuBar(mainMenu);

		// Use a media tracker to make sure all of the images are
		// loaded before we continue with the program
		MediaTracker tracker = new MediaTracker(this);
		firstImage = Toolkit.getDefaultToolkit().getImage("banana.gif");
		tracker.addImage(firstImage, 0);
		secondImage = Toolkit.getDefaultToolkit().getImage("strawberry.gif");
		tracker.addImage(secondImage, 1);

		//  Wait until all of the images are loaded
		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
		}

		// Set up the icon image (Tracker not needed for the icon image)
		Image iconImage = Toolkit.getDefaultToolkit().getImage("banana.gif");
		frame.setIconImage(iconImage);

		// Start a new game and then make the window visible
		newGame();

		newOption.setActionCommand("New");
		newOption.addActionListener(this);
		exitOption.setActionCommand("Exit");
		exitOption.addActionListener(this);
		undoOption.setActionCommand("Undo");
		undoOption.addActionListener(this);

		setFocusable(true); // Need this to set the focus to the panel in order to add the keyListener
		addKeyListener(this);

		addMouseListener(this);

	} // Constructor


	// To handle normal menu items
	public void actionPerformed(ActionEvent event) {
		String eventName = event.getActionCommand();
		if (eventName.equals("New")) {
			newGame();
		} else if (eventName.equals("Exit")) {
			System.exit(0);
		} else if (eventName.equals("Undo")) {
			undo(board);
		}

	}
	
	public void newGame() {
		// Original code
		currentPlayer = BANANA;
		clearBoard(board);
		gameOver = false;
		currentColumn = board[0].length / 2 - 1;
		repaint();
	}

//------------YOUR CODE GOES HERE  ------------------//

	/**
	 * Clear the board.
	 *
	 * @param board the board to remove
	 */
	public void clearBoard(int[][] board) {
		for (int i = 1; i < board.length - 1; i++)
			for (int j = 1; j < board[i].length - 1; j++)
				board[i][j] = 0;
	}

	/**
	 * Find the next row available for the move.
	 *
	 * @param board  the board playing with
	 * @param column the column to find
	 * @return the next row available, if none, return -1
	 */
	public int findNextRow(int[][] board, int column) {
		for (int i = board.length - 2; i > 0; i--)
			if (board[i][column] == 0) return i;
		return -1;
	}

	/**
	 * Check which is the winner.
	 *
	 * @param board      the board to check
	 * @param lastRow    the row of last move
	 * @param lastColumn the column of last move
	 * @return the winner, if there is no winner, return 0
	 */
	public int checkForWinner(int[][] board, int lastRow, int lastColumn) {
		int[] connected = {1, 1, 1, 1};
		int[] posNeg = {1, -1};
		int[][] directions = {{1, 0}, {0, 1}, {1, 1}, {-1, 1}};
		boolean[][] unblocked = {{true, true}, {true, true}, {true, true}, {true, true}};
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				for (int k = 0; k < 2; k++) {
					if (unblocked[i][k]) {
						int row = lastRow + directions[i][0] * posNeg[k] * j;
						int column = lastColumn + directions[i][1] * posNeg[k] * j;
						if (board[row][column] == board[lastRow][lastColumn]) connected[i] += 1;
						else unblocked[i][k] = false;
					}
				}
			}
			if (connected[i] == 4) return board[lastRow][lastColumn];
		}
		return EMPTY;
	}

	/**
	 * Undo last move. Can only undo the last move.
	 *
	 * @param board the board to undo
	 */
	public void undo(int[][] board) {
		if (board[lastPosition[0]][lastPosition[1]] == EMPTY || gameOver) return;
		board[lastPosition[0]][lastPosition[1]] = EMPTY;
		currentPlayer *= -1;
		repaint();
	}

	/**
	 * Check if the board is full.
	 *
	 * @param board the board to check
	 * @return true if the board is full
	 */
	public boolean boardIsFull(int[][] board) {
		for (int i = 1; i < board.length - 1; i++) {
			for (int j = 1; j < board[i].length - 1; j++) {
				if (board[i][j] == EMPTY)
					return false;
			}
		}
		return true;
	}

//----------------------------------------------------//


	public void handleAction(int x, int y) {
		if (gameOver) {
			JOptionPane.showMessageDialog(this, "Please Select Game...New to start a new game",
					"Game Over", JOptionPane.WARNING_MESSAGE);
			return;
		}

		int column = (x - BORDER_SIZE) / SQUARE_SIZE + 1;
		// Check if clicked out of bound
		if (column < 1 || column > board[0].length - 2) return;
		int row = findNextRow(board, column);
		if (row <= 0) {
			JOptionPane.showMessageDialog(this, "Please Select another Column",
					"Column is Full", JOptionPane.WARNING_MESSAGE);
			return;
		}
		animatePiece(currentPlayer, column, row);
		board[row][column] = currentPlayer;
		lastPosition[0] = row;
		lastPosition[1] = column;

		int winner = checkForWinner(board, row, column);
		if (winner == BANANA) {
			gameOver = true;
			bananaScore += 1;
			repaint();
			JOptionPane.showMessageDialog(this, "Banana Wins!!!",
					"GAME OVER", JOptionPane.INFORMATION_MESSAGE);

		} else if (winner == STRAWBERRY) {
			gameOver = true;
			strawberryScore += 1;
			repaint();
			JOptionPane.showMessageDialog(this, "Strawberry Wins!!!",
					"GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		} else if (boardIsFull((board))) {
			gameOver = true;
			repaint();
			JOptionPane.showMessageDialog(this, "Draw!!!",
					"GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		} else {
			// Switch to the other player
			currentPlayer *= -1;
		}
		currentColumn = board[0].length / 2 - 1;
		repaint();
	}

	// MouseListener methods
	public void mouseClicked(MouseEvent e) {
		int x, y;
		x = e.getX();
		y = e.getY();

		handleAction(x, y);
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}


	// KeyListener methods
	public void keyPressed(KeyEvent e) {
		int prompt;
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
			case 'D':
				if (currentColumn < board[0].length - 3)
					currentColumn++;
				break;
			case KeyEvent.VK_DOWN:
			case 'S':
				handleAction((currentColumn) * SQUARE_SIZE + BORDER_SIZE, 0);
				break;
			case KeyEvent.VK_LEFT:
			case 'A':
				if (currentColumn > 0)
					currentColumn--;
				break;
			case 'W':
			case KeyEvent.VK_UP:
				undo(board);
				break;
			case 'N':
				prompt = JOptionPane.showOptionDialog(this,
						"Do you really want to start a new game?", "New Game",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						null,
						options, options[1]);
				if (prompt == 0)
					newGame();
				break;
			case 'X':
				prompt = JOptionPane.showOptionDialog(this,
						"Do you really want to exit?", "Exit",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						null,
						options, options[1]);
				if (prompt == 0)
					System.exit(0);
				break;
			default:
				return;
		}

		// Original code
//		if (kp.getKeyCode() == KeyEvent.VK_RIGHT) {
//			if (currentColumn < 6)
//				currentColumn++;
//		} else if (kp.getKeyCode() == KeyEvent.VK_DOWN) {
//			handleAction((currentColumn) * SQUARE_SIZE + BORDER_SIZE, 0);
//		} else if (kp.getKeyCode() == KeyEvent.VK_LEFT) {
//			if (currentColumn > 0)
//				currentColumn--;
//		} else
//			return;
		repaint();
	}


	public void keyReleased(KeyEvent e) {
	}


	public void keyTyped(KeyEvent e) {
	}


	public void animatePiece(int player, int column, int finalRow) {
		Graphics g = getGraphics();

		// Find the x and y positions for each row and column
		int xPos = (4 - 1) * SQUARE_SIZE + BORDER_SIZE;
		int yPos = TOP_OFFSET + 0 * SQUARE_SIZE;
		offScreenBuffer.clearRect(xPos, yPos, SQUARE_SIZE, SQUARE_SIZE);
		for (double row = 0; row < finalRow; row += 0.10) {
			// Find the x and y positions for each row and column
			xPos = (column - 1) * SQUARE_SIZE + BORDER_SIZE;
			yPos = (int) (TOP_OFFSET + row * SQUARE_SIZE);
			// Redraw the grid for this column
			for (int gridRow = 1; gridRow <= board.length - 2; gridRow++) {
				// Draw the squares
				offScreenBuffer.setColor(Color.black);
				offScreenBuffer.drawRect((column - 1) * SQUARE_SIZE + BORDER_SIZE,
						TOP_OFFSET + gridRow * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
			}

			// Draw each piece, depending on the value in board
			if (player == BANANA)
				offScreenBuffer.drawImage(firstImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);
			else if (player == STRAWBERRY)
				offScreenBuffer.drawImage(secondImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);

			// Transfer the offScreenBuffer to the screen
			g.drawImage(offScreenImage, 0, 0, this);
			delay(3);
			offScreenBuffer.clearRect(xPos + 1, yPos + 1, SQUARE_SIZE - 2, SQUARE_SIZE - 2);
		}
	}


	// Avoid flickering -- smoother graphics
	public void update(Graphics g) {
		paint(g);
	}


	public void paintComponent(Graphics g) {

		// Set up the offscreen buffer the first time paint() is called
		if (offScreenBuffer == null) {
			offScreenImage = createImage(this.getWidth(), this.getHeight());
			offScreenBuffer = offScreenImage.getGraphics();
		}

		// All of the drawing is done to an off screen buffer which is
		// then copied to the screen.  This will prevent flickering
		// Clear the offScreenBuffer first
		offScreenBuffer.clearRect(0, 0, this.getWidth(), this.getHeight());

		// Redraw the board with current pieces
		for (int row = 1; row <= board.length - 2; row++) {
			for (int column = 1; column <= board[0].length - 2; column++) {
				// Find the x and y positions for each row and column
				int xPos = (column - 1) * SQUARE_SIZE + BORDER_SIZE;
				int yPos = TOP_OFFSET + row * SQUARE_SIZE;

				// Draw the squares
				offScreenBuffer.setColor(Color.black);
				offScreenBuffer.drawRect(xPos, yPos, SQUARE_SIZE, SQUARE_SIZE);

				// Draw each piece, depending on the value in board
				if (board[row][column] == BANANA)
					offScreenBuffer.drawImage(firstImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);
				else if (board[row][column] == STRAWBERRY)
					offScreenBuffer.drawImage(secondImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);
			}
		}

		// Draw the scores
		offScreenBuffer.setFont(CALIBRI_16);
		offScreenBuffer.drawString("Banana: " + bananaScore, 8, 20);
		offScreenBuffer.drawString("Strawberry: " + strawberryScore, 8, 40);

		// Draw next player
		if (!gameOver)
			if (currentPlayer == BANANA)
				offScreenBuffer.drawImage(firstImage, currentColumn * SQUARE_SIZE + BORDER_SIZE, TOP_OFFSET, SQUARE_SIZE, SQUARE_SIZE, this);
			else
				offScreenBuffer.drawImage(secondImage, currentColumn * SQUARE_SIZE + BORDER_SIZE, TOP_OFFSET, SQUARE_SIZE, SQUARE_SIZE, this);

		// Transfer the offScreenBuffer to the screen
		g.drawImage(offScreenImage, 0, 0, this);
	}


	/**
	 * Purpose: To delay the given number of milliseconds
	 *
	 * @param milliSec The number of milliseconds to delay
	 */
	private void delay(int milliSec) {
		try {
			Thread.sleep(milliSec);
		} catch (InterruptedException e) {
		}
	}


	public static void main(String[] args) {
		// Original code
		frame = new JFrame("Connect Four");
		ConnectFour myPanel = new ConnectFour();
		myPanel.newGame();
		// Prevent user from resizing the window
		frame.setResizable(false);
		// Exit the program when the window is closed
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(myPanel);
		frame.pack();
		frame.setVisible(true);

	} // main method
} // ConnectFourWorking class