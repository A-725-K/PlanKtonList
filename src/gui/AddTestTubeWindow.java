/*
  License: GPL-3.0
  Author: Andrea Canepa (A_725_K) <andrea.canepa.12@gmail.com>
*/

package gui;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableModel;

import data_structure.TestTube;

public class AddTestTubeWindow {
	private final JFrame window;
	private final Container cont;
	private final JLabel nameLabel;
	private final JLabel dateLabel;
	private final JTextField nameField;
	private final Choice ddChoice;
	private final Choice mmChoice;
	private final Choice yyChoice;
	
	private final JButton submitButton;
	
	private final DefaultTableModel model;
	private final List<TestTube> data;
	
	public AddTestTubeWindow(DefaultTableModel m, List<TestTube> l) {
		this.window = new JFrame("Add a test tube");
		this.cont = window.getContentPane();
		
		this.nameLabel = new JLabel("Code:");
		this.dateLabel = new JLabel("Date:");
		
		this.nameField = new JTextField();
		
		this.submitButton = new JButton("Insert");
		
		this.ddChoice = new Choice();
		this.mmChoice = new Choice();
		this.yyChoice = new Choice();
		
		this.model = m;
		this.data = l;
		
		initializeComponents();
	}

	private void initializeComponents() {
		/* Timing for tool tips */
		ToolTipManager.sharedInstance().setInitialDelay(1000);
        ToolTipManager.sharedInstance().setDismissDelay(15000);
		
		/* Button */
		this.submitButton.setBounds(125, 180, 150, 30);
		this.submitButton.setFont(new Font("Verdana", Font.BOLD, 16));
		this.submitButton.setBackground(Color.RED);
		this.submitButton.setForeground(Color.WHITE);
		this.submitButton.addActionListener(new SubmitNewTestTubeListener(ddChoice, mmChoice, yyChoice, nameField, window, model, data));
		this.cont.add(submitButton);
		
		/* Date Choices */
		this.ddChoice.add("");
		this.mmChoice.add("");
		this.yyChoice.add("");
		for (int i = 1, j = 1, k = 20; i < 32; i++, j++, k--) {
			this.ddChoice.add(String.valueOf(i));
			if (j < 13)
				this.mmChoice.add(String.valueOf(j));
			if (k > 0)
				this.yyChoice.add(String.valueOf(2000 + k));
		}
		this.ddChoice.setBounds(100, 90, 60, 25);
		this.mmChoice.setBounds(200, 90, 60, 25);
		this.yyChoice.setBounds(300, 90, 80, 25);
		this.cont.add(ddChoice);
		this.cont.add(mmChoice);
		this.cont.add(yyChoice);
			
		/* Labels */
		this.nameLabel.setBounds(20, 30, 50, 25);
		this.dateLabel.setBounds(20, 90, 50, 25);
		this.cont.add(nameLabel);
		this.cont.add(dateLabel);
		
		/* Text field */
		this.nameField.setToolTipText("The test tube code format is: 123ABCDE4-567890FG");
		this.nameField.setBounds(80, 30, 300, 25);
		this.cont.add(nameField);
		
		/* Frame */
		this.window.setSize(400, 250);
		this.window.setLocationRelativeTo(null);
		this.window.setLayout(null);
		this.window.setResizable(false);
		this.window.setVisible(true);
	}
}
