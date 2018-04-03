/*
  License: GPL-3.0
  Author: Andrea Canepa (A_725_K) <andrea.canepa.12@gmail.com>
*/

package gui;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import data_structure.Element;

public class SubmitNewElementListener implements ActionListener {
	private final JFrame window;
	private final JTextField textField;
	private final Choice quantity;
	private String urlPhoto;
	
	private final DefaultTableModel model;
	private final List<Element> data;
	
	public SubmitNewElementListener(JFrame w, JTextField f, Choice q, DefaultTableModel d, List<Element> l) {
		this.window = w;
		this.textField = f;
		this.quantity = q;
		this.model = d;
		this.data = l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = textField.getText();
		Pattern pt = Pattern.compile("[a-zA-Z][a-zA-Z0-9_\s]*");
		Matcher mt = pt.matcher(s);
		int qty;
		
		if (s == null || s.equals("") || !mt.matches()) {
			JOptionPane.showMessageDialog(null, "Name of the element not valid", "Error element", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (quantity.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Please choose a quantity", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		qty = Integer.valueOf(quantity.getSelectedItem());
		
		Element toInsert = new Element(s, qty);
		if (data.contains(toInsert)) {
			JOptionPane.showMessageDialog(null, "This element already present", "Duplicate element", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (urlPhoto != null)
			toInsert.setPathToPhoto(urlPhoto);
		
		model.addRow(new Object[]{s, qty});
		data.add(toInsert);
		this.window.dispose();
	}
	
	public void setUrl(String u) {
		this.urlPhoto = u;
	}
}
