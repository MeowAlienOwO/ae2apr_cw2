// 6511043 zy11043 Zhang Huayan
//                              -*- Mode: Java -*- 
// GUI.java --- 
// Filename: GUI.java
// Description: 
// Author: Zhang Huayan
// ID number: 6511043
// E-mail: zy11043@nottingham.edu.cn / MeowAlienOwO@gmail.com
// Version: 
// 

// Commentary: 
// 
// 

// Change Log:
// Status: 
// Table of Contents: 
// 
//     Update #: 83
// 

// Code:

package stock;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

class GUI{
    // variables
    private static GUI gui = null;
    private JFrame frame;
    private JPanel panel;
    private Datapool datapool;

    // left side:
    private JComponent left;
    private JButton login;
    private JButton logout;
    private JButton ask;
    private JButton bid;

    // top-right:
    private JComponent topRight;
    private JTextArea tickmsg;
    private JLabel ticketlabel;
    
    // bottom-right:
    private JComponent bottomRight;
    private TitledBorder bookBorder;
    private JTable book;
    private JButton refresh;
    private JButton cancel;
    
    // constructor
    private GUI(){
    
	this.frame = new JFrame("GUI Client");
	this.panel = new JPanel(new BorderLayout());
	// this.datapool;
	frame.setSize(800, 600);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// left side:
	this.left   = new JPanel();
	this.login  = new JButton("Log in");
	this.logout = new JButton("Log out");
	this.ask    = new JButton("ASK");
	this.bid    = new JButton("BID");
	// left.setSize(200, 200);
	left.add(login);
	left.add(logout);
	left.add(ask);
	left.add(bid);
	// top-right:
	this.topRight    = new JPanel();
	this.tickmsg     = new JTextArea(5, 10);
	this.ticketlabel = new JLabel("Stock Ticker");
	// topRight.setSize(200, 200);
	topRight.add(tickmsg);
	tickmsg.setSize(200, 200);
	tickmsg.setVisible(true);
	topRight.add(ticketlabel);
	// bottom-right:
	String[] columnNames = {"ID", "Type", "Company", "Volume", "Price"};


	this.bottomRight = new JPanel();
	this.bookBorder      = new TitledBorder("Your Offer Book");
	// this.book        = new JTable(null, columnNames);
	this.book        = new JTable(3, 5);
	this.refresh     = new JButton("Refresh");
	this.cancel      = new JButton("Cancel...");
    	// bottomRight.add(border);
	bottomRight.setBorder(bookBorder);
	bottomRight.add(book);
	bottomRight.add(refresh);
	bottomRight.add(cancel);
	
	panel.add("West", left);
	panel.add("North", topRight);
	panel.add("South", bottomRight);
	
    }
    // setter

    // getter
    public static GUI getGUI(){
	if(GUI.gui == null){
	    GUI.gui = new GUI();
	}
	
	return GUI.gui;
    }
    // methods

    public void update(){

	frame.add(panel);
	// frame.pack();
	frame.setVisible(true);
	
    }

}

// 
// GUI.java ends here
