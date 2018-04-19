/*
  License: GPL-3.0
  Author: Andrea Canepa (A_725_K) <andrea.canepa.12@gmail.com>
*/

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import data_structure.Element;
import utils.ButtonElem;

public class ElementWindowButtonsListener implements ActionListener {
	private final ButtonElem type;
	private static DefaultTableModel model;
	private static List<Element> data;
	
	public ElementWindowButtonsListener(ButtonElem be, DefaultTableModel m, List<Element> l) {
		this.type = be;
		model = m;
		data = l;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { 
		switch(type) {
			case ADD:
				handleAdd();
				break;
			case DEL:
				handleDelete();
				break;
			case MOD:
				handleModify();
				break;
			default:
				break;			
		}
	}

	//This function gives the user the opportunity to add an element
	private void handleAdd() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new AddAndModifyElementToTestTubeWindow(data, model, ButtonElem.ADD);
			}
		});
	}

	//This function gives the user the opportunity to delete an element
	private void handleDelete() {
		if (data.isEmpty())
			return;
		SwingUtilities.invokeLater(new DeleteElementRun(data, model));
	}

	//This function gives the user the opportunity to modify an element 
	private void handleModify() {		
		if (data.isEmpty())
			return;
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new AddAndModifyElementToTestTubeWindow(data, model, ButtonElem.MOD);
			}
		});
	}
}
