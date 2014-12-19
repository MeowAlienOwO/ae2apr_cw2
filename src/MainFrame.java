// 6511043 zy11043 Zhang Huayan
//                              -*- Mode: Java -*- 
// MainFrame.java --- 
// Filename: MainFrame.java
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
//     Update #: 248
// 

// Code:

package stock;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.util.*;
class MainFrame implements DataObserver{
    public static final String[] COLUMN_NAME = {"ID", "Type", "Company", "Volume", "Price"};
    // variables
    private static MainFrame mainframe = null;
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
    private DefaultTableModel tableModel;
    private JPanel operation;
    private JButton refresh;
    private JButton cancel;
    
    // constructor
    private MainFrame(Datapool datapool){
    
	this.frame = new JFrame("MainFrame Client");
	this.panel = new JPanel(new BorderLayout());
	this.datapool = datapool;




	frame.setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
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
	left.setSize(200, MainFrame.HEIGHT);
	login.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    LoginFrame lf = new LoginFrame(datapool);
		}
	    });

	ask.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    TradeFrame ask = new TradeFrame("ASK", datapool);
		}
	    });
	bid.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    
		    TradeFrame bid = new TradeFrame("BID", datapool);

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
	tickmsg.setEditable(false);

	// bottom-right:
	
	this.tableModel = new DefaultTableModel(MainFrame.COLUMN_NAME, 0){
		// Override the isCellEditable method to make every cell 
		// not editable
		public boolean isCellEditable(int row, int column){
		    return false;
		}
	    };

	this.bottomRight = new JPanel(new BorderLayout());
	this.bookBorder  = new TitledBorder("Your Offer Book");
	this.book        = new JTable(tableModel);
	this.operation   = new JPanel(new GridLayout(1, 2));
	this.refresh     = new JButton("Refresh");
	this.cancel      = new JButton("Cancel...");

	bottomRight.setBorder(bookBorder);
	operation.add(refresh);
	operation.add(cancel);
	// book.setEditable(false);
	bottomRight.add("Center", new JScrollPane(book));
	bottomRight.add("South", operation);
	
	refresh.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    datapool.setCommands(Datapool.CreateBOOK());
		}
	    });

	cancel.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    int id = (int)book.getSelectedRows()[0];
		    datapool.setCommands(Datapool.CreateCancel(id));
		}
	    });
	

	right.add("Center", topRight);
	right.add("South", bottomRight);
	panel.add("West", left);
	panel.add("Center", right);

	frame.add(panel);
	frame.setVisible(true);	
	
    }
    // setter

    // getter
    public static MainFrame getMainFrame(Datapool datapool){
	if(MainFrame.mainframe == null){
	    MainFrame.mainframe = new MainFrame(datapool);
	}
	return MainFrame.mainframe;
    }
    // methods

    
    @Override
    public void update(){
	
	// update tickmsg
	LinkedList<String> serverInfor = datapool.getServerInfor();
	String infor;
	while((infor = serverInfor.poll()) != null){
	    String[] splited = infor.split(" ");
	    if(splited[0].equals(Datapool.TICK)){
		tickmsg.append(infor + "\n");
	    } else if(splited[0].equals(Datapool.EXEC)){
		tickmsg.append(infor + "<<<<<<<<<<\n");
	    }
	}
	// update book table
	LinkedList<TradeInfor> tradeInfor = datapool.getTradeInfor();

	for(int i = tableModel.getRowCount() - 1; i >= 0; i--){
	    tableModel.removeRow(i);
	}

	for(int i = 0; i < tradeInfor.size(); i++){
	    Object[] rowData = {
		tradeInfor.get(i).getID(),
		tradeInfor.get(i).getType(),
		tradeInfor.get(i).getCompany(),
		tradeInfor.get(i).getVolume(),
		tradeInfor.get(i).getPrice()
	    };
	    
	    tableModel.addRow(rowData);
	}
	
    }

}

// 
// MainFrame.java ends here
