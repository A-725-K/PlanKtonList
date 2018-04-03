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

import data_structure.TestTube;

public class SubmitNewTestTubeListener implements ActionListener {
	private final Choice dd;
	private final Choice mm;
	private final Choice yy;
	
	private final JFrame window;
	private final JTextField textField;
	
	private final DefaultTableModel model;
	private final List<TestTube> data;
	
	public SubmitNewTestTubeListener(Choice d, Choice m, Choice y, JTextField f, JFrame w, DefaultTableModel mo, List<TestTube> l) {
		this.dd = d;
		this.mm = m;
		this.yy = y;
		this.textField = f;
		this.window = w;
		this.model = mo;
		this.data = l;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = textField.getText();
		Pattern pt = Pattern.compile("[0-9]{3}[A-Za-z]{5}[0-9]-[0-9]{6}[A-Za-z]{2}");
		Matcher mt = pt.matcher(s);
		int day, month, year;
		
		if (s == null || s.equals("") || !mt.matches()) {
			JOptionPane.showMessageDialog(null, "Code of the test tube not valid", "Error code", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (dd.getSelectedItem().equals("") || mm.getSelectedItem().equals("") || yy.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Please fill all fields of date", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		day = Integer.valueOf(dd.getSelectedItem());
		month = Integer.valueOf(mm.getSelectedItem());
		year = Integer.valueOf(yy.getSelectedItem());
		
		if (!validDate(day, month, year)) {
			JOptionPane.showMessageDialog(null, "Not valid date", "Error date", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		TestTube toInsert = new TestTube(s, String.format("%d-%d-%d", day, month, year));
		if (data.contains(toInsert)) {
			JOptionPane.showMessageDialog(null, "This test tube already exists", "Duplicate test tube", JOptionPane.ERROR_MESSAGE);
			return;
		}
			
		model.addRow(new Object[]{s, String.format("%d-%d-%d", day, month, year)});
		data.add(toInsert);
		this.window.dispose(); 
	}
	
	//This function test the validity of the date
	private boolean validDate(int d, int m, int y) {
		/*
		 * "Thirty days has november with april, june and september
		 * of twenty-eight there's only one
		 * the others all, they've got thirty-one"
		 */
		switch(m) {
			case 4:
			case 6: 
			case 9:
			case 11:
				if (d > 30)
					return false;
				return true;
			case 2:
				if (d > 29)	
					return false;
				if (!isBissextile(y) && d == 29)
					return false;
				return true;
			default:
				return true;
		}
	}
	
	//This function test if a year is bissextile
	private boolean isBissextile(int y) {
		if (y % 4 != 0)
			return false;
		
		if (y % 100 == 0 && y % 400 != 0)
			return false;
		
		return true;
	}
}
