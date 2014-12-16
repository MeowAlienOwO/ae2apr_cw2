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
//     Update #: 164
// 

// Code:

package stock;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
class GUI{
    // variables
    private static GUI gui = null;
    private JFrame frame;
    private JPanel panel;
    private Datapool datapool;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    // left side:
    private JPanel left;
    private JPanel account;
    private JButton login;
    private JButton logout;
    private JPanel trade;
    private JButton ask;
    private JButton bid;


    // right:
    private JPanel right;
    // top-right:
    private JPanel topRight;
    private JScrollPane tickScrollPane;
    private JTextArea tickmsg;
    private TitledBorder tickborder;
    
    // bottom-right:
    private JPanel bottomRight;
    private TitledBorder bookBorder;
    private JTable book;
    private JPanel operation;
    private JButton refresh;
    private JButton cancel;
    
    // constructor
    private GUI(){
    
	this.frame = new JFrame("GUI Client");
	this.panel = new JPanel(new BorderLayout());
	// this.datapool;


	frame.setSize(GUI.WIDTH, GUI.HEIGHT);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// left side:
	this.left   = new JPanel(new BorderLayout(5, 0));
	this.account = new JPanel(new BorderLayout());
	this.login  = new JButton("Log in");
	this.logout = new JButton("Log out");
	this.trade  = new JPanel(new GridLayout(2, 1));
	this.ask    = new JButton("ASK");
	this.bid    = new JButton("BID");
	
	account.add("Center", login);
	account.add("South", logout);
	trade.add(ask);
	trade.add(bid);
	left.add("North", account);
	left.add("Center", trade);
	left.setSize(200, GUI.HEIGHT);
	login.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    LoginFrame lf = new LoginFrame();
		}
	    });

	ask.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    
		    TradeFrame ask = new TradeFrame("ASK");

		}
	    });
	bid.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    
		    TradeFrame bid = new TradeFrame("BID");

		}
	    });



	// right:
	this.right = new JPanel(new GridLayout(2, 1));
	// top-right:
	this.topRight    = new JPanel(new BorderLayout());
	this.tickmsg     = new JTextArea();
	this.tickborder = new TitledBorder("Stock Ticker");
	
	tickScrollPane = new JScrollPane(tickmsg);
	tickScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	topRight.add("Center", tickScrollPane);
	
	topRight.setBorder(tickborder);
	

	// bottom-right:
	String[] columnNames = {"ID", "Type", "Company", "Volume", "Price"};
	this.bottomRight = new JPanel(new BorderLayout());
	this.bookBorder  = new TitledBorder("Your Offer Book");
	this.book        = new JTable(new DefaultTableModel(columnNames, 0));
	this.operation   = new JPanel(new GridLayout(1, 2));
	this.refresh     = new JButton("Refresh");
	this.cancel      = new JButton("Cancel...");

	bottomRight.setBorder(bookBorder);
	operation.add(refresh);
	operation.add(cancel);
	bottomRight.add("Center", new JScrollPane(book));
	bottomRight.add("South", operation);

	right.add("Center", topRight);
	right.add("South", bottomRight);
	panel.add("West", left);
	panel.add("Center", right);

	frame.add(panel);
	frame.setVisible(true);	
	
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

	
    }

}

// 
// GUI.java ends here
