package edu.elon.contact;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.List;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class WindowMaker {
	//Fields for Main Window 
	private JFrame frame;
	
	private JPanel labelPanel; 
	private JPanel buttonPanel;
	private JPanel inputPanel;
	
	private JTextField firstField;
	private JTextField middleField;
	private JTextField lastField;
	private JTextField emailField;
	private JTextField majorField;
	
	//Fields for Connection Window
	
	private JPanel connectLabelPanel;
	private JPanel connectButtonPanel;
	private JPanel connectInputPanel;
	
	private JTextField userField;
	private JTextField passField;
	private JTextField ipField;
	private JTextField dbField;
	private JTextField tableField;
	
	//Hold Contact and DB objects 
	private ArrayList<Contact> contactBook;
	
	private Contact currentContact;
	private Contact passableContact;
	private int counter;
	
	private UserDB databaseConnect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowMaker window = new WindowMaker();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WindowMaker() {
		initialize();
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		contactBook= new ArrayList<Contact>();
				
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JMenuBar toolBar = new JMenuBar();
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        toolBar.add(fileMenu);
        toolBar.add(editMenu);
		
        JMenuItem clearAction = new JMenuItem("Clear DB");
        JMenuItem connectAction = new JMenuItem("Connect");
        JMenuItem exitAction = new JMenuItem("Exit");
        JMenuItem addAction = new JMenuItem("Add");
        JMenuItem removeAction = new JMenuItem("Remove");
        JMenuItem updateAction = new JMenuItem("Update");
        
        
        //The best idea for the menu items would be 
        //to add the menu item to a dictionary as a key, then
        //have the value be the method that needs to get called
        //then, create a method that acts as an event handler
        //for any JMenuItem
        HashMap<String, String> menuItems = new HashMap<String, String>();
        menuItems.put("clearAction", clearAction.getText());
        menuItems.put("connectAction", connectAction.getText());
        menuItems.put("exitAction", exitAction.getText());
        menuItems.put("addAction", addAction.getText());
        menuItems.put("removeAction", removeAction.getText());
        menuItems.put("updateAction", updateAction.getText());
        
        Set keys = menuItems.keySet();
        for(Iterator i = keys.iterator(); i.hasNext();) {
        		String key = (String) i.next();
        		String value = (String) menuItems.get(key);
        		System.out.println(value);
        }

        
        fileMenu.add(clearAction);
        clearAction.addActionListener(e -> {
			clearContacts();
		});
        fileMenu.add(connectAction);
        connectAction.addActionListener(e -> {
			makeConnection();
		});
        
        fileMenu.add(exitAction);
        exitAction.addActionListener(e -> {
			frame.dispose();
		});
        
        editMenu.add(addAction);
        addAction.addActionListener(e -> {
        		addContact();
		});
        
        editMenu.add(removeAction);
        removeAction.addActionListener(e -> {
			removeContact();
		});
        
        editMenu.add(updateAction);
        updateAction.addActionListener(e -> {
        		dbError();
		});
        
        buttonPanel=new JPanel();
		frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton prevButton = new JButton("Previous");
		buttonPanel.add(prevButton);
		prevButton.addActionListener(e -> {
			previousButtonClicked();
		});
		
		JButton nextButton = new JButton("Next");
		buttonPanel.add(nextButton);
		nextButton.addActionListener(e -> {
			nextButtonClicked();
		});
		
		labelPanel= new JPanel();
		labelPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.getContentPane().add(labelPanel, BorderLayout.WEST);
		labelPanel.setLayout(new GridLayout(5, 1, 0, 0));
		
		
		JLabel firstLabel = new JLabel("First Name");
		firstLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPanel.add(firstLabel);
		
		JLabel middleLabel = new JLabel("Middle Name");
		middleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPanel.add(middleLabel);
		
		JLabel lastLabel = new JLabel("Last Name");
		lastLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPanel.add(lastLabel);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPanel.add(emailLabel);
		
		JLabel majorLabel = new JLabel("Major");
		majorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPanel.add(majorLabel);
		
		inputPanel=new JPanel();
		inputPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.getContentPane().add(inputPanel, BorderLayout.CENTER);
		inputPanel.setLayout(new GridLayout(5, 1, 0, 0));
		
		firstField = new JTextField();
		inputPanel.add(firstField);
		firstField.setColumns(10);
		
		middleField = new JTextField();
		inputPanel.add(middleField);
		middleField.setColumns(10);
		
		lastField = new JTextField();
		inputPanel.add(lastField);
		lastField.setColumns(10);
		
		emailField = new JTextField();
		inputPanel.add(emailField);
		emailField.setColumns(10);
		
		majorField = new JTextField();
		inputPanel.add(majorField);
		majorField.setColumns(10);
	}
	
	private void initializeConnectionFrame() {
		
		buttonPanel.setVisible(false);
		labelPanel.setVisible(false);
		inputPanel.setVisible(false);
		
		connectButtonPanel=new JPanel();
		
		frame.getContentPane().add(connectButtonPanel, BorderLayout.SOUTH);
		connectButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton okayButton = new JButton("OK");
		okayButton.addActionListener(e -> {
			okayConnect();
		});
		
		connectButtonPanel.add(okayButton);
		
		connectLabelPanel=new JPanel();
		connectLabelPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		frame.getContentPane().add(connectLabelPanel, BorderLayout.WEST);
		connectLabelPanel.setLayout(new GridLayout(5, 1, 0, 0));
		
		JLabel firstLabel = new JLabel("User Name");
		firstLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		connectLabelPanel.add(firstLabel);
		
		JLabel middleLabel = new JLabel("Password");
		middleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		connectLabelPanel.add(middleLabel);
		
		JLabel lastLabel = new JLabel("IP Address");
		lastLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		connectLabelPanel.add(lastLabel);
		
		JLabel emailLabel = new JLabel("Database Name");
		emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		connectLabelPanel.add(emailLabel);
		
		JLabel majorLabel = new JLabel("Table Name");
		majorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		connectLabelPanel.add(majorLabel);
		
		connectInputPanel=new JPanel();
		connectInputPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		frame.getContentPane().add(connectInputPanel, BorderLayout.CENTER);
		connectInputPanel.setLayout(new GridLayout(5, 1, 0, 0));
		
		userField = new JTextField();
		connectInputPanel.add(userField);
		userField.setColumns(10);
		
		passField = new JTextField();
		connectInputPanel.add(passField);
		passField.setColumns(10);
		
		ipField = new JTextField();
		connectInputPanel.add(ipField);
		ipField.setColumns(10);
		
		dbField = new JTextField();
		connectInputPanel.add(dbField);
		dbField.setColumns(10);
		
		tableField = new JTextField();
		connectInputPanel.add(tableField);
		tableField.setColumns(10);
	}
	
	public void makeConnection() {
		initializeConnectionFrame();
	}
	
	
	public void okayConnect() {
		databaseConnect = new UserDB(userField.getText(), passField.getText(), ipField.getText(),
				dbField.getText(), tableField.getText());
		
		connectButtonPanel.setVisible(false);
		connectLabelPanel.setVisible(false);
		connectInputPanel.setVisible(false);
		
		buttonPanel.setVisible(true);
		labelPanel.setVisible(true);
		inputPanel.setVisible(true);
	}
	public void displayContact(Contact contact) {
		firstField.setText(contact.getFirstName());
		middleField.setText(contact.getMiddleName());
		lastField.setText(contact.getLastName());
		emailField.setText(contact.getEmail());
		majorField.setText(contact.getMajor());
	}
	
	public void addContact() {
		if(contactBook==null) {
			contactBook= new ArrayList<Contact>();
		}
		Contact current = new Contact(firstField.getText(), middleField.getText(), lastField.getText(), 
				emailField.getText(), majorField.getText());
		contactBook.add(current);
		counter=0;
		currentContact=contactBook.get(0);
		displayContact(currentContact);
	}
	
	public void updateContact() {
		for(int i=0; i<contactBook.size();i++) {
			System.out.println(contactBook.get(i).getEmail());
		}
	}
	
	public void previousButtonClicked(){
		//DISPLAY NOTHING IF NO ELEMENTS
		if(contactBook.size()==0) {
			clearDisplay();
		}
		else {
		//Go to end if at first element
			if(counter==0){
		counter=contactBook.size()-1;
		}
		//Otherwise just go back;
		else{
		counter--;
		}
		System.out.println(counter);
		currentContact= contactBook.get(counter);
		displayContact(currentContact);
		}
	}


	public void nextButtonClicked(){
		if(contactBook.size()==0) {
			clearDisplay();
		}
		else {
		if(counter==contactBook.size()-1){
		counter=0;
		}
		else{
		counter++;
		}
		System.out.println(counter);
		currentContact= contactBook.get(counter);
		displayContact(currentContact);
		}
	}
	
	public void removeContact() {
		contactBook.remove(counter);
		counter=0;
		if(contactBook.size()!=0) {
			displayContact(contactBook.get(0));
		}
		else {
			clearDisplay();
		}
	}
	
	public void clearContacts() {
		counter=0;
		contactBook=new ArrayList<Contact>();
		clearDisplay();
	}
	
	public void dbError() {
		JOptionPane.showMessageDialog(new JFrame(), "You did not correctly specify db paramaters", "DB Settings",
			        JOptionPane.ERROR_MESSAGE);
	}
	
	public void clearDisplay() {
		firstField.setText("");
		middleField.setText("");
		lastField.setText("");
		emailField.setText("");
		majorField.setText("");
	}
	
		
}
