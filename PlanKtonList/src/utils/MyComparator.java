/*
  License: GPL-3.0
  Author: Andrea Canepa (A_725_K) <andrea.canepa.12@gmail.com>
*/

package utils;

import java.util.Comparator;

import data_structure.TestTube;

public class MyComparator implements Comparator<TestTube> {
	private final static int ERROR = -666;
	private final ViewElem type;
	
	public MyComparator(ViewElem t) {
		this.type = t;
	}
	
	@Override
	public int compare(TestTube o1, TestTube o2) {
		switch (this.type) {
			case DATE:
				return o1.dateLessThan(o2);
			case NAME:
				return o1.getCode().compareTo(o2.getCode());
			default:
				return ERROR;
		}
	}
}