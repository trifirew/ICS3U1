/*
Keisun Wu
17 November 2017
Methods Exercises 10 - 11
 */

import javax.swing.*;
import java.awt.*;

public class HappyFaceWindow extends JPanel {

	public HappyFaceWindow() {
		setPreferredSize(new Dimension(600, 480));
		setBackground(new Color(240, 240, 240));
	}

	/**
	 * Draw a 100x100 happy face on the panel.
	 *
	 * @param g   the Graphics object to draw in
	 * @param x,y position of the top left corner of the happy face
	 */
	public void drawHappyFace(Graphics g, int x, int y) {
		g.setColor(Color.YELLOW);
		// Face
		g.fillOval(x, y, 100, 100);
		g.setColor(Color.DARK_GRAY);
		// Eyes
		g.fillOval(x + 24, y + 28, 16, 16);
		g.fillOval(x + 60, y + 28, 16, 16);
		// Mouth
		g.drawArc(x + 20, y + 40, 60, 40, 210, 120);
	}

	/**
	 * Draw a custom size happy face on the panel.
	 *
	 * @param g            the Graphics object to draw in
	 * @param x,y          position of the top left corner of the happy face
	 * @param width,height size of the happy face
	 */
	public void drawHappyFace(Graphics g, int x, int y, int width, int height) {
		g.setColor(Color.YELLOW);
		g.fillOval(x, y, width, height);
		g.setColor(Color.DARK_GRAY);
		g.fillOval((int) (x + width * 0.24), (int) (y + height * 0.28), (int) (width * 0.16), (int) (height * 0.16));
		g.fillOval((int) (x + width * 0.6), (int) (y + height * 0.28), (int) (width * 0.16), (int) (height * 0.16));
		g.drawArc((int) (x + width * 0.2), (int) (y + height * 0.4), (int) (width * 0.6), (int) (height * 0.4), 210, 120);
	}

	/**
	 * Draw a 100x100 Huaji emotion on the panel.
	 *
	 * @param g   the Graphics object to draw in
	 * @param x,y position of the top left corner of the Huaji emotion
	 */
	public void drawHuaji(Graphics g, int x, int y) {
		g.setColor(Color.YELLOW);
		// Face
		g.fillOval(x, y, 100, 100);
		g.setColor(Color.DARK_GRAY);

		g.drawArc(x + 8, y + 4, 24, 20, 0, 180);
		g.drawArc(x + 68, y + 4, 24, 20, 0, 180);
		// Eyes
		g.drawArc(x + 4, y + 20, 36, 20, 0, 180);
		g.drawArc(x + 60, y + 20, 36, 20, 0, 180);
		g.drawArc(x + 4, y + 28, 36, 8, 0, 180);
		g.drawArc(x + 60, y + 28, 36, 8, 0, 180);
		g.fillOval(x + 8, y + 22, 8, 8);
		g.fillOval(x + 64, y + 22, 8, 8);
		g.setColor(Color.PINK);

		g.fillOval(x + 6, y + 36, 28, 12);
		g.fillOval(x + 66, y + 36, 28, 12);
		// Mouth
		g.setColor(Color.BLACK);
		g.drawArc(x + 10, y + 20, 80, 70, 0, -180);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawHappyFace(g, 50, 100);
		drawHappyFace(g, 100, 200, 300, 200);
//		drawHuaji(g, 400, 300);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Draw Happy Face");
		HappyFaceWindow window = new HappyFaceWindow();

		frame.add(window);
		frame.pack();
		frame.setVisible(true);
	}
}
