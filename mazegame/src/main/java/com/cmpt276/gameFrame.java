package com.cmpt276;

import javax.swing.JFrame;

/**
 * Class to provide a GUI for the user
 */
public class gameFrame extends JFrame {
	/**
	 * Default constructor.
	 */
	public gameFrame() {
		this.add(new gamePanel());
		this.setTitle("Instructor vs Students");
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null); // show frame in middle
		this.setResizable(false); // do not allow window resize
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
