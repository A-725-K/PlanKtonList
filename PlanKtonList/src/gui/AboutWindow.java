/*
  License: GPL-3.0
  Author: Andrea Canepa (A_725_K) <andrea.canepa.12@gmail.com>
*/

package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AboutWindow {
	private final JFrame window;
	private final Container cont;
	private final JLabel textLabel;
	private final JLabel labelForLogo;
	
	public AboutWindow() {
		this.window = new JFrame("About");
		this.cont = window.getContentPane();
		
		this.textLabel = new JLabel();
		this.labelForLogo = new JLabel();
		
		initializeComponents();
	}

	private void initializeComponents() {
		/* Image */
		BufferedImage img = null;
		try {
			img = ImageIO.read(ClassLoader.getSystemResourceAsStream("img/logo.jpg"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Cannot load the photo", "Error photo", JOptionPane.ERROR_MESSAGE);
			this.window.dispose();
			return;
		}
		Image dimg = img.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
		ImageIcon aboutImage = new ImageIcon(dimg);
		this.labelForLogo.setBounds(30, 10, 140, 140);
		this.labelForLogo.setIcon(aboutImage);
		this.cont.add(labelForLogo);
		
		/* Label */
		this.textLabel.setHorizontalAlignment(JLabel.LEFT);
		this.textLabel.setVerticalAlignment(JLabel.CENTER);
		this.textLabel.setFont(new Font("Verdana", Font.ITALIC, 10));
		this.textLabel.setText("<html>Copyright (c) Andrea Canepa<br>"
				+ "This program is entirely made by A_725_K (Andrea Canepa) between March and April 2018.<br>"
				+ "The software is free and it was developed thanks to Eclipse IDE in Parrot OS.<br>"
				+ "Latest version: 1.0</html>");
		this.textLabel.setBounds(200, 10, 150, 140);
		this.cont.add(textLabel);
		
		/* Window */
		this.window.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - 150, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - 150);
		this.cont.setBackground(Color.LIGHT_GRAY);
		this.window.setLayout(null);
		this.window.setResizable(false);
		this.window.setSize(400, 200);
		this.window.setVisible(true);
	}
}
