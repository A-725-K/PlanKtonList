/*
  License: GPL-3.0
  Author: Andrea Canepa (A_725_K) <andrea.canepa.12@gmail.com>
*/

package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import data_structure.TestTube;
import utils.ButtonElem;
import utils.FileElem;
import utils.ViewElem;

public class Program_Gui {
	private final JFrame window;
	private final Container cont;
	
	private final JMenuBar menubar;
	private final JMenu menuFile;
	private final JMenuItem newItem;
	private final JMenuItem saveItem;
	private final JMenuItem openItem;
	private final JMenuItem exportItem;
	private final JMenuItem closeItem;
	private final JMenu menuView;
	private final JRadioButtonMenuItem lexicOrderChoice;
	private final JRadioButtonMenuItem dateOrderChoice;
	private final JMenu menuHelp;
	private final JMenuItem aboutItem;
	
	private final JFileChooser fileChooser;
	
	private final JScrollPane tableScrollPane;
	private final JTable table;
	private final DefaultTableModel model;
	
	private final JButton addButton;
	private final JButton removeButton;
	private final JButton searchButton;
	
	private final Color myBlue = new Color(32, 74, 135);
	
	private final List<TestTube> data;
	
	private Runnable setVisibleTable = new Runnable() {
		@Override
		public void run() {
			tableScrollPane.setVisible(true);
			addButton.setVisible(true);
			removeButton.setVisible(true);
			searchButton.setVisible(true);
			lexicOrderChoice.setEnabled(true);
			dateOrderChoice.setEnabled(true);
		}
	};
	
	public Program_Gui() {        
		this.window = new JFrame("PlanKtonList");
		this.cont = window.getContentPane();
		
		this.menubar = new JMenuBar();
		
		this.menuFile = new JMenu("File");
		this.newItem = new JMenuItem("New");
		this.saveItem = new JMenuItem("Save");
		this.openItem = new JMenuItem("Open");
		this.exportItem = new JMenuItem("Export");
		this.closeItem = new JMenuItem("Close");
		
		this.menuView = new JMenu("View");
		this.lexicOrderChoice = new JRadioButtonMenuItem("Name");
		this.dateOrderChoice = new JRadioButtonMenuItem("Date");
		
		this.menuHelp = new JMenu("Help");
		this.aboutItem = new JMenuItem("About");
		
		this.fileChooser = new JFileChooser();
		
		this.addButton = new JButton();
		this.removeButton = new JButton();
		this.searchButton = new JButton();
		
		this.model = new DefaultTableModel(0, 2) {
			private static final long serialVersionUID = -8380050186216067783L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.setColumnIdentifiers(new String[]{"Test tube code", "Date"});
		this.table = new JTable(model);
		this.tableScrollPane = new JScrollPane(table);
		
		this.data = new ArrayList<>();
		
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
			this.searchButton.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResourceAsStream("img/search.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
        this.addButton.setToolTipText("Add test tube");
        this.addButton.setText("Add test tube");
        this.addButton.setBounds(50, 280, 200, 40);
        this.addButton.setVisible(false);
        this.addButton.setFont(new Font("Verdana", Font.BOLD, 13));
        this.addButton.addActionListener(new HomeButtonsListener(ButtonElem.ADD, model, data));
        this.cont.add(addButton);
        
        this.removeButton.setToolTipText("Remove test tube");
        this.removeButton.setText("Remove test tube");
        this.removeButton.setBounds(50, 330, 200, 40);
        this.removeButton.setVisible(false);
        this.removeButton.setFont(new Font("Verdana", Font.BOLD, 13));
        this.removeButton.addActionListener(new HomeButtonsListener(ButtonElem.DEL, model, data));
        this.cont.add(removeButton);
        
        this.searchButton.setToolTipText("Search for an element in the test tubes");
        this.searchButton.setText("Search ...");
        this.searchButton.setBounds(50, 380, 200, 40);
        this.searchButton.setVisible(false);
        this.searchButton.setFont(new Font("Verdana", Font.BOLD, 13));
        this.searchButton.addActionListener(new HomeButtonsListener(ButtonElem.SEARCH, model, data));
        this.cont.add(searchButton);
        
        /* Table */
        this.tableScrollPane.setBounds(300, 60, 674, 580);
		this.tableScrollPane.setVisible(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		this.table.getColumnModel().getColumn(0).setMaxWidth(506);
		this.table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		this.table.getColumnModel().getColumn(1).setMaxWidth(168);
		this.table.addMouseListener(new TestTubeTableListener(data));
		this.cont.add(tableScrollPane);
        
		/* File menu */
		this.newItem.setForeground(Color.WHITE);
		this.newItem.setBackground(myBlue);
		this.newItem.setToolTipText("Create a new file");
		this.newItem.addActionListener(new FileMenuListener(FileElem.NEW, setVisibleTable));
		this.saveItem.setForeground(Color.WHITE);
		this.saveItem.setBackground(myBlue);
		this.saveItem.setToolTipText("Save the file, if new it creates a new one");
		this.saveItem.addActionListener(new FileMenuListener(FileElem.SAVE));
		this.openItem.setForeground(Color.WHITE);
		this.openItem.setBackground(myBlue);
		this.openItem.setToolTipText("Open an existing file");
		this.openItem.addActionListener(new FileMenuListener(FileElem.OPEN, fileChooser, model, setVisibleTable, data));
		this.exportItem.setForeground(Color.WHITE);
		this.exportItem.setBackground(myBlue);
		this.exportItem.setToolTipText("Export in a txt file");
		this.exportItem.addActionListener(new FileMenuListener(FileElem.EXPORT, fileChooser, model, setVisibleTable, data));
		this.closeItem.setForeground(Color.WHITE);
		this.closeItem.setBackground(myBlue);
		this.closeItem.setToolTipText("Close the program");
		this.closeItem.addActionListener(new FileMenuListener(FileElem.EXIT));
		this.menuFile.add(newItem);
		this.menuFile.add(saveItem);
		this.menuFile.add(openItem);
		this.menuFile.add(exportItem);
		this.menuFile.add(closeItem);
		this.menuFile.setForeground(Color.WHITE);
		this.menubar.add(menuFile);
		
		/* View menu */
		ButtonGroup bg = new ButtonGroup();	//to choice only one between date and name order
		this.menuView.setForeground(Color.WHITE);
		this.lexicOrderChoice.setEnabled(false);
		this.dateOrderChoice.setEnabled(false);
		this.lexicOrderChoice.setToolTipText("Show test tubes in lexicographic order");
		this.dateOrderChoice.setToolTipText("Show test ordered by date");
		this.lexicOrderChoice.setForeground(Color.WHITE);
		this.dateOrderChoice.setForeground(Color.WHITE);
		this.lexicOrderChoice.setBackground(myBlue);
		this.dateOrderChoice.setBackground(myBlue);
		//this.lexicOrderChoice.setSelected(true);
		this.lexicOrderChoice.addActionListener(new ViewRadioButtonListener(ViewElem.NAME, model, data));
		this.dateOrderChoice.addActionListener(new ViewRadioButtonListener(ViewElem.DATE, model, data));
		bg.add(lexicOrderChoice);
		bg.add(dateOrderChoice);
		this.menuView.add(lexicOrderChoice);
		this.menuView.add(dateOrderChoice);
		this.menubar.add(menuView);
		
		/* Help menu */
		this.menuHelp.setForeground(Color.WHITE);
		this.aboutItem.setToolTipText("Show some information about the program");
		this.aboutItem.setForeground(Color.WHITE);
		this.aboutItem.setBackground(myBlue);
		this.aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new AboutWindow();						
					}
				});
			}
		});
		this.menuHelp.add(aboutItem);
		this.menubar.add(menuHelp);
		
		/* Menu bar */
		this.menubar.setBounds(0, 0, 1024, 30);
		this.menubar.setBackground(myBlue);
		this.cont.add(menubar);	
		
		/* File chooser */
		this.fileChooser.setAcceptAllFileFilterUsed(false);
		//this.fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt")); //optionally
		this.fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.zoop", "zoop")); //my extension
		
		/* window */
		this.window.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("img/icon.png")));
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.window.setSize(1024, 700);
		this.window.setLocationRelativeTo(null);
		this.window.setLayout(null);
		this.window.setResizable(false);
		this.window.setVisible(true);
	}
}
