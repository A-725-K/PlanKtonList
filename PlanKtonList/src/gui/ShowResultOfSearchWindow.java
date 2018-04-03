/*
  License: GPL-3.0
  Author: Andrea Canepa (A_725_K) <andrea.canepa.12@gmail.com>
*/

package gui;

import java.awt.Container;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import data_structure.TestTube;

public class ShowResultOfSearchWindow {
	private final JFrame window;
	private final Container cont;
	
	private final List<TestTube> data;
	
	private final JScrollPane tableScrollPane;
	private final JTable table;
	private final DefaultTableModel model;
	
	public ShowResultOfSearchWindow(List<TestTube> d) {
		this.data = d;
		
		this.window = new JFrame("Results");
		this.cont = window.getContentPane();
		
		this.model = new DefaultTableModel(0, 1) {
			private static final long serialVersionUID = -8380050186216067783L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.setColumnIdentifiers(new String[]{"Test Tube"});
		this.table = new JTable(model);
		this.tableScrollPane = new JScrollPane(table);
		
		initializeComponents();
	}
	
	private void initializeComponents() {
		/* Table */
		this.tableScrollPane.setBounds(50, 50, 300, 450);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		this.table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		this.table.addMouseListener(new TestTubeTableListener(data));
		initializeTable();
		this.cont.add(tableScrollPane);
		
		/* Window */
		this.window.setSize(400, 600);
		this.window.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - 200, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - 300);
		this.window.setLayout(null);
		this.window.setResizable(false);
		this.window.setVisible(true);
	}
	
	//This function add data to the table
	private void initializeTable() {
		for(TestTube tt : data) 
			model.addRow(new Object[]{tt.getCode()});
		model.fireTableDataChanged();
	}
}
