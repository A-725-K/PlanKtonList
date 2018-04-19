/*
  License: GPL-3.0
  Author: Andrea Canepa (A_725_K) <andrea.canepa.12@gmail.com>
*/

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import data_structure.TestTube;
import utils.MyComparator;
import utils.ViewElem;

public class ViewRadioButtonListener implements ActionListener {
	private final ViewElem type;
	private final DefaultTableModel model;
	private final List<TestTube> data;
	
	public ViewRadioButtonListener(ViewElem t, DefaultTableModel m, List<TestTube> d) {
		this.type = t;
		this.model = m;
		this.data = d;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		model.getDataVector().removeAllElements();
		data.sort(new MyComparator(type));
		for(TestTube tt : data)
			model.addRow(new Object[] {tt.getCode(), tt.getDate()});
		model.fireTableDataChanged();
	}
}
