/*
  License: GPL-3.0
  Author: Andrea Canepa (A_725_K) <andrea.canepa.12@gmail.com>
*/

package gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import data_structure.Element;
import utils.ButtonElem;

public class TestTubeContentWindow {
	private final JFrame window;
	private final Container cont;
	
	private final JScrollPane tableScrollPane;
	private final JTable table;
	private final DefaultTableModel model;
	
	private final JButton addButton;
	private final JButton removeButton;
	private final JButton modifyButton;
	
	private final List<Element> elems;
	
	public TestTubeContentWindow(List<Element> e, String name) {		
		this.elems = e;
		
		this.window = new JFrame(name);
		this.cont = window.getContentPane();
		
		this.model = new DefaultTableModel(0, 2) {
			private static final long serialVersionUID = -8380050186216067784L;
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.setColumnIdentifiers(new String[]{"Element", "Quantity"});
		this.table = new JTable(model);
		this.tableScrollPane = new JScrollPane(table);
		
		this.addButton = new JButton();
		this.removeButton = new JButton();
		this.modifyButton = new JButton();
		
		initializeComponents();
	}
	
	private void initializeComponents() {
		/* Timing for tool tips */
		ToolTipManager.sharedInstance().setInitialDelay(1000);
        ToolTipManager.sharedInstance().setDismissDelay(3000);
		
		/* Buttons */
		try {
			this.addButton.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResourceAsStream("img/plus.png"))));
			this.removeButton.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResourceAsStream("img/minus.png"))));
			this.modifyButton.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResourceAsStream("img/modify.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.addButton.setToolTipText("Add new element");
		this.addButton.setText("Add new element");
		this.addButton.setFont(new Font("Verdana", Font.BOLD, 13));
		this.addButton.setBounds(15, 150, 225, 40);
		this.addButton.addActionListener(new ElementWindowButtonsListener(ButtonElem.ADD, model, elems));
		this.cont.add(addButton);
		
		this.removeButton.setToolTipText("Remove an element");
		this.removeButton.setText("Remove an element");
		this.removeButton.setFont(new Font("Verdana", Font.BOLD, 13));
		this.removeButton.setBounds(15, 200, 225, 40);
		this.removeButton.addActionListener(new ElementWindowButtonsListener(ButtonElem.DEL, model, elems));
		this.cont.add(removeButton);
		
		this.modifyButton.setToolTipText("Modify an element");
		this.modifyButton.setText("Modify an element");
		this.modifyButton.setFont(new Font("Verdana", Font.BOLD, 13));
		this.modifyButton.setBounds(15, 250, 225, 40);
		this.modifyButton.addActionListener(new ElementWindowButtonsListener(ButtonElem.MOD, model, elems));
		this.cont.add(modifyButton);
		
		/* Table */
		this.tableScrollPane.setBounds(265, 30, 400, 400);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		this.table.getColumnModel().getColumn(0).setMaxWidth(300);
		this.table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		this.table.getColumnModel().getColumn(1).setMaxWidth(100);
		this.table.addMouseListener(new ElementsTableListener(elems));
		initializeTable();
		this.cont.add(tableScrollPane);
		
		/* Window */
		this.window.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - 350, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - 250);
		this.window.setResizable(false);
		this.window.setLayout(null);
		this.window.setSize(700, 500);
		this.window.setVisible(true);
	}

	//This function add data to the table
	private void initializeTable() {
		for(Element e : elems) 
			model.addRow(new Object[]{e.getName(), e.getQuantity()});
		model.fireTableDataChanged();
	}
}
