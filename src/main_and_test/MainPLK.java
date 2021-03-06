/*
  License: GPL-3.0
  Author: Andrea Canepa (A_725_K) <andrea.canepa.12@gmail.com>
*/

package main_and_test;

import javax.swing.SwingUtilities;
import gui.Program_Gui;

public class MainPLK {
	public static void main(String[] args) {
		//Start the program and the GUI
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Program_Gui();
			}
		});
	}
}
