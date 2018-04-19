/*
  License: GPL-3.0
  Author: Andrea Canepa (A_725_K) <andrea.canepa.12@gmail.com>
*/

package gui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ShowImageWindow {
	private final JFrame window;
	
	private final String photoURL;
	
	public ShowImageWindow(String url) {
		this.photoURL = url;
		this.window = new JFrame("Photo");
		
		initializeComponents();
	}

	private void initializeComponents() {
		/* Photo */
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(photoURL));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Cannot load the photo", "Error photo", JOptionPane.ERROR_MESSAGE);
			this.window.dispose();
			return;
		}
		Image dimg = img.getScaledInstance(425, 425, Image.SCALE_SMOOTH);
		ImageIcon backgroundImage = new ImageIcon(dimg);
		this.window.setContentPane(new JLabel(backgroundImage));	

		/* Window */
		this.window.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - 150, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - 150);
		this.window.setResizable(false);
		this.window.setLayout(null);
		this.window.setSize(300, 300);
		this.window.setVisible(true);
	}
}
