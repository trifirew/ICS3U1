/*
Keisun Wu
17 November 2017
Methods Exercises 12
 */

import javax.swing.*;
import java.awt.*;

public class DiamondWindow extends JPanel {

	public DiamondWindow() {
		setPreferredSize(new Dimension(600, 480));
	}

	/**
	 * Draw a 20x20 diamond on the panel
	 *
	 * @param g   the Graphics object to draw in
	 * @param x,y position of the top left corner of the diamond
	 */
	public void drawDiamond(Graphics g, int x, int y) {
		int[] xPoints = {x + 10, x, x + 10, x + 20};
		int[] yPoints = {y, y + 10, y + 20, y + 10};
		g.setColor(Color.RED);
		g.fillPolygon(xPoints, yPoints, 4);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawDiamond(g, 0, 0);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Draw Diamond");
		DiamondWindow panel = new DiamondWindow();

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
