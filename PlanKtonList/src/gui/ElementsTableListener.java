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

import data_structure.Element;

public class ElementsTableListener extends MouseAdapter {
	private final List<Element> data; 
	
	public ElementsTableListener(List<Element> l) {
		this.data = l;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int row;
		int col;
		JTable target;
		String url;
		if (e.getClickCount() == 2) {
			target = (JTable)e.getSource();
			col = target.getSelectedColumn();
			
			//if the click is on a code open the content of that test tube
			if (col == 0) {
				row = target.getSelectedRow();
				url = data.get(row).getPathToPhoto();
				
				//if there is no photo return
				if (url == null)
					return;
				
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new ShowImageWindow(url);
					}
				});
			}
		}
	}
}
