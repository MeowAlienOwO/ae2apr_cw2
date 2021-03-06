// 6511043 zy11043 Zhang Huayan
//                              -*- Mode: Java -*- 
// LoginFrame.java --- 
// Filename: LoginFrame.java
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
//     Update #: 145
// 

// Code:

package stock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
class LoginFrame{
    
    private JFrame frame;
    private JTextField server, usrname;
    private JPasswordField passwd;
    private JLabel serverLabel, usrLabel, passwdLabel;
    private JPanel panel, serverPanel, usrPanel, passwdPanel, buttonPanel;
    private JButton login, cancel;
    private Datapool datapool;
    private MainFrame mainframe;

    LoginFrame(MainFrame mainframe, Datapool datapool){
	this.frame       = new JFrame("Log in");
	this.server      = new JTextField(10);
	this.usrname     = new JTextField(10);
	this.passwd      = new JPasswordField(10);
	this.serverLabel = new JLabel("Server:");
	this.usrLabel    = new JLabel("Username:");
	this.passwdLabel = new JLabel("Password:");
	this.login       = new JButton("Login");
	this.cancel      = new JButton("Cancel");
	this.datapool    = datapool;
	this.mainframe   = mainframe;
	this.serverPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
	serverPanel.add(serverLabel);
	serverPanel.add(server);
	
	this.usrPanel    = new JPanel(new FlowLayout(FlowLayout.TRAILING));
	usrPanel.add(usrLabel);
	usrPanel.add(usrname);
	this.passwdPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
	passwdPanel.add(passwdLabel);
	passwdPanel.add(passwd);
	this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
	buttonPanel.add(login);
	buttonPanel.add(cancel);

	this.panel = new JPanel(new GridLayout(4, 1));
	panel.add(serverPanel);
	panel.add(usrPanel);
	panel.add(passwdPanel);
	panel.add(buttonPanel);
	frame.add(panel);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.pack();
	frame.setVisible(true);
	frame.setResizable(false);

	cancel.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    frame.dispose();
		}
	    });

	login.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    try {
			String usrnameText = usrname.getText();
			String passwdText = new String(passwd.getPassword()); 
			String[] splited = server.getText().split(":");

			if(splited.length          != 2
			   || usrnameText.length() == 0
			   || passwdText.length()  == 0)
			    throw new IllegalArgumentException("Illegal input!");
			    
			String portText = splited[1];
			String hostText = splited[0];

			datapool.logIn(new LoginInfor(
						      Integer.parseInt(portText),
						      hostText,
						      usrnameText,
						      passwdText));
			mainframe.getTickArea().setText("");
			frame.dispose();
		    }catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);			
		    }
		}
	    });
    }

}


class IllegalArgumentException extends Exception {
    public IllegalArgumentException() {
	super();
    }
    public IllegalArgumentException(String msg) {
	super(msg);
    }
    public IllegalArgumentException(String msg, Throwable cause) {
	super(msg, cause);
    }
    public IllegalArgumentException(Throwable cause) {
	super(cause);
    }

}

// 
// LoginFrame.java ends here
