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
//     Update #: 485
// 

// Code:

package stock;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.util.*;
import java.io.*;
import java.net.SocketException;
class MainFrame implements DataObserver{
    private static final String[] COLUMN_NAME = {"ID", "Type", "Company", "Volume", "Price"};
    // variables
    private static MainFrame mainframe = null;
    private JFrame frame;
    private JPanel panel;
    private Datapool datapool;
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;

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
    
	this.frame = new JFrame("GUI Client");
	this.panel = new JPanel(new BorderLayout());
	this.datapool = datapool;

	frame.setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// left side:
	// this.left   = new JPanel(new BorderLayout(5, 0));
	// this.account = new JPanel(new BorderLayout());
	// this.login  = new JButton("Log in");
	// this.logout = new JButton("Log out");
	// this.trade  = new JPanel(new GridLayout(2, 1));
	// this.ask    = new JButton("ASK");
	// this.bid    = new JButton("BID");
	
	// account.add("Center", login);
	// account.add("South", logout);
	// trade.add(ask);
	// trade.add(bid);
	// left.add("North", account);
	// left.add("Center", trade);
	// left.setSize(200, MainFrame.HEIGHT);
	// login.addActionListener(new ActionListener(){
	// 	public void actionPerformed(ActionEvent e){
	// 	    LoginFrame lf = new LoginFrame(MainFrame.mainframe, datapool);
		    
	// 	}
	//     });

	// logout.addActionListener(new ActionListener(){
	// 	public void actionPerformed(ActionEvent e){
	// 	    try{
	// 	    datapool.logOut();
	// 	    }catch(IOException ioe){
	// 		JOptionPane.showMessageDialog(null, ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	// 	    }
	// 	}
	//     });

	// ask.addActionListener(new ActionListener(){
	// 	public void actionPerformed(ActionEvent e){
	// 	    TradeFrame ask = new TradeFrame("ASK", datapool);
	// 	}
	//     });
	// bid.addActionListener(new ActionListener(){
	// 	public void actionPerformed(ActionEvent e){
		    
	// 	    TradeFrame bid = new TradeFrame("BID", datapool);

	// 	}
	//     });



	// // right:
	// this.right = new JPanel(new GridLayout(2, 1));
	// // top-right:
	// this.topRight    = new JPanel(new BorderLayout());
	// this.tickmsg     = new JTextArea();
	// this.tickborder = new TitledBorder("Stock Ticker");
	
	// tickScrollPane = new JScrollPane(tickmsg);
	// tickScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	// topRight.add("Center", tickScrollPane);
	
	// topRight.setBorder(tickborder);
	// tickmsg.setEditable(false);

	// // bottom-right:
	
	// this.tableModel = new DefaultTableModel(MainFrame.COLUMN_NAME, 0){
	// 	// Override the isCellEditable method to make every cell 
	// 	// not editable
	// 	public boolean isCellEditable(int row, int column){
	// 	    return false;
	// 	}
	//     };

	// this.bottomRight = new JPanel(new BorderLayout());
	// this.bookBorder  = new TitledBorder("Your Offer Book");
	// this.book        = new JTable(tableModel);
	// this.operation   = new JPanel(new GridLayout(1, 2));
	// this.refresh     = new JButton("Refresh");
	// this.cancel      = new JButton("Cancel...");

	// bottomRight.setBorder(bookBorder);
	// operation.add(refresh);
	// operation.add(cancel);

	// tableModel.setDataVector(datapool.getBooks(), 
	// 			 new Vector(Arrays.asList(MainFrame.COLUMN_NAME)));
	// bottomRight.add("Center", new JScrollPane(book));
	// bottomRight.add("South", operation);
	
	// refresh.addActionListener(new ActionListener(){
	// 	public void actionPerformed(ActionEvent e){
	// 	    datapool.setCommands(Datapool.CreateBOOK());
	// 	    tableModel.fireTableDataChanged();
	// 	    datapool.setBookChanged(false);
	// 	}
	//     });

	// cancel.addActionListener(new ActionListener(){
	// 	public void actionPerformed(ActionEvent e){

	// 	    int selectedRow = book.getSelectedRow();
		    

	// 	    if (selectedRow == -1) {
	// 		JOptionPane.showMessageDialog(null, "You didn't select!", "Error", JOptionPane.ERROR_MESSAGE);
	// 	    }else{
	// 		String id = (String)tableModel.getValueAt(selectedRow, 0);
	// 		int choice = JOptionPane.showConfirmDialog(null, ("Are you going to cancel book No." + id), "Cancel", JOptionPane.YES_NO_OPTION);
		    
	// 		if(choice == 0){

			    
	// 		    datapool.setCommands(Datapool.CreateCancel(id));
	// 		}
	// 	    }
	// 	}

	//     });

	// right.add("Center", topRight);
	// right.add("South", bottomRight);
	// panel.add("West", left);
	// panel.add("Center", right);

	setLeft();
	setRight();
	setAction();
	frame.add(panel);
	frame.setVisible(true);	
	
    }
    // setter

    private void setLeft(){
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

	panel.add("West", left);	

    }
    private void setRight(){
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
	operation.add(cancel);
	operation.add(refresh);


	tableModel.setDataVector(datapool.getBooks(), 
				 new Vector(Arrays.asList(MainFrame.COLUMN_NAME)));
	bottomRight.add("Center", new JScrollPane(book));
	bottomRight.add("South", operation);


	right.add("Center", topRight);
	right.add("South", bottomRight);	
	panel.add("Center", right);
    }
    private void setAction(){
	// left:
	login.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    LoginFrame lf;
		    if(!datapool.isLoggedIn())
			lf = new LoginFrame(MainFrame.mainframe, datapool);
		}
	    });

	logout.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if(datapool.isLoggedIn()){
			try{
			    datapool.logOut();
			}catch(IOException ioe){
			    JOptionPane.showMessageDialog(null, ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		    }
		}
	    });

	ask.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    TradeFrame ask;
		    if(datapool.isLoggedIn()){
			ask = new TradeFrame("ASK", datapool);
			// datapool.refresh();
		    }
		}
	    });
	bid.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent e){
		    TradeFrame bid;
		    if(datapool.isLoggedIn()){
			bid = new TradeFrame("BID", datapool);
			// datapool.refresh();
		    }
		}
	    });

	// right
	refresh.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if(datapool.isLoggedIn()){

			datapool.refresh();
			// book.updateUI();
			tableModel.fireTableDataChanged();
		    }
		}
	    });

	cancel.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    if(datapool.isLoggedIn()){
			int[] selectedRows = book.getSelectedRows();
			String id;
			int choice;
			if (selectedRows.length == 0) {
			    JOptionPane.showMessageDialog(null,
							  "You didn't select!",
							  "Error",
							  JOptionPane.ERROR_MESSAGE);
			}else if(selectedRows.length == 1){
			    id = (String)tableModel.getValueAt(selectedRows[0], 0);
			    choice = JOptionPane.showConfirmDialog(null,
								   ("Are you going to cancel book No." + id),
								   "Cancel",
								   JOptionPane.YES_NO_OPTION);
			    if(choice == 0){
				datapool.setCommands(Datapool.CreateCancel(id));
			    }

			}else{
			    choice = JOptionPane.showConfirmDialog(null, "Are you going to cancel all books seleceted?", "Cancel", JOptionPane.YES_NO_OPTION);
			    if(choice == 0){
				for(int i = 0; i < selectedRows.length; i++){
				    id = (String)tableModel.getValueAt(selectedRows[i], 0);
				    datapool.setCommands(Datapool.CreateCancel(id));
				}
			    }
			}
		    }
		    datapool.refresh();
		}

	    });
    }
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
	updateError();
	updateTickmsg();
	updateBook();

    }

    private void updateError(){
	LinkedList<Exception> errorList = datapool.getException();
	Exception exception;
	while((exception = errorList.poll()) != null){
	    try {
		throw exception;
	    
	    }catch(SocketException ioe){
		JOptionPane.showMessageDialog(null, "Connection Closed.", "Error", JOptionPane.ERROR_MESSAGE);		
	    }catch(Exception e){
		JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }

	}
	
    }

    private void updateTickmsg(){
	// LinkedList<String> serverInfor = datapool.getServerInfor();
	String infor;
	while((infor = datapool.getServerInfor().poll()) != null){
	    System.out.println("Server Infor: " + infor);
	    String[] splited = infor.split(" ");
	    if(splited[0].equals(Datapool.TICK)){
		String tick = splited[1];
		for(int i = 2; i < splited.length; i++){
		    // tick.concat(splited[i]).concat(" ");
		    tick = tick + " " + splited[i];
		}
		tickmsg.append(tick + "\n");
	    } else if(splited[0].equals(Datapool.EXEC)){
		// String exec = "????";
		String exec = datapool.getCompanyName(splited[1]);
		for(int i = 1; i < splited.length; i++){
		    // exec.concat(" ").concat(splited[i]);
		    exec = exec + " " + splited[i];
		}
		tickmsg.append(exec + "<<<<<<<<<<\n");
	    }
	}
	SwingUtilities.invokeLater(new Runnable(){
		public void run(){
		    tickmsg.updateUI();
		}
	    });

    }

    private void updateBook(){
	// SwingUtilities.invokeLater(new Runnable(){
	// 	public void run(){
	// 	    // tableModel.fireTableDataChanged();
	// 	    // book.updateUI();
	// 	}
	//     });
	tableModel.fireTableDataChanged();

    }
    public JTextArea getTickArea(){
	return tickmsg;
    }

}

// 
// MainFrame.java ends here
