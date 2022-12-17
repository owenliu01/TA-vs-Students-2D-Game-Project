package com.cmpt276;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Class to provide a title page to the user
 */
public class startFrame extends JFrame {
	private String instructionImagePath = "/images/infor.png";
	private String backgroundImagePath = "/images/classroom.png";

	/**
	 * Default constructor. Creates a title page with a button to start the game.
	 */
	public startFrame() {

		super("Instructor vs Students");

		setSize(800, 650);
		this.setLocationRelativeTo(null); // show frame in middle
		this.setResizable(false); // do not allow window resize
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		// Create and add start game button
		JButton playButton = new JButton("Start Game");
		playButton.setBounds(140, 460, 140, 40);
		this.getContentPane().add(playButton);

		// Create and add information image
		JLabel instructionLabel = new JLabel();
		instructionLabel.setBounds(400, 425, 380, 180);
		ImageIcon instructionImage = new ImageIcon(returnImage(instructionImagePath,instructionLabel));// info image path
		instructionLabel.setIcon(instructionImage);
		instructionLabel.setHorizontalAlignment(JLabel.CENTER);
		this.getContentPane().add(instructionLabel);

		// Create and add start menu background
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setBounds(0, -20, 800, 650);
		ImageIcon backgroundImage = new ImageIcon(returnImage(backgroundImagePath, backgroundLabel));
		backgroundLabel.setIcon(backgroundImage);
		backgroundLabel.setHorizontalAlignment(JLabel.CENTER);
		this.getContentPane().add(backgroundLabel);

		/**
		 * Create action listener for Play button to start game and create gameFrame when pressed
		 */
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new gameFrame();
			}
		});
		setVisible(true);
	}


	/**
	 * Function to get the scaled image to fit the full size of the Label being passed to make use of the entire size of the 
	 * Label
	 * 
	 * @param path the given string path to image that is to be convereted to bufferedImage
	 * @param label the label that the image is supposed to fill
	 * @return the Image specified scaled to the label size
	 */
	private Image returnImage(String path, JLabel label){
		BufferedImage img = null;
		try {
    		img = ImageIO.read(getClass().getResourceAsStream(path)); //Create Buffered Image from given path to Image
		}catch (IOException e){
    			e.printStackTrace();
		}
		Image backgroundImage = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH); //size the buffered image to size of area of label
		return backgroundImage;
	}

}