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

public class SubmitModifyElementLister implements ActionListener {
	private final JFrame window;
	private final JTextField textField;
	private final Choice quantity;
	private String urlPhoto;
	
	private final DefaultTableModel model;
	private final List<Element> data;
	
	public SubmitModifyElementLister(DefaultTableModel m, List<Element> l, JFrame w, JTextField nf, Choice q) {
		this.model = m;
		this.data = l;
		this.window = w;
		this.textField = nf;
		this.quantity = q;
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
		
		Element toModify = new Element(s, qty);
		if (!myContains(s)) {
			JOptionPane.showMessageDialog(null, "This element doesn't exists", "Inexistent element", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (urlPhoto != null) 
			toModify.setPathToPhoto(urlPhoto);		
		
		int index = myGet(s);
		model.removeRow(index);
		model.addRow(new Object[]{s, qty});
		data.remove(index);
		data.add(toModify);
		this.window.dispose();
	}
	
	public void setUrl(String u) {
		this.urlPhoto = u;
	}
	
	//To check if I modify an existing element 
	private boolean myContains(String s) {
		for(Element e : data)
			if (e.getName().equals(s))
				return true;
		return false;
	}
	
	//Return the index of the element in data and also table
	private int myGet(String s) {
		int i = 0;
		for(Element e : data) {
			if (e.getName().equals(s))
				return i;
			i++;
		}
		return -1;
	}
}