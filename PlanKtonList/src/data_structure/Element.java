/*
  License: GPL-3.0
  Author: Andrea Canepa (A_725_K) <andrea.canepa.12@gmail.com>
*/

package data_structure;

public class Element {
	private String name;
	private int quantity;
	private String pathToPhoto;
		
	public Element(String n, int q) {
		this.name = n;
		this.quantity = q;
	}
	
	public Element(String n, int q, String p) {
		this.name = n;
		this.quantity = q;
		this.pathToPhoto = p;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setPathToPhoto(String pathToPhoto) {
		this.pathToPhoto = pathToPhoto;
	}
	
	public String getPathToPhoto() {
		return pathToPhoto;
	}
}
