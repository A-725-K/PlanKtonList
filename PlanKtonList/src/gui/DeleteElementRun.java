/*
  License: GPL-3.0
  Author: Andrea Canepa (A_725_K) <andrea.canepa.12@gmail.com>
*/

package gui;

import java.util.List;
import java.util.ListIterator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import data_structure.Element;

public class DeleteElementRun implements Runnable {
	private final List<Element> data;
	private final DefaultTableModel model;

	public DeleteElementRun(List<Element> d, DefaultTableModel m) {
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

	//This function check if a list of element eventually contains the element to delete
	private boolean myContains(List<Element> list, String s) {
		for (Element t : list)
			if (t.getName().equals(s))
				return true;
		return false;
	}

	//This function actually delete that element
	private void myDelete(List<Element> list, String s) {
		Element aux = null;
		ListIterator<Element> lt = list.listIterator();

		while(lt.hasNext()) {
			aux = lt.next();
			if (aux.getName().equals(s)) {
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
