/*
  License: GPL-3.0
  Author: Andrea Canepa (A_725_K) <andrea.canepa.12@gmail.com>
*/

package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingUtilities;

import data_structure.TestTube;

public class TestTubeTableListener extends MouseAdapter {
	private final List<TestTube> data;
	
	public TestTubeTableListener(List<TestTube> t) {
		this.data = t;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int row;
		int col;
		JTable target;
		if (e.getClickCount() == 2) {
			target = (JTable)e.getSource();
			col = target.getSelectedColumn();
			
			//if the click is on a code open the content of that test tube
			if (col == 0) {
				row = target.getSelectedRow();
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							new TestTubeContentWindow(data.get(row).getContent(), data.get(row).getCode());	
						}
					});
			}
		}
	}
}

