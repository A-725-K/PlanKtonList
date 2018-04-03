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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import data_structure.Element;
import utils.ButtonElem;

public class AddAndModifyElementToTestTubeWindow {
	private final JFrame window;
	private final Container cont;
	private final JLabel elementNameLabel;
	private final JLabel quantityLabel;
	private final JLabel photoLabel;
	private final JTextField nameField;
	private final Choice quantityChoice;
	
	private final JButton submitButton;
	private final JButton miniFileChooserButton;	
	
	private final DefaultTableModel model;
	private final List<Element> data;
	private final ButtonElem type;
	
	private final JFileChooser fileChooser;
	
	private String urlPhoto;
	private SubmitNewElementListener snel;
	private SubmitModifyElementLister smel;
	
	
	public AddAndModifyElementToTestTubeWindow(List<Element> d, DefaultTableModel m, ButtonElem e) {
		this.model = m;
		this.data = d;
		this.type = e;
		
		this.fileChooser = new JFileChooser();
		
		this.window = new JFrame();
		this.cont = this.window.getContentPane();
		
		this.elementNameLabel = new JLabel("Element:");
		this.quantityLabel = new JLabel("Quantity:");
		this.photoLabel = new JLabel("Photo:");
		
		this.nameField = new JTextField();
		this.quantityChoice = new Choice();
		
		this.submitButton = new JButton();
		this.miniFileChooserButton = new JButton("...");
		
		initializeComponents();
	}

	private void initializeComponents() {
		/* Timing for tool tips */
		ToolTipManager.sharedInstance().setInitialDelay(1000);
        ToolTipManager.sharedInstance().setDismissDelay(3000);
		
		/* File chooser */
		this.fileChooser.setAcceptAllFileFilterUsed(false);
		this.fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.jpg", "jpg"));
		this.fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.png", "png"));
		
		/* Labels */
		this.elementNameLabel.setBounds(20, 30, 100, 25);
		this.quantityLabel.setBounds(20, 90, 100, 25);
		this.photoLabel.setBounds(230, 90, 70, 25);
		this.cont.add(elementNameLabel);
		this.cont.add(quantityLabel);
		this.cont.add(photoLabel);
		
		/* Text field */
		this.nameField.setBounds(150, 30, 200, 25);
		this.cont.add(nameField);
		
		/* Buttons */		
		this.submitButton.setBounds(125, 180, 150, 30);
		this.submitButton.setFont(new Font("Verdana", Font.BOLD, 16));
		if (this.type == ButtonElem.ADD) {
			this.window.setTitle("Add a new element");
			this.submitButton.setText("Insert");
			this.submitButton.setBackground(Color.RED);
			this.submitButton.setForeground(Color.WHITE);
			this.nameField.setToolTipText("Insert the name of the new element");
			this.snel = new SubmitNewElementListener(window, nameField, quantityChoice, model, data);
			this.submitButton.addActionListener(snel);
			this.miniFileChooserButton.addActionListener(new MiniFileChooserButtonListener(fileChooser, urlPhoto, snel));
		} else if (this.type == ButtonElem.MOD) {
			this.window.setTitle("Modify an element");
			this.submitButton.setText("Modify");
			this.submitButton.setBackground(Color.YELLOW);
			this.submitButton.setForeground(Color.RED);
			this.nameField.setToolTipText("Insert the name of the element you want to modify");
			this.smel = new SubmitModifyElementLister(model, data, window, nameField, quantityChoice);
			this.submitButton.addActionListener(smel);
			this.miniFileChooserButton.addActionListener(new MiniFileChooserButtonListener(fileChooser, urlPhoto, smel));
		}
		this.cont.add(submitButton);
		this.miniFileChooserButton.setBounds(300, 90, 50, 25);
		this.miniFileChooserButton.setFont(new Font("Verdana", Font.BOLD, 16));
		this.cont.add(miniFileChooserButton);
		
		/* Choice */
		this.quantityChoice.add("");
		for(int i = 1; i < 11; i++)
			this.quantityChoice.add(String.valueOf(i));
		this.quantityChoice.setBounds(150, 90, 50, 25);
		this.cont.add(quantityChoice);
		
		/* Window */
		this.window.setSize(400, 250);
		this.window.setLocationRelativeTo(null);
		this.window.setLayout(null);
		this.window.setResizable(false);
		this.window.setVisible(true);
	}
}
