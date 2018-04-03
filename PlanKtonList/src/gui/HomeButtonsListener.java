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

import data_structure.TestTube;
import utils.ButtonElem;

public class HomeButtonsListener implements ActionListener {
	private final ButtonElem type;
	private final DefaultTableModel model;
	private final List<TestTube> data;
	
	public HomeButtonsListener(ButtonElem t, DefaultTableModel m, List<TestTube> l) {
		this.type = t;
		this.model = m;
		this.data = l;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(type) {
			case ADD:
				handleAdd();
				break;
			case DEL:
				handleDel();
				break;
			case SEARCH:
				handleSearch();
				break;
			default:
				break;
		}
	}
	
	//This function give to the user the opportunity to add a new test tube
	private void handleAdd() {	
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new AddTestTubeWindow(model, data);
			}
		});
	}

	//This function give to the user the opportunity to delete a test tube
	private void handleDel() {
		if (data.isEmpty())
			return;
		SwingUtilities.invokeLater(new DeleteTestTubeRun(data, model));
	}
	
	//This function give to the user the opportunity to find all test tubes which contain an element
	private void handleSearch() {
		if (data.isEmpty())
			return;
		SwingUtilities.invokeLater(new SearchRun(data));
	}
}
