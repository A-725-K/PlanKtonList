/*
  License: GPL-3.0
  Author: Andrea Canepa (A_725_K) <andrea.canepa.12@gmail.com>
*/

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class MiniFileChooserButtonListener implements ActionListener {
	private final JFileChooser fileChooser;
	private String urlPhoto;
	private final ActionListener al;
	
	public MiniFileChooserButtonListener(JFileChooser fc, String u, SubmitNewElementListener s) {
		this.fileChooser = fc;
		this.urlPhoto = u;
		this.al = s;
	}
	
	public MiniFileChooserButtonListener(JFileChooser fc, String u, SubmitModifyElementLister s) {
		this.fileChooser = fc;
		this.urlPhoto = u;
		this.al = s;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (fileChooser.showOpenDialog(new JFrame()) != JFileChooser.APPROVE_OPTION)
			return;
		this.urlPhoto = fileChooser.getSelectedFile().getAbsolutePath();
		notifyList(al);
	}

	private void notifyList(ActionListener al) {
		if (al instanceof SubmitNewElementListener)
			((SubmitNewElementListener)al).setUrl(urlPhoto);
		else if (al instanceof SubmitModifyElementLister)
			((SubmitModifyElementLister)al).setUrl(urlPhoto);
	}
}
