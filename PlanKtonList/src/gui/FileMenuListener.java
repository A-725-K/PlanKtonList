/*
  License: GPL-3.0
  Author: Andrea Canepa (A_725_K) <andrea.canepa.12@gmail.com>
*/

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import data_structure.Element;
import data_structure.TestTube;
import utils.FileElem;

public class FileMenuListener implements ActionListener {
	private final FileElem type;
	private static File workingFile;
	private Runnable setTableVisible;
	private JFileChooser fileChooser;
	private static DefaultTableModel model;
	private static List<TestTube> data;
	
	public FileMenuListener(FileElem t) {
		this.type = t;
	}
	
	public FileMenuListener(FileElem t, Runnable r) {
		this.type = t;
		this.setTableVisible = r;
	}
	
	public FileMenuListener(FileElem t, JFileChooser jfc, DefaultTableModel dtm, Runnable r, List<TestTube> l) {
		this.type = t;
		this.fileChooser = jfc;
		this.setTableVisible = r;
		model = dtm;
		data = l;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(type) {
			case NEW:				
				handleNew();
				break;
			case SAVE:
				handleSave();
				break;
			case OPEN:
				handleOpen();
				break;
			case EXPORT:
				handleExport();
				break;
			case EXIT:
				System.exit(0);
		}
	}
	
	//This function handle the export choice in file menu
	private void handleExport() {
		if (data.isEmpty())
			return;
		
		try (BufferedWriter buf = new BufferedWriter(new FileWriter("export.txt"))) {
			buf.write("Date:\t" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) + "\n\n");
			int i = 1;
			for(TestTube tt : data) {
				buf.write("Test tube (" + i++ + "):\n");
				buf.write("\tCode:\t" + tt.getCode() + "\n");
				buf.write("\tDate:\t" + tt.getDate() + "\n");
				
				if (!tt.getContent().isEmpty()) {
					buf.write("\tContent:\n");
					for(Element e : tt.getContent())
						buf.write("\t\t- " + e.getName() + "\t\tX " + e.getQuantity() + "\n");
				}
					
				buf.write('\n');
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(null, "The file has been exported successfully", "Export successful", JOptionPane.INFORMATION_MESSAGE);
	}

	//This function handle the save choice in file menu
	private void handleSave() {
		if (workingFile == null)
			return;
		
		/* Writing on file */
		StringBuilder sb = new StringBuilder();
		try (BufferedWriter buf = new BufferedWriter(new FileWriter(workingFile))){
			for(TestTube tt : data) {
				sb.append("# " + tt.getCode() + " " + tt.getDate() + "\n");
				for(Element e : tt.getContent())
					sb.append(e.getName() + " " + e.getQuantity() + " " + ((e.getPathToPhoto() == null) ? "" : e.getPathToPhoto()) + "\n");
			}
			buf.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(null, "File saved successfully", "Save", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//This function handle the open choice in file menu
	private void handleOpen() {	
		clearData();
		/* Getting the file to open */
		if (fileChooser.showOpenDialog(new JFrame()) != JFileChooser.APPROVE_OPTION)
			return;
		workingFile = fileChooser.getSelectedFile();
		
		/* Reading the file and adding data to the table */
		try (BufferedReader buf = new BufferedReader(new FileReader(workingFile))) {
			String line;
			String[] infoLine;
			TestTube toInsert = null;
			do {
				line = buf.readLine();
				if (line == null)
					break;
				infoLine = line.split(" ");
				if (line.startsWith("#")) {
					toInsert = new TestTube(infoLine[1], infoLine[2]);
					model.addRow(new Object[]{infoLine[1], infoLine[2]});
					data.add(toInsert);
				} else {
					if (infoLine.length == 2) {
						toInsert.addElem(new Element(infoLine[0], Integer.valueOf(infoLine[1])));
					} else if (infoLine.length == 3) {
						toInsert.addElem(new Element(infoLine[0], Integer.valueOf(infoLine[1]), infoLine[2]));
					} else {
						throw new IOException();
					}
				}
			} while (true);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "This file doesn't exists", "Error open file", JOptionPane.ERROR_MESSAGE);
			return;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "The file is not in the correct format", "Bad file", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		/* Printing data to table */
		model.fireTableDataChanged();
		this.setTableVisible.run();
		
		/* *************************************\
		|* *************** DEBUG ***************|
		\* *************************************/
		/*
		 for(TestTube tt : data) {
			System.out.println("+ " + tt.getCode() + " " + tt.getDate());
			for(Element e : tt.getContent())
				System.out.println("\t- " + e.getName() + " " + e.getQuantity() + " " + e.getPathToPhoto());
		}*/
	}

	//This function handle the new choice in file menu
	private void handleNew() {	
		clearData();
		Pattern pt = Pattern.compile("[A-Za-z][A-Za-z0-9]*.zoop");
		Matcher mt;
		String fileName;
		
		/* Getting the new file name */
		do {
			fileName = JOptionPane.showInputDialog(null, "Insert the name of the new file, give it the extension \".zoop\"", "New File", JOptionPane.QUESTION_MESSAGE);
			if (fileName == null)
				return;
			mt = pt.matcher(fileName);
			if (mt.matches())
				break;
			else
				JOptionPane.showMessageDialog(null, "The file name must end with \".zoop\" extension and must not start with a number", "Bad file name", JOptionPane.ERROR_MESSAGE);
		} while (true);
		
		model.fireTableDataChanged();
		
		/* Creating a new file */
		try {
			workingFile = new File(fileName);
			if (workingFile.createNewFile())
				JOptionPane.showMessageDialog(null, "Createad " + fileName, "New file", JOptionPane.PLAIN_MESSAGE);
			else {
				JOptionPane.showMessageDialog(null, "Cannot create a new file", "Error new file", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Cannot create a new file", "Error new file", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		/* Show the table without content */
		this.setTableVisible.run();
	}
	
	//This function clear all the data from table and from memory
	private void clearData() {
		if (workingFile != null) {
			data.clear();
			model.getDataVector().removeAllElements();
		}
	}
}