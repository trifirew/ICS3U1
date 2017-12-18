/*
Keisun Wu
12 December 2017

Assignment #4B - Connect Four BONUS

This program is a game of Connect Four.
The program was restructured completely for the functionality added to this game.

Comparing to the original game, this game:
- implemented score counting on screen and popup on exit
- allowed the use of WASD to move in addition to ARROW KEYS
- allowed player to undo one last step (press W or ARROW UP, or select it from the menu)
- implemented keyboard shortcut for navigation and control
  - e(X)it
  - (N)ew game
- prompt when user tries to close the window
- when the board is filled up and no one wins, display DRAW
+ implemented options to change the size of the board (EXTRA)
+ when game is over, show a popup for player to choose exit or start a new game (EXTRA)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This is the controller / main class of the game.
 */
public class ConnectFourBonus {
	public static void main(String[] args) {
		// Create a new instance of the game
		ConnectFourGame game = new ConnectFourGame(6, 7);
	}
}

/**
 * The ConnectFourGame class which performs the calculation and checking.
 */
class ConnectFourGame {

	ConnectFourWindow window;
	ConnectFourPanel panel;

	String[] rowOptions, colOptions;

	final int BANANA = -1;
	final int STRAWBERRY = 1;
	final int EMPTY = 0;

	int[][] board;
	int currentPlayer;
	int currentColumn;
	int[] lastPosition = {0, 0};
	int bananaScore, strawberryScore;
	boolean gameOver;

	/**
	 * Create a new game with a board of assigned rows and column
	 *
	 * @param row    the number of rows of the board
	 * @param column the number of columns of the board
	 */
	public ConnectFourGame(int row, int column) {
		board = new int[row + 2][column + 2];
		bananaScore = 0;
		strawberryScore = 0;
		currentPlayer = BANANA;
		gameOver = false;
		currentColumn = board[0].length / 2 - 1;

		rowOptions = new String[10];
		colOptions = new String[18];
		for (int i = 0; i < 10; i++) rowOptions[i] = (4 + i) + "";
		for (int i = 0; i < 18; i++) colOptions[i] = (5 + i) + "";

		panel = new ConnectFourPanel(this);
		window = new ConnectFourWindow(this);
		window.add(panel);
		window.pack();
		window.setVisible(true);

		window.undoOption.setEnabled(false);
	} // Constructor

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
	 */
	public void undo() {
		if (board[lastPosition[0]][lastPosition[1]] == EMPTY || gameOver) return;
		window.undoOption.setEnabled(false);
		board[lastPosition[0]][lastPosition[1]] = EMPTY;
		currentPlayer *= -1;
		panel.repaint();
	}

	/**
	 * Check if the board is full.
	 *
	 * @return true if the board is full
	 */
	public boolean boardIsFull() {
		for (int i = 1; i < board.length - 1; i++) {
			for (int j = 1; j < board[i].length - 1; j++) {
				if (board[i][j] == EMPTY) return false;
			}
		}
		return true;
	}

	/**
	 * Handle each move by the player.
	 *
	 * @param x,y the coordinate of mouse click
	 */
	public void handleAction(int x, int y) {
		if (gameOver) {
			JOptionPane.showMessageDialog(panel, "Please Select Game...New to start a new game",
					"Game Over", JOptionPane.WARNING_MESSAGE);
			return;
		}

		int column = (x - panel.BORDER_SIZE) / panel.SQUARE_SIZE + 1;
		// Check if clicked out of bound
		if (column < 1 || column > board[0].length - 2) return;
		int row = findNextRow(board, column);
		if (row <= 0) {
			JOptionPane.showMessageDialog(panel, "Please Select another Column",
					"Column is Full", JOptionPane.WARNING_MESSAGE);
			return;
		}
		panel.animatePiece(currentPlayer, column, row);
		board[row][column] = currentPlayer;
		lastPosition[0] = row;
		lastPosition[1] = column;

		int prompt;
		String[] options = {"New game", "Cancel", "Exit"};
		int winner = checkForWinner(board, row, column);
		if (winner != EMPTY) window.undoOption.setEnabled(false);
		if (winner == BANANA) {
			gameOver = true;
			bananaScore += 1;
			panel.repaint();
			prompt = JOptionPane.showOptionDialog(panel,
					"Banana Wins!!!\nDo you really want to start a new game?", "GAME OVER",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null,
					options, options[1]);
			if (prompt == 0) newGame();
			else if (prompt == 2) exit();
		} else if (winner == STRAWBERRY) {
			gameOver = true;
			strawberryScore += 1;
			panel.repaint();
			prompt = JOptionPane.showOptionDialog(panel,
					"Strawberry Wins!!!\nDo you really want to start a new game?", "GAME OVER",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null,
					options, options[1]);
			if (prompt == 0) newGame();
			else if (prompt == 2) exit();
		} else if (boardIsFull()) {
			gameOver = true;
			panel.repaint();
			prompt = JOptionPane.showOptionDialog(panel,
					"Draw!!!\nDo you really want to start a new game?", "GAME OVER",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null,
					options, options[1]);
			if (prompt == 0) newGame();
			else if (prompt == 2) exit();
		} else {
			// Switch to the other player
			currentPlayer *= -1;
			window.undoOption.setEnabled(true);
		}
		currentColumn = board[0].length / 2 - 1;
		panel.repaint();
	}

	/**
	 * Start a new game without clearing the scores.
	 */
	public void newGame() {
		currentPlayer = BANANA;
		clearBoard(board);
		gameOver = false;
		currentColumn = board[0].length / 2 - 1;
		panel.repaint();
		window.undoOption.setEnabled(false);
	}

	/**
	 * Change the size of the board and start a new game without clearing the scores.
	 *
	 * @param row    the new number of rows of the board
	 * @param column the new number of columns of the board
	 */
	public void changeSize(int row, int column) {
		board = new int[row + 2][column + 2];
		currentPlayer = BANANA;
		gameOver = false;
		currentColumn = board[0].length / 2 - 1;

		window.dispose();
		panel = new ConnectFourPanel(this);
		window = new ConnectFourWindow(this);
		window.add(panel);
		window.pack();
		window.setVisible(true);
		window.undoOption.setEnabled(false);
	}

	/**
	 * Display the scores and exit the game.
	 */
	public void exit() {
		JOptionPane.showMessageDialog(panel, String.format("Scores\n----------\nBanana: %d\nStrawberry: %d", bananaScore, strawberryScore), "Thank you for playing!", JOptionPane.PLAIN_MESSAGE);
		System.exit(0);
	}

}

/**
 * The frame of the game window
 */
class ConnectFourWindow extends JFrame {
	private final String ACTION_NEW = "New game";
	private final String ACTION_EXIT = "Exit game";
	private final String ACTION_CHANGE_SIZE = "Change board size";
	private final String ACTION_UNDO = "Undo last move";

	JMenuItem newOption, exitOption, undoOption, changeSizeOption;

	/**
	 * Construct a new window for the game.
	 *
	 * @param game the ConnectFourGame object to display on the panel
	 */
	public ConnectFourWindow(ConnectFourGame game) {
		super("Connect Four BONUS");

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		// Set up the icon image (Tracker not needed for the icon image)
		Image iconImage = Toolkit.getDefaultToolkit().getImage("banana.gif");
		setIconImage(iconImage);

		undoOption = new JMenuItem(ACTION_UNDO);
		newOption = new JMenuItem(ACTION_NEW);
		exitOption = new JMenuItem(ACTION_EXIT);
		changeSizeOption = new JMenuItem(ACTION_CHANGE_SIZE);

		// Set up the Game Menu
		JMenu gameMenu = new JMenu("Game");
		// Add each MenuItem to the Game Menu (with a separator)
		gameMenu.add(undoOption);
		gameMenu.addSeparator();
		gameMenu.add(newOption);
		gameMenu.add(changeSizeOption);
		gameMenu.addSeparator();
		gameMenu.add(exitOption);

		JMenuBar mainMenu = new JMenuBar();
		mainMenu.add(gameMenu);
		// Set the menu bar for this frame to mainMenu
		setJMenuBar(mainMenu);

		// Create an ActionListener
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String eventName = e.getActionCommand();
				if (eventName.equals(ACTION_NEW)) {
					game.newGame();
				} else if (eventName.equals(ACTION_EXIT)) {
					game.exit();
				} else if (eventName.equals(ACTION_UNDO)) {
					game.undo();
				} else if (eventName.equals(ACTION_CHANGE_SIZE)) {
					int rowNumber = JOptionPane.showOptionDialog(getContentPane(), "How many rows would you like to have?", "Change Board Size", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, game.rowOptions, game.rowOptions[2]);
					int colNumber = JOptionPane.showOptionDialog(getContentPane(), "How many columns would you like to have?", "Change Board Size", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, game.colOptions, game.colOptions[2]);
					game.changeSize(Integer.parseInt(game.rowOptions[rowNumber]), Integer.parseInt(game.colOptions[colNumber]));
				}
			}
		};

		newOption.setActionCommand(ACTION_NEW);
		newOption.addActionListener(listener);
		exitOption.setActionCommand(ACTION_EXIT);
		exitOption.addActionListener(listener);
		undoOption.setActionCommand(ACTION_UNDO);
		undoOption.addActionListener(listener);
		changeSizeOption.setActionCommand(ACTION_CHANGE_SIZE);
		changeSizeOption.addActionListener(listener);

		// Set action on closing
		WindowListener windowListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				String[] options = {"Yes", "No"};
				int prompt = JOptionPane.showOptionDialog(ConnectFourWindow.this,
						"Do you really want to exit?", "Exit",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						null,
						options, options[1]);
				if (prompt == 0)
					game.exit();
			}
		};
		addWindowListener(windowListener);
	}
}

/**
 * The panel of the game
 */
class ConnectFourPanel extends JPanel {
	private ConnectFourGame game;

	final int TOP_OFFSET = 42;
	final Font CALIBRI_16 = new Font("Calibri", Font.PLAIN, 16);
	final int SQUARE_SIZE = 60;
	final int BORDER_SIZE = 4;

	private Image firstImage, secondImage;

	// For drawing images offScreen (prevents Flicker)
	// These variables keep track of an off screen image object and
	// its corresponding graphics object
	private Image offScreenImage;
	private Graphics offScreenBuffer;

	/**
	 * Construct a panel for the game.
	 *
	 * @param game the ConnectFourGame object to display on the panel
	 */
	public ConnectFourPanel(ConnectFourGame game) {
		int myWidth = (game.board[0].length - 2) * SQUARE_SIZE + 2 * BORDER_SIZE + 1;
		int myHeight = (game.board.length - 1) * SQUARE_SIZE + TOP_OFFSET + BORDER_SIZE + 1;
		this.game = game;
		// Setting the defaults for the panel
		setPreferredSize(new Dimension(myWidth, myHeight));
		setLocation(100, 10);
		setBackground(new Color(200, 200, 200));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

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

		KeyListener keyListener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int prompt;
				String[] options = {"Yes", "No"};
				switch (e.getKeyCode()) {
					case KeyEvent.VK_RIGHT:
					case 'D':
						if (game.currentColumn < game.board[0].length - 3)
							game.currentColumn++;
						break;
					case KeyEvent.VK_DOWN:
					case 'S':
						game.handleAction((game.currentColumn) * SQUARE_SIZE + BORDER_SIZE, 0);
						break;
					case KeyEvent.VK_LEFT:
					case 'A':
						if (game.currentColumn > 0)
							game.currentColumn--;
						break;
					case KeyEvent.VK_UP:
					case 'W':
						game.undo();
						break;
					case 'N':
						prompt = JOptionPane.showOptionDialog(ConnectFourPanel.this,
								"Do you really want to start a new game?", "New Game",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
								null,
								options, options[0]);
						if (prompt == 0)
							game.newGame();
						break;
					case 'X':
						prompt = JOptionPane.showOptionDialog(ConnectFourPanel.this,
								"Do you really want to exit?", "Exit",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
								null,
								options, options[1]);
						if (prompt == 0)
							game.exit();
						break;
					default:
						return;
				}
				repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		};

		MouseListener mouseListener = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x, y;
				x = e.getX();
				y = e.getY();
				game.handleAction(x, y);
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		};

		setFocusable(true); // Need this to set the focus to the panel in order to add the keyListener
		addKeyListener(keyListener);
		addMouseListener(mouseListener);

	} // Constructor

	/**
	 * Show an animation of pieces falling down.
	 *
	 * @param player   the current player to display
	 * @param column   the column the player places its move
	 * @param finalRow the row the player places its move
	 */
	public void animatePiece(int player, int column, int finalRow) {
		Graphics g = getGraphics();

		// Find the x and y positions for each row and column
		int xPos = (game.board[0].length / 2 - 1) * SQUARE_SIZE + BORDER_SIZE;
		int yPos = TOP_OFFSET;
		offScreenBuffer.clearRect(xPos, yPos, SQUARE_SIZE, SQUARE_SIZE);
		for (double row = 0; row < finalRow; row += 0.10) {
			// Find the x and y positions for each row and column
			xPos = (column - 1) * SQUARE_SIZE + BORDER_SIZE;
			yPos = (int) (TOP_OFFSET + row * SQUARE_SIZE);
			// Redraw the grid for this column
			for (int gridRow = 1; gridRow <= game.board.length - 2; gridRow++) {
				// Draw the squares
				offScreenBuffer.setColor(Color.black);
				offScreenBuffer.drawRect((column - 1) * SQUARE_SIZE + BORDER_SIZE,
						TOP_OFFSET + gridRow * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
			}

			// Draw each piece, depending on the value in board
			if (player == game.BANANA)
				offScreenBuffer.drawImage(firstImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);
			else if (player == game.STRAWBERRY)
				offScreenBuffer.drawImage(secondImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);

			// Transfer the offScreenBuffer to the screen
			g.drawImage(offScreenImage, 0, 0, this);
			delay(3);
			offScreenBuffer.clearRect(xPos + 1, yPos + 1, SQUARE_SIZE - 2, SQUARE_SIZE - 2);
		}
	}


	/**
	 * Avoid flickering -- smoother graphics.
	 *
	 * @param g the Graphics object to paint on
	 */
	public void update(Graphics g) {
		paint(g);
	}

	/**
	 * Paint the components on the screen.
	 *
	 * @param g the Graphics object to paint on
	 */
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
		for (int row = 1; row <= game.board.length - 2; row++) {
			for (int column = 1; column <= game.board[0].length - 2; column++) {
				// Find the x and y positions for each row and column
				int xPos = (column - 1) * SQUARE_SIZE + BORDER_SIZE;
				int yPos = TOP_OFFSET + row * SQUARE_SIZE;

				// Draw the squares
				offScreenBuffer.setColor(Color.black);
				offScreenBuffer.drawRect(xPos, yPos, SQUARE_SIZE, SQUARE_SIZE);

				// Draw each piece, depending on the value in board
				if (game.board[row][column] == game.BANANA)
					offScreenBuffer.drawImage(firstImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);
				else if (game.board[row][column] == game.STRAWBERRY)
					offScreenBuffer.drawImage(secondImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);
			}
		}

		// Draw the scores
		offScreenBuffer.setFont(CALIBRI_16);
		offScreenBuffer.drawString("Banana: " + game.bananaScore, 8, 20);
		offScreenBuffer.drawString("Strawberry: " + game.strawberryScore, 8, 40);

		// Draw next player
		if (!game.gameOver)
			if (game.currentPlayer == game.BANANA)
				offScreenBuffer.drawImage(firstImage, game.currentColumn * SQUARE_SIZE + BORDER_SIZE, TOP_OFFSET, SQUARE_SIZE, SQUARE_SIZE, this);
			else
				offScreenBuffer.drawImage(secondImage, game.currentColumn * SQUARE_SIZE + BORDER_SIZE, TOP_OFFSET, SQUARE_SIZE, SQUARE_SIZE, this);

		// Transfer the offScreenBuffer to the screen
		g.drawImage(offScreenImage, 0, 0, this);
	}

	/**
	 * Delay the given number of milliseconds.
	 *
	 * @param milliSec the number of milliseconds to delay
	 */
	private void delay(int milliSec) {
		try {
			Thread.sleep(milliSec);
		} catch (InterruptedException e) {
		}
	}
}