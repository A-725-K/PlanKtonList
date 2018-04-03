/*
  License: GPL-3.0
  Author: Andrea Canepa (A_725_K) <andrea.canepa.12@gmail.com>
*/

package gui;

import java.util.List;
import java.util.ListIterator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import data_structure.TestTube;

public class DeleteTestTubeRun implements Runnable {
	private final List<TestTube> data;
	private final DefaultTableModel model;
	
	public DeleteTestTubeRun(List<TestTube> d, DefaultTableModel m) {
		this.data = d;
		this.model = m;
	}
	
	@Override
	public void run() {
		String toDelete;
		
		do {
			toDelete = JOptionPane.showInputDialog(null, "Enter the code of the test tube you want to delete", "Delete", JOptionPane.WARNING_MESSAGE);
			if (toDelete == null)
				return;
			if (!myContains(data, toDelete)) {
				JOptionPane.showMessageDialog(null, "This test tube does not exists", "Error delete", JOptionPane.ERROR_MESSAGE);
				return;
			}
			myDelete(data, toDelete);
			break;
		} while(true);
	}
		
	private boolean myContains(List<TestTube> list, String s) {
		for (TestTube t : list)
			if (t.getCode().equals(s))
				return true;
		return false;
	}
	
	private void myDelete(List<TestTube> list, String s) {
		TestTube aux = null;
		ListIterator<TestTube> lt = list.listIterator();
		
		while(lt.hasNext()) {
			aux = lt.next();
			if (aux.getCode().equals(s)) {
				lt.remove();
				for(int i = 0; i < model.getRowCount(); i++)
					if (model.getValueAt(i, 0).equals(s)) {
						model.removeRow(i);
						model.fireTableDataChanged();
						break;
					}
				break;
			}
		}
	}
}
