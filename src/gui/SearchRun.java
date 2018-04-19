/*
  License: GPL-3.0
  Author: Andrea Canepa (A_725_K) <andrea.canepa.12@gmail.com>
*/

package gui;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import data_structure.Element;
import data_structure.TestTube;

public class SearchRun implements Runnable {
	private final List<TestTube> data;
	
	public SearchRun(List<TestTube> d) {
		this.data = d;
	}
	
	@Override
	public void run() {
		String toFind;
		int howMany = 0;
		List<TestTube> result = new LinkedList<>();
		if ((toFind = JOptionPane.showInputDialog(null, "Insert the element that are you looking for")) == null)
			return;
	
		howMany = findMatches(result, toFind);
		
		if (result.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No element found", "Search", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(null, "Founded " + howMany + " matches", "Search", JOptionPane.PLAIN_MESSAGE);
		new ShowResultOfSearchWindow(result);
	}

	//This function find matches and returns how many it finds out
	private int findMatches(List<TestTube> list, String s) {
		int count = 0;
		for(TestTube tt : data) {
			for (Element e : tt.getContent())
				if (e.getName().toLowerCase().equals(s.toLowerCase())) {
					count++;
					list.add(tt);
					break;
				}
		}
		return count;
	}
}
