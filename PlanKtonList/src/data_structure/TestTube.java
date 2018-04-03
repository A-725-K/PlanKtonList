/*
  License: GPL-3.0
  Author: Andrea Canepa (A_725_K) <andrea.canepa.12@gmail.com>
*/

package data_structure;

import java.util.ArrayList;
import java.util.List;

public class TestTube {
	private String code;
	private String date;
	
	private List<Element> content;
	
	public TestTube(String c, String d) {
		this.code = c;
		this.date = d;
		this.content = new ArrayList<Element>();
	}
	
	//This function compare two dates
	public int dateLessThan(TestTube other) {
		//These arrays has 3 fields: day(0), month(1), year(2)
		String[] date1 = this.date.split("-");
		String[] date2 = other.getDate().split("-");
		
		if (Integer.parseInt(date1[2]) < Integer.parseInt(date2[2]))
			return 1;
		else if (Integer.parseInt(date1[2]) > Integer.parseInt(date2[2]))
			return -1;
		
		if (Integer.parseInt(date1[1]) < Integer.parseInt(date2[1]))
			return 1;
		else if (Integer.parseInt(date1[1]) > Integer.parseInt(date2[1]))
			return -1;
		
		if (Integer.parseInt(date1[0]) < Integer.parseInt(date2[0]))
			return 1;
			
		return 0;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return date;
	}
	
	public void addElem(Element e) {
		this.content.add(e);
	}
	
	public void removeElem(Element e) {
		this.content.remove(e);
	}
	
	public List<Element> getContent() {
		return content;
	}
}
